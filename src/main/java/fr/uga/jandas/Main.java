package fr.uga.jandas;

class Main {
    public static void main(String[] args) {
        System.out.println("Docker demo file");
        System.out.println("| The objective of this demonstration is to show how it is possible \n" +
                "| to use the DataFrame library by making a tour of the features.");
        System.out.println("|");
        System.out.println("| The csv file 'resources/role.csv' contains a list of people with their name, age and \n" +
                "| their assigned role to play Dungeons and Dragons. It will be our DataFrame base.");
        System.out.println("|1. DataFrame creation using a csv file :");
        DataFrame roles = new DataFrame("src/main/resources/roles.csv");
        System.out.println("|2. DataFrame multiple displays :");

        System.out.println("   - Call to print()");
        roles.print();
        System.out.println("\n");

        System.out.println("   - Call to printFirstLines(5)");
        roles.printFirstLines(5);
        System.out.println("\n");

        System.out.println("   - Call to printLastLines(3)");
        roles.printLastLines(3);
        System.out.println("\n");

        System.out.println("|3. New DataFrame creation from the one already created :");
        System.out.println("   - Call to createFrom(['Name','Age']) : we create a new DataFrame by a selection of columns");
        String [] labels = new String[] {"Name", "Age"};
        DataFrame roles2 = roles.createFrom(labels);
        roles2.print();
        System.out.println("\n");

        System.out.println("   - Call to createFrom([0,4,7]) : we create a new DataFrame by a selection of lines");
        int [] index = new int[] {0, 4, 7};
        DataFrame roles3 = roles.createFrom(index);
        roles3.print();
        System.out.println("\n");

        System.out.println("   - Call to createFrom(['Name','Age'],[0,4,7]) : we create a new DataFrame by a selection of columns AND lines");
        DataFrame roles4 = roles.createFrom(labels, index);
        roles4.print();
        System.out.println("\n");

        System.out.println("|4. Statistics on the created dataframes : where NaN is 'not a number'");
        System.out.println("   - Call to mean()");
        roles.mean();

        System.out.println("   - Call to max()");
        roles.max();

        System.out.println("   - Call to min()");
        roles.min();
    }
}
