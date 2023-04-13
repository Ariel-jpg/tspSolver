package System;

import main.GraphTheoryModels.Vertex;
import main.GraphTheoryModels.WeightedRelation;

import java.util.Hashtable;

public class GraphCases {
    GraphCases(){}

    public Hashtable<String, Vertex> getCase1_1(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D");

        WeightedRelation wr1 = new WeightedRelation(A, B, 1);
        WeightedRelation wr2 = new WeightedRelation(A, C, 3);
        WeightedRelation wr3 = new WeightedRelation(A, D, 8);

        WeightedRelation wr5 = new WeightedRelation(B, C, 2);
        WeightedRelation wr6 = new WeightedRelation(B, D, 10);

        WeightedRelation wr8 = new WeightedRelation(C, D, 4);

        Hashtable<String, Vertex> vertices = new Hashtable<>();

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);

        return vertices;
    }

    public Hashtable<String, Vertex> getCase1(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D");

        WeightedRelation wr1 = new WeightedRelation(A, B, 20);
        WeightedRelation wr2 = new WeightedRelation(A, C, 4);
        WeightedRelation wr3 = new WeightedRelation(A, D, 16);

        WeightedRelation wr5 = new WeightedRelation(B, C, 5);
        WeightedRelation wr6 = new WeightedRelation(B, D, 3);

        WeightedRelation wr8 = new WeightedRelation(C, D, 7);

        Hashtable<String, Vertex> vertices = new Hashtable<>();

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);

        return vertices;
    }

    public Hashtable<String, Vertex> getCase2_1(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D"), E = new Vertex("E");

        WeightedRelation wr1 = new WeightedRelation(A, B, 8);
        WeightedRelation wr2 = new WeightedRelation(A, C, 2);
        WeightedRelation wr3 = new WeightedRelation(A, D, 17);
        WeightedRelation wr4 = new WeightedRelation(A, E, 5);

        WeightedRelation wr5 = new WeightedRelation(B, C, 20);
        WeightedRelation wr6 = new WeightedRelation(B, D, 3);
        WeightedRelation wr7 = new WeightedRelation(B, E, 11);

        WeightedRelation wr8 = new WeightedRelation(C, D, 15);
        WeightedRelation wr9 = new WeightedRelation(C, E, 10);

        WeightedRelation wr10 = new WeightedRelation(D, E, 7);

        Hashtable<String, Vertex> vertices = new Hashtable<>();

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);
        vertices.put(E.getId(), E);

        return vertices;
    }

    public Hashtable<String, Vertex> getCase2_2(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D"), E = new Vertex("E");

        WeightedRelation wr1 = new WeightedRelation(A, B, 3);
        WeightedRelation wr2 = new WeightedRelation(A, C, 10);
        WeightedRelation wr3 = new WeightedRelation(A, D, 7);
        WeightedRelation wr4 = new WeightedRelation(A, E, 5);

        WeightedRelation wr5 = new WeightedRelation(B, C, 20);
        WeightedRelation wr6 = new WeightedRelation(B, D, 8);
        WeightedRelation wr7 = new WeightedRelation(B, E, 2);

        WeightedRelation wr8 = new WeightedRelation(C, D, 12);
        WeightedRelation wr9 = new WeightedRelation(C, E, 4);

        WeightedRelation wr10 = new WeightedRelation(D, E, 1);

        Hashtable<String, Vertex> vertices = new Hashtable<>();

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);
        vertices.put(E.getId(), E);

        return vertices;
    }

    public Hashtable<String, Vertex> getCase2_3(){
        Vertex A = new Vertex("A"), B = new Vertex("B"), C = new Vertex("C"), D = new Vertex("D"), E = new Vertex("E");

        WeightedRelation wr1 = new WeightedRelation(A, B, 1);
        WeightedRelation wr2 = new WeightedRelation(A, C, 4);
        WeightedRelation wr3 = new WeightedRelation(A, D, 3);
        WeightedRelation wr4 = new WeightedRelation(A, E, 2);

        WeightedRelation wr5 = new WeightedRelation(B, C, 10);
        WeightedRelation wr6 = new WeightedRelation(B, D, 15);
        WeightedRelation wr7 = new WeightedRelation(B, E, 20);

        WeightedRelation wr8 = new WeightedRelation(C, D, 8);
        WeightedRelation wr9 = new WeightedRelation(C, E, 18);

        WeightedRelation wr10 = new WeightedRelation(D, E, 5);

        Hashtable<String, Vertex> vertices = new Hashtable<>();

        vertices.put(A.getId(), A);
        vertices.put(B.getId(), B);
        vertices.put(C.getId(), C);
        vertices.put(D.getId(), D);
        vertices.put(E.getId(), E);

        return vertices;
    }

    public Hashtable<String, Vertex> getHardCase(){
        Vertex CA = new Vertex("CA"), LP = new Vertex("LP"), CBA = new Vertex("CBA"), LR = new Vertex("LR"), CO = new Vertex("CO"), FO = new Vertex("FO"), R = new Vertex("R"), TF = new Vertex("TF"), TU = new Vertex("TU"), JU = new Vertex("JU");

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
        WeightedRelation FOTF = new WeightedRelation(FO, TF, 3284);
        WeightedRelation FOTU = new WeightedRelation(FO, TU, 703);
        WeightedRelation FOJU = new WeightedRelation(FO, JU, 750);

        WeightedRelation RTF = new WeightedRelation(R, TF, 1300);
        WeightedRelation RTU = new WeightedRelation(R, TU, 1827);
        WeightedRelation RJU = new WeightedRelation(R, JU, 2120);

        WeightedRelation TFTU = new WeightedRelation(TF, TU, 3116);
        WeightedRelation TFJU = new WeightedRelation(TF, JU, 3408);

        WeightedRelation TUJU = new WeightedRelation(TU, JU, 689);

        Hashtable<String, Vertex> vertices = new Hashtable<>();
        vertices.put(LP.getId(), LP);
        vertices.put(CBA.getId(), CBA);
        vertices.put(LR.getId(), LR);
        vertices.put(CO.getId(), CO);
        vertices.put(FO.getId(), FO);
        vertices.put(R.getId(), R);
        vertices.put(TF.getId(), TF);
        vertices.put(TU.getId(), TU);
        vertices.put(JU.getId(), JU);
        vertices.put(CA.getId(), CA);

        return vertices;
    }
}
