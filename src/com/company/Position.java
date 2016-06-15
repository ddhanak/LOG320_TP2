package com.company;

/**
 * Représente une position (ou case) sur le plateau
 */
public class Position {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
