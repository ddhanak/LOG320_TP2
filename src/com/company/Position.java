package com.company;

/**
 * Représente une position (ou case) sur le plateau
 */
public class Position {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }



    public int x;
    public int y;
    public int v;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
