package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class WeightedGraph extends GeneralWeightedGraph {
    private final int MAX_VALUE = 100000;

    public WeightedGraph(Hashtable<String, Vertex> vertices) {
        super(vertices);
    }

    public void getMinHamiltonianCycle() {
    }

    public GeneralWeightedGraph inducedGraphFrom(ArrayList<String> vertices) {
        return new GeneralWeightedGraph_1(this.induceOnVertices(vertices));
    }

    public void getMinimumHamiltonianPath() {
        GraphPath gp;

        WeightedRelation generalMinRelation = null, iterationMinRelation;
        Vertex vl, vr; // vertex left, vertex right

        for (Vertex v : vertices.values()) {
            WeightedRelation vRelation = v.getMinRelation();

            if (generalMinRelation == null) generalMinRelation = vRelation;
            else if (vRelation.getWeight() < generalMinRelation.getWeight()) generalMinRelation = vRelation;
        }


        vl = vertices.get(generalMinRelation.idV1);
        vr = vertices.get(generalMinRelation.idV2);
        gp = new GraphPath(vl, vr, generalMinRelation);


        Vertex newBorder;
        int direction = 0;
        PriorityQueue<WeightedRelation> vlP, vrP;

        while (gp.path.size() <= (n - 1)) {
            vlP = vl.getOrderedRelations();
            vrP = vr.getOrderedRelations();

            do {
                // That they are the same is not contemplated
                if (vlP.peek().getWeight() < vrP.peek().getWeight()) {
                    iterationMinRelation = vlP.remove();

                    direction = -1;

                    // determines the vertex to be added
                    if (vl.getId().equals(iterationMinRelation.idV1))
                        newBorder = vertices.get(iterationMinRelation.idV2);
                    else newBorder = vertices.get(iterationMinRelation.idV1);
                } else {
                    iterationMinRelation = vrP.remove();

                    direction = 1;

                    // determines the vertex to be added
                    if (vr.getId().equals(iterationMinRelation.idV1))
                        newBorder = vertices.get(iterationMinRelation.idV2);
                    else newBorder = vertices.get(iterationMinRelation.idV1);
                }
            } while((newBorder.equals(vl) || newBorder.equals(vr)) || gp.containsVertex(newBorder));

            if(direction == -1) {
                gp.addToBeginning(newBorder, iterationMinRelation);
                vl = newBorder;
            } else {
                gp.addToEnd(newBorder, iterationMinRelation);
                vr = newBorder;
            }
        }

        gp.showPath();
    }
}
