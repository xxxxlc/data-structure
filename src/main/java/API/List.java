package API;

public interface List extends Collection{

    // Positional Access
    public Object get (int index);
    public Object set (int index, Object element);

    // Value
    public void add(int index, Object element);
    public Object remove(int index);

    // Search
    public int indexOf(Object o);
    public int lastIndexOf(Object o);

    // Range-View
    List subList(int from, int to);
}
