package fr.uga.jandas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataFrameTest {

    Integer [] col1, col2, col3, lin1, lin2;
    Column c1, c2, c3;
    DataFrame df;
    @BeforeEach
    public void setUp() {
        //labels :     A B C
        // Array    [[ 1 2 3 ]
        //           [ 4 5 6 ]]
        col1 = new Integer[]{1, 4};
        col2 = new Integer[]{2, 5};
        col3 = new Integer[]{3, 6};
        lin1 = new Integer[]{1, 2, 3};
        lin2 = new Integer[]{4, 5, 6};
        // Column
        c1 = new Column<>("A", "Integer", 2, col1);
        c2 = new Column<>("B", "Integer", 2, col2);
        c3 = new Column<>("C", "Integer", 2, col3);
        // DataFrame
        Column [] columns = new Column[]{c1, c2, c3};
        df = new DataFrame(columns);
    }
    @Test
    public void CreateDataFrameInt() {
        assertNotNull(df);
    }
    @Test
    void getColumn() {
        // test col 1
        Column col1r = df.getColumn(0);
        assertEquals(col1r, c1);
        // test col 2
        Column col2r = df.getColumn(1);
        assertEquals(col2r, c2);
        // test col 3
        Column col3r = df.getColumn(2);
        assertEquals(col3r, c3);
    }

    @Test
    void getLine() {
        // test ligne 1
        Object [] line1r = df.getLine(0);
        assertArrayEquals(lin1, line1r);
        // test ligne 2
        Object [] line2r = df.getLine(1);
        assertArrayEquals(lin2, line2r);
        assertThrows(IndexOutOfBoundsException.class, () -> {df.getLine(2);});
        assertThrows(IndexOutOfBoundsException.class, () -> {df.getLine(-1);});

    }

    @Test
    void getElement() {
        // test value 1 à 6
        int index;
        for (int i = 1; i <= 6; i++){
            index = i -1;
            assertEquals(i, (Integer) df.getElement(index%3,index/3));
        }
    }

    @Test
    void Equals() {
        Column [] columns = new Column[]{c1, c2, c3};
        DataFrame df2 = new DataFrame(columns);
        assertEquals(df,df2);
        Integer[] col3bis = new Integer[]{9, 6};
        Column c3bis = new Column("C", "Integer" ,2, col3bis);
        columns = new Column[]{c1, c2, c3bis};
        df2 = new DataFrame(columns);
        assertNotEquals(df, df2);
        Column c3label = new Column("E", "Integer", 2, col3);
        columns = new Column[]{c1, c2, c3label};
        df2 = new DataFrame(columns);
        assertNotEquals(df, df2);
    }

    @Test
    void DataFrameFromFile() {
        String filename = "src/test/resources/Test1.csv";
        DataFrame df2 = new DataFrame(filename);
        DataFrame df3 = new DataFrame(filename);
        assertEquals(df3, df2);
        assertEquals(df, df3);
    }
    @Test
    void DataFrameMultiType() {
        String filename = "src/test/resources/Test2.csv";
        DataFrame dft = new DataFrame(filename);
        assertNotNull(dft);
        Integer a = (Integer) dft.getElement(0,1);
        String b = (String) dft.getElement(1,1);
        Float c = (Float) dft.getElement(2,1);
        assertEquals(a, 4);
        assertEquals(b, "Jo");
        assertEquals(c, 666.1F);
        dft = new DataFrame("src/test/resources/Test3.csv");
        a = (Integer) dft.getElement(2,2);
        b = (String) dft.getElement(1,0);
        c = (Float) dft.getElement(4,3);
        Boolean d = (Boolean) dft.getElement(0,0);
        assertEquals(a, 87);
        assertEquals(b, "Alice");
        assertEquals(c, 80.2F);
        assertEquals(d, true);
        d = (Boolean) dft.getElement(0,1);
        assertEquals(d, false);
        b = (String) dft.getElement(3,1);
        assertEquals(b, "Marseille");
    }

    @Test
    void getColumnLabel() {
        // test col 1
        Column col1r = df.getColumn("A");
        assertEquals(col1r, c1);
        // test col 2
        Column col2r = df.getColumn("B");
        assertEquals(col2r, c2);
        // test col 3
        Column col3r = df.getColumn("C");
        assertEquals(col3r, c3);
        Column colerr = df.getColumn("Zou");
        assertNull(colerr);
    }


    @Test
    void TestCreateFrom1() {
        String filename = "src/test/resources/Test2.csv";
        DataFrame dft = new DataFrame(filename);
        String[] labels = {"B", "C"};
        DataFrame d2 = dft.createFrom(labels);
        DataFrame dft2 = new DataFrame("src/test/resources/DF_BC.csv");
        assertEquals(dft2, d2);
        String[] labels2 = {"C", "A", "A", "B"};
        d2 = dft.createFrom(labels2);
        dft2 = new DataFrame("src/test/resources/DF_CAAB.csv");
        assertEquals(dft2, d2);
    }

    @Test
    void testCreateFrom2() {
        String filename = "src/test/resources/Test2.csv";
        DataFrame dft = new DataFrame(filename);
        int[] indexe1 = {0};
        DataFrame d2 = dft.createFrom(indexe1);
        DataFrame dft2 = new DataFrame("src/test/resources/DF_L1.csv");
        assertEquals(dft2, d2);
        int[] indexe2 = {1};
        d2 = dft.createFrom(indexe2);
        dft2 = new DataFrame("src/test/resources/DF_L2.csv");
        assertEquals(dft2, d2);

    }

    @Test
    void testCreateFrom3() {
        String filename = "src/test/resources/Test2.csv";
        DataFrame dft = new DataFrame(filename);
        int[] indexe1 = {0};
        String[] labels = {"B", "C"};
        DataFrame d2 = dft.createFrom(labels, indexe1);
        DataFrame dft2 = new DataFrame("src/test/resources/DF_L1BC.csv");
        assertEquals(dft2, d2);
    }

    @Test
    void testToString(){
        String filename = "src/test/resources/Test2.csv";
        DataFrame dft = new DataFrame(filename);
        String str = dft.toString();
        String[] lines = str.split("\n");
        assertEquals(3, lines.length);
        for (int i = 0; i > lines.length; i++){
            String s = lines[i];
            System.out.println(s);
            if (i==0 || i == lines.length-1) assertEquals(4, s.split(" ").length);
            else assertEquals(5, s.split(" ").length);
        }
    }
}
