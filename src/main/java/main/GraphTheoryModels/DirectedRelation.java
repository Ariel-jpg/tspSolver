package main.GraphTheoryModels;

public class DirectedRelation extends SimpleRelation {
    public DirectedRelation(String left, String right, int weight) {
        super(left, right);
        this.weight = weight;
    }

    public String getNextVertexId() { return idV2; }
    public String getOriginVertexId(){ return idV1; }
    public boolean isNextEqualsTo(Vertex w) { return w.equals(idV2); }
}