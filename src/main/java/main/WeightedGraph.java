package main;

import main.DataStructures.AccuStack;
import main.GraphTheoryModels.Vertex;

public interface WeightedGraph {
    AccuStack<Vertex> getMinHamiltonianCircuit();

}
