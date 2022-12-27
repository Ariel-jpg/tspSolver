package TspResolver;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class WeightedGraph {
    Hashtable<Integer, WeightedNode> nodes;

    WeightedGraph(List<WeightedNode> nodes) {  }

    public Stack<Relation> getGeneralMinHamiltonianCircuit() throws Exception { // TODO check return value
        return null;
    }

    private Relation searchMinRelation(){
        Relation r = nodes.get(0).getMinRelations().get(0);

        return r;
    }
}
