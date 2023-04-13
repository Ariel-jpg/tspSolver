package System;

import main.DataStructures.AccuStackImm;
import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class KruskalAlgorithm implements MinimumSpanningTree {
    int weight = 0;
    int diameter = 0;
    Hashtable<String, Vertex> vertices = new Hashtable<>();
    WeightedForest wf = new WeightedForest();
    private PriorityQueue<WeightedRelation> borderSet = new PriorityQueue<>(Comparator.comparingInt(WeightedRelation::getWeight));

    KruskalAlgorithm(Hashtable<String, Vertex> vertices) {
        generateSets(vertices);
    }

    public int getWeight() { return weight; }
    public int getDiameter() { return diameter; }

    private void generateSets(Hashtable<String, Vertex> vertices){
        for(Vertex v: vertices.values()){
            PriorityQueue<WeightedRelation> relationsOfVertex = v.getOrderedRelations();

            // Add each relation of each vertex to border
            while(!relationsOfVertex.isEmpty()) {
                WeightedRelation wr = relationsOfVertex.remove();

                if(!borderSet.contains(wr))
                    borderSet.add(wr);
            }

            // Add each vertex but without their relations
            this.vertices.put(v.getId(), new Vertex(v.getId()));

            // Create a forest with each vertex
            wf.addTree(new WeightedTree(v.getId()));
        }
    }

    /**
     * @implNote If there exists more than one set of vertices with the same cardinal then this method returns the minimum of all
     * @return The set of vertices that make up the diameter of the tree.
     */
    public AccuStackImm<Vertex> getSetDiameter() {
        return null;
    }

    public WeightedTree buildMinimumSpanningTree() {
        while(!wf.forestIsTree()){
            WeightedRelation minimumRelation = borderSet.remove();
            Vertex v = vertices.get(minimumRelation.idV1);
            Vertex w = vertices.get(minimumRelation.idV2);

            if(!wf.existsSameTree(v, w))
                wf.connectTwoTrees(minimumRelation);
        }

        WeightedTree minimumSpanningTree = wf.getTree();

        return minimumSpanningTree;
    }
}
