package com.example.pkubi.tamzprojekt;

import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Board {

    private int size;
    private BoardCell BOARD[][];

    Board(int size, LinearLayout board, String load){
        // TODO: Read load?

        BOARD = new BoardCell[size][size];
        this.size = size;
        int cellSize = board.getLayoutParams().width/(size+1);

        GridLayout grid = new GridLayout(board.getContext());
        if(size==9)
            grid.setBackground(board.getResources().getDrawable(R.drawable.board6));
        else if(size==13)
            grid.setBackground(board.getResources().getDrawable(R.drawable.board13));
        else
            grid.setBackground(board.getResources().getDrawable(R.drawable.board19));

        grid.setPadding(cellSize/2, cellSize/2, cellSize/2, cellSize/2);
        grid.setColumnCount(size);
        grid.setRowCount(size);
        for(int y = 0 ; y < size; y++){
            for(int x = 0; x < size; x++){
                BoardCell cell = new BoardCell(x, y, cellSize , CellState.EMPTY, board.getContext());
                BOARD[x][y] = cell;
                grid.addView(cell, cellSize , cellSize);
            }
        }
        board.addView(grid);
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
