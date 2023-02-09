package main;

import main.DataStructures.AccuStackImm;
import main.GraphTheoryModels.SimpleRelation;
import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.*;

public class WeightedGraph_2 {
    Hashtable<String, Vertex> vertices;
    Hashtable<Integer, Vertex> hashtable = new Hashtable<>();
    int localMin = 1000000;

    public WeightedGraph_2(Hashtable<String, Vertex> vertices) {
        this.vertices = vertices;
    }
    public WeightedGraph_2(Hashtable<String, Vertex> vertices, int localMin) { this.vertices = vertices; this.localMin = localMin; }


    public AccuStackImm<Vertex> getMinHC(){
        AccuStackImm<Vertex> S = new AccuStackImm<>();
        PriorityQueue<AccuStackImm<Vertex>> Sf = new PriorityQueue<>(Comparator.comparingInt(AccuStackImm::getStackValue));


        for(Vertex v: vertices.values()){

//        Vertex v = vertices.get("7");
            S = S.push(v, 0);


            findPathFrom(v, S, Sf);

            S = new AccuStackImm<>();
            break;
        }

//        int i = 0;
//
//        while(!Sf.isEmpty())
//            if(Sf.remove().getStackValue() == 28) System.out.println(++i);
//
//
//        AccuStackImm<Vertex> minCH = closeCycle(Sf.remove()), partialCh;
//
//        int hPathWeight, minWeight = 100000;
//
//        while (!Sf.isEmpty()){
//            partialCh = closeCycle(Sf.peek());
//
//            if(partialCh.getStackValue() < minCH.getStackValue())
//                minCH = partialCh;
//
//            Sf.remove();
//        }


        return Sf.remove();
    }

    private AccuStackImm<Vertex> closeCycle(AccuStackImm<Vertex> hPath){
        Vertex origin = hashtable.get(hPath.hashCode());

        return hPath.push(origin, hPath.peek().getRelationWith(origin).getWeight());
    }


    public void findPathFrom(Vertex origin, AccuStackImm<Vertex> S, PriorityQueue<AccuStackImm<Vertex>> Sf){
        PriorityQueue<AccuStackImm<Vertex>> M = new PriorityQueue<>(Comparator.comparingInt(AccuStackImm::getStackValue));
        Vertex v = origin, nextVertex;

        boolean flag = false, SfFilled = false;
        AccuStackImm<Vertex> minCh;

        do {
            if (flag) {
                S = M.remove();

                for(;;){
                    if(M.isEmpty()) break;

                    if(S.getStackValue() > localMin) {
                        S = M.remove();
                        continue;
                    }
                    if(!SfFilled) break;

                    if(S.getStackValue() > Sf.peek().getStackValue()) S = M.remove();
                    else break;

                }

                 // check immutability
                v = S.peek();
            }

            if(S.getSize() == vertices.size()){
                Vertex lastVertex = S.peek();
                int lastRelationWeight = lastVertex.getRelationWith(origin).getWeight();

                Sf.add(S.push(lastVertex, lastRelationWeight));

//                hashtable.put(S.hashCode(), origin);
//                Sf.add(S);
                SfFilled = true;
            } else {
                // a v le quiero preguntar por todas sus aristas que no terminen en un vértice que está en S
                PriorityQueue<WeightedRelation> vRelation = v.getOrderedRelations();

                while (!vRelation.isEmpty()){
                    WeightedRelation w = vRelation.remove();
                    nextVertex = getOtherVertex(v, w);

                    if(!S.containsItem(nextVertex))
                        M.add(S.push(nextVertex, w.getWeight()));
                }
            }

            flag = true;
        } while (!M.isEmpty());

    }

    private Vertex getVertex(String vertexId){ return vertices.get(vertexId); }

    private Vertex getOtherVertex(Vertex t, SimpleRelation minLocalRelation) {
        Vertex h;

        if (t.equals(minLocalRelation.idV1)) h = getVertex(minLocalRelation.idV2);
        else h = getVertex(minLocalRelation.idV1);

        return h;
    }
}
