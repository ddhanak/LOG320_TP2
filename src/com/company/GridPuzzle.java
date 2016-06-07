package com.company;

import java.util.Observable;

/**
 * Created by walid on 2016-06-07.
 */
public class GridPuzzle extends Observable {
    public static final int MAXIMUM = 7;
    private int[][] grid;

    public GridPuzzle(){
        init();
    }

    private void init() {
       this.grid = new int[MAXIMUM][MAXIMUM];
        for(int i =0 ; i<MAXIMUM;i++) {
            for(int j= 0; j<MAXIMUM;j++) {

            }
        }
    }
}
