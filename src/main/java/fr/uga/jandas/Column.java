package fr.uga.jandas;

/**
 * The Column class represents a 1-dimension array. It is used by the DataFrame class to store elements in 2-dimensions array.
 * A Column store one type of object. This type can be : Integer, Float, Double, Boolean or String. Others types are not
 * supported.
 *
 * This class contains an attribute called type used to do calculations and statistics. It is overkill since Column is
 * already generic, but this is the only way we found to get the elements type.
 *
 * @param <T> The type of objects stored in the column
 */
@SuppressWarnings("unchecked")
public class Column<T>{
    String label;
    private String type;
    T [] elements;

    /**
     * Initialize a newly created Column object so that it represents an empty 1D-array. It will contain
     * a certain number of objects of defined type.
     * @param type The type of objects in the column (array).
     * @param nb_elements The number of objects in the array.
     */
    public Column(String type, int nb_elements){
        this.type = type;
        elements = (T[]) new Object[nb_elements];
    }

    /**
     * Initialize a newly created Column object so that it represents a 1D-array given in argument. It contains
     * a certain number of objects of defined type. Note that the given array is deep copied.
     * @param type The type of objects in the column (array).
     * @param nb_elements The number of objects in the array.
     * @param arr The array with objects to add in column.
     */
    public Column(String type, int nb_elements, T [] arr){
        this.type = type;
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    /**
     * Same as others constructors but it allows to add the column label directly.
     * @param l The column label.
     * @param type The type of objects in the column (array).
     * @param nb_elements The number of objects in the array.
     * @param arr The array with objects to add in column.
     */
    public Column(String l, String type, int nb_elements, T [] arr){
        this(type, nb_elements, arr);
        label = l;
    }

    /**
     * Used internally to create a deep copy of Column.
     * @param l The copied label
     * @param indexes The indexes of lines to add in the new Column.
     * @param arr The elements to be copied.
     * @param t The type of elements.
     */
    private Column(String l, String t, int [] indexes, T [] arr){
        // We create a copy of elements
        T [] new_elements = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, new_elements, 0, arr.length);
        label = l;
        type = t;
        elements = (T[]) new Object[indexes.length];
        // And we select only a certain amount of elements
        for (int i=0; i<indexes.length; i++){
            elements[i] = new_elements[indexes[i]];
        }
    }

    /**
     * Set the label of the current Column.
     * @param l The new label name.
     */
    public void setLabel(String l){
        label = l;
    }

    /**
     * Return the label of the current Column.
     * @return The String representing the label.
     */
    public String getLabel(){
        return label;
    }

    /**
     * Used to add an element in the current Column at given index. If there is another element in there,
     * it will be erased. Note that it suppose that the element has the good type.
     * @param e The element to add.
     * @param index The element index.
     */
    public void addElement(T e, int index){
        elements[index] = e;
    }

    /**
     * Returns all elements in the current Column on an 1D array.
     * @return An array filled of element in column.
     */
    public T[] getElements(){
        return elements;
    }

    /**
     * Returns the element at given index.
     * Returns null if the index is out of range.
     * @param index The element index.
     * @return The requested element or null if error, or if it not exists.
     */
    public T getElement(int index){
        if (index < 0 || index > elements.length)
            return null;
        return elements[index];
    }

    /**
     * Returns the number of element in the column.
     * @return The number of elements.
     */
    public int getSize(){
        return elements.length;
    }

    /**
     * Returns mean value of column if and only if the column
     * is of type Integer, or Float, or Double. Else, it will return null.
     * @return The mean value computed.
     */
    public Double mean() {
        Double sum = 0.;
        switch (type){
            case "Integer":
                for (T element : elements) {
                    sum += ((Integer) element).doubleValue();
                }
                return sum/elements.length;
            case "Float":
                for (T element : elements) {
                    sum += ((Float) element).doubleValue();
                }
                return sum/elements.length;
            default:
                return null;
        }
    }

    /**
     * Returns the max value of column if and only if the column
     * is of type Integer, or Float, or Double. Else, it will return null.
     * @return The max value computed.
     */
    public Double max(){
        Double max = null;
        switch (type){
            case "Integer":
                for (T element : elements) {
                    if ( max == null || ((Integer) element).doubleValue() > max)
                        max = ((Integer) element).doubleValue();
                }
                return max;
            case "Float":
                for (T element : elements) {
                    if ( max == null || ((Float) element).doubleValue() > max)
                        max = ((Float) element).doubleValue();
                }
                return max;
            default:
                return null;
        }
    }

    /* min :
        Function that calculates min value of column if and only if the column
        is of type Integer, or Float, or Double. Else, it will return null.
     */

    /**
     * Returns the min value of column if and only if the column
     * is of type Integer, or Float, or Double. Else, it will return null.
     * @return The min value computed.
     */
    public Double min(){
        Double min = null;
        switch (type){
            case "Integer":
                for (T element : elements) {
                    if ( min == null || ((Integer) element).doubleValue() < min)
                        min = ((Integer) element).doubleValue();
                }
                return min;
            case "Float":
                for (T element : elements) {
                    if ( min == null || ((Float) element).doubleValue() < min)
                        min = ((Float) element).doubleValue();
                }
                return min;
            default:
                return null;
        }
    }

    /**
     * Returns a partial deep copy of current Column. The newly created Columns contains copies of every element
     * at given index.
     * @param indexes The array of indexes of elements in column to be copied in new Column.
     * @return The newly created Column.
     */
    public Column<T> createPartialCopy(int [] indexes){
        for (int i=0; i<indexes.length; i++){
            if (indexes[i] < 0 || indexes[i] > elements.length) return null;
        }
        return new Column<>(label, type, indexes, elements);
    }

    /**
     * Compares this Column to the specified object. The result is true if and only if the argument is not null and is a
     * Column object that represents the same sequence of elements, and same type, as this object.
     * @param obj The object to compare this Column against
     * @return true if the given object represents a Column equivalent to this Column, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;
        Column col = (Column) obj;
        if (col.getSize() != getSize()) return false;
        if (!col.type.equals(type)) return false;
        if (col.label == null && label != null) return false;
        if (col.label != null && label == null) return false;
        if (label != null && !col.label.equals(label)) return false;
        boolean res = true;
        for (int i = 0; i < col.getSize() ;i++){
            res = res && col.getElement(i).equals(getElement(i));
        }
        return res;
    }
}
