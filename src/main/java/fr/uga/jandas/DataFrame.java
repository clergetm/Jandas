package fr.uga.jandas;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Dataframe class represents a 2-dimensions array where each column is identified by a label and each line by an index.
 * Two different columns can host two different types.
 * <p>
 * The Dataframe class includes methods for examinating individual or multiple elements of the array. Moreover there are
 * functions that providing statistics on columns.
 * <p>
 * Below the actual file format attend to create a Dataframe from a file.
 * Input file format with n the number of column and m the number of line :<br>
 * t0,t1,...,tn<br>
 * l0,l1,...,ln<br>
 * e00,e01,...,e0n<br>
 * e10,e11,...,e1n<br>
 * ...,...,...,...<br>
 * em0,em1,...,emn<br>
 * The first line gives the type of each column. The second line gives the labels of each column. The following lines
 * give the elements (data) to add in the DataFrame. Each cell is separated by a coma <bold>without space</bold>.
 *
 * Columns are stored thanks to Column class, and each Column can store objects sharing the same type.
 *
 **/
public class DataFrame {

    Column [] columns;
    int lines;
    String [] labels;

    /**
     * Initialize a newly created DataFrame object so that it represent the 2D-array given in argument.
     * Note that labels attribute is initialized with the labels written in columns.
     * @param columns The array containing the columns with labels initialized.
     */
    DataFrame(Column [] columns){
        lines = columns[0].getSize();
        this.columns = columns;

        labels = new String[columns.length];
        for (int l=0; l<columns.length; l++){
            labels[l] = columns[l].getLabel();
        }
    }

    /**
     * Initialize a newly created DataFrame object so that it represent the 2D-array given a file name. This file need
     * to follow the CSV format described on DataFrame documentation. Recall : <br>
     * The first line gives the type of each column. The second line gives the labels of each column. The following lines
     * give the elements (data) to add in the DataFrame. Each cell is separated by a coma <bold>without space</bold>.
     * @param filename The name of the file to create DataFrame from.
     */
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

    /**
     * Returns the column localized at given index.
     * @param index The column index.
     * @return The requested column.
     */
    public Column getColumn(int index){
        return columns[index];
    }

    /**
     * Returns the column identified by given label. Returns null if there is not a column labelled with this label.
     * @param label The column label.
     * @return The requested column or null.
     */
    public Column getColumn(String label){
        for (int l=0; l< columns.length; l++){
            if (Objects.equals(columns[l].getLabel(), label)){
                return columns[l];
            }
        }
        return null;
    }

    /**
     * Returns the line identified by given index.
     * @param index The index of requested line.
     * @return The object array containing requested elements.
     */
    public Object[] getLine(int index){
        if ( index < 0 || index > lines)
            throw new IndexOutOfBoundsException();
        Object [] chosen_lines = new Object[columns.length];
        for (int i = 0; i < columns.length; i++){
            chosen_lines[i] = columns[i].getElement(index);
        }
        return chosen_lines;
    }

