package com.log320;

    //**********************************************************
    // Laboratoire #2 : Résolution d'un puzzle
    // CODE EMPRUNTÉ :
    // Ce code a été largement inspiré par l'algorithme partagé par l'utilisateur jagatsastry (github.com/jagatsastry)
    // L'algorithme de jagatsastry utilise deux algorithmes avec des heuristiques différentes (DFS et A*)
    // Nous avons seulement implémenté l'algorithme A* pour l'optimisation.
    // Lien : https://github.com/jagatsastry/peg-solitaire-solver
    //*********************************************************

public class PriorityNode {

    private int weight;
    private int distance;
    private Move move;

    public Move getMove() {
        return move;
    }

    public void setMove(Move mv) {
        move = mv;
    }

    private long state;
    private long previousState;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getPreviousState() {
        return previousState;
    }

    public void setPreviousState(long m_prevState) {
        this.previousState = m_prevState;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
