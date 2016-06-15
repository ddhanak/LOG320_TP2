package com.log320;

import java.util.*;

public class PuzzleSolver {

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré de l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************


    private PuzzleBoard puzzleBoard;
    private Map<Long, PriorityNode> visitedStates = new HashMap<>();
    private LinkedList<Move> moves = new LinkedList<>();

    public int expandedNodes = 0;

    private static CostComparator costComparator = new CostComparator();
    private PriorityQueue<PriorityNode> priorityQueue = new PriorityQueue<>(15, costComparator);

    public PuzzleSolver(PuzzleBoard puzzleBoard) {
        this.puzzleBoard = puzzleBoard;
    }

    int[][] deltas = new int[][]{{-2, 0}, {2, 0}, {0, -2}, {0, 2}};

    public boolean isSolvable() {
        expandedNodes = 0;
        int distance;
        PriorityNode root = new PriorityNode();
        root.setWeight(getCost());
        root.setState(puzzleBoard.bitBoard());
        root.setPreviousState(-1);
        priorityQueue.add(root);
        PuzzleBoard currentState = puzzleBoard;

        for (Long st : puzzleBoard.getSymmetricConfigs())
            visitedStates.put(st, root);

        int prevX = 0;
        int prevY = 0;

        root.setMove(new Move(prevX, prevY, prevX, prevY));
        while (!isFinalState(currentState) && !priorityQueue.isEmpty()) {

            expandedNodes++;
            long curStateBmp = priorityQueue.peek().getState();
            currentState = PuzzleBoard.getBitBoard(curStateBmp);
            PriorityNode curNode = priorityQueue.poll();
            distance = curNode.getDistance();

            List<Integer> xOrder = new ArrayList<>(PuzzleBoard.SIZE);
            List<Integer> yOrder = new ArrayList<>(PuzzleBoard.SIZE);
            List<Integer> deltaOrder = new ArrayList<>(4);
            int idxx = prevX;

            for (int i = 0; i < PuzzleBoard.SIZE; i++)
                xOrder.add(idxx++ % PuzzleBoard.SIZE);

            int idxy = prevY;

            for (int i = 0; i < PuzzleBoard.SIZE; i++)
                yOrder.add(idxy++ % PuzzleBoard.SIZE);

            int idx = 0;
            for (int i = 0; i < 4; i++)
                deltaOrder.add(idx++);


            for (int x : xOrder)
                for (int y : yOrder) {
                    if (currentState.get(x, y) == Value.STICK) {
                        for (int index : deltaOrder) {
                            puzzleBoard = currentState.copyBoard();
                            int dx = deltas[index][0];
                            int dy = deltas[index][1];
                            if (validMove(x, y, dx, dy)) {
                                Move mv = new Move(x, y, x + dx, y + dy);
                                puzzleBoard.move(mv);

                                Long boardSt = puzzleBoard.bitBoard();
                                if (visitedStates.get(boardSt) != null)
                                    continue;
                                int pgCost = getCost();

                                PriorityNode pn = new PriorityNode();
                                pn.setState(boardSt);
                                pn.setPreviousState(curStateBmp);
                                pn.setDistance(++distance);
                                pn.setMove(mv);
                                pn.setWeight(pgCost);
                                --distance;
                                visitedStates.put(boardSt, pn);


                                priorityQueue.add(pn);

                            }
                        }
                    }
                }
            if (!priorityQueue.isEmpty())
                currentState = PuzzleBoard.getBitBoard(priorityQueue.peek().getState());
        }
        if (isFinalState(currentState) && !priorityQueue.isEmpty()) {
            long st = currentState.bitBoard();
            while (visitedStates.get(st).getPreviousState() != -1) {
                PriorityNode pn = visitedStates.get(st);
                assert pn != null;
                this.moves.push(pn.getMove());
                st = this.visitedStates.get(st).getPreviousState();
            }
        }
        return isFinalState(currentState);

    }

    private boolean validMove(int x, int y, int dx, int dy) {
        int stepx = x + dx / 2, stepy = y + dy / 2,
                jumpx = x + dx, jumpy = y + dy;
        if (PuzzleBoard.invalidPosition(stepx, stepy) || PuzzleBoard.invalidPosition(jumpx, jumpy))
            return false;

        Value intValue = puzzleBoard.get(stepx, stepy);
        Value destValue = puzzleBoard.get(jumpx, jumpy);
        if (intValue == Value.NOT_ON_PUZZLE || intValue == Value.EMPTY ||
                destValue == Value.NOT_ON_PUZZLE || destValue != Value.EMPTY)
            return false;
        return true;
    }

    private boolean isFinalState(PuzzleBoard state) {
        int pegCount = 0;
        for (int i = 0; i < PuzzleBoard.SIZE; i++)
            for (int j = 0; j < PuzzleBoard.SIZE; j++)
                if (state.get(i, j) == Value.STICK)
                    pegCount++;
        if (1 == pegCount)
            return true;
        return false;
    }

    private int getCost() {
        return Heuristics.weightedCost(puzzleBoard);
    }

    public List<Move> getMoves() {
        LinkedList<Move> moves = new LinkedList<>(this.moves);
        return moves;
    }

}
