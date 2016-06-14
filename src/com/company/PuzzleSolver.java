//package com.company;
//
//import java.util.Observable;
//
//public class PuzzleSolver extends Observable {
//
//    private Board board = new Board();
//    private Board[] solution = new Board[32];
//    private int[] directions = board.getDirections();
//
//    public PuzzleSolver() {
//        for (int i = 0; i < solution.length; i++) {
//            solution[i] = new Board();
//        }
//    }
//
//    public boolean findSolution(int move) {
//        for (int x = 0; move <= 31 && x < board.getWidth(); x++) {
//            for (int y = 0; y < board.getHeight(); y++) {
//                for (int direction : directions) {
//                    if (board.jump(x, y, direction)) {
//                        board.copyBoard(board, solution[move]);
//                        if (!(move >= 31 && board.isOccupied(3, 3))) {
//                            if (findSolution(move + 1)) {
//                                return true;
//                            } else {
//                                board.jumpBack(x, y, direction);
//                            }
//                        } else {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//}
