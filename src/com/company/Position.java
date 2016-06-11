package com.company;

/**
 * Représente une position (ou case) sur le plateau
 */
public class Position {

    public Position(int x, int y) {
        X = x;
        Y = y;
    }
    public Position(int x, int y, int v) {
        X = x;
        Y = y;
        V = v;
    }

    public int X;
    public int Y;
    public int V;

    @Override
    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}
