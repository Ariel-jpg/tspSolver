package TspResolver;

import java.util.*;

final public class WeightedNode implements Cloneable {
    private OrderedHasTable<Integer, Relation> relations = new OrderedHasTable<>(Comparator.comparingInt(r -> r.weight));
    private Hashtable<Integer, Boolean> validRelations = new Hashtable<>();
    private int totalWeight;
    private int partialWeight = 0;
    String title;

    WeightedNode(String title) { this.title = title; }
    WeightedNode(String title, Integer hashCode, OrderedHasTable<Integer, Relation> relations, int totalWeight) {
        this.title = title; this.relations = relations; this.totalWeight = totalWeight;
    }

    public OrderedHasTable<Integer, Relation> getRelations() { return relations; }

//    @Override
//    public int hashCode() {
//        return (this.hashCode != 0) ? hashCode : super.hashCode();
//    }
    @Override
    public int hashCode() {
        int hashCode = 0;
        for(char l : title.toCharArray())
            hashCode += l;

        return (hashCode);
    }

    public void addRelation(Relation r){
        // The relations hashTable is indexed by Node
        if(r.right == this.hashCode()) this.relations.put(r.left.hashCode(), r);
        else this.relations.put(r.right.hashCode(), r);
        partialWeight += r.weight;
    }

    public void addOnlyRelation(Relation r){
        // The relations hashTable is indexed by Node
        if(r.right == this.hashCode()) this.relations.put(r.left.hashCode(), r);
        else this.relations.put(r.right.hashCode(), r);
    }

    public void setRelations(Collection<Relation> relations) {
        for(Relation r: relations)
            addRelation(r);

        totalWeight = partialWeight;
    }

    public void restorePartialWeight(){ partialWeight = totalWeight; }

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
    public Relation removeRelationWith(WeightedNode node) throws Exception { return relations.remove(node.hashCode()); }

    public boolean containsRelationWith(WeightedNode node){ return relations.containsKey(node.hashCode()); }

    public Relation getRelationWith(WeightedNode node) { return relations.get(node.hashCode()); }
    public int getPartialWeight() { return partialWeight; }
    public List<Relation> removeMinRelation(){ return relations.removeMin(); }
    public int getTotalWeight() { return totalWeight; }
    public void discountRelation(Relation r){ partialWeight -= r.weight; }


    @Override
    public WeightedNode clone() {
        try {
            WeightedNode clone = (WeightedNode) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.relations = relations.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
