package fr.uga.jandas;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
    Format des fichiers en entrée:
        t0,     t1,     t2,     t3
        n00,    n01,    n02,    n03
        n10,    n11,    n12,    n13
    La première ligne donne les types des colonnes.
    Puis chaque ligne correspond à une ligne du tableau et les virgules correspondent aux séparations
    entre les colonnes. Les portions de texte séparées par une virgule correspondent ainsi aux contenus des
    cellules du tableau.
 **/
public class DataFrame {

    Column [] columns;
    int lines;

    DataFrame(Column [] columns){
        lines = columns[0].getSize();
        this.columns = columns;
    }

    DataFrame(String filename){
        try {
            // Counting the number of lines
            FileInputStream file = new FileInputStream(filename);
            Scanner nbLineReader = new Scanner(file);
            int nbLines = -1;
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
                        aColumn = new Column<Integer>(nbLines);
                        break;
                    case "String":
                        aColumn = new Column<String>(nbLines);
                        break;
                    case "Float":
                        aColumn = new Column<Float>(nbLines);
                        break;
                    case "Boolean":
                        aColumn = new Column<Boolean>(nbLines);
                        break;
                    case "Date":
                        aColumn = new Column<Date>(nbLines);
                        break;
                    default:
                        throw new Exception("Unknown column type !");
                }
                columns[i] = aColumn;
            }

            int lineCounter = 0;
            while(scanner.hasNextLine())
            {
                String [] lineContent = scanner.nextLine().split(",");
                for (int c=0; c<nbCol; c++){
                    columns[c].addElement(lineContent[c], lineCounter);
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

    private void print(int start, int end){
        System.out.print("[ ");
        for (int i=start; i<end; i++){
            Object[] line = getLine(i);
            for (int j=0; j<columns.length; j++){
                System.out.print(line[j] + " ");
            }
            if (i != end -1 ) System.out.print("\n  ");
        }
        System.out.println("]");
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
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i=0; i<lines; i++){
            Object[] line = getLine(i);
            for (int j=0; j<columns.length; j++){
                str.append(line[j]).append(" ");
            }
            if (i != lines -1 ) str.append("\n  ");
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;
        DataFrame df = (DataFrame) obj;
        if (lines != df.lines) return false;
        return Arrays.equals(columns, df.columns);
    }
}
