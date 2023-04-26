package fr.uga.jandas;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Dataframe demonstration : " +
                "We create and print a Dataframe from 'src/main/resources/test.txt'" );
        DataFrame dataFrame = new DataFrame("src/main/resources/test.txt");
        dataFrame.print();

        int [] indexes = { 1, 2 };

        String [] labels = { "Name", "Profession" };
        DataFrame dataFrame1 = dataFrame.createFrom(labels, indexes);

        dataFrame1.print();
        dataFrame.print();

    }
}
