package com.log320;

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré par l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************


public enum Value {

    NOT_ON_PUZZLE(0, 0),
    STICK(1, 1),
    EMPTY(2, 0);

    private int value;
    private int bit;

    Value(int value, int bit) {
        this.value = value;
        this.bit = bit;
    }

    static Value holeValue(int c) {
        for(Value v: values())
            if(v.getValue() == c)
                return v;
        return null;
    }

    private int getValue() {
        return value;
    }

    int bit() {
        assert bit != -1;
        return bit;
    }
}