package com.company;

/**
 * Created by deep on 2016-06-12.
 */
public class PriorityNode {

    private int cost;
    private int distance;
    private Move m_move;

    public Move getMove() {
        return m_move;
    }

    public void setMove(Move mv) {
        m_move = mv;
    }

    @Override
    public String toString() {
        return "PriorityNode [cost=" + cost + ", distance=" + distance
                + ", state=" + Board.getBoard(state) + "]";
    }

    private long state;
    private long m_prevState;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getPrevState() {
        return m_prevState;
    }

    public void setPrevState(long m_prevState) {
        this.m_prevState = m_prevState;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
