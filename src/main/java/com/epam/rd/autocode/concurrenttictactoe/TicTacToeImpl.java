package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    static final char X_M ='X';
    static final char O_M ='O';
    char prevMark = O_M;

    char[][] board;

    public TicTacToeImpl() {
        newBoard();
    }

    @Override
    public synchronized void setMark(int x, int y, char mark) {
        if(x>2 || y > 2 || (mark != X_M && mark != O_M))
            throw new IllegalArgumentException();
        if (board[x][y] == ' ') {
            board[x][y] = mark;
            prevMark = mark;
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public synchronized char[][] table() {
        return Arrays.stream(board).map(char[]::clone).toArray(char[][]::new); //clone the board to a new char table
    }

    @Override
    public synchronized char lastMark() {
        return prevMark;
    }

    private void newBoard() {
        board= new char[][]{{' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
    }
}