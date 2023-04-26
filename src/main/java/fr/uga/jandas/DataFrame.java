package fr.uga.jandas;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
    Format des fichiers en entrée:
        t0,     t1,     t2,     t3
        l0;     l1,     l2,     l3
        n00,    n01,    n02,    n03
        n10,    n11,    n12,    n13
    La première ligne donne les types des colonnes. La seconde contient les étiquettes de chaque colonne.
    Puis chaque ligne correspond à une ligne du tableau et les virgules correspondent aux séparations
    entre les colonnes. Les portions de texte séparées par une virgule correspondent ainsi aux contenus des
    cellules du tableau.
 **/
public class DataFrame {

    Column [] columns;
    int lines;
    String [] labels;

    DataFrame(Column [] columns){
        lines = columns[0].getSize();
        this.columns = columns;

        labels = new String[columns.length];
        for (int l=0; l<columns.length; l++){
            labels[l] = columns[l].getLabel();
        }
    }

    DataFrame(String filename){
        try {
            // Counting the number of lines
            FileInputStream file = new FileInputStream(filename);
            Scanner nbLineReader = new Scanner(file);
            int nbLines = -2; // We remove first and second line
            while (nbLineReader.hasNextLine()){
                nbLineReader.nextLine();
                nbLines++;
            }
            nbLineReader.close();

            // Reading of lines
            file = new FileInputStream(filename);
            Scanner scanner = new Scanner(file);
            // Defining type of each column
            String[] types = scanner.nextLine().split(",");
            int nbCol = types.length;
            Column[] columns = new Column[types.length];
            for (int i=0; i<nbCol; i++){
                Column aColumn;
                // Parsing types
                switch (types[i]){
                    case "Integer":
                        aColumn = new Column<Integer>("Integer", nbLines);
                        break;
                    case "String":
                        aColumn = new Column<String>("String", nbLines);
                        break;
                    case "Float":
                        aColumn = new Column<Float>("Float", nbLines);
                        break;
                    case "Boolean":
                        aColumn = new Column<Boolean>("Boolean", nbLines);
                        break;
                    case "Date":
                        aColumn = new Column<Date>("Date", nbLines);
                        break;
                    default:
                        throw new Exception("Unknown column type !");
                }
                columns[i] = aColumn;
            }

            // Defining label of each column
            String[] labels = scanner.nextLine().split(",");
            for (int i=0; i<nbCol; i++){
                columns[i].setLabel(labels[i]);
            }
            this.labels = labels;

            int lineCounter = 0;
            while(scanner.hasNextLine())
            {
                String [] lineContent = scanner.nextLine().split(",");
                for (int c=0; c<nbCol; c++){
                    switch (types[c]){
                        case "Integer":
                            columns[c].addElement(Integer.parseInt(lineContent[c]), lineCounter);
                            break;
                        case "String":
                            columns[c].addElement(lineContent[c], lineCounter);
                            break;
                        case "Float":
                            columns[c].addElement(Float.parseFloat(lineContent[c]), lineCounter);
                            break;
                        case "Boolean":
                            columns[c].addElement(Boolean.parseBoolean(lineContent[c]), lineCounter);
                            break;
                        case "Date":
                            columns[c].addElement((lineContent[c]), lineCounter);
                            throw new Exception("Dates not implemented yet !");
                        default:
                            throw new Exception("Unknown column type !");
                    }
                }
                lineCounter += 1;
            }
            scanner.close();

            this.lines = nbLines;
            this.columns = columns;

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Column getColumn(int index){
        return columns[index];
    }

    public Column getColumn(String label){
        for (int l=0; l< columns.length; l++){
            if (Objects.equals(columns[l].getLabel(), label)){
                return columns[l];
            }
        }
        return null;
    }

    public Object[] getLine(int index){
        if (index > lines)
            throw new IndexOutOfBoundsException();
        Object [] chosen_lines = new Object[columns.length];
        for (int i = 0; i < columns.length; i++){
            chosen_lines[i] = columns[i].getElement(index);
        }
        return chosen_lines;
    }

    public Object getElement(int colIndex, int lineIndex){
        return columns[colIndex].getElement(lineIndex);
    }

    public String [] getLabels(){
        return labels;
    }

    /* createFrom :
        Create a new Dataframe based on a column set of current Dataframe.
     */
    public DataFrame createFrom(String [] labels){
        Column [] cols = new Column[labels.length];
        for (int l=0; l<labels.length; l++){
            cols[l] = getColumn(labels[l]);
        }
        return new DataFrame(cols);
    }

    /* createFrom :
    Create a new Dataframe based on a line set of current Dataframe.
    It uses createPartialCopy() for every column, a function that returns
    a copy with only specified lines.
 */
    public DataFrame createFrom(int [] indexes){
        Column [] newCols = new Column[columns.length];
        for (int i=0; i<columns.length; i++){
            newCols[i] = columns[i].createPartialCopy(indexes);
        }
        return new DataFrame(newCols);
    }

    /* createFrom :
    Create a new Dataframe based on a line and column set of current Dataframe.
    Only the lines of indexes referenced in parameter are present in columns.
     */
    public DataFrame createFrom(String [] labels, int [] lines){
        Column [] cols = new Column[labels.length];
        for (int l=0; l<labels.length; l++){
            cols[l] = getColumn(labels[l]);
        }
        Column [] newCols = new Column[labels.length];
        for (int i=0; i<cols.length; i++){
            newCols[i] = cols[i].createPartialCopy(lines);
        }
        return new DataFrame(newCols);
    }


    private void print(int start, int end){
        System.out.print(toString(start, end));
    }

    public void print(){
        print(0, lines);
    }

    public void printFirstLines(int nbLines){
        print(0, nbLines);
    }

    public void printLastLines(int nbLines){
        print(lines-nbLines, lines);
    }

    @Override
    public String toString(){
        return toString(0, lines);
    }

    public String toString(int start, int end){
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int c=0; c<columns.length; c++){
            str.append(columns[c].getLabel()).append(" ");
        }
        str.append("\n  ");
        for (int i=start; i<end; i++){
            Object[] line = getLine(i);
            for (int j=0; j<columns.length; j++){
                str.append(line[j]).append(" ");
            }
            if (i != end -1 ) str.append("\n  ");
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;
        DataFrame df = (DataFrame) obj;
        if (lines != df.lines) return false;
        if ( labels.length != df.labels.length) return false;
        boolean res = true;
        for (int i = 0; i < columns.length; i++){
            res = res && (columns[i].equals(df.columns[i]));
            res = res && (labels[i].equals(df.labels[i]));
        }
        return res;
    }
}
