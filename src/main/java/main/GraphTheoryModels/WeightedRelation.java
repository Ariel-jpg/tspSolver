package main.GraphTheoryModels;

import java.util.Arrays;
import java.util.List;

// Simple weighted Relation
public class WeightedRelation extends SimpleRelation {
    public WeightedRelation(Vertex v1, Vertex v2, int weight){
        super(v1, v2);
        this.weight = weight;
    }

    public List<DirectedRelation> generateOrderedPairs() {
        DirectedRelation v1_v2 = new DirectedRelation(idV1, idV2, weight), v2_v1 = new DirectedRelation(idV2, idV1, weight);

        return Arrays.asList(v1_v2, v2_v1);
    }
}
