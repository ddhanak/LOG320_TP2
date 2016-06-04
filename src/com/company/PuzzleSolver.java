package com.company;

import java.util.ArrayList;
import java.util.Observable;

public class PuzzleSolver extends Observable {

    private int[][] _plateau;

    public PuzzleSolver(int[][] plateau) {
        _plateau = plateau;
    }

    /**
     *  Renvoie les déplacements possibles depuis une position sur le plateau.
     * @param position une position sur le plateau
     */
    public ArrayList<Deplacement> getDeplacementsPossibles(Position position) {
        ArrayList<Deplacement> coupsPossibles = new ArrayList<Deplacement>();



        return coupsPossibles;
    }


}
