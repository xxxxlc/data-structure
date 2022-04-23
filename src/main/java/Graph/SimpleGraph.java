package Graph;

public interface SimpleGraph {

    /**
     * number of vertices
     * @return int
     */
    public int V();

    /**
     * number of edge
     * @return int
     */
    public int E();

    /**
     * add a edge to connect v and w
     * @param v point
     * @param w point
     */
    public void addEdge(int v, int w);

    /**
     * all point adjacent to v
     * @param v point
     * @return set of points
     */
    public Iterable<Integer> adj(int v);

    /**
     * print out string
     * @return string
     */
    public String toString();
}
