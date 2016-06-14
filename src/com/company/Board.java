package com.company;

import java.util.*;

/**
 * Created by walid on 2016-06-07.
 */
public class Board {

    private static final Map<Integer, Integer> s_posIndex = new HashMap<Integer, Integer>();

    static {
        int idx = 0;
        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                if (!invalidPos(i, j))
                    s_posIndex.put(i * Board.SIZE + j, idx++);
    }

    public static int getPosIndex(int x, int y) {
        return s_posIndex.get(x * Board.SIZE + y);
    }

    public Hole get(int i, int j) {
        return m_board[i][j];
    }

    public void set(int i, int j, Hole h) {
        m_board[i][j] = h;
    }

    private Hole[][] m_board = new Hole[SIZE][SIZE];

    public long bitMap() {
        long bitMap = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bitMap |= m_board[i][j].bit();
                if (j != SIZE - 1 || i != SIZE - 1) bitMap <<= 1;
            }
        }
        return bitMap;
    }


    public static Board getBoard(long bitMap) {
        Board brd = new Board();

        for (int i = 0; i < SIZE * SIZE; i++) {
            int idx = SIZE * SIZE - 1 - i;
            int bit = (int) (bitMap & 1);
            int x = idx / SIZE;
            int y = idx % SIZE;
            brd.m_board[x][y] = ((bit != 0) ? Hole.PEG : (Board.invalidPos(x, y) ? Hole.INVALID : Hole.EMPTY));
            bitMap >>= 1;
        }
        return brd;
    }

    private List<Long> getRotateConfigs() {
        List<Long> rotations = new ArrayList<Long>(3);
        for (int i = 0; i < 3; i++) {
            rotate();
            rotations.add(bitMap());
        }
        rotate();
        return rotations;
    }

    private void rotate() {
        for (int i = 0; i < (SIZE) / 2; i++) {
            for (int j = 0; j <= (SIZE - 1) / 2; j++) {

                Hole temp = m_board[i][j];
                m_board[i][j] = m_board[SIZE - j - 1][i];
                m_board[SIZE - j - 1][i] = m_board[SIZE - i - 1][SIZE - j - 1];
                m_board[SIZE - i - 1][SIZE - j - 1] = m_board[j][SIZE - i - 1];
                m_board[j][SIZE - i - 1] = temp;
            }
        }
    }

    private void verticalReflect() {
        for (int j = 0; j < SIZE / 2; j++)
            for (int i = 0; i < SIZE; i++) {
                Hole temp = m_board[i][j];
                m_board[i][j] = m_board[i][SIZE - j - 1];
                m_board[i][SIZE - j - 1] = temp;
            }
    }

    public List<Long> getSymmetricConfigs() {
        List<Long> configs = new ArrayList<Long>();
        configs.add(bitMap());
        List<Long> rotations = getRotateConfigs();
        configs.addAll(rotations);
        verticalReflect();
        configs.add(bitMap());
        List<Long> reflectRotations = getRotateConfigs();
        configs.addAll(reflectRotations);
        verticalReflect();
        return configs;

    }


    public static final int SIZE = 7;

    private Board(Hole[][] slots) {
        m_board = slots;
    }

    private Board() {

    }


    public static Board getBoard(List<String> boardList) {
        Hole[][] arrayBoard = new Hole[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            char[] row = boardList.get(i).toCharArray();
            for (int j = 0; j < SIZE; j++) {
                arrayBoard[i][j] = Hole.fromChar(row[j]);
            }
        }
        return new Board(arrayBoard);
    }

    public void move(Move movement) {
        move(movement.fromx(), movement.fromy(), movement.dx(), movement.dy());
    }

    public void move(int x, int y, int dx, int dy) {
        m_board[x][y] = Hole.EMPTY;
        m_board[x + dx / 2][y + dy / 2] = Hole.EMPTY;
        m_board[x + dx][y + dy] = Hole.PEG;
    }


    public Board copyBoard() {
        Board newBoard = new Board();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newBoard.set(i, j, this.get(i, j));
        return newBoard;
    }

    public static boolean invalidPos(int stepx, int stepy) {
        return (stepx < 0 || stepx >= Board.SIZE || stepy < 0 || stepy >= Board.SIZE)
                || ((stepx < 2 && stepy < 2) || (stepx < 2 && stepy > 4)
                || (stepx > 4 && stepy < 2) || (stepx > 4 && stepy > 4));
    }
}


