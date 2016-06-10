package com.company;

/**
 * Représente une position (ou case) sur le plateau
 */
public class Position {

    public Position(int x, int y) {
        X = x;
        Y = y;
    }

    public int X;
    public int Y;

    @Override
    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}
