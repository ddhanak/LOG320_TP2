package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<List<String>> getTests() {
        List<List<String>> tests = new ArrayList<List<String>>();

        List<String> test1 = new ArrayList<String>()
        {
            private static final long serialVersionUID = 1L;
            {add("--XXX--"); }
            {add("--XXX--"); }
            {add("XXXXXXX"); }
            {add("X0X0XXX"); }
            {add("X000XXX"); }
            {add("--XXX--"); }
            {add("--XXX--"); }
        };
        tests.add(test1);
        return tests;
    }

    public static void main(String[] args) {

        int[][] board = {
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 2, 1, 1, 1},
                {1, 2, 2, 2, 1, 1, 1},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0}};

        List<List<String>> boardConfigs = getTests();

        for(List<String> cfg: boardConfigs) {
            Board testBoard = Board.getBoard(cfg);

            PegSolitaireSolver solver = new PegSolitaireSolver(testBoard);

            solver.setHeuristic(PegSolitaireSolver.Heuristic.WEIGHTED_COST);

            if (!solver.aStar()) {
                System.out.println("no solution");
            } else {
                System.out.println("solution !");
            }
        }
    }
}
