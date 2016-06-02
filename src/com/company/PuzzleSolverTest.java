package com.company;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
}
