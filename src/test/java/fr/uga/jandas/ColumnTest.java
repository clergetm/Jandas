package fr.uga.jandas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {

    Integer[] data;
    Column col;

    @BeforeEach
    void setUp() {
        // Column Test
        // [ 1 2 3 4 5 6 ]
        data = new Integer[]{1, 2, 3, 4, 5, 6};
        col = new Column("Integer", 6, data);
    }

    @Test
    void getElements() {
        assertArrayEquals(data, col.getElements());
    }

    @Test
    void getElement() {
        for (int i = 0; i < data.length; i++) {
            assertEquals(col.getElement(i), data[i]);
        }
    }

    @Test
    void getSize() {
        assertEquals(data.length, col.getSize());
    }

    @Test
    void testEquals() {
        Column wrongCol;
        // 1 element off
        Integer[] d1 = new Integer[]{1, 2, 3, 4, 9, 6};
        wrongCol = new Column("Integer", 6, d1);
        assertNotEquals(wrongCol, col);
        // less elements
        Integer[] d2 = new Integer[]{1, 2, 3, 4, 5, 6};
        wrongCol = new Column("Integer", 5, d2);
        assertNotEquals(wrongCol, col);
        // more elements
        Integer[] d3 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        wrongCol = new Column("Integer",  7, d3);
        assertNotEquals(wrongCol, col);
        // equals
        wrongCol = new Column("Integer",  6, d3);
        assertEquals(col, wrongCol);
    }

    @Test
    void addElement() {
        // 1 element off
        Column colbis;
        colbis = new Column("Integer", 6);
        for (int i = 0; i < 6; i++){
            colbis.addElement(data[i], i);
        }
        assertEquals(col, colbis);
        colbis = new Column("Integer",  6, data);
        assertEquals(col, colbis);
        colbis.addElement(100, 4);
        assertNotEquals(col, colbis);
        col.addElement(100, 4);
        assertEquals(col, colbis);
    }
}