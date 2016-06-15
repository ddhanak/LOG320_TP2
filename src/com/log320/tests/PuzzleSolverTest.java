package com.log320.tests;

import com.log320.Statistics;
import com.log320.PuzzleBoard;
import com.log320.PuzzleSolver;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleSolverTest {

    @Test
    public void solvePuzzle_PuzzleWithSolution_PuzzleSolved() {
        PuzzleSolver ps = new PuzzleSolver(mainPuzzleBoard);
        assertTrue("Le puzzle a été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle6_PuzzleSolved() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard6);
        assertTrue("Le puzzle a été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle7_PuzzleSolved() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard7);
        assertTrue("Le puzzle a été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle1_ReturnsFalse() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard1);
        assertFalse("Le puzzle a été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle2_ReturnsFalse() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard2);
        assertFalse("Le puzzle n'a pas été résolu", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle3_ReturnsFalse() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard3);
        assertFalse("Le puzzle n'a pas été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_Puzzle4_ReturnsFalse() {
        PuzzleSolver ps = new PuzzleSolver(puzzleBoard4);
        assertTrue("Le puzzle n'a pas été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
    }

    @Test
    public void solvePuzzle_PuzzleWithNoSolution_ReturnsFalse() {
        PuzzleSolver ps = new PuzzleSolver(unsolvablePuzzleBoard);
        assertFalse("Le puzzle n'a pas été résolu.", ps.isSolvable());
        Statistics.printStatistics(ps);
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

    int[][] puzzleBoard_1 = new int[][]{
            {0,0,1,1,2,0,0},
            {0,0,1,2,1,0,0},
            {2,2,1,2,1,2,2},
            {2,2,1,2,1,2,2},
            {2,2,1,2,1,2,2},
            {0,0,1,2,1,0,0},
            {0,0,1,1,2,0,0}};

    int[][] puzzleBoard_2 = new int[][]{
            {0,0,1,2,2,0,0},
            {0,0,1,2,2,0,0},
            {2,2,2,1,2,1,1},
            {2,2,1,2,1,2,2},
            {1,1,2,1,2,2,2},
            {0,0,2,2,1,0,0},
            {0,0,2,2,1,0,0}};

    int[][] puzzleBoard_3 = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,2,2,0,0},
            {1,2,2,1,2,1,1},
            {1,2,1,1,1,2,1},
            {1,1,2,1,2,2,1},
            {0,0,2,2,1,0,0},
            {0,0,1,1,1,0,0}};

    int[][] puzzleBoard_4 = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,1,1,2,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};

    int[][] puzzleBoard_5 = new int[][]{
            {0,0,0,1,0,0,0},
            {0,1,1,1,1,1,0},
            {0,1,0,1,0,1,0},
            {1,1,1,2,1,1,1},
            {0,1,0,1,0,1,0},
            {0,1,1,1,1,1,0},
            {0,0,0,1,0,0,0}};

    int[][] puzzleBoard_6 = new int[][]{
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1},
            {1,2,1,1,1,2,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}};

    int[][] puzzleBoard_7 = new int[][]{
            {0,0,2,2,2,0,0},
            {0,0,1,1,1,0,0},
            {2,1,1,1,1,1,2},
            {2,1,1,2,1,1,2},
            {2,1,1,1,1,1,2},
            {0,0,1,1,1,0,0},
            {0,0,2,2,2,0,0}};

    PuzzleBoard mainPuzzleBoard = PuzzleBoard.getBitBoard(defaultPuzzle);
    PuzzleBoard unsolvablePuzzleBoard = PuzzleBoard.getBitBoard(unsolvablePuzzle);
    PuzzleBoard puzzleBoard1 = PuzzleBoard.getBitBoard(puzzleBoard_1);
    PuzzleBoard puzzleBoard2 = PuzzleBoard.getBitBoard(puzzleBoard_2);
    PuzzleBoard puzzleBoard3 = PuzzleBoard.getBitBoard(puzzleBoard_3);
    PuzzleBoard puzzleBoard4 = PuzzleBoard.getBitBoard(puzzleBoard_4);
    PuzzleBoard puzzleBoard6 = PuzzleBoard.getBitBoard(puzzleBoard_6);
    PuzzleBoard puzzleBoard7 = PuzzleBoard.getBitBoard(puzzleBoard_7);


}
