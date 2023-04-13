package System;

import main.DataStructures.AccuStackImm;
import main.GraphTheoryModels.Vertex;

public interface MinimumSpanningTree {
    int getWeight();
    int getDiameter();
    AccuStackImm<Vertex> getSetDiameter();
    WeightedTree buildMinimumSpanningTree();
}
