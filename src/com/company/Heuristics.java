package com.company;

/**
 * Created by deep on 2016-06-12.
 */
public class Heuristics {

    public static int weightedCost(Board m_board) {
        int[][] costMatrix =  new int[][]{
                { 0, 0, 4, 0, 4, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 1, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 4, 0, 4, 0, 0 }};
        return evaluateCostMatrix(m_board, costMatrix);

    }

    public static int evaluateCostMatrix(Board board, int[][] costMatrix ) {
        int pagodaValue = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.get(i, j) == Hole.PEG) {
                    pagodaValue += costMatrix[i][j];

                }
            }
        }
        return (pagodaValue);
    }

}
