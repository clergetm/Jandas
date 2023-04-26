package fr.uga.jandas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

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
        wrongCol = new Column("Integer",6, d1);
        assertNotEquals(wrongCol, col);
        // less elements
        Integer[] d2 = new Integer[]{1, 2, 3, 4, 5, 6};
        wrongCol = new Column("Integer",5, d2);
        assertNotEquals(wrongCol, col);
        // more elements
        Integer[] d3 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        wrongCol = new Column("Integer",7, d3);
        assertNotEquals(wrongCol, col);
        // equals
        wrongCol = new Column("Integer",6, d3);
        assertEquals(col, wrongCol);
    }

    @Test
    void addElement() {
        // 1 element off
        Column colbis;
//        colbis = new Column<Integer>( 6);
        colbis = new Column("Integer",6);
        for (int i = 0; i < 6; i++){
            colbis.addElement(data[i], i);
        }
        assertEquals(col, colbis);
        colbis = new Column("Integer",6, data);
        assertEquals(col, colbis);
        colbis.addElement(100, 4);
        assertNotEquals(col, colbis);
        col.addElement(100, 4);
        assertEquals(col, colbis);
    }

    @Test
    void TestSetLabel(){
        Float[] arr = {1.F, 2.F, 2.9F, 3.9F, 4.8F, 5.5F};
        Column<Float> c = new Column<>("ratio", "Float", 6, arr);
        Column<Float> c2 = new Column<>("Float", 6, arr);
        assertNotEquals(c, c2);
        c2.setLabel("ratio");
        assertEquals(c, c2);
        c.setLabel("flower");
        assertNotEquals(c, c2);
    }

    @Test
    void ColumnLowArg(){
        Float[] arr = {1.F, 2.F, 2.9F, 3.9F, 4.8F, 5.5F};
        Column<Float> c = new Column<>("Float", 6, arr);
        Column<Float> c2 = new Column<>("Float", 6);
        assertNotEquals(c, c2);
        for (int i = 0; i < arr.length; i++){
            c2.addElement(arr[i], i);
        }
        assertEquals(c, c2);
    }

    @Test
    void TestMax(){
        Float[] arr = {1.F, 2.F, 2.9F, 3.9F, 4.8F, 5.5F};
        Column<Float> c = new Column<>("ratio", "Float", 6, arr);
        assertEquals(5.5F, c.max());
        Integer[] arri = {1, 33, 29, 39, 8, 5};
        Column<Integer> c2 = new Column<>("ratio", "Integer", 6, arri);
        assertEquals(39, c2.max());
        String[] str = {"aeaea", "sjdfhsf"};
        Column<String> c3 = new Column<>("ratio", "String", 2, str);
        assertNull(c3.max());
        Boolean[] b = {false, true, false, false};
        Column<Boolean> c4 = new Column<>("ratio", "Boolean", 4, b);
        assertNull(c4.max());
    }

    @Test
    void TestMin(){
        Float[] arr = {1.F, 2.F, 2.9F, 3.9F, 4.8F, 5.5F};
        Column<Float> c = new Column<>("ratio", "Float", 6, arr);
        assertEquals(1.F, c.min());
        Integer[] arri = {1, 33, 29, 39, 8, 5};
        Column<Integer> c2 = new Column<>("ratio", "Integer", 6, arri);
        assertEquals(1, c2.min());
        String[] str = {"aeaea", "sjdfhsf"};
        Column<String> c3 = new Column<>("ratio", "String", 2, str);
        assertNull(c3.min());
        Boolean[] b = {false, true, false, false};
        Column<Boolean> c4 = new Column<>("ratio", "Boolean", 4, b);
        assertNull(c4.min());
    }

    @Test
    void TestMean(){
        DecimalFormat decf = new DecimalFormat("0.#####");
        Float[] arr = {1.F, 2.F, 2.9F, 3.9F, 4.8F, 5.5F};
        Column<Float> c = new Column<>("ratio", "Float", 6, arr);
        assertEquals("3.35", decf.format(c.mean()));
        Integer[] arri = {1, 33, 29, 39, 8, 5};
        Column<Integer> c2 = new Column<>("ratio", "Integer", 6, arri);
        assertEquals(19.166666666666668, c2.mean());
        String[] str = {"aeaea", "sjdfhsf"};
        Column<String> c3 = new Column<>("ratio", "String", 2, str);
        assertNull(c3.mean());
        Boolean[] b = {false, true, false, false};
        Column<Boolean> c4 = new Column<>("ratio", "Boolean", 4, b);
        assertNull(c4.mean());
    }
}