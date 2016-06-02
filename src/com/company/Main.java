package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] puzzle = null;

        try {
            puzzle = FileHelper.getPuzzleFromFile("test.puzzle");
        } catch (Exception e) {
            System.out.println("File does not exist");
        }

    }
}
