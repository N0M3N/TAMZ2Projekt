package com.example.pkubi.tamzprojekt;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Board {

    public static int size;
    private BoardCell BOARD[][];

    public Board(int size, LinearLayout board, String load){
        // TODO: Read load?

        BOARD = new BoardCell[size][size];
        this.size = size;

        for(int y = 0 ; y < size; y++){
            LinearLayout row = new LinearLayout(board.getContext());
            row.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            for(int x = 0; x < size; x++){
                BoardCell cell = new BoardCell(x, y, board.getWidth()/size, CellState.EMPTY, row.getContext());
                BOARD[x][y] = cell;
                row.addView(cell, x);
            }
            board.addView(row, y);
        }
    }

    public Board(String loadData){
        InitGame(loadData);
    }

    private void InitGame(String loadData) {
        // Todo: load data
    }

    private void placeStone(int x, int y){
        // Todo: place stone of #player to #x, #y coordinates
    }
}
