package com.example.pkubi.tamzprojekt;

import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Board {
    private static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;

    private final int size;
    private Game game;
    private BoardCell BOARD[][];

    CellState getPlayerOnTurn(){
        return game.getPlayerOnTurn();
    }

    Board(Game game, int size, LinearLayout board, String load){
        // TODO: Read load?
        this.game = game;
        BOARD = new BoardCell[size][size];
        this.size = size;
        int cellSize = board.getLayoutParams().width/(size+1);

        GridLayout grid = new GridLayout(board.getContext());
        if(size==9)
            grid.setBackground(board.getResources().getDrawable(R.drawable.board9));
        else if(size==13)
            grid.setBackground(board.getResources().getDrawable(R.drawable.board13));
        else
            grid.setBackground(board.getResources().getDrawable(R.drawable.board19));

        grid.setPadding(cellSize/2, cellSize/2, cellSize/2, cellSize/2);
        grid.setColumnCount(size);
        grid.setRowCount(size);
        for(int y = 0 ; y < size; y++){
            for(int x = 0; x < size; x++){
                BoardCell cell = new BoardCell(x, y, cellSize , CellState.EMPTY,board.getContext(), this);
                BOARD[x][y] = cell;
                grid.addView(cell, cellSize , cellSize);
            }
        }
        board.addView(grid);
    }

    public Board(Save savedGame, int size){
        this.size = size;
        // TODO: load data
    }

    private boolean hasLiberty(BoardCell cell, CellState enemy){
        if(cell == null) // end of board
            return false;
        if(cell.getState()==enemy || cell.Checked){ // enemy cell or checked
            return false;
        }
        if(cell.getState()==CellState.EMPTY){ // empty cell
            return true;
        }
        // ally
        cell.Checked = true;

        BoardCell Top = cell.posY-1>=0 ? BOARD[cell.posX][cell.posY-1] : null;
        BoardCell Right = (cell.posX+1 < size) ? BOARD[cell.posX+1][cell.posY] : null;
        BoardCell Bottom = (cell.posY+1 < size) ? BOARD[cell.posX][cell.posY+1] : null;
        BoardCell Left = (cell.posX-1 >= 0) ? BOARD[cell.posX-1][cell.posY] : null;

        return hasLiberty(Top, enemy) || hasLiberty(Right, enemy) || hasLiberty(Bottom, enemy) || hasLiberty(Left, enemy);
    }

    public void checkBoard(int x, int y){
        BoardCell cell = BOARD[x][y];
        BoardCell Neighbours[] = getNeighbours(cell);
        CellState enemy = cell.getState()==CellState.BLACK?CellState.WHITE:CellState.BLACK;

        double score = 0;
        double enemyScore = 0;

        for(int i = 0; i < 4; i++){ // check stones from all sides
            if(Neighbours[i]!=null && Neighbours[i].getState()==enemy) { // if neighbour is enemy
                if(!hasLiberty(Neighbours[i], cell.getState())){ // and doesn't have empty space
                    score += removeStones(Neighbours[i], enemy); // remove all his stones and give me points
                }
            }
        }

        if(!hasLiberty(cell, enemy)){ // recently placed stone does not have a free space around self or ally
            enemyScore = removeStones(cell, cell.getState());
        }

        game.endOfTurn(score, enemyScore);
        cleanBoard();
    }

    /**
     * @param cell of which neighbouting stones should be removed
     * @param state color of stones which are being removed
     * @return number of stones which were removed
     */
    private double removeStones(BoardCell cell, CellState state) {
        BoardCell Neighbours[] = getNeighbours(cell);
        double score = 1; // 1 point for cell
        cell.setEmpty(); // remove stone from cell (i don't want an infinity loop)

        for(int i =0; i < 4; i++){ // check every side of cell
            if(Neighbours[i]!=null && Neighbours[i].getState()==state){ // if neighbour is same state as cell is
                score += removeStones(Neighbours[i], state); // remove that stone all his neighbours and give me points
            }
        }
        return score;
    }

    /**
     * Get neighbours of cell
     * @param cell which's neighbours to find
     * @return array of Top, Right, Bottom and Left neighbours cells
     */
    private BoardCell[] getNeighbours(BoardCell cell){
        BoardCell ret[] = new BoardCell[4];

        ret[TOP] = cell.posY-1>=0 ? BOARD[cell.posX][cell.posY-1] : null;
        ret[RIGHT] = (cell.posX+1 < size) ? BOARD[cell.posX+1][cell.posY] : null;
        ret[BOTTOM] = (cell.posY+1 < size) ? BOARD[cell.posX][cell.posY+1] : null;
        ret[LEFT] = (cell.posX-1 >= 0) ? BOARD[cell.posX-1][cell.posY] : null;

        return ret;
    }


    private void cleanBoard(){
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                BOARD[x][y].Checked = false;
            }
        }
    }

    int getSize() {
        return size;
    }

    BoardCell[][] getBoard() {
        return BOARD;
    }
}
