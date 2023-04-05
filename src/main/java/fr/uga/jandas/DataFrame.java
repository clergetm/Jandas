package fr.uga.jandas;


public class DataFrame {

    Column [] columns;
    int lines;

    DataFrame(Column [] columns){
        this.columns = columns;
    }

    DataFrame(String filename){

    }

}
