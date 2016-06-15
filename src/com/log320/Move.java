package com.log320;

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré par l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************

public class Move {
    private int fromx;
    private int fromy;
    private int tox;
    private int toy;

    public Move(int fromx, int fromy, int tox, int toy) {
        this.fromx = fromx;
        this.fromy = fromy;
        this.tox = tox;
        this.toy = toy;
    }

    int fromx() {
        return fromx;
    }

    int fromy() {
        return fromy;
    }

    int tox() {
        return tox;
    }

    int toy() {
        return toy;
    }

    int dx() {
        return tox - fromx;
    }

    int dy() {
        return toy - fromy;
    }
}
