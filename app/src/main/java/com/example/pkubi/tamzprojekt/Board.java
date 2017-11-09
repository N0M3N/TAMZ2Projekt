package com.example.pkubi.tamzprojekt;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Board {
    private int Size;
    private BoardCell[][] Cells;

    public Board(int size){
        Size = size;
        init();
    }

    public Board(int size, String loadData){
        Size = size;
        init(loadData);
    }

    private void init(){
        Cells = new BoardCell[Size][Size];
        for(int y = 0; y < Size; y++){
            for(int x = 0; x < Size; x++){
                Cells[x][y] = new BoardCell(x, y, CellState.EMPTY);
            }
        }
    }

    private void init(String loadData){
        Cells = new BoardCell[Size][Size];
    }

}
