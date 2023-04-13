package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.ArrayList;
import java.util.Hashtable;

public class GraphPath {
    Integer start, end;
    Hashtable<Integer, Vertex> path = new Hashtable<>();
    Hashtable<String, Vertex> vertices = new Hashtable<>();
    ArrayList<WeightedRelation> relations = new ArrayList<>(); // for metrics
    int pathWeight = 0;

    GraphPath(Vertex start, Vertex end, WeightedRelation wr){
        vertices.put(start.getId(), start);
        path.put(0, start);
        this.start = 0;

        vertices.put(end.getId(), end);
        path.put(1, end);
        this.end = 1;

        relations.add(wr);
        pathWeight = pathWeight + wr.getWeight();
    }

    public void addToBeginning(Vertex v, WeightedRelation wr){
        if(containsVertex(v)) {
            System.out.println("El vértice " + v.getId() + " ya está en el camino");
            return;
        }

        start = start - 1;
        pathWeight = pathWeight + wr.getWeight();

        path.put(start, v);
        relations.add(wr);
        vertices.put(v.getId(), v);
    }

    public void addToEnd(Vertex v, WeightedRelation wr) {
        if(containsVertex(v)) {
            System.out.println("El vértice " + v.getId() + " ya está en el camino");
            return;
        }

        end = end + 1;
        pathWeight = pathWeight + wr.getWeight();

        path.put(end, v);
        relations.add(wr);
        vertices.put(v.getId(), v);
    }

    public Vertex getStartVertex(){ return path.get(start); }
    public Vertex getEndVertex(){ return path.get(end); }
    public boolean containsVertex(Vertex v){ return vertices.containsKey(v.getId()); }

    public void showPath(){
        int i = start;

        System.out.println("----- Hamiltonian path -----");
        for(; i < end; i++) System.out.print(path.get(i).getId() + " --> ");

        System.out.print(path.get(i).getId() + ": " + pathWeight);
    }
}
