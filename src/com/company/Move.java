package com.company;

public class Move {

    public Position start;
    public Position end;
    public Position jumpedOver;
    //public Integer weight;

    public Move(Position start, Position jumpedOver, Position end) {
        this.start = start;
        this.jumpedOver = jumpedOver;
        this.end = end;
        //this.weight = WEIGHTS[end.x][end.y] - WEIGHTS[jumpedOver.x][jumpedOver.y] - WEIGHTS[start.x][start.y];
    }

   /* @Override
    public int compareTo(Move o) {
        return weight.compareTo(o.weight);
    }*/
}
