package com.company;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PuzzleSolver extends Observable {
    private static final int NOT_ON_PUZZLE = 0;
    private static final int STICK = 1;
    private static final int EMPTY = 2;

   // private int[][] _puzzle;
    private Stack<Move> _moves;
    private int _nbSticks;
    private int _nbPositionsVisited;
    private GridPuzzle gPuzzle;

   /* public PuzzleSolver(int[][] puzzle) {
        _puzzle = puzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks(puzzle);
    }
*/
    public PuzzleSolver(GridPuzzle gPuzzle) {
        this.gPuzzle = gPuzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks();
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
                // On est au premier essai ou revenu au début et il y a aucun déplacement possible.
                return false;

            revertLastMove();
        }

        for (Move move : possibleMoves) {
            makeMove(move);

            // On tente de résoudre le puzzle avec une tige en moins
            if (solvePuzzle())
                return true;
        }

        return false;
    }

    public void revertLastMove() {
        _moves.pop();
        _nbSticks++;
        setChanged();
        notifyObservers();
    }

    public boolean isPuzzleSolved() {
        return _nbSticks == 1;
    }

    public void makeMove(Move move) {
        gPuzzle.setCase(move.getStart().X,move.getStart().Y,EMPTY);
        gPuzzle.setCase(move.getMiddle().X,move.getMiddle().Y,EMPTY);
        gPuzzle.setCase(move.getEnd().X,move.getEnd().Y,STICK);

        _moves.add(move);
        _nbSticks--;
        _nbPositionsVisited++;
        setChanged();
        notifyObservers();
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<Move>();

        for (int x = 0; x != gPuzzle.getLength(); x++) {
            for (int y = 0; y != gPuzzle.getLength(); y++) {
                int positionType = gPuzzle.getCase(x,y);

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
        if (y >= 2 && gPuzzle.getCase(x,y - 1) == STICK && gPuzzle.getCase(x,y - 2) == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y - 2)));
        }

        // RIGHT
        if (gPuzzle.getLength()> y + 2 && gPuzzle.getCase(x,y + 1) == STICK && gPuzzle.getCase(x,y + 2) == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y + 2)));
        }

        // UP
        if (x >= 2 && gPuzzle.getCase(x - 1,y) == STICK && gPuzzle.getCase(x - 2,y) == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x - 2, y)));
        }

        // DOWN
        if (gPuzzle.getLength() > x + 2 && gPuzzle.getCase(x + 1,y) == STICK && gPuzzle.getCase(x + 2,y) == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x + 2, y)));
        }

        return possibleMoves;
    }

    private int calculateNbSticks() {
        int nbSticks = 0;

        for (int x = 0; x != gPuzzle.getLength(); x++) {
            for (int y = 0; y != gPuzzle.getLength(); y++) {
                if (gPuzzle.getCase(x,y) == STICK)
                    nbSticks++;
            }
        }

        return nbSticks;
    }
}
