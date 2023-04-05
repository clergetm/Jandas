package fr.uga.jandas;


public class DataFrame {

    Column [] columns;
    int lines;

    DataFrame(Column [] columns){
        lines = columns[0].getSize();
        this.columns = columns;
    }

    DataFrame(String filename){

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

}
