package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

public class PuzzleSolver {
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
        // Si il reste une tige, le puzzle est résolu.
        if (_nbSticks == 1)
            return true;

        // On va chercher tous les coups possible sur le puzzle.
        List<Move> possibleMoves = getPossibleMoves();

        for (Move move : possibleMoves) {
            // Pour chaque coup possible, on tente de le jouer.
            makeMove(move);

            // Si le nouvel état du puzzle peut mener à une solution, on retourne qu'on l'a résolu.
            // Sinon, on annule le coup et on essaie le suivant.
            if (solvePuzzle()) {
                return true;
            }
            else {
                revertLastMove();
            }
        }

        // Si on se rend ici, on a essayé toutes les possibilités sans succès.
        return false;
    }

    public void revertLastMove() {
        Move move = _moves.pop();

        _puzzle[move.start.x][move.start.y] = STICK;
        _puzzle[move.jumpedOver.x][move.jumpedOver.y] = STICK;
        _puzzle[move.end.x][move.end.y] = EMPTY;
        _nbSticks++;
    }

    public void makeMove(Move move) {
        _puzzle[move.start.x][move.start.y] = EMPTY;
        _puzzle[move.jumpedOver.x][move.jumpedOver.y] = EMPTY;
        _puzzle[move.end.x][move.end.y] = STICK;

        _moves.add(move);
        _nbSticks--;
        _nbPositionsVisited++;
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<>();

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
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y - 1), new Position(x, y - 2)));
        }

        // RIGHT
        if (_puzzle.length > y + 2 && _puzzle[x][y + 1] == STICK && _puzzle[x][y + 2] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x, y + 1), new Position(x, y + 2)));
        }

        // UP
        if (x >= 2 && _puzzle[x - 1][y] == STICK && _puzzle[x - 2][y] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x - 1, y), new Position(x - 2, y)));
        }

        // DOWN
        if (_puzzle.length > x + 2 && _puzzle[x + 1][y] == STICK && _puzzle[x + 2][y] == EMPTY) {
            possibleMoves.add(new Move(new Position(x, y), new Position(x + 1, y), new Position(x + 2, y)));
        }

        return possibleMoves;
    }

    public int calculateNbSticks(int[][] puzzle) {
        int nbSticks = 0;

        for (int x = 0; x != _puzzle.length; x++) {
            for (int y = 0; y != _puzzle.length; y++) {
                if (puzzle[x][y] == STICK)
                    nbSticks++;
            }
        }

        return nbSticks;
    }

    public void printGridToConsole(){

        int[][]puzzle = _puzzle;
        int count = 0;

        for (int i=0;i<puzzle.length;i++){

            for (int j=0;j<puzzle.length;j++){
                count++;

                if(count==7){
                    System.out.println(puzzle[i][j]);
                }else{
                    System.out.print(puzzle[i][j]);
                }
            }
            count=0;
        }
    }
}
