package com.company;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PuzzleSolver extends Observable {
    private static final int NOT_ON_PUZZLE = 0;
    private static final int STICK = 1;
    private static final int EMPTY = 2;

    private Stack<Move> _moves;
    private int _nbPositionsVisited;
    private GridPuzzle gPuzzle;
    private int _nbSticks;

    public PuzzleSolver(GridPuzzle gPuzzle) {
        this.gPuzzle = gPuzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks();
    }

    public boolean solvePuzzle() {
        if (_nbSticks == 1) {
            return true;
        }

        ArrayList<Move> possibleMoves = getPossibleMoves();

        if (possibleMoves.size() == 0) {
           return false;
        }

        for (Move move : possibleMoves) {
            makeMove(move);

            // On tente de résoudre le puzzle avec une tige en moins
            if (solvePuzzle()) {
                return true;
            }
            else {
                revertLastMove();
            }
        }

        return false;
    }

    public void revertLastMove() {
        Move lastMove = _moves.pop();

        gPuzzle.setCase(lastMove.getStart().X,lastMove.getStart().Y,STICK);
        gPuzzle.setCase(lastMove.getMiddle().X,lastMove.getMiddle().Y,STICK);
        gPuzzle.setCase(lastMove.getEnd().X,lastMove.getEnd().Y,EMPTY);

        _nbSticks++;
      //  setChanged();
      //  notifyObservers();
    }

    public void makeMove(Move move) {
        gPuzzle.setCase(move.getStart().X,move.getStart().Y,EMPTY);
        gPuzzle.setCase(move.getMiddle().X,move.getMiddle().Y,EMPTY);
        gPuzzle.setCase(move.getEnd().X,move.getEnd().Y,STICK);

        _moves.add(move);
        _nbSticks--;
        _nbPositionsVisited++;
      //  setChanged();
     //   notifyObservers();
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

    public int calculateNbSticks() {
        int nbSticks = 0;

        for (int x = 0; x != gPuzzle.getLength(); x++) {
            for (int y = 0; y != gPuzzle.getLength(); y++) {
                if (gPuzzle.getCase(x,y) == STICK)
                    nbSticks++;
            }
        }

        return nbSticks;
    }

    public int getNbSticks() {
        return _nbSticks;
    }

    public int getNbPositionsVisited() {
        return _nbPositionsVisited;
    }

    public Stack<Move> getMoves() {
        return _moves;
    }
}
