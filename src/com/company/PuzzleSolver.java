package com.company;

import java.util.ArrayList;
import java.util.List;
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
        Move lastMove = _moves.pop();

        gPuzzle.setCase(lastMove.start.x,lastMove.start.y,STICK);
        gPuzzle.setCase(lastMove.jumpedOver.x,lastMove.jumpedOver.y,STICK);
        gPuzzle.setCase(lastMove.end.x,lastMove.end.y,EMPTY);

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
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int x = 0; x != gPuzzle.getLength(); x++) {
            for (int y = 0; y != gPuzzle.getLength(); y++) {
                int positionType = gPuzzle.getCase(x,y);

                // On peut seulement déplacer des tiges.
                if (positionType == NOT_ON_PUZZLE || positionType == EMPTY)
                    continue;

                // Ici, on vérifie les coups possibles parmi les 4 possibles dans les règles du jeu.
                // LEFT
                if (y >= 2 && gPuzzle.getCase(x,y - 1) == STICK && gPuzzle.getCase(x,y - 2) == EMPTY) {
                    possibleMoves.add(new Move(new Position(x, y), new Position(x, y - 1), new Position(x, y - 2)));
                }

                // RIGHT
                if (gPuzzle.getLength()> y + 2 && gPuzzle.getCase(x,y + 1) == STICK && gPuzzle.getCase(x,y + 2) == EMPTY) {
                    possibleMoves.add(new Move(new Position(x, y), new Position(x, y + 1), new Position(x, y + 2)));
                }

                // UP
                if (x >= 2 && gPuzzle.getCase(x - 1,y) == STICK && gPuzzle.getCase(x - 2,y) == EMPTY) {
                    possibleMoves.add(new Move(new Position(x, y), new Position(x - 1, y), new Position(x - 2, y)));
                }

                // DOWN
                if (gPuzzle.getLength() > x + 2 && gPuzzle.getCase(x + 1,y) == STICK && gPuzzle.getCase(x + 2,y) == EMPTY) {
                    possibleMoves.add(new Move(new Position(x, y), new Position(x + 1, y), new Position(x + 2, y)));
                }
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
