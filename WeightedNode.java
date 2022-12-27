package TspResolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

public class WeightedNode {
    private OrderedHasTable<Integer, Relation> relations = new OrderedHasTable<>(Comparator.comparingInt(r -> r.weight));
    private Hashtable<Integer, Boolean> validRelations = new Hashtable<>();
    private int totalWeight = 0;
    String title;
    Integer hashCode = 0;

    WeightedNode(String title) { this.title = title; }
    WeightedNode(String title, Integer hashCode, OrderedHasTable<Integer, Relation> relations, int totalWeight) {
        this.hashCode = hashCode; this.title = title; this.relations = relations; this.totalWeight = totalWeight;
    }

    public OrderedHasTable<Integer, Relation> getRelations() { return relations; }

    @Override
    public int hashCode() {
        return (this.hashCode != 0) ? hashCode : super.hashCode();
    }

    public void setRelations(List<Relation> relations) {
        for(Relation r: relations) {
//            this.relations.put(r.hashCode(), r);
            if(r.right == this.hashCode()) this.relations.put(r.left.hashCode(), r);
            else this.relations.put(r.right.hashCode(), r);
            totalWeight += r.weight;
        }
    }

    public List<Relation> getMinRelations(){
        List<Relation> minRelations = new ArrayList<>();

        for(Integer hashCode : relations.getMin())
            minRelations.add(relations.get(hashCode));

        return minRelations;
    }

    public boolean hasMultipleMinRelations(){ return relations.hasMultipleMinRelations(); }
    /**
     * @implNote O(n)
     */
    private void removeRelationWith(WeightedNode node) throws Exception {
//        Relation r = getRelationWith(node);
        relations.remove(node.hashCode());
//        totalWeight -= r.weight;
    }

    public Relation getRelationWith(WeightedNode node) { return relations.get(node.hashCode()); }
    public int getTotalWeight() { return totalWeight; }
    public void removeRelation(Relation r) throws Exception { relations.remove(r.hashCode()); }
}
