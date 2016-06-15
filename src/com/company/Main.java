package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        PuzzleSolver pSolver;
        String gridFile = args[0];

        try {

            int[][] puzzle = FileHelper.getPuzzleFromFile(gridFile);

            pSolver = new PuzzleSolver(puzzle);
            System.out.println("Puzzle à jouer:");
            System.out.println("=================================");

            pSolver.printGridToConsole();
            System.out.println("=================================");
            long endTime;

            long startTime = System.currentTimeMillis();
            if (pSolver.solvePuzzle()) {
                endTime = System.currentTimeMillis();
                System.out.println("Temps d'execution: " + (endTime - startTime) + " milisecondes");
                System.out.println("Solution : " + "Résolu");
                System.out.println("Nombre de noeuds visités : " + pSolver.getNbPositionsVisited());
                System.out.println("");
                System.out.println("Puzzle résolu:");
                System.out.println("=================================");
                System.out.println("=================================");
                System.out.println("Nombre de coups de la solution : " + pSolver.getMoves().size());
            } else {
                endTime = System.currentTimeMillis();
                System.out.println("Temps d'execution: " + (endTime - startTime) + " milisecondes");
                System.out.println("Solution : " + "Pas de Solution");
                System.out.println("Nombre de noeuds visités : " + pSolver.getNbPositionsVisited());
                System.out.println("Nombre de coups de la solution : " + pSolver.getMoves().size());
            }

            for (Move move : pSolver.getMoves()) {
                System.out.println(move.start + " to " + move.end);
            }

            System.out.println("=================================");

            pSolver.printGridToConsole();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
