package API;

import java.util.Comparator;

public interface SortedMap extends Map{
    Comparator comparator();

    // range view operations
    SortedMap subMap(Object fromKey, Object toKey);
    SortedMap headMap(Object toKey);
    SortedMap tailMap(Object fromKey);

    Object firstKey();
    Object lastKey();
}
