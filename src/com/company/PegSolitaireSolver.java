package com.company;

import java.util.*;

/**
 * Created by deep on 2016-06-12.
 */
public class PegSolitaireSolver {

    private Heuristic m_heuristic = Heuristic.WEIGHTED_COST;

    private long m_timeTaken;

    public void setHeuristic(Heuristic heuristic) {
        m_heuristic = heuristic;
    }

    private Board m_board;
    private Map<Long, PriorityNode> m_visitedStates = new HashMap<>();
    private LinkedList<Move> m_moves = new LinkedList<>();
    private static CostComparator m_cc = new CostComparator();
    private PriorityQueue<PriorityNode> m_priorityQueue = new PriorityQueue<>(15, m_cc);
    public int m_numExpandedStates = 0;

    public PegSolitaireSolver(Board board) {
        m_board = board;
    }

    int[][] deltas = new int[][]{{-2, 0}, {2, 0}, {0, -2}, {0, 2}};

    public enum Heuristic {
        WEIGHTED_COST {
            public String toString() {
                return ("Weighted Position Heuristic");
            }
        }
    }

    private int getCost() {
        switch (m_heuristic) {
            case WEIGHTED_COST:
                return Heuristics.weightedCost(m_board);
        }
        return 0;
    }

    boolean aStar() {
        long startTime = System.currentTimeMillis();
        try {
            m_numExpandedStates = 0;
            int distance;
            PriorityNode root = new PriorityNode();
            root.setCost(getCost());
            root.setState(m_board.bitMap());
            root.setPrevState(-1);
            m_priorityQueue.add(root);
            Board currentState = m_board;

            for (Long st : m_board.getSymmetricConfigs())
                m_visitedStates.put(st, root);

            int prevX = 0;
            int prevY = 0;

            root.setMove(new Move(prevX, prevY, prevX, prevY));
            while (!isGoalState(currentState) && !m_priorityQueue.isEmpty()) {

                m_numExpandedStates++;
                long curStateBmp = m_priorityQueue.peek().getState();
                currentState = Board.getBoard(curStateBmp);
                PriorityNode curNode = m_priorityQueue.poll();
                distance = curNode.getDistance();

                List<Integer> xOrder = new ArrayList<>(Board.SIZE);
                List<Integer> yOrder = new ArrayList<>(Board.SIZE);
                List<Integer> deltaOrder = new ArrayList<>(4);
                int idxx = prevX;

                for (int i = 0; i < Board.SIZE; i++)
                    xOrder.add(idxx++ % Board.SIZE);

                int idxy = prevY;

                for (int i = 0; i < Board.SIZE; i++)
                    yOrder.add(idxy++ % Board.SIZE);

                int idx = 0;
                for (int i = 0; i < 4; i++)
                    deltaOrder.add(idx++);


                for (int x : xOrder)
                    for (int y : yOrder) {
                        if (currentState.get(x, y) == Hole.PEG) {
                            for (int index : deltaOrder) {
                                m_board = currentState.copyBoard();
                                int dx = deltas[index][0];
                                int dy = deltas[index][1];
                                if (validMove(x, y, dx, dy)) {
                                    Move mv = new Move(x, y, x + dx, y + dy);
                                    m_board.move(mv);

                                    Long boardSt = m_board.bitMap();
                                    if (m_visitedStates.get(boardSt) != null)
                                        continue;
                                    int pgCost = getCost();

                                    PriorityNode pn = new PriorityNode();
                                    pn.setState(boardSt);
                                    pn.setPrevState(curStateBmp);
                                    pn.setDistance(++distance);
                                    pn.setMove(mv);
                                    pn.setCost(pgCost);
                                    --distance;
                                    m_visitedStates.put(boardSt, pn);


                                    m_priorityQueue.add(pn);

                                }
                            }
                        }
                    }
                if (!m_priorityQueue.isEmpty())
                    currentState = Board.getBoard(m_priorityQueue.peek().getState());
            }
            if (isGoalState(currentState) && !m_priorityQueue.isEmpty()) {

                long st = currentState.bitMap();
                while (m_visitedStates.get(st).getPrevState() != -1) {
                    PriorityNode pn = m_visitedStates.get(st);
                    assert pn != null;
                    this.m_moves.push(pn.getMove());
                    st = this.m_visitedStates.get(st).getPrevState();
                }
            }

            return isGoalState(currentState);

        } finally {
            System.out.println("Number of moves :" + this.m_moves.size());
            System.out.println("Number of states expanded :" + this.m_numExpandedStates);
            this.m_timeTaken = System.currentTimeMillis() - startTime;
        }

    }

    private boolean validMove(int x, int y, int dx, int dy) {
        int stepx = x + dx / 2, stepy = y + dy / 2,
                jumpx = x + dx, jumpy = y + dy;
        if (Board.invalidPos(stepx, stepy) || Board.invalidPos(jumpx, jumpy))
            return false;

        Hole intHole = m_board.get(stepx, stepy);
        Hole destHole = m_board.get(jumpx, jumpy);
        if (intHole == Hole.INVALID || intHole == Hole.EMPTY ||
                destHole ==  Hole.INVALID || destHole != Hole.EMPTY)
            return false;
        return true;
    }

    private boolean isGoalState(Board state) {
        int pegCount = 0;
        if (state.get(3, 3) != Hole.PEG)
            return false;
        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                if (state.get(i, j) == Hole.PEG)
                    pegCount++;
        if (1 == pegCount)
            return true;
        return false;
    }

}
