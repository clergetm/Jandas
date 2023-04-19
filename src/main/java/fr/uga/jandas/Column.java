package fr.uga.jandas;

import java.util.Arrays;

public class Column {

    String type;
    Integer [] elements;

    public Column(int nb_elements){
        type = "Integer";
        elements = new Integer[nb_elements];
    }

    public Column(int nb_elements, Integer [] arr){
        type = "Integer";
        elements = new Integer[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    public Integer[] getElements(){
        return elements;
    }

    public int getElement(int index){
        return elements[index];
    }

    public int getSize(){
        return elements.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;
        Column col = (Column) obj;
        if (col.getSize() != getSize()) return false;
        return Arrays.equals(col.getElements(), getElements());
    }
}
