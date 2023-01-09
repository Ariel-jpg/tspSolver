import DataStructures.AccuStack;
import DataStructures.AccuStackImm;
import GraphTheoryModels.SimpleRelation;
import GraphTheoryModels.Vertex;
import GraphTheoryModels.WeightedRelation;

import javax.swing.event.MenuKeyListener;
import java.util.*;

public class WeightedGraph_2 {
    Hashtable<String, Vertex> vertices;


    public WeightedGraph_2(Hashtable<String, Vertex> vertices) {
        this.vertices = vertices;
    }

    public void getMinHC(){
        AccuStackImm<Vertex> S = new AccuStackImm<>();
        PriorityQueue<AccuStackImm<Vertex>> Sf = new PriorityQueue<>(Comparator.comparingInt(AccuStackImm::getStackValue));



//        for(Vertex v: vertices.values()){

        Vertex v = vertices.get("A");
            S = S.push(v, 0);

            findPathFrom(v, S, Sf);

            S = new AccuStackImm<>();
//            break;
//        }

//        int i = 0;

//        while(!Sf.isEmpty())
//            if(Sf.remove().getStackValue() == 28) System.out.println(++i);

        System.out.println("a");

    }


    public void findPathFrom(Vertex origin, AccuStackImm<Vertex> S, PriorityQueue<AccuStackImm<Vertex>> Sf){
        PriorityQueue<AccuStackImm<Vertex>> M = new PriorityQueue<>(Comparator.comparingInt(AccuStackImm::getStackValue));
        Vertex v = origin, nextVertex;

        boolean flag = false, SfFilled = false;

        do {
            if (flag) {
                S = M.remove();

                for(;;){
                    if(!SfFilled) break;
                    if(M.isEmpty()) break;

                    if(S.getStackValue() > Sf.peek().getStackValue()) S = M.remove();
                    else break;

                }

                 // check immutability
                v = S.peek();
            }

            if(S.getSize() == vertices.size()){
                Vertex lastVertex = S.peek();
                int lastRelationWeight = lastVertex.getRelationWith(origin).getWeight();

                Sf.add(S.push(origin, lastRelationWeight));
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
