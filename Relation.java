package TspResolver;

public class Relation {
    Integer left, right;
    String sLeft, sRight; // debug
    int weight = 0;

    Relation(WeightedNode left, WeightedNode right, int weight) {
        this.right = right.hashCode(); this.left = left.hashCode();
        this.sRight = right.title; this.sLeft = left.title;
        this.weight = weight;
    }
    public boolean containsNode(WeightedNode node) { return (left == node.hashCode() || right == node.hashCode()); }

    @Override
    public String toString() { return sLeft + " <--> " + sRight; }
}