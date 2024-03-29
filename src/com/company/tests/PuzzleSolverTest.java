package com.company.tests;

import com.company.FileHelper;
import com.company.GridPuzzle;
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
        PuzzleSolver solver = new PuzzleSolver(gPuzzleDefault2);
        double startTime = System.nanoTime();
        assertTrue(solver.solvePuzzle());
        assertEquals(1, solver.getNbSticks());
        printResults(solver);
        double endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1000000000;
        System.out.println("TEMP EXEC " + elapsedTime);
    }

    @Test
    public void solvePuzzle_PuzzleWithNoSolution_ReturnsFalse() {
        PuzzleSolver solver = new PuzzleSolver(gPuzzleUnsolvable);
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

    private void printResults(PuzzleSolver solver) {
        System.out.println("Nombre de noeuds visités : " + solver.getNbPositionsVisited());
        System.out.println("Nombre de coups de la solution : " + solver.getMoves().size());
        System.out.println("Solution : ");

        for (Move move : solver.getMoves()) {
            System.out.println(move.start + " to " + move.end);
        }
    }

    int[][] defaultPuzzle = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,1,1,2,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};

    int[][] defaultPuzzle2 = new int[][]{
            {0,0,2,1,2,0,0},
            {0,0,1,1,1,0,0},
            {2,1,1,1,1,1,2},
            {2,2,2,1,2,2,2},
            {2,2,2,1,2,2,2},
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

    GridPuzzle gPuzzleDefault = new GridPuzzle(defaultPuzzle);
    GridPuzzle gPuzzleDefault2 = new GridPuzzle(defaultPuzzle2);
    GridPuzzle gPuzzleUnsolvable = new GridPuzzle(unsolvablePuzzle);

}
