package main.GraphTheoryModels;

public abstract class SimpleRelation {
    public String idV1;
    public String idV2;
    int weight = 0;

    public SimpleRelation(Vertex v1, Vertex v2) { this.idV1 = v1.getId(); this.idV2 = v2.getId(); }
    public SimpleRelation(String idV1, String idv2) { this.idV1 = idV1; this.idV2 = idv2; }

    public boolean containsVertex(Vertex v){ return (v.equals(idV1) || v.equals(idV2)); }
    public int getWeight() { return weight; }
}
