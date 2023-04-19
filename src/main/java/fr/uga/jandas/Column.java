package fr.uga.jandas;

import java.util.Arrays;

public class Column<T>{
    T [] elements;

    public Column(int nb_elements){
        elements = (T[]) new Object[nb_elements];
    }
    public Column(int nb_elements, T [] arr){
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }
    public void addElement(T e, int index){
        elements[index] = e;
    }
    public T[] getElements(){
        return elements;
    }

    public T getElement(int index){
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
