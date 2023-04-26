package fr.uga.jandas;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Column<T>{
    String label;
    T [] elements;

    public Column(int nb_elements){
        elements = (T[]) new Object[nb_elements];
    }

    public Column(int nb_elements, T [] arr){
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    public Column(String l, int nb_elements, T [] arr){
        label = l;
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    public void setLabel(String l){
        label = l;
    }

    public String getLabel(){
        return label;
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
        boolean res = true;
        for (int i = 0; i < col.getSize() ;i++){
            res = res && col.getElement(i).equals(getElement(i));
        }
        return res;
    }
}
