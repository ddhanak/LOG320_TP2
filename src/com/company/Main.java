package com.company;
import UI.GridUIPuzzle;

public class Main {

    public static void main(String[] args) {
        GridUIPuzzle gp = new GridUIPuzzle("Puzzle");
        Thread t = new Thread(gp);
        t.start();

    }
}
