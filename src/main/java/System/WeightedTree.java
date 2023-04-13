package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class WeightedTree {
    Hashtable<String, Vertex> vertices = new Hashtable<>();
    int weight = 0;

    @Override
    public String toString() {
        ArrayList<WeightedRelation> relations = new ArrayList<>();

        System.out.println("----- Minimum spanning tree edges -----");
        for(Vertex v: vertices.values()){
            PriorityQueue<WeightedRelation> wrp = v.getOrderedRelations();

            while(!wrp.isEmpty()) {
                WeightedRelation wr = wrp.remove();

                if (!relations.contains(wr))
                    relations.add(wr);
            }
        }
        for(WeightedRelation wr: relations)
            System.out.println(wr);

        return "";
    }

    WeightedTree(String idRootVertex){ vertices.put(idRootVertex, new Vertex(idRootVertex)); }

    public void addVertex(String idExistingVertex, String idNewVertex, int relationWeight){
        if(!vertices.containsKey(idExistingVertex)) {
            System.out.println("El vertice " + idExistingVertex +  " no existe en el arbol");
            return;
        }

        vertices.put(idNewVertex, new Vertex(idNewVertex));

        Vertex existingVertex = vertices.get(idExistingVertex), newVertex = vertices.get(idNewVertex);
        WeightedRelation wr = new WeightedRelation(existingVertex, newVertex, relationWeight);

        weight += relationWeight;
    }

    public int getWeight() { return weight; }

    public void addSubtree(String idExistingVertex, String idVertexPivot, WeightedTree subtree, int relationWeight){
        if(!vertices.containsKey(idExistingVertex)) {
            System.out.println("El vertice " + idExistingVertex +  " no existe en el arbol");
            return;
        }

        for(Vertex v: subtree.vertices.values())
            this.vertices.put(v.getId(), v);

        Vertex existingVertex = vertices.get(idExistingVertex), newVertex = vertices.get(idVertexPivot);
        WeightedRelation wr = new WeightedRelation(existingVertex, newVertex, relationWeight);

        weight += subtree.getWeight() + relationWeight;
    }
}
