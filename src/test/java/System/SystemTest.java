package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;
import org.junit.Test;

import java.util.*;

public class SystemTest {
    GraphCases gc = new GraphCases();

    public void printRelations(Hashtable<String, Vertex> vertices){
        PriorityQueue<WeightedRelation> rel = new PriorityQueue<>(Comparator.comparingInt(WeightedRelation::getWeight));

        for(Vertex v: vertices.values()){
            PriorityQueue<WeightedRelation> re = v.getOrderedRelations();

            while(!re.isEmpty()) {
                WeightedRelation wr = re.remove();

//                System.out.print(wr +  ", ");
                if (!rel.contains(wr)) {
                    rel.add(wr);
                }
            }

            System.out.println();

        }

        while(!rel.isEmpty()){
            System.out.println(rel.remove());
        }
    }

    @Test
    public void basicTest() {
        GeneralWeightedGraph wg = new GeneralWeightedGraph_1(gc.getHardCase());

        wg.runHeldKarp();

        WeightedTree minimumSpanningTree = wg.getMinimumSpanningTree();
        System.out.println("The weight of minimum spanning tree is: " + minimumSpanningTree.getWeight());
//        minimumSpanningTree.toString();
        printRelations(wg.vertices);

//        ArrayList<String> aaa1 = new ArrayList<String>(){{{ add("TU"); add("JU"); add("CO"); add("FO"); add("LR"); add("CA"); add("R"); add("TF"); add("CBA");  }}};
        ArrayList<String> aaa2 = new ArrayList<String>() {{
            {
                add("CA");
                add("TF");
                add("LR");
                add("TU");
                add("JU");
                add("FO");
            }
        }};

//        WeightedGraph l1 = wg.inducedGraphFrom(aaa1);
        GeneralWeightedGraph l2 = wg.inducedGraphFrom(aaa2);

//        printRelations(l1.vertices);
        System.out.println("-----------------------------");
        printRelations(l2.vertices);
//        minimumSpanningTree = l1.getMinimumSpanningTree();
        System.out.println("The weight of minimum spanning tree is: " + minimumSpanningTree.getWeight());

        minimumSpanningTree = l2.getMinimumSpanningTree();
        System.out.println("The weight of minimum spanning tree is: " + minimumSpanningTree.getWeight());


//        l.getMinimumSpanningTree().toString();
//        System.out.println("aaa");

    }

    @Test
    public void treeTest(){
        WeightedTree wt = new WeightedTree("A");
        wt.addVertex("A", "B", 100);
        wt.addVertex("A", "C", 50);
        wt.addVertex("B", "E", 900);

        WeightedTree wt2 = new WeightedTree("T");
        wt2.addVertex("T", "H", 23);
        wt2.addVertex("T", "R", 7);
        wt2.addVertex("H", "G", 2);

        WeightedTree wt3 = new WeightedTree("L");
        wt3.addVertex("L", "V", 23);
        wt3.addVertex("V", "P", 7);
        wt3.addVertex("V", "O", 2);

        WeightedForest wf = new WeightedForest();
        wf.addTree(wt);
        wf.addTree(wt2);
        wf.addTree(wt3);

        WeightedRelation wrPivot = new WeightedRelation(wt.vertices.get("B"), wt2.vertices.get("T"), 20);
        wf.connectTwoTrees(wrPivot);

        System.out.println("a");
    }

    @Test
    public void minPathTest(){
        WeightedGraph wg = new WeightedGraph(gc.getHardCase());
//        wg.getMinimumHamiltonianPath();
        System.out.println();

        wg = new WeightedGraph(gc.getCase1_1());
        System.out.println(wg.getMinimumSpanningTree().getWeight());
        wg.getMinimumHamiltonianPath();
        System.out.println();

        wg = new WeightedGraph(gc.getCase1());
        System.out.println(wg.getMinimumSpanningTree().getWeight());
        wg.getMinimumHamiltonianPath();
        System.out.println();

        wg = new WeightedGraph(gc.getCase2_3());
        System.out.println("WT: " + wg.getMinimumSpanningTree().getWeight());
        wg.getMinimumHamiltonianPath();
        System.out.println();

        wg = new WeightedGraph(gc.getCase2_1());
        System.out.println(wg.getMinimumSpanningTree().getWeight());
        wg.getMinimumHamiltonianPath();
    }
}
