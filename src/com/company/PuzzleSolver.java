package com.company;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PuzzleSolver extends Observable {
    private static final int NOT_ON_PUZZLE = 0;
    private static final int STICK = 1;
    private static final int EMPTY = 2;

    private int[][] _puzzle;
    private Stack<Move> _moves;
    private int _nbSticks;
    private int _nbPositionsVisited;

    public PuzzleSolver(int[][] puzzle) {
        _puzzle = puzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks(puzzle);
    }

    public int getNbPositionsVisited() {
        return _nbPositionsVisited;
    }

    public Stack<Move> getMoves() {
        return _moves;
    }

    public int getNbSticks() {
        return _nbSticks;
    }

    public boolean solvePuzzle() {
        if (isPuzzleSolved())
            return true;

        ArrayList<Move> possibleMoves = getPossibleMoves();

        if (possibleMoves.size() == 0) {
            if (_moves.size() == 0)
                return false;

            revertLastMove();
        }

        for (Move move : possibleMoves) {
            makeMove(move);

            if (solvePuzzle())
                return true;
        }

        return false;
    }

    public void revertLastMove() {
        _moves.pop();
        _nbSticks++;
        notifyObservers();
    }

    public boolean isPuzzleSolved() {
        return _nbSticks == 1;
    }

    public void makeMove(Move move) {
        _puzzle[move.getStart().X][move.getStart().Y] = EMPTY;
        _puzzle[move.getMiddle().X][move.getMiddle().Y] = EMPTY;
        _puzzle[move.getEnd().X][move.getEnd().Y] = STICK;

        _moves.add(move);
        _nbSticks--;
        _nbPositionsVisited++;
        notifyObservers();
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<Move>();

        for (int x = 0; x != _puzzle.length; x++) {
            for (int y = 0; y != _puzzle.length; y++) {
                int positionType = _puzzle[x][y];

                if (positionType == NOT_ON_PUZZLE || positionType == EMPTY)
                    continue;

                possibleMoves.addAll(getPossibleMovesForPosition(x, y));
            }
        }

        return possibleMoves;
    }

    public ArrayList<Move> getPossibleMovesForPosition(int x, int y) {
        ArrayList<Move> possibleMoves =  new ArrayList<>();

        // LEFT
        if (y >= 2 && _puzzle[x][y - 1] == STICK && _puzzle[x][y - 2] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y - 2)));
        }

        // RIGHT
        if (_puzzle.length > y + 2 && _puzzle[x][y + 1] == STICK && _puzzle[x][y + 2] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y + 2)));
        }

        // UP
        if (x >= 2 && _puzzle[x - 1][y] == STICK && _puzzle[x - 2][y] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x - 2, y)));
        }

        // DOWN
        if (_puzzle.length > x + 2 && _puzzle[x + 1][y] == STICK && _puzzle[x + 2][y] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x + 2, y)));
        }

        return possibleMoves;
    }

    private int calculateNbSticks(int[][] puzzle) {
        int nbSticks = 0;

        for (int x = 0; x != _puzzle.length; x++) {
            for (int y = 0; y != _puzzle.length; y++) {
                if (puzzle[x][y] == STICK)
                    nbSticks++;
            }
        }

        return nbSticks;
    }
}
