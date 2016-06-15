package com.log320;

import java.util.*;

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré par l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************


public class PuzzleBoard {

    public static final int SIZE = 7;

    private PuzzleBoard() {

    }

    private PuzzleBoard(Value[][] values) {
        puzzleBoard = values;
    }

    private Value[][] puzzleBoard = new Value[SIZE][SIZE];

    public Value get(int i, int j) {
        return puzzleBoard[i][j];
    }

    public void set(int i, int j, Value h) {
        puzzleBoard[i][j] = h;
    }

    public long bitBoard() {
        long bitBoard = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bitBoard |= puzzleBoard[i][j].bit();
                if (j != SIZE - 1 || i != SIZE - 1) bitBoard <<= 1;
            }
        }
        return bitBoard;
    }

    public static PuzzleBoard getBitBoard(long bitBoard) {
        PuzzleBoard puzzle = new PuzzleBoard();

        for (int i = 0; i < SIZE * SIZE; i++) {
            int idx = SIZE * SIZE - 1 - i;
            int bit = (int) (bitBoard & 1);
            int x = idx / SIZE;
            int y = idx % SIZE;
            puzzle.puzzleBoard[x][y] = ((bit != 0) ? Value.STICK : (PuzzleBoard.invalidPosition(x, y) ? Value.NOT_ON_PUZZLE : Value.EMPTY));
            bitBoard >>= 1;
        }
        return puzzle;
    }

    public PuzzleBoard copyBoard() {
        PuzzleBoard newPuzzle = new PuzzleBoard();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newPuzzle.set(i, j, this.get(i, j));
        return newPuzzle;
    }

    public void move(Move mv) {
        move(mv.fromx(), mv.fromy(), mv.dx(), mv.dy());
    }

    public void move(int x, int y, int dx, int dy) {
        puzzleBoard[x][y] = Value.EMPTY;
        puzzleBoard[x + dx / 2][y + dy / 2] = Value.EMPTY;
        puzzleBoard[x + dx][y + dy] = Value.STICK;
    }

    private List<Long> getRotateConfigs() {
        List<Long> rotations = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rotate();
            rotations.add(bitBoard());
        }
        rotate();
        return rotations;
    }

    private void rotate() {
        for (int i = 0; i < (SIZE) / 2; i++) {
            for (int j = 0; j <= (SIZE - 1) / 2; j++) {

                Value temp = puzzleBoard[i][j];
                puzzleBoard[i][j] = puzzleBoard[SIZE - j - 1][i];
                puzzleBoard[SIZE - j - 1][i] = puzzleBoard[SIZE - i - 1][SIZE - j - 1];
                puzzleBoard[SIZE - i - 1][SIZE - j - 1] = puzzleBoard[j][SIZE - i - 1];
                puzzleBoard[j][SIZE - i - 1] = temp;
            }
        }
    }

    private void verticalReflect() {
        for (int j = 0; j < SIZE / 2; j++)
            for (int i = 0; i < SIZE; i++) {
                Value temp = puzzleBoard[i][j];
                puzzleBoard[i][j] = puzzleBoard[i][SIZE - j - 1];
                puzzleBoard[i][SIZE - j - 1] = temp;
            }
    }

    public List<Long> getSymmetricConfigs() {
        List<Long> configs = new ArrayList<>();
        configs.add(bitBoard());
        List<Long> rotations = getRotateConfigs();
        configs.addAll(rotations);
        verticalReflect();
        configs.add(bitBoard());
        List<Long> reflectRotations = getRotateConfigs();
        configs.addAll(reflectRotations);
        verticalReflect();
        return configs;
    }

    public static PuzzleBoard getBitBoard(int[][] board) {
        Value[][] arrayBoard = new Value[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arrayBoard[i][j] = Value.holeValue(board[i][j]);
            }
        }
        return new PuzzleBoard(arrayBoard);
    }

    public static boolean invalidPosition(int x, int y) {
        return (x < 0 || x >= PuzzleBoard.SIZE || y < 0 || y >= PuzzleBoard.SIZE)
                || ((x < 2 && y < 2) || (x < 2 && y > 4)
                || (x > 4 && y < 2) || (x > 4 && y > 4));
    }

}


