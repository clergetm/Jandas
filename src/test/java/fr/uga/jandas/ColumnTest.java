package fr.uga.jandas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {

    Integer [] data;
    Column col;

    @BeforeEach
    void setUp() {
        // Column Test
        // [ 1 2 3 4 5 6 ]
        data = new Integer[]{1, 2, 3, 4, 5, 6};
        col = new Column(6, data);
    }

    @Test
    void getElements() {
        Integer [] elements = col.getElements();
        assertEquals(elements.length, data.length);
        for (int i = 0; i < elements.length; i ++){
           assertEquals(elements[i], data[i]);
        }
    }

    @Test
    void getElement() {
        for (int i = 0; i < data.length; i ++){
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
        Integer [] d1 = new Integer[]{1, 2, 3, 4, 9, 6};
        wrongCol = new Column(6, d1);
        assertNotEquals(wrongCol, col);
        // less elements
        Integer [] d2 = new Integer[]{1, 2, 3, 4, 5, 6};
        wrongCol = new Column(5, d2);
        assertNotEquals(wrongCol, col);
        // more elements
        Integer [] d3 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        wrongCol = new Column(7, d3);
        assertNotEquals(wrongCol, col);
        // equals
        wrongCol = new Column(6, d3);
        assertEquals(col, wrongCol);
    }
}