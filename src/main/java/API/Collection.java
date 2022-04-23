package API;

import java.util.Iterator;

/**
 * @Auther:
 * @Date: 2022/4/8 17:05
 * @Description: collection
 */

public interface Collection extends Iterable<Object> {
    public int size();
    public boolean isEmpty();
    public boolean contains();

    // use equals() for comparison
    public boolean equal(Object obj);

    // new equals() requires new hashCode()
    public int hashCode();

    // basic operations
    public boolean add(Object obj);
    public boolean remove(Object obj);

    // Bulk Operation
    public boolean containsAll(Collection c);
    // this = this U c
    public boolean addAll(Collection c);
    // this = this - c
    public boolean removeAll(Collection c);
    // this = this and c
    public boolean retainAll(Collection c);

    public void clear();

    public Iterator iterator();
    public Object[] toArray();
    public <T> T[] toArray(T[] a);
}
