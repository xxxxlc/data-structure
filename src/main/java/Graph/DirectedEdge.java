package Graph;

/**
 * @Auther:
 * @Date: 2022/4/16 15:59
 * @Description:
 */

public class DirectedEdge {

    // start of edge
    private final int v;

    // end of edge
    private final int w;

    // weight of edge
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {
    }
}
