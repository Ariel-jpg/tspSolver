import main.DataStructures.AccuStack;
import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;
import main.WeightedGraph;
import main.WeightedGraph_1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GraphSimulation {
    public void run(){
//        int[][] matrix = new int[][];
//        Hashtable<String, Vertex> vertices = generateRandomGraphs(matrix, -1);
//        WeightedGraph wg = new WeightedGraph_n();

//        AccuStack<Vertex> minHamiltonianCircuit = wg.getMinHamiltonianCircuit();
    }

    private Hashtable<String, Vertex> generateRandomGraphs(int[][] matrix, int max){

        if(max == -1) max = (int) ((Math.random() * 100) + 1);

        List<Vertex> vertices = new ArrayList<>(max);
        Hashtable<String, Vertex> vs = new Hashtable<>();

        // Genero n vértices
        for (int i = 0; i < max; i++)
            vertices.add(new Vertex("V " + i));

        Vertex vi, vj;

        // Asigno relaciones entre ellos
        for (int i = 0; i < max; i++){
            for (int j = 0; j < max; j++){
                vi = vertices.get(i);
                vj = vertices.get(j);

                int weight = (int) (Math.random() * 1000) + 100;


                if(i < j) {
                    WeightedRelation w = new WeightedRelation(vi, vj, weight);

                    vi.addRelation(w);
                    vj.addRelation(w);

                    matrix[j][i] = weight;
                    matrix[i][j] = weight;
                }
            }
        }

        for(Vertex v: vertices)
            vs.put(v.getId(), v);

        return vs;
    }
}
