package fr.uga.jandas;

public class Column {

    String type;
    Integer [] elements;

    public Column(int nb_elements){
        type = "Integer";
        elements = new Integer[nb_elements];
    }

    public Integer[] getElements(){
        return elements;
    }

}
