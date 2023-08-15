package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    final static char xM='X';
    final static char oM='O';
    char prevMark =oM;

    char[][] board;

    public TicTacToeImpl() {
        newBoard();
    }

    @Override
    synchronized public void setMark(int x, int y, char mark) {
        if(x>2||y>2||(mark!=xM&&mark!=oM))
            throw new IllegalArgumentException();
        if (board[x][y]==' ') {
            board[x][y] = mark;
            prevMark = mark;
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    synchronized public char[][] table() {
        return Arrays.stream(board).map(char[]::clone).toArray(char[][]::new); //clone the board to a new char table
    }

    @Override
    synchronized public char lastMark() {
        return prevMark;
    }

    private void newBoard() {
        board= new char[][]{{' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
    }
}