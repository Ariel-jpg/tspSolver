package GraphTheoryModels;

import DataStructures.OrderedHasTable;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Vertex {
    String id;

    // The relations are indexed by the other Vertex (v --> w, so the index is w)
    OrderedHasTable<String, WeightedRelation> relations = new OrderedHasTable<>(Comparator.comparingInt(WeightedRelation::getWeight));

    public Vertex(String id){ this.id = id; }

    public void addRelation(WeightedRelation wr) {
        if(wr.idV1.equals(id)) relations.put(wr.idV2, wr);
        else relations.put(wr.idV1, wr);
    } // Check the wr hashcode

    public WeightedRelation getRelationWith(Vertex w){ return relations.get(w.getId()); }
    public WeightedRelation getMinRelation(){ return relations.getMin(); }

    public void setRelations(Collection<WeightedRelation> relations){
        for(WeightedRelation r: relations)
            addRelation(r);
    }

    /**
     * @implNote This method must be used to obtain, immutably, the minimum relations that the vertex possesses and thus operate on them.
     */
    public PriorityQueue<WeightedRelation> getOrderedRelations(){ return relations.getOrderedValues(); }

    // Implementation details --------------

    public String getId() { return id; }
    @Override
    public boolean equals(Object obj) {
        if(this.getClass().equals(obj.getClass())) {
            Vertex v = (Vertex) obj;

            return id.equals(v.id);
        } else if(obj.getClass().equals(String.class)) return id.equals(obj);
        else return false;
    }
}