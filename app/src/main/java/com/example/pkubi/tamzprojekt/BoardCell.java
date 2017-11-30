package com.example.pkubi.tamzprojekt;

import android.content.Context;
import android.widget.Button;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class BoardCell extends Button implements IBoardCell  {

    //todo board
    //private final Board board;
    private int posX, posY;
    private CellState state;

    public final int viewId;

    public BoardCell(int posX, int posY, int sizePx, CellState state, Context context) {
        super(context);
        super.setWidth(sizePx);
        super.setHeight(sizePx);
        super.setPadding(0, 0, 0, 0);

        // TODO: Uncomment background
        // super.setBackground(new ColorDrawable(Color.TRANSPARENT));
        this.posX = posX;
        this.posY = posY;
        this.state = state;
        this.viewId = super.getId();

        super.setOnClickListener(v -> {
            if(Game.onTurn == CellState.BLACK)
            {
                v.setBackground(getResources().getDrawable(R.drawable.blackstone));
                this.state = CellState.BLACK;
            }
            else{
                v.setBackground(getResources().getDrawable(R.drawable.whitestone));
                this.state = CellState.WHITE;
            }

        });
    }
}
