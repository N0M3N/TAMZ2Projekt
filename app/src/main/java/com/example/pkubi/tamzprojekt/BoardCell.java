package com.example.pkubi.tamzprojekt;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class BoardCell extends ImageView {

    private Drawable WhiteStone = getResources().getDrawable(R.drawable.whitestone);
    private Drawable BlackStone = getResources().getDrawable(R.drawable.blackstone);
    private CellState state;

    public final int posX, posY;
    public final int viewId;
    public Boolean Checked = false;

    public BoardCell(int posX, int posY, int sizePx, CellState state, Context context, Board board) {
        super(context);
        super.requestLayout();
        super.setMaxWidth(sizePx);
        super.setMinimumWidth(sizePx);
        super.setMaxHeight(sizePx);
        super.setMinimumHeight(sizePx);
        super.setPadding(0, 0, 0, 0);

        this.posX = posX;
        this.posY = posY;
        this.state = state;
        this.viewId = super.getId();

        if(state == CellState.WHITE)
            super.setImageDrawable(WhiteStone);
        else if (state == CellState.BLACK)
            super.setImageDrawable(BlackStone);

        super.setOnClickListener(v -> {
            if(this.state==CellState.EMPTY) { // can place stone to empty only
                setStone(board.getPlayerOnTurn());
                board.checkBoard(posX, posY);
            }
        });
    }
    public void setStone(CellState state){
        if (state == CellState.BLACK) {
            this.setImageDrawable(BlackStone);
            this.state = CellState.BLACK;
        } else {
            this.setImageDrawable(WhiteStone);
            this.state = CellState.WHITE;
        }
    }

    public void setEmpty(){
        this.state = CellState.EMPTY;
        this.setImageDrawable(null);
    }

    public CellState getState(){
        return this.state;
    }
}
