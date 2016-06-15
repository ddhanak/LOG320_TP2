package com.company;

import java.util.*;

public class PuzzleSolver {
    private static final int NOT_ON_PUZZLE = 0;
    private static final int STICK = 1;
    private static final int EMPTY = 2;

    private static final int[][] WEIGHTS = {
            {3,3,3,3,3,3,3},
            {3,2,2,2,2,2,3},
            {3,2,1,1,1,2,3},
            {3,2,1,0,1,2,3},
            {3,2,1,1,1,2,3},
            {3,2,2,2,2,2,3},
            {3,3,3,3,3,3,3}};

   // private int[][] _puzzle;
    private GridPuzzle gPuzzle;
    private List<Position> _possiblePositions;
    private Stack<Move> _moves;
    private int _nbSticks;
    private int _nbPositionsVisited;
    private HashSet<String> _badBoardStates;
/*
    public PuzzleSolver(int[][] puzzle) {
        _puzzle = puzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks(puzzle);
        _badBoardStates = new HashSet<>();
        _possiblePositions = getPossiblePositions(puzzle);
    }
*/
    public PuzzleSolver(GridPuzzle gPuzzle) {
        this.gPuzzle = gPuzzle;
        _moves = new Stack<>();
        _nbSticks = calculateNbSticks();
        _badBoardStates = new HashSet<>();
        _possiblePositions = getPossiblePositions();
    }

    public boolean solvePuzzle() {
        // Si il reste une tige, le puzzle est résolu.
        if (_nbSticks == 1)
            return true;

        if (_badBoardStates.contains(getPuzzleString())) {
            return false;
        }

        // On va chercher tous les coups possible sur le puzzle.
        List<Move> possibleMoves = getPossibleMoves();

        while (!possibleMoves.isEmpty()) {

            Move move = getBestMove(possibleMoves);
            possibleMoves.remove(move);

            // Pour chaque coup possible, on tente de le jouer.
            makeMove(move);

            // Si le nouvel état du puzzle peut mener à une solution, on retourne qu'on l'a résolu.
            // Sinon, on annule le coup et on essaie le suivant.
            if (solvePuzzle()) {
                return true;
            } else {
                _badBoardStates.add(getPuzzleString());
                revertLastMove();
            }
        }

        // Si on se rend ici, on a essayé toutes les possibilités sans succès.
        return false;
    }

    public Move getBestMove(List<Move> possibleMoves1) {
        Move bestMove = possibleMoves1.get(0);
        int bestWeight = Integer.MAX_VALUE;

        for (Move move1 : possibleMoves1) {
            makeMove(move1);
            List<Move> possibleMoves2 = getPossibleMoves();

            for (Move move2 : possibleMoves2) {
                makeMove(move2);
                List<Move> possibleMoves3 = getPossibleMoves();

                for (Move move3 : possibleMoves3) {
                    makeMove(move3);

                    int weight = calculateBoardWeight();
                    if (weight < bestWeight) {
                        bestWeight = weight;
                        bestMove = move1;
                    }

                    revertLastMove();
                }

                revertLastMove();
            }

            revertLastMove();
        }

        return bestMove;
    }

    public int calculateBoardWeight() {
        int boardWeight = 0;

        for (Position position : _possiblePositions) {
            boardWeight += WEIGHTS[position.x][position.y];
        }

        return boardWeight;
    }

    public List<Position> getPossiblePositions() {
        List<Position> positions = new LinkedList<>();

        for (int x = 0; x != gPuzzle.getLength(); x++)
            for (int y = 0; y != gPuzzle.getLength(); y++)
                if (gPuzzle.getCase(x,y) != NOT_ON_PUZZLE)
                    positions.add(new Position(x, y));

        return positions;
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

    public String getPuzzleString() {
        StringBuilder builder = new StringBuilder(49);

        for (int x = 0; x != gPuzzle.getLength(); x++) {
            for (int y = 0; y != gPuzzle.getLength(); y++) {
                builder.append(gPuzzle.getCase(x,y));
            }
        }

        return builder.toString();
    }

    public void revertLastMove() {
        Move move = _moves.pop();

        gPuzzle.setCase(move.start.x,move.start.y,STICK);
        gPuzzle.setCase(move.jumpedOver.x,move.jumpedOver.y,STICK);
        gPuzzle.setCase(move.end.x,move.end.y,EMPTY);
        _nbSticks++;
    }

    public void makeMove(Move move) {
        gPuzzle.setCase(move.start.x,move.start.y,EMPTY);
        gPuzzle.setCase(move.jumpedOver.x,move.jumpedOver.y,EMPTY);
        gPuzzle.setCase(move.end.x,move.end.y,STICK);

        _moves.add(move);
        _nbSticks--;
        _nbPositionsVisited++;
    }

    public List<Move> getPossibleMoves() {
        List<Move> possibleMoves = new LinkedList<>();

        for (Position position : _possiblePositions) {
            int x = position.x;
            int y = position.y;

            if (gPuzzle.getCase(x,y) == EMPTY)
                continue;

            // LEFT
            if (y >= 2 && gPuzzle.getCase(x,y-1) == STICK && gPuzzle.getCase(x,y-2) == EMPTY) {
                possibleMoves.add(new Move(new Position(x, y), new Position(x, y - 1), new Position(x, y - 2)));
            }

            // RIGHT
            if (gPuzzle.getLength() > y + 2 && gPuzzle.getCase(x,y+1) == STICK && gPuzzle.getCase(x,y+2) == EMPTY) {
                possibleMoves.add(new Move(new Position(x, y), new Position(x, y + 1), new Position(x, y + 2)));
            }

            // UP
            if (x >= 2 && gPuzzle.getCase(x-1,y) == STICK && gPuzzle.getCase(x-2,y) == EMPTY) {
                possibleMoves.add(new Move(new Position(x, y), new Position(x - 1, y), new Position(x - 2, y)));
            }

            // DOWN
            if (gPuzzle.getLength() > x + 2 && gPuzzle.getCase(x+1,y) == STICK && gPuzzle.getCase(x+2,y) == EMPTY) {
                possibleMoves.add(new Move(new Position(x, y), new Position(x + 1, y), new Position(x + 2, y)));
            }
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
}
