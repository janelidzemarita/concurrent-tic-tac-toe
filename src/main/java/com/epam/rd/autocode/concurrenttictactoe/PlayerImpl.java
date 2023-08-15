package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player {

    final TicTacToe ticTacToe;
    final char mark;
    PlayerStrategy strategy;

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy=strategy;
    }

    @Override
    public boolean wonBoard(char[][] table) {
        final char xM='X';
        final char oM='O';

        for(int i=0; i<=2;i++){
            if(table[0][i]==xM&&table[1][i]==xM&&table[2][i]==xM||table[0][i]==oM&&table[1][i]==oM&&table[2][i]==oM)
                return true;
            if(table[i][0]==xM&&table[i][1]==xM&&table[i][2]==xM||table[i][0]==oM&&table[i][1]==oM&&table[i][2]==oM)
                return true;
        }
        return table[0][0] == xM && table[1][1] == xM && table[2][2] == xM
                || table[0][2] == xM && table[1][1] == xM && table[2][0] == xM
                || table[0][0] == oM && table[1][1] == oM && table[2][2] == oM
                || table[0][2] == oM && table[1][1] == oM && table[2][0] == oM;
    }

    @Override
    public void run() {
        synchronized (ticTacToe) {
            try {
                while (!wonBoard(ticTacToe.table())) {
                    while (mark == ticTacToe.lastMark()) { // if the last placed mark is the same as this player's mark wait for the other thread
                        ticTacToe.wait();
                    }
                    if ((!wonBoard(ticTacToe.table())))
                        ticTacToe.setMark(strategy.computeMove(mark, ticTacToe).row, strategy.computeMove(mark, ticTacToe).column, mark);
                    ticTacToe.notify();
                }  //check if the board has 3 consecutive marks on any line
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}