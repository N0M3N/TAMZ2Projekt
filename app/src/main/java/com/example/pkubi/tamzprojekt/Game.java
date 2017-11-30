package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TextView;

public class Game extends Activity {
    private Board Board;
    private Player BlackPlayer;
    private Player WhitePlayer;
    public static CellState onTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        this.onTurn = CellState.BLACK;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String load = extras.getString("load");
        int size = extras.getInt("size");

        // TODO: Load data
        TextView blackplayerName = findViewById(R.id.blackPlayerNameView);
        TextView whitePlayerName = findViewById(R.id.whitePlayerNameView);

        Board = new Board(size, findViewById(R.id.BoardLayout), load);
        BlackPlayer = new Player(extras.getString("blackName"), false);
        WhitePlayer = new Player(extras.getString("whiteName"), true);

        blackplayerName.setText(BlackPlayer.getName());
        whitePlayerName.setText(WhitePlayer.getName());

    }
}
