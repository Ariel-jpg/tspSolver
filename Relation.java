package TspResolver;

public class Relation implements Cloneable {
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

    @Override
    public Relation clone() {
        try {
            Relation clone = (Relation) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}