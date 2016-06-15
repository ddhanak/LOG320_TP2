package com.log320;

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré par l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************

public class Heuristics {

    public static int weightedCost(PuzzleBoard m_Puzzle_board) {
        int[][] costMatrix =  new int[][]{
                { 0, 0, 4, 0, 4, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 1, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 4, 0, 4, 0, 0 }};
        return evaluateCostMatrix(m_Puzzle_board, costMatrix);
    }

    public static int evaluateCostMatrix(PuzzleBoard puzzleBoard, int[][] costMatrix ) {
        int weightValue = 0;
        for (int i = 0; i < PuzzleBoard.SIZE; i++) {
            for (int j = 0; j < PuzzleBoard.SIZE; j++) {
                if (puzzleBoard.get(i, j) == Value.STICK) {
                    weightValue += costMatrix[i][j];
                }
            }
        }
        return weightValue;
    }
}
