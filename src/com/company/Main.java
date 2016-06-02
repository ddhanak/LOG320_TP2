package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int[][] plateau = new int[][]{
                {0,0,1,1,1,0,0},
                {0,0,1,1,1,0,0},
                {1,1,1,1,1,1,1},
                {1,1,1,2,1,1,1},
                {1,1,1,1,1,1,1},
                {0,0,1,1,1,0,0},
                {0,0,1,1,1,0,0}};

        PuzzleSolver solver = new PuzzleSolver(plateau);
    }


}
