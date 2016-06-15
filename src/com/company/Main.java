package com.company;

import UI.GridUIPuzzle;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        PuzzleSolver pSolver;
        GridPuzzle gPuzzle;
        String gridFile = args[0];

        gPuzzle = new GridPuzzle(FileHelper.getPuzzleFromFile(gridFile));
        pSolver = new PuzzleSolver(gPuzzle);

        long startTime = System.currentTimeMillis();
        if (pSolver.solvePuzzle()){
            long endTime = System.currentTimeMillis();
            System.out.println("Temps d'execution: " + (endTime - startTime) + " milisecondes");
            System.out.println("Solution : "+"Résolu");
            System.out.println("Nombre de noeuds visités : " + pSolver.getNbPositionsVisited());
            System.out.println("Nombre de coups de la solution : " + pSolver.getMoves().size());
        }else{
            System.out.println("Solution : "+"Pas de Solution");
            System.out.println("Nombre de noeuds visités : " + pSolver.getNbPositionsVisited());
            System.out.println("Nombre de coups de la solution : " + pSolver.getMoves().size());
        }

        for (Move move : pSolver.getMoves()) {
            System.out.println(move.start + " to " + move.end);
        }

    }
}
