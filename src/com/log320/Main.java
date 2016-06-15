package com.log320;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        double startTime = System.nanoTime();

        int[][] board = {
                {0,0,2,2,2,0,0},
                {0,0,1,1,1,0,0},
                {1,1,1,1,1,1,1},
                {1,1,1,2,1,1,1},
                {1,1,1,1,1,1,1},
                {0,0,1,1,1,0,0},
                {0,0,2,2,2,0,0}};

        PuzzleBoard testPuzzleBoard = PuzzleBoard.getBitBoard(board);

        PuzzleSolver solver = new PuzzleSolver(testPuzzleBoard);

        if (!solver.isSolvable()) {
            System.out.println("Aucune solution trouvée pour ce puzzle");
            Statistics.printStatistics(solver);
        } else {
            System.out.println("Une solution a été trouvée pour ce puzzle");
            Statistics.printStatistics(solver);
        }

        double timeTaken = System.nanoTime() - startTime;
        System.out.println(timeTaken / 1000000000 + "s");

    }
}
