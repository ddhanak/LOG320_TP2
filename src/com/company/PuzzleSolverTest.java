package com.company;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class PuzzleSolverTest {
    @Test
    public void getDeplacementsPossibles_AucunDeplacementPossible() {
        // Prepare
        PuzzleSolver solver = makePuzzleSolver();
        Position position = new Position(0, 1);

        // Act
        ArrayList<Deplacement> deplacementsPossibles = solver.getDeplacementsPossibles(position);


        // Assert
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
