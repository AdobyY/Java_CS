public class ArrayList<E> {
    private static final int INITIAL_CAPACITY = 20;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }
}
