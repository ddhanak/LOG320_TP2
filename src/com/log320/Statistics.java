package com.log320;

/**
 * Created by deep on 2016-06-14.
 */
public class Statistics {

    public static void printStatistics(PuzzleSolver solver) {

        System.out.println("Nombre de noeuds visités : " + solver.expandedNodes);
        System.out.println("Nombre de coups de la solution : " + solver.getMoves().size());

        for (Move move : solver.getMoves()) {
            System.out.println(move.fromx() + "," + move.fromy() + " to " + move.tox() + "," + move.toy());
        }
    }
}
