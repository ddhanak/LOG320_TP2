package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Equipe on 2016-06-07.
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

    public boolean isGridValid(){
        int[][]puzzle = this.grid;

        ArrayList<Integer> invalidPositions = new ArrayList<Integer>();
        invalidPositions.add(0);
        invalidPositions.add(1);
        invalidPositions.add(5);
        invalidPositions.add(6);
        invalidPositions.add(7);
        invalidPositions.add(8);
        invalidPositions.add(12);
        invalidPositions.add(13);
        invalidPositions.add(35);
        invalidPositions.add(36);
        invalidPositions.add(40);
        invalidPositions.add(41);
        invalidPositions.add(42);
        invalidPositions.add(43);
        invalidPositions.add(47);
        invalidPositions.add(48);

        int numberOfBlocks = 0;

        for (int i=0;i<puzzle.length;i++){
            for (int j=0;j<puzzle.length;j++){

                if(invalidPositions.contains(numberOfBlocks)){
                    //System.out.println(puzzle[i][j]+" "+invalidPositions.get(numberOfBlocks)+" "+numberOfBlocks);
                    if(puzzle[i][j]!= 0){
                        return false;
                    }
                }
                numberOfBlocks++;
            }
        }

        return true;
    }

    public void printGridToConsole(){

        int[][]puzzle = this.grid;
        int count = 0;

        for (int i=0;i<puzzle.length;i++){

            for (int j=0;j<puzzle.length;j++){
                count++;

                if(count==7){
                    System.out.println(puzzle[i][j]);
                }else{
                    System.out.print(puzzle[i][j]);
                }
            }
            count=0;
        }
    }

}
