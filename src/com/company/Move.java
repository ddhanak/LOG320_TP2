package com.company;

public class Move implements Comparable<Move> {
    private static final int[][] WEIGHTS = {
            {6,5,4,3,4,5,6},
            {5,4,3,2,3,4,5},
            {4,3,2,1,2,3,4},
            {3,2,1,0,1,2,3},
            {4,3,2,1,2,3,4},
            {5,4,3,2,3,4,5},
            {6,5,4,3,4,5,6}};

    public Position start;
    public Position end;
    public Position jumpedOver;
    public Integer weight;

    public Move(Position start, Position jumpedOver, Position end) {
        this.start = start;
        this.jumpedOver = jumpedOver;
        this.end = end;
        this.weight = WEIGHTS[end.x][end.y] - WEIGHTS[jumpedOver.x][jumpedOver.y] - WEIGHTS[start.x][start.y];
    }

    @Override
    public int compareTo(Move o) {
        return weight.compareTo(o.weight);
    }
}
