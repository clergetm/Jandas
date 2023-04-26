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
        // Array    [[ 1 2 3 ]
        //           [ 4 5 6 ]]
        col1 = new Integer[]{1, 4};
        col2 = new Integer[]{2, 5};
        col3 = new Integer[]{3, 6};
        lin1 = new Integer[]{1, 2, 3};
        lin2 = new Integer[]{4, 5, 6};
        // Column
        c1 = new Column("Integer", 2, col1);
        c2 = new Column("Integer", 2, col2);
        c3 = new Column("Integer", 2, col3);
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
    }

    @Test
    void getElement() {
        // test value 1 à 6
        int index;
        for (int i = 1; i <= 6; i++){
            index = i -1;
            assertEquals(i, df.getElement(index%3,index/3));
        }
    }
}