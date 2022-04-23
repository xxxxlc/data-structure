package Graph;

import java.util.HashMap;

/**
 * @Auther:
 * @Date: 2022/4/14 15:57
 * @Description:
 */

public class SymbolGraph {

    private HashMap<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String[] sp1, String[] sp2) {
        st = new HashMap<>();

        for (String s : sp1) {
            if (!st.containsKey(s)) {
                st.put(s, st.size());
            }
        }

        for (String s : sp2) {
            if (!st.containsKey(s)) {
                st.put(s, st.size());
            }
        }

        keys = new String[st.size()];

        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());

        for (int i = 0; i < sp1.length; ++i) {
            G.addEdge(st.get(sp1[i]), st.get(sp2[i]));
        }
    }

    public boolean contains(String s) {
        return st.containsKey(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
    }
}
