package TspResolver;
import java.util.*;

// Complete graph
public class WeightedGraph {
    final Hashtable<Integer, WeightedNode> nodes = new Hashtable<>();
    List<Relation> removedRelations = new ArrayList<>();
    int pmt = 0;

    WeightedGraph(List<WeightedNode> nodes) { initializeGraph(nodes); }

    public void initializeGraph(List<WeightedNode> nodes){
        for(WeightedNode wn: nodes) {
            this.nodes.put(wn.hashCode(), wn);
            pmt += wn.getTotalWeight();
        }
    }

    private void restoreInitialRelations(){
        for(Relation r: removedRelations) {
            WeightedNode rn = nodes.get(r.right), ln = nodes.get(r.left);

            if (!rn.containsRelationWith(ln)) rn.addOnlyRelation(r);
            else if (!ln.containsRelationWith(rn)) ln.addOnlyRelation(r);
        }
    }

    private void restoreInitialWeights(){
        for(WeightedNode wn: nodes.values()) wn.restorePartialWeight();
    }

    public Stack<Relation> getGeneralMinHamiltonianCircuit() throws Exception { // TODO check return value
        Hashtable<Integer, Boolean> visited = new Hashtable<>();
        Queue<WeightedNode> queue = new Queue<>();
        for(WeightedNode wn: nodes.values()) queue.enqueue(wn);

        List<AccuStack<WeightedNode>> hamiltonianCircuits = new ArrayList<>();


        do {
            AccuStack<WeightedNode> stack = new AccuStack<>();
            WeightedNode currentNode = queue.peek();

            do {
                WeightedNode runtimeNode = currentNode;
                Relation minRelation;

//                if(runtimeNode.hasMultipleMinRelations()){
//                    minRelation = searchMinRelation(runtimeNode, visited);
//                } else {
                    minRelation = runtimeNode.getMinRelations().get(0);
//                }

                for(WeightedNode wn : nodes.values()){
                    if(wn.hashCode() != runtimeNode.hashCode()) {
                        Relation removedRelation = wn.removeRelationWith(runtimeNode);
                        removedRelations.add(removedRelation);
                    }
                }

                visited.put(runtimeNode.hashCode(), true);

                stack.push(runtimeNode, runtimeNode.getPartialWeight() - minRelation.weight);

                runtimeNode.discountRelation(minRelation);

                if(runtimeNode.hashCode() == minRelation.left) currentNode = nodes.get(minRelation.right);
                else currentNode = nodes.get(minRelation.left);

                currentNode.discountRelation(minRelation);
            } while (visited.size() != nodes.size()-1);

            restoreInitialRelations();

            Relation finalRelation = currentNode.getRelationWith(queue.peek());
            stack.push(currentNode, currentNode.getPartialWeight() - finalRelation.weight);
            currentNode.discountRelation(finalRelation);
            stack.push(queue.peek(), (-1)*finalRelation.weight);

            visited.clear();
            restoreInitialWeights();

            hamiltonianCircuits.add(stack);

            queue.dequeue();
        } while (!queue.isEmpty());

        hamiltonianCircuits.sort(Comparator.comparingInt(AccuStack::getValue));
        Collections.reverse(hamiltonianCircuits);

        int i = 1;
        System.out.println("---- The minumum hamilton circuits are: ----");

        for(AccuStack<WeightedNode> hc : hamiltonianCircuits) {
            Stack<WeightedNode> sAcc = hc.getStack();

            WeightedNode initialNode = sAcc.pop();
            System.out.print(i + " | " + initialNode.title);
            while (!sAcc.isEmpty())
                System.out.print(" --> " + sAcc.pop().title);

            int circuitWeight = ((pmt - hc.getValue())/2);
            System.out.println(": "+ circuitWeight + ", Pmax: " + hc.getValue());
            i++;
        }

        return null;
    }

    private Relation searchMinRelation(WeightedNode runtimeNode, Hashtable<Integer, Boolean> visited){
        List<Relation> minRelations = runtimeNode.getMinRelations();

        for(Relation r: minRelations){
            WeightedNode nextNode;

            if(runtimeNode.hashCode() == r.left) nextNode = nodes.get(r.left);
            else nextNode = nodes.get(r.right);

            if(visited.containsKey(nextNode.hashCode())) continue; // TODO check this ...
            else System.out.println();
        }

        return minRelations.get(0);
    }
}
