import DataStructures.AccuStack;
import GraphTheoryModels.DirectedRelation;
import GraphTheoryModels.SimpleRelation;
import GraphTheoryModels.Vertex;
import GraphTheoryModels.WeightedRelation;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraph_1 {
    Hashtable<String, Vertex> vertices;
    PriorityQueue<WeightedRelation> generalMinRelations = new PriorityQueue<>(Comparator.comparingInt(SimpleRelation::getWeight));
    Hashtable<String, Boolean> visited = new Hashtable<>();

    public WeightedGraph_1(Hashtable<String, Vertex> vertices){
        this.vertices = vertices;
        setGeneralMinRelations();
        setVisited();
    }

    private void setVisited(){
        for(String vertexId: vertices.keySet())
            visited.put(vertexId, false);
    }

    private void setGeneralMinRelations(){
        for(Vertex v : vertices.values())
            for(WeightedRelation wr: v.relations.values())
                if(!generalMinRelations.contains(wr)) generalMinRelations.add(wr);
    }

    private Vertex getVertex(String vertexId){ return vertices.get(vertexId); }

    public void getMinHamiltonianCircuit(){
        PriorityQueue<AccuStack<Vertex>> S = new PriorityQueue<>(Comparator.comparingInt(AccuStack::getValue));

        do {
            AccuStack<Vertex> vAcc = new AccuStack<>();
            AccuStack<Vertex> wAcc = new AccuStack<>();

            List<DirectedRelation> directedRelations = generalMinRelations.remove().generateOrderedPairs();

            Vertex v = getVertex(directedRelations.get(0).getOriginVertexId());
            Vertex w = getVertex(directedRelations.get(1).getOriginVertexId());
            DirectedRelation vw = directedRelations.get(0);
            DirectedRelation wv = directedRelations.get(1);

            getMinHamiltonianCircuitFrom(v, vw, vAcc);
            getMinHamiltonianCircuitFrom(w, wv, wAcc);

            S.add(vAcc);
            S.add(wAcc);

        } while (!generalMinRelations.isEmpty());


        int i = 0;

        do {
            AccuStack<Vertex> minCH = S.remove();
            int chWeight = minCH.getValue();

            Vertex v = minCH.pop();
            System.out.print(++i + " - Min ch: " + v.getId());

            while (!minCH.isEmpty()) {
                v = minCH.pop();

                System.out.print(" <-- " + v.getId());
            }

            System.out.println(" : " + chWeight);
        } while (!S.isEmpty());
    }

    private void getMinHamiltonianCircuitFrom(Vertex origin, DirectedRelation r, AccuStack<Vertex> hamiltonianCircuitOfOrigin){
        Hashtable<String, Boolean> visited = new Hashtable<>();

        Vertex t, h;
        boolean finalFlag = false;
        PriorityQueue<WeightedRelation> minRelations;


        visited.put(origin.getId(), true);
        hamiltonianCircuitOfOrigin.push(origin, r.getWeight());

        t = getVertex(r.getNextVertexId()); // t = h
        SimpleRelation minLocalRelation = r;

        while (visited.size() != vertices.size() && !finalFlag){

            if(visited.size() == (vertices.size() - 1)) {
                visited.remove(origin.getId());
                finalFlag = true;

                minLocalRelation = t.getRelationWith(origin);
                h = getOtherVertex(t, minLocalRelation);
            } else {
                minRelations = t.getOrderedRelations();

                do {
                    minLocalRelation = minRelations.remove();

                    // Determine which is the vertex to which it will go
                    h = getOtherVertex(t, minLocalRelation);
                    //

                    // If the vertex with the minimum relationship with respect to the origin vertex was not visited, then the others should no longer be evaluated.
                    if(!visited.containsKey(h.getId())) break;

                } while (!minRelations.isEmpty()); // The case that the queue is empty will never occur. O(n - 1)
            }

            visited.put(t.getId(), true);
            hamiltonianCircuitOfOrigin.push(t, minLocalRelation.getWeight());
            t = h;
        }

        hamiltonianCircuitOfOrigin.push(origin, 0);
    }

    private Vertex getOtherVertex(Vertex t, SimpleRelation minLocalRelation) {
        Vertex h;

        if (t.equals(minLocalRelation.idV1)) h = getVertex(minLocalRelation.idV2);
        else h = getVertex(minLocalRelation.idV1);

        return h;
    }
}