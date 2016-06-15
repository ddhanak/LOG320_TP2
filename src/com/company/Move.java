package com.company;

public class Move {

    public Position start;
    public Position end;
    public Position jumpedOver;

    public Move(Position start, Position jumpedOver, Position end) {
        this.start = start;
        this.jumpedOver = jumpedOver;
        this.end = end;
    }
}
