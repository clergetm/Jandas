package fr.uga.jandas;


@SuppressWarnings("unchecked")
public class Column<T>{
    String label;
    private String type;
    T [] elements;

    public Column(String type, int nb_elements){
        this.type = type;
        elements = (T[]) new Object[nb_elements];
    }

    public Column(String type, int nb_elements, T [] arr){
        this.type = type;
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    public Column(String l, String type, int nb_elements, T [] arr){
        this.type = type;
        label = l;
        elements = (T[]) new Object[nb_elements];
        System.arraycopy(arr, 0, elements, 0, nb_elements);
    }

    private Column(String l, int [] indexes, T [] arr){
        // We create a copy of elements
        T [] new_elements = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, new_elements, 0, arr.length);
        label = l;
        elements = (T[]) new Object[indexes.length];
        // And we select only a certain amount of elements
        for (int i=0; i<indexes.length; i++){
            elements[i] = new_elements[indexes[i]];
        }
    }

    public void setLabel(String l){
        label = l;
    }

    public String getLabel(){
        return label;
    }

    public void addElement(T e, int index){
        elements[index] = e;
    }

    public T[] getElements(){
        return elements;
    }

    public T getElement(int index){
        return elements[index];
    }

    public int getSize(){
        return elements.length;
    }

    /* mean :
        Function that calculates mean value of column if and only if the column
        is of type Integer, or Float, or Double. Else, it will return null.
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
            case "Double":
                for (T element : elements) {
                    sum += (Double) element;
                }
                return sum/elements.length;
            default:
                return null;
        }
    }

    /* max :
        Function that calculates max value of column if and only if the column
        is of type Integer, or Float, or Double. Else, it will return null.
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
            case "Double":
                for (T element : elements) {
                    if ( max == null || ((Double) element) > max)
                        max = (Double) element;
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
            case "Double":
                for (T element : elements) {
                    if ( min == null || ((Double) element) < min)
                        min = (Double) element;
                }
                return min;
            default:
                return null;
        }
    }

    public Column<T> createPartialCopy(int [] indexes){
        return new Column<>(label, indexes, elements);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;
        Column col = (Column) obj;
        if (col.getSize() != getSize()) return false;
        if (!col.type.equals(type)) return false;
        boolean res = true;
        for (int i = 0; i < col.getSize() ;i++){
            res = res && col.getElement(i).equals(getElement(i));
        }
        return res;
    }
}
