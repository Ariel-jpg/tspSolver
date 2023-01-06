package GraphTheoryModels;

public abstract class SimpleRelation {
    String idV1, idV2;
    int weight = 0;

    public SimpleRelation(String idV1, String idV2) { this.idV1 = idV1; this.idV2 = idV2; }

    public boolean containsVertex(Vertex v){ return (v.equals(idV1) || v.equals(idV2)); }
    public int getWeight() { return weight; }
}
