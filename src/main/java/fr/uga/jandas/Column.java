package fr.uga.jandas;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unchecked")
public class Column<T>{
    String label;
    private ArrayList<T> elements;
    public Column(int nb_elements){
        elements = new ArrayList<>(nb_elements);
    }

    public Column(int nb_elements, T [] arr){
        elements = new ArrayList<>(nb_elements);
        Collections.addAll(elements, arr);
    }

    public Column(String l, int nb_elements, T [] arr){
        label = l;
        elements = new ArrayList<>(nb_elements);
        Collections.addAll(elements, arr);
    }

    public void setLabel(String l){
        label = l;
    }

    public String getLabel(){
        return label;
    }

    public void addElement(T e, int index){
        elements.add(index, e);
    }

    public ArrayList<T> getElements(){
        return elements;
    }

    public T getElement(int index){
        return elements.get(index);
    }

    public int getSize(){
        return elements.size();
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
