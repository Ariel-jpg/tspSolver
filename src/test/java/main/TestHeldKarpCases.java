package main;

import main.GraphAlgorithms.DijkstraA;
import main.GraphAlgorithms.HeldKarpA.HeldKarp;
import main.GraphAlgorithms.FloydWarshallA;
import main.GraphAlgorithms.PrimA;
import org.junit.Test;

public class TestHeldKarpCases {
    final static int INF = 99999;

    public int[][] getMatrix1(){
        int[][] matrix = new int[5][5];

        matrix[0][1] = 15;
        matrix[0][2] = 1;
        matrix[0][3] = 7;
        matrix[0][4] = 10;

        matrix[1][2] = 2;
        matrix[1][3] = 25;
        matrix[1][4] = 30;

        matrix[2][3] = 9;
        matrix[2][4] = 8;

        matrix[3][4] = 20;

        completeSymmetry(matrix);

        return matrix;
    }

    public int[][] getMatrix2(){
        int[][] matrix = new int[4][4];

        matrix[0][1] = 10;
        matrix[0][2] = 20;
        matrix[0][3] = 1;

        matrix[1][2] = 5;
        matrix[1][3] = 9;

        matrix[2][3] = 8;

        completeSymmetry(matrix);

        return matrix;
    }

    public int[][] getMatrix3(){
        int[][] matrix = new int[6][6];

        matrix[0][1] = 20;
        matrix[0][2] = 8;
        matrix[0][3] = 30;
        matrix[0][4] = 5;
        matrix[0][5] = 80;

        matrix[1][2] = 6;
        matrix[1][3] = 10;
        matrix[1][4] = 50;
        matrix[1][5] = 17;

        matrix[2][3] = 60;
        matrix[2][4] = 11;
        matrix[2][5] = 54;

        matrix[3][4] = 25;
        matrix[3][5] = 70;

        matrix[4][5] = 31;

        completeSymmetry(matrix);

        return matrix;
    }

    @Test
    public void tesPrimA(){
        int[][] matrix = getMatrix3();

            PrimA mst = new PrimA(matrix);

            // Print the Minimum Spanning Tree solution
            mst.designMST();
    }

    @Test
    public void testFloydWarshallA() {
        int graph[][] = getMatrix3();

        FloydWarshallA f = new FloydWarshallA(graph);

        // Function call
        f.calculate();
    }

    @Test public void TestDijkstraA(){
        int matrix[][] = getMatrix3();

        DijkstraA obj = new DijkstraA(matrix);
        obj.dijkstra(4);
    }

    @Test
    public void TestCase(){
        int[][] matrix = getMatrix4();

        HeldKarp h = new HeldKarp(matrix, 0);

        System.out.println(h.calculateHeldKarp());
        System.out.println(h.getOpt());
    }

    public int[][] getMatrix4(){
        int[][] matrix = new int[5][5];

        matrix[0][1] = 8;
        matrix[0][2] = 2;
        matrix[0][3] = 5;
        matrix[0][4] = 30;

        matrix[1][2] = 10;
        matrix[1][3] = 3;
        matrix[1][4] = 15;

        matrix[2][3] = 1;
        matrix[2][4] = 4;

        matrix[3][4] = 48;

        completeSymmetry(matrix);

        return matrix;
    }

    public void completeSymmetry(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j ++){
                if(i == j) matrix[i][j] = 0;
                else if(i < j) matrix[j][i] = matrix[i][j];
            }
        }
    }
}
