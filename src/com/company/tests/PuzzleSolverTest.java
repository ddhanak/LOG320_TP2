package com.company.tests;

import com.company.FileHelper;
import com.company.Move;
import com.company.PuzzleSolver;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleSolverTest {

    @Test
    public void solvePuzzle_PuzzleWithSolution_PuzzleSolved() {
        PuzzleSolver solver = makePuzzleSolver();
        assertTrue(solver.solvePuzzle());
        assertEquals(1, solver.getNbSticks());
        System.out.println("Nombre de noeuds visités : " + solver.getNbPositionsVisited());
        System.out.println("Solution : ");

        for (Move move : solver.getMoves()) {
            System.out.println(move.getStart() + " to " + move.getEnd());
        }
    }

    @Test
    public void solvePuzzle_PuzzleWithNoSolution_ReturnsFalse() {
        PuzzleSolver solver = new PuzzleSolver(unsolvablePuzzle);
        assertFalse(solver.solvePuzzle());
    }

    @Test
    public void checkPuzzle_PuzzleIsIdentical() throws IOException {
        int[][] puzzle1 = new int[][]{
                {1,0,1,1,1,0,2},
                {0,0,1,1,1,0,0},
                {1,1,1,1,1,1,1},
                {1,1,1,2,1,1,1},
                {1,1,1,1,1,1,1},
                {0,0,1,1,1,0,0},
                {1,0,1,1,1,0,2}};


        int[][] puzzle2 = FileHelper.getPuzzleFromFile("test.puzzle");

        for (int x = 0; x != puzzle1.length; x++) {
            for (int y = 0; y != puzzle1.length; y++) {
                if (puzzle1[x][y] != puzzle2[x][y])
                    fail("Puzzles are not identicals.");
            }
        }
    }

    PuzzleSolver makePuzzleSolver() {
        return new PuzzleSolver(defaultPuzzle);
    }

    int[][] defaultPuzzle = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,1,1,2,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};

    int[][] unsolvablePuzzle = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};
}
