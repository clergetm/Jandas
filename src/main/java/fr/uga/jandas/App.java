package fr.uga.jandas;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DataFrame dataFrame = new DataFrame("test.txt");
        System.out.println(dataFrame);
        dataFrame.print();
        dataFrame.printFirstLines(2);
        dataFrame.printLastLines(2);
    }
}
