package com.company;

import org.junit.Test;

import java.io.IOException;

public class PuzzleSolverTest {
    @Test
    public void getDeplacementsPossibles_AucunDeplacementPossible() {
        PuzzleSolver solver = makePuzzleSolver();

    }

    PuzzleSolver makePuzzleSolver() {
        return new PuzzleSolver(plateau);
    }

    int[][] plateau = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,1,1,2,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};


    @Test
    public void checkPuzzle_PuzzleIsIdentical() {
        int[][] puzzle = new int[][]{
                {0,0,1,1,1,0,0},
                {0,0,1,1,1,0,0},
                {1,1,1,1,1,1,1},
                {1,1,1,2,1,1,1},
                {1,1,1,1,1,1,1},
                {0,0,1,1,1,0,0},
                {0,0,1,1,1,0,0}};

        try {
            int[][] puzzle2 = FileHelper.getPuzzleFromFile("test.puzzle");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
