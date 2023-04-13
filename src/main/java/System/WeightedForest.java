package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.ArrayList;
import java.util.Hashtable;

public class WeightedForest {
    int weight = 0;
    ArrayList<WeightedTree> trees = new ArrayList<>();
    Hashtable<String, Integer> indexVerticesTrees = new Hashtable<>();

    WeightedForest(){}

    public void addTree(WeightedTree wt){
        trees.add(wt);

        int hashcodeWt = wt.hashCode();
        for(String v: wt.vertices.keySet())
            indexVerticesTrees.put(v, hashcodeWt);

        weight += wt.getWeight();
    }

    public void connectTwoTrees(WeightedRelation wrPivot){
        String idV1 = wrPivot.idV1, idV2 = wrPivot.idV2;
        int hashCodeTree1 = indexVerticesTrees.get(idV1), hashCodeTree2 = indexVerticesTrees.get(idV2);

        WeightedTree wt1 = null, wt2 = null;
        for(WeightedTree wt: trees){
            if(wt.hashCode() == hashCodeTree1) wt1 = wt;
            if(wt.hashCode() == hashCodeTree2) wt2 = wt;

            if(wt1 != null && wt2 != null) break;
        }

        wt1.addSubtree(idV1, idV2, wt2, wrPivot.getWeight());
        weight += wrPivot.getWeight();

        trees.remove(wt2);

        for(String idV : wt2.vertices.keySet())
            indexVerticesTrees.put(idV, hashCodeTree1);

        return;
    }

    public boolean existsSameTree(Vertex v, Vertex w){
        return indexVerticesTrees.get(v.getId()).equals(indexVerticesTrees.get(w.getId()));
    }

    public boolean forestIsTree(){ return trees.size() == 1; }
    public WeightedTree getTree(){
        if(!forestIsTree()) {
            System.out.println("Forest is not a tree");
            return null;
        }

        return trees.get(0);
    }
}
