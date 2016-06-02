package com.company;

import UI.GrillePuzzle;

public class Main {

    public static void main(String[] args) {
        GrillePuzzle gp = new GrillePuzzle("Puzzle");
        Thread t = new Thread(gp);
        t.start();

    }
}
