package System;

import main.GraphAlgorithms.HeldKarpA.HeldKarp;
import main.GraphAlgorithms.PrimA;
import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.*;

public abstract class GeneralWeightedGraph {
    protected Hashtable<String, Vertex> vertices;
    protected int n; // cardinal of set V
    protected int[][] adjacencyMatrix;
    protected String[] identifiers;

    public GeneralWeightedGraph(Hashtable<String, Vertex> vertices) {
        identifiers = new String[vertices.size()];

        this.vertices = vertices;
        this.adjacencyMatrix = getAdjacencyMatrix();
        n = vertices.size();
    }

    abstract public void getMinHamiltonianCycle();
//    abstract public void getMinHamiltonianPath();

    public void runHeldKarp(){
        HeldKarp hk = new HeldKarp(adjacencyMatrix, 0);

        System.out.println("----- The held karp result with " + vertices.size() + " vertices is -----");
        List<Integer> cities = hk.calculateHeldKarp();

        System.out.print("[");
        for(Integer city: cities)
            System.out.print(identifiers[city] + ", ");
        System.out.print("]");

        System.out.println("\t weight: " + hk.getOpt());
    }

    public int[][] getAdjacencyMatrix(){
        int[][] matrix = new int[vertices.size()][vertices.size()];

        int i = 0, j = 0;

        for(Vertex vi: vertices.values()){
            j = 0;
            for(Vertex vj: vertices.values()){

                if(vi.equals(vj))
                    matrix[i][j] = 0;
                else if(vi.getRelationWith(vj) != null){
                    int weight = vi.getRelationWith(vj).getWeight();
                    matrix[i][j] = weight;
                    matrix[j][i] = weight;
                } else {
                    matrix[i][j] = 20000;
                    matrix[j][i] = 20000;
                }

                j++;
            }
            this.identifiers[i] = vi.getId();
            i++;
        }

        return matrix;
    }

    public WeightedTree getMinimumSpanningTree(){
        PrimA pa = new PrimA(adjacencyMatrix, identifiers);
        pa.designMST();

        KruskalAlgorithm ka = new KruskalAlgorithm(vertices);
        WeightedTree minimumSpanningTree = ka.buildMinimumSpanningTree();

        if(pa.getFinalMinimumWeight() != minimumSpanningTree.getWeight())
            System.out.println("Los algoritmos de arbol de expansion minima no dan lo mismo ---------- || Kruskal: " + minimumSpanningTree.getWeight() + " - Prim: " + pa.getFinalMinimumWeight());

        return minimumSpanningTree;
    }

    abstract GeneralWeightedGraph inducedGraphFrom(ArrayList<String> vertices);

    protected Hashtable<String, Vertex> induceOnVertices(ArrayList<String> inducedVertices){
        Hashtable<String, Vertex> verticesI = new Hashtable<>();
        WeightedRelation wr;

        for(String id: inducedVertices)
            verticesI.put(id, new Vertex(id));

        for(Vertex v: vertices.values()){
            if(!inducedVertices.contains(v.getId())) continue;

            for(String id: inducedVertices){
                if(v.getId().equals(id)) continue;

                if(verticesI.get(id).getRelationWith(verticesI.get(v.getId())) == null) {
                    wr = new WeightedRelation(verticesI.get(v.getId()), verticesI.get(id), v.getRelationWith(vertices.get(id)).getWeight());
                }
            }
        }

        return verticesI;
    }

    public void addRelation(WeightedRelation wr){
        if(!vertices.containsKey(wr.idV1) && !vertices.containsKey(wr.idV2)) System.out.println("La relación " + wr + " no contiene vértices válidos");
        else {
            vertices.get(wr.idV1).addRelation(wr);
            vertices.get(wr.idV2).addRelation(wr);
        }
    }
}
