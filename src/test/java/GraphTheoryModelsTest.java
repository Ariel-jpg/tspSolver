import main.DataStructures.AccuStack;
import main.DataStructures.AccuStackImm;
import main.GraphTheoryModels.DirectedRelation;
import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;
import main.WeightedGraph_1;
import main.WeightedGraph_2;
import org.junit.Test;

import main.GraphAlgorithms.HeldKarpA.HeldKarp;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTheoryModelsTest {
    @Test public void generalTestsWeightedGraph(){
        int max = 10;
        int[][] matrix = new int[max][max];

        Hashtable<String, Vertex> relations_1 = getGraphTestRelations_1();
        Hashtable<String, Vertex> relations_2 = getGraphTestRelations_2(); // check
        generateMatrix_2(matrix);

        HeldKarp m = new HeldKarp(matrix, 2);
        long i1 = System.currentTimeMillis();
        System.out.println(m.calculateHeldKarp());
        long f1 = System.currentTimeMillis() - i1;

        System.out.println(m.getOpt());

        WeightedGraph_2 g = new WeightedGraph_2(relations_2);
        WeightedGraph_1 g2 = new WeightedGraph_1(relations_2);
        g2.getMinHamiltonianCircuit();

        long i2 = System.currentTimeMillis();
        g.getMinHC();
        long f2 = System.currentTimeMillis() - i2;

        System.out.println("a");
    }

    @Test
    public void TestInMultipleCase(){
        int max = 15;
        int[][] matrix = new int[max][max];

        Hashtable<String, Vertex> vertices = generateRandomGraphs(matrix, max);

        HeldKarp hk = new HeldKarp(matrix, 0);
        System.out.println(hk.calculateHeldKarp());
        System.out.print(hk.getOpt() + " - ");

        WeightedGraph_1 g1 = new WeightedGraph_1(vertices);
        AccuStack<Vertex> localMin = g1.getMinHamiltonianCircuit();

        WeightedGraph_2 g = new WeightedGraph_2(vertices, localMin.getValue());

        AccuStackImm<Vertex> minCh = g.getMinHC();

        assertThat(minCh.getStackValue()).isEqualTo(hk.getOpt());
        System.out.println(minCh.getStackValue());
    }

    @Test
    public void AutomateTestMultipleCases(){
        for (int i = 0; i < 10; i++)
            TestInMultipleCase();
    }

    private Hashtable<String, Vertex> generateRandomGraphs(int[][] matrix, int max){
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

    private void generateMatrix_2(int[][] matrix){
        matrix[0][1]= 53;
        matrix[0][2]= 646;
        matrix[0][3]= 986;
        matrix[0][4]= 792;
        matrix[0][5]= 933;
        matrix[0][6]= 1127;
        matrix[0][7]= 2373;
        matrix[0][8]= 1080;
        matrix[0][9]= 1334;

        matrix[1][2] = 698;
        matrix[1][3] = 1038;
        matrix[1][4] = 830;
        matrix[1][5] = 968;
        matrix[1][6] = 1116;
        matrix[1][7] = 2350;
        matrix[1][8] = 1132;
        matrix[1][9] = 1385;

        matrix[2][3] = 340;
        matrix[2][4] = 677;
        matrix[2][5] = 824;
        matrix[2][6] = 1321;
        matrix[2][7] = 2618;
        matrix[2][8] = 517;
        matrix[2][9] = 809;

        matrix[3][4] = 814;
        matrix[3][5] = 927;
        matrix[3][6] = 1548;
        matrix[3][7] = 2821;
        matrix[3][8] = 330;
        matrix[3][9] = 600;

        matrix[4][5] = 157;
        matrix[4][6] = 1845;
        matrix[4][7] = 3131;
        matrix[4][8] = 633;
        matrix[4][9] = 742;

        matrix[5][6] = 1999;
        matrix[5][7] = 3284;
        matrix[5][8] = 703;
        matrix[5][9] = 750;

        matrix[6][7] = 1300;
        matrix[6][8] = 1827;
        matrix[6][9] = 2120;

        matrix[7][8] = 3116;
        matrix[7][9] = 3408;

        matrix[8][9] = 689;

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j ++){
                if(i == j) matrix[i][j] = 0;
                else if(i < j) matrix[j][i] = matrix[i][j];
            }
        }

    }

    private void generateMatrix_1(int[][] matrix){

        // 0 = A, 1 = B, 2 = C, 3 = D, 4 = E
        // A -> D -> B ->
        matrix[0][1] = 10;
        matrix[0][2] = 2;
        matrix[0][3] = 3;
        matrix[0][4] = 8;

        matrix[1][2] = 20;
        matrix[1][3] = 15;
        matrix[1][4] = 7;

        matrix[2][3] = 3;
        matrix[2][4] = 5;

        matrix[3][4] = 30;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j ++){
                if(i == j) matrix[i][j] = 0;
                else if(i < j) matrix[j][i] = matrix[i][j];
            }
        }
    }


    @Test
    public void generalTestsWeightedGraph_2(){
        Hashtable<String, Vertex> vertices = new Hashtable<>();

        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D"), E = new Vertex("E");

        WeightedRelation wr1 = new WeightedRelation(A, B, 8);
        WeightedRelation wr2 = new WeightedRelation(A, C, 2);
        WeightedRelation wr3 = new WeightedRelation(A, D, 5);

//        WeightedRelation wr4 = new WeightedRelation(B, C, 10);
        WeightedRelation wr5 = new WeightedRelation(B, D, 3);

        WeightedRelation wr6 = new WeightedRelation(C, D, 1);

        WeightedRelation wr7 = new WeightedRelation(C, E, 4);
        WeightedRelation wr8 = new WeightedRelation(B, E, 15);

        A.addRelation(wr1);
        A.addRelation(wr2);
        A.addRelation(wr3);

        B.addRelation(wr1);
        B.addRelation(wr8);
        B.addRelation(wr5);

        C.addRelation(wr2);
        C.addRelation(wr7);
        C.addRelation(wr6);

        D.addRelation(wr3);
        D.addRelation(wr5);
        D.addRelation(wr6);

        E.addRelation(wr7);
        E.addRelation(wr8);

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);
        vertices.put(E.getId(), E);

        AccuStack<Vertex> acc = new AccuStack<>(), l;
        acc.push(A, 0);
        acc.push(B, 123);
        acc.push(C, 43);
        acc.push(D, 123);

        l = (AccuStack<Vertex>) acc.clone();
        acc.pop();
        acc.pop();
        acc.pop();
        acc.pop();

        System.out.println(l.size());


        WeightedGraph_2 g = new WeightedGraph_2(vertices);
        AccuStackImm<Vertex> MIN = g.getMinHC();

        System.out.println("a");
    }

    private Hashtable<String, Vertex> getGraphTestRelations_2(){
        Vertex CA = new Vertex("0"), LP = new Vertex("1"), CBA = new Vertex("2"), LR = new Vertex("3"), CO = new Vertex("4"), FO = new Vertex("5"), R = new Vertex("6"), TF = new Vertex("7"), TU = new Vertex("8"), JU = new Vertex("9");

        WeightedRelation CALP = new WeightedRelation(CA, LP, 53);
        WeightedRelation CACBA = new WeightedRelation(CA, CBA, 646);
        WeightedRelation CALR = new WeightedRelation(CA, LR, 986);
        WeightedRelation CACO = new WeightedRelation(CA, CO, 792);
        WeightedRelation CAFO = new WeightedRelation(CA, FO, 933);
        WeightedRelation CAR = new WeightedRelation(CA, R, 1127);
        WeightedRelation CATF = new WeightedRelation(CA, TF, 2373);
        WeightedRelation CATU = new WeightedRelation(CA, TU, 1080);
        WeightedRelation CAJU = new WeightedRelation(CA, JU, 1334);

        WeightedRelation LPCBA = new WeightedRelation(LP, CBA, 698);
        WeightedRelation LPLR = new WeightedRelation(LP, LR, 1038);
        WeightedRelation LPCO = new WeightedRelation(LP, CO, 830);
        WeightedRelation LPFO = new WeightedRelation(LP, FO, 968);
        WeightedRelation LPR = new WeightedRelation(LP, R, 1116);
        WeightedRelation LPTF = new WeightedRelation(LP, TF, 2350);
        WeightedRelation LPTU = new WeightedRelation(LP, TU, 1132);
        WeightedRelation LPJU = new WeightedRelation(LP, JU, 1385);

        WeightedRelation CBALR = new WeightedRelation(CBA, LR, 340);
        WeightedRelation CBACO = new WeightedRelation(CBA, CO, 677);
        WeightedRelation CBAFO = new WeightedRelation(CBA, FO, 824);
        WeightedRelation CBAR = new WeightedRelation(CBA, R, 1321);
        WeightedRelation CBATF = new WeightedRelation(CBA, TF, 2618);
        WeightedRelation CBATU = new WeightedRelation(CBA, TU, 517);
        WeightedRelation CBAJU = new WeightedRelation(CBA, JU, 809);

        WeightedRelation LRCO = new WeightedRelation(LR, CO, 814);
        WeightedRelation LRFO = new WeightedRelation(LR, FO, 927);
        WeightedRelation LRR = new WeightedRelation(LR, R, 1548);
        WeightedRelation LRTF = new WeightedRelation(LR, TF, 2821);
        WeightedRelation LRTU = new WeightedRelation(LR, TU, 330);
        WeightedRelation LRJU = new WeightedRelation(LR, JU, 600);

        WeightedRelation COFO = new WeightedRelation(CO, FO, 157);
        WeightedRelation COR = new WeightedRelation(CO, R, 1845);
        WeightedRelation COTF = new WeightedRelation(CO, TF, 3131);
        WeightedRelation COTU = new WeightedRelation(CO, TU, 633);
        WeightedRelation COJU = new WeightedRelation(CO, JU, 742);

        WeightedRelation FOR = new WeightedRelation(FO, R, 1999);
//        WeightedRelation FOTF = new WeightedRelation(FO, TF, 3284);
        WeightedRelation FOTU = new WeightedRelation(FO, TU, 703);
        WeightedRelation FOJU = new WeightedRelation(FO, JU, 750);

        WeightedRelation RTF = new WeightedRelation(R, TF, 1300);
        WeightedRelation RTU = new WeightedRelation(R, TU, 1827);
        WeightedRelation RJU = new WeightedRelation(R, JU, 2120);

        WeightedRelation TFTU = new WeightedRelation(TF, TU, 3116);
        WeightedRelation TFJU = new WeightedRelation(TF, JU, 3408);

        WeightedRelation TUJU = new WeightedRelation(TU, JU, 689);

        List<WeightedRelation> CARelations = Arrays.asList(CALP, CACBA, CALR, CACO, CAFO, CAR, CATU, CAJU, CATF);
        List<WeightedRelation> LPRelations = Arrays.asList(CALP, LPCBA, LPLR, LPCO, LPFO, LPR, LPTF, LPJU, LPTU);
        List<WeightedRelation> CBARelations = Arrays.asList(CACBA, LPCBA, CBALR, CBACO, CBAFO, CBAR, CBATF, CBATU, CBAJU);
        List<WeightedRelation> LRRelations = Arrays.asList(CALR, LPLR, CBALR, LRCO, LRFO, LRR, LRTF, LRJU, LRTU);
        List<WeightedRelation> CORelations = Arrays.asList(CACO, LPCO, CBACO, LRCO, COFO, COR, COTU, COJU, COTF);
//        List<WeightedRelation> FORelations = Arrays.asList(CAFO, LPFO, CBAFO, COFO, LRFO, FOR, FOTF, FOTU, FOJU);
        List<WeightedRelation> FORelations = Arrays.asList(CAFO, LPFO, CBAFO, COFO, LRFO, FOR, FOTU, FOJU);
        List<WeightedRelation> RRelations = Arrays.asList(CAR, LPR, CBAR, LRR, COR, FOR, RTF, RTU, RJU);
//        List<WeightedRelation> TFRelations = Arrays.asList(CATF, LPTF, CBATF, LRTF, COTF, FOTF, RTF, TFTU, TFJU);
        List<WeightedRelation> TFRelations = Arrays.asList(CATF, LPTF, CBATF, LRTF, COTF, RTF, TFTU, TFJU);
        List<WeightedRelation> TURelations = Arrays.asList(CATU, LPTU, CBATU, LRTU, COTU, FOTU, RTU, TFTU, TUJU);
        List<WeightedRelation> JURelations = Arrays.asList(CAJU, LPJU, CBAJU, LRJU, COJU, FOJU, RJU, TFJU, TUJU);

        CA.setRelations(CARelations);
        LP.setRelations(LPRelations);
        CBA.setRelations(CBARelations);
        LR.setRelations(LRRelations);
        CO.setRelations(CORelations);
        FO.setRelations(FORelations);
        R.setRelations(RRelations);
        TF.setRelations(TFRelations);
        TU.setRelations(TURelations);
        JU.setRelations(JURelations);

        Hashtable<String, Vertex> h = new Hashtable<>();
        h.put(LP.getId(), LP);
        h.put(CBA.getId(), CBA);
        h.put(LR.getId(), LR);
        h.put(CO.getId(), CO);
        h.put(FO.getId(), FO);
        h.put(R.getId(), R);
        h.put(TF.getId(), TF);
        h.put(TU.getId(), TU);
        h.put(JU.getId(), JU);
        h.put(CA.getId(), CA);

        return h;
    }

    private Hashtable<String, Vertex> getGraphTestRelations_1(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D"), E = new Vertex("E");;

        WeightedRelation wr1 = new WeightedRelation(A, B, 10);
        WeightedRelation wr2 = new WeightedRelation(A, C, 2);
        WeightedRelation wr3 = new WeightedRelation(A, D, 3);
        WeightedRelation wr4 = new WeightedRelation(A, E, 8);

        WeightedRelation wr5 = new WeightedRelation(B, C, 20);
        WeightedRelation wr6 = new WeightedRelation(B, D, 15);
        WeightedRelation wr7 = new WeightedRelation(B, E, 7);

        WeightedRelation wr8 = new WeightedRelation(C, D, 3);
        WeightedRelation wr9 = new WeightedRelation(C, E, 5);

        WeightedRelation wr10 = new WeightedRelation(D, E, 30);

        A.addRelation(wr1);
        A.addRelation(wr2);
        A.addRelation(wr3);
        A.addRelation(wr4);

        B.addRelation(wr1);
        B.addRelation(wr5);
        B.addRelation(wr6);
        B.addRelation(wr7);

        C.addRelation(wr2);
        C.addRelation(wr5);
        C.addRelation(wr8);
        C.addRelation(wr9);

        D.addRelation(wr3);
        D.addRelation(wr6);
        D.addRelation(wr8);
        D.addRelation(wr10);

        E.addRelation(wr4);
        E.addRelation(wr7);
        E.addRelation(wr9);
        E.addRelation(wr10);

        Hashtable<String, Vertex> h = new Hashtable<>();

        h.put(A.getId(), A);
        h.put(B.getId(), B);
        h.put(C.getId(), C);
        h.put(D.getId(), D);
        h.put(E.getId(), E);

        return h;
    }

    @Test public void generalTestsModels(){
        Vertex v = new Vertex("v"), w = new Vertex("w"), t = new Vertex("t"), h = new Vertex("h");

        WeightedRelation wr = new WeightedRelation(v, w, 200);
        WeightedRelation wr1 = new WeightedRelation(v, t, 100);
        WeightedRelation wr2 = new WeightedRelation(v, h, 150);

        v.addRelation(wr);
        v.addRelation(wr1);
        v.addRelation(wr2);

        assertThat(wr.containsVertex(v)).isTrue();
        assertThat(wr.containsVertex(w)).isTrue();
        assertThat(wr.containsVertex(t)).isFalse();
        assertThat(wr.getWeight()).isEqualTo(200);

        PriorityQueue<WeightedRelation> copy = v.getOrderedRelations();

        // Immutability test
        assertThat(copy.remove().getWeight()).isEqualTo(100);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);
        assertThat(copy.remove().getWeight()).isEqualTo(150);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);
        assertThat(copy.remove().getWeight()).isEqualTo(200);
        assertThat(v.getMinRelation().getWeight()).isEqualTo(100);

        assertThat(copy.isEmpty()).isTrue();

        assertThat(v.getRelationWith(t)).isEqualTo(wr1);
        assertThat(v.getRelationWith(h)).isEqualTo(wr2);


        List<DirectedRelation> orderedRelationsV = v.getMinRelation().generateOrderedPairs();
        DirectedRelation vt = orderedRelationsV.get(0), tv = orderedRelationsV.get(1);

        assertThat(vt.getNextVertexId()).isEqualTo(t.getId());
        assertThat(tv.isNextEqualsTo(v)).isTrue();
        assertThat(tv.isNextEqualsTo(t)).isFalse();
        assertThat(tv.getOriginVertexId()).isEqualTo(t.getId());
        assertThat(tv.getWeight()).isEqualTo(v.getMinRelation().getWeight());
        assertThat(vt.getWeight()).isEqualTo(v.getMinRelation().getWeight());
    }
}
