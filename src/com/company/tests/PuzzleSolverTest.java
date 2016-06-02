package com.company.tests;

import com.company.Deplacement;
import com.company.FileHelper;
import com.company.Position;
import com.company.PuzzleSolver;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class PuzzleSolverTest {
    @Test
    public void getDeplacementsPossibles_AucunDeplacementPossible() {
        // Prepare
        PuzzleSolver solver = makePuzzleSolver();
        Position position = new Position(1, 3);

        // Act
        ArrayList<Deplacement> deplacementsPossibles = solver.getDeplacementsPossibles(position);


        // Assert
        assertEquals(1, deplacementsPossibles.size());
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
