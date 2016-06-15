package com.company;

import java.awt.*;
import java.util.Observable;

/**
 * Created by walid on 2016-06-07.
 */
public class GridPuzzle extends Observable {
    // public static final int MAXIMUM = 7;
    private int[][] grid;



    public GridPuzzle(int[][] grid) {
        this.grid = grid;

    }

    public int getCase(int i, int j) {
        return this.grid[i][j];
    }

    public void setCase(int i, int j, int value) {
        grid[i][j] = value;
        setChanged();
        notifyObservers(new Position(i,j));
    }

    public int getLength(){
        return grid.length;
    }
}
