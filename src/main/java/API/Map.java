package API;

public interface Map {
    // Basic Operations
    public Object put(Object key, Object value);
    public Object get(Object key);
    public Object remove(Object key);
    public boolean containsKey(Object key);
    public boolean containsValue(Object value);
    public int size();
    public boolean isEmpty();

    // Bulk Operations
    public void putAll(Map t);
    public void clear();

    // Collection Views
    public Set keySet();
    public Collection values();
    public Set entrySet();

    // nested interface for entrySet element
    public interface Entry {
        Object getKey();
        Object getValue();
        Object setValue(Object value);
    }
}
