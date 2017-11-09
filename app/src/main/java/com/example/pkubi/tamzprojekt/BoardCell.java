package com.example.pkubi.tamzprojekt;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class BoardCell implements IBoardCell {
    private int posX, posY;
    private CellState state;

    public BoardCell(int x, int y){
        posX = x;
        posY = y;
    }

    public BoardCell(int x, int y, CellState state){
        posX = x;
        posY = y;
        this.state = state;
    }
}