    /**
     * Returns the element localized at given column and lineIndex index. Return null if an index is out of range.
     * @param colIndex The column index of accessed element.
     * @param lineIndex The line index of accessed element.
     * @return The requested element or null.
     */
    public Object getElement(int colIndex, int lineIndex){
        try {
            return columns[colIndex].getElement(lineIndex);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Returns an array containing each labels in order of attribute column array.
     * @return The labels array
     */
    public String [] getLabels() {
        return labels;
    }

    /**
     * Prints the mean value calculated of each column. If the mean is not calculable, it prints NaN instead.
     */
    public void mean(){
        StringBuilder str = new StringBuilder("     ");
        DecimalFormat df = new DecimalFormat("0.#");
        for (String l: labels){
            str.append(l).append(" ");
        }
        str.append("\nmean");

        for (Column c: columns){
            Double res = c.mean();
            if (res == null)
                str.append(" NaN ");
            else
                str.append(df.format(res)).append(" ");
        }
        str.append("\n");
        System.out.println(str);
    }

    /**
     * Prints the max value calculated of each column. If the max is not calculable, it prints NaN instead.
     */
    public void max(){
        StringBuilder str = new StringBuilder("     ");
        DecimalFormat df = new DecimalFormat("0.#");
        for (String l: labels){
            str.append(l).append(" ");
        }
        str.append("\nmax");

        for (Column c: columns){
            Double res = c.max();
            if (res == null)
                str.append(" NaN ");
            else
                str.append(df.format(res)).append(" ");
        }
        str.append("\n");
        System.out.println(str);
    }

    /**
     *  Prints the min value calculated of each column. If the min is not calculable, it prints NaN instead.
     */
    public void min(){
        StringBuilder str = new StringBuilder("     ");
        DecimalFormat df = new DecimalFormat("0.#");
        for (String l: labels){
            str.append(l).append(" ");
        }
        str.append("\nmin");

        for (Column c: columns){
            Double res = c.min();
            if (res == null)
                str.append(" NaN ");
            else
                str.append(df.format(res)).append(" ");
        }
        str.append("\n");
        System.out.println(str);
    }

    /**
     * Returns a new Dataframe initialized given a column set of current Dataframe. Note that this is a deep copy,
     * there are new objects used in this newly created DataFrame. Moreover, it can be many times the same label in the given column set.
     * Returns null a label is unknown.
     * @param labels The columns label to add in the new DataFrame.
     * @return The newly created DataFrame or null.
     */
    public DataFrame createFrom(String [] labels){
        Column [] cols = new Column[labels.length];
        for (int l=0; l<labels.length; l++){
            Column c = getColumn(labels[l]);
            if (c == null) return null;
            cols[l] = c;
        }
        return new DataFrame(cols);
    }

    /**
     * Returns a new Dataframe initialized given a line set of current DataFrame. It uses function createPartialCopy()
     * from the class Column on each column, a function that returns a deep copy with only specified lines. Note that this is a deep copy,
     * there are new objects used in this newly created DataFrame.
     * Returns null if an index is out of range.
     * @param indexes The indexes set to add in the new DataFrame.
     * @return The newly created DataFrame or null.
     */
    public DataFrame createFrom(int [] indexes){
        Column [] newCols = new Column[columns.length];
        for (int i=0; i<columns.length; i++){
            Column c = columns[i].createPartialCopy(indexes);
            if (c == null) return null;
            newCols[i] = c;
        }
        return new DataFrame(newCols);
    }

    /**
     * Returns a new DataFrame initialized given a line and column set of current DataFrame. Only lines of indexes referenced
     * in parameter are present in obtained columns. Note that this is a deep copy,
     * there are new objects used in this newly created DataFrame.
     * Returns null if an index is out of range or a labels is unknown.
     * @param labels The columns label to add in the new DataFrame.
     * @param lines The indexes set to add in the new DataFrame.
     * @return The newly created Dataframe based on the given selection and projection or null.
     */
    public DataFrame createFrom(String [] labels, int [] lines){
        Column [] cols = new Column[labels.length];
        for (int l=0; l<labels.length; l++){
            Column c = getColumn(labels[l]);
            if (c == null) return null;
            cols[l] = c;
        }
        Column [] newCols = new Column[labels.length];
        for (int i=0; i<cols.length; i++){
            Column c2 = cols[i].createPartialCopy(lines);
            newCols[i] = c2;
        }
        return new DataFrame(newCols);
    }

    private void print(int start, int end){
        System.out.print(toString(start, end));
    }

    /**
     * Prints the entire DataFrame.
     */
    public void print(){
        print(0, lines);
    }

    /**
     * Prints the DataFrame given first lines.
     * @param nbLines The number of lines to display.
     */
    public void printFirstLines(int nbLines){
        print(0, nbLines);
    }

    /**
     * Prints the DataFrame given first lines.
     * @param nbLines The number of lines to display.
     */
    public void printLastLines(int nbLines){
        print(lines-nbLines, lines);
    }

    /**
     * Returns a String object representing this DataFrame's value. Uses print() method.
     * @return a string representation of the value of this object like an array.
     */
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

    /**
     * Compares this DataFrame to the specified object. The result is true if and only if the argument is not null and is a
     * DataFrame object that represents the same sequence of elements as this object.
     * @param obj The object to compare this DataFrame against
     * @return true if the given object represents a DataFrame equivalent to this DataFrame, false otherwise
     */
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
