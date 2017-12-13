package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Game extends Activity {
    private Board Board;
    private Player BlackPlayer;
    private Player WhitePlayer;
    private CellState onTurn;
    private CellState notOnTurn;
    private ImageView onTurnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        this.onTurn = CellState.BLACK;
        this.notOnTurn = CellState.WHITE;
        this.onTurnView = findViewById(R.id.playerOnTurn);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String load = extras.getString("load");
        int size = extras.getInt("size");

        // TODO: Load data
        TextView blackPlayerName = findViewById(R.id.blackPlayerNameView);
        TextView whitePlayerName = findViewById(R.id.whitePlayerNameView);
        TextView blackPlayerScore = findViewById(R.id.blackPlayerScoreView);
        TextView whitePlayerScore = findViewById(R.id.whitePlayerScoreView);
        Board = new Board(this, size, findViewById(R.id.BoardLayout), load);
        BlackPlayer = new Player(extras.getString("blackName"), blackPlayerScore , false);
        WhitePlayer = new Player(extras.getString("whiteName"), whitePlayerScore, true);

        blackPlayerName.setText(BlackPlayer.getName());
        whitePlayerName.setText(WhitePlayer.getName());

        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
            dialog.setTitle("Save game");
            dialog.setMessage("Write your save's name");
            EditText input = new EditText(Game.this);
            dialog.setView(input);
            dialog.setPositiveButton("Save", (dialog1, which) -> {
                Save save = new Save(
                        whitePlayerName.getText().toString(),
                        blackPlayerName.getText().toString(),
                        Double.parseDouble(whitePlayerScore.getText().toString()),
                        Double.parseDouble(blackPlayerScore.getText().toString()),
                        this.Board.getSize(),
                        this.Board.getBoard(),
                        this.onTurn == CellState.WHITE);
                if (save.Save(getApplicationContext(), input.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Game Saved", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Save Failed", Toast.LENGTH_SHORT).show();
            });
            dialog.show();
        });
    }

    public CellState getPlayerOnTurn(){
        return onTurn;
    }

    private void addPlayerScore(CellState player, double points){
        if(player == CellState.BLACK)
            BlackPlayer.addScore(points);
        else
            WhitePlayer.addScore(points);
    }

    private void nextPlayer() {
        CellState tmp = notOnTurn;
        notOnTurn = onTurn;
        onTurn = tmp;

        onTurnView.setImageDrawable(getDrawable(this.onTurn==CellState.WHITE?R.drawable.whitestone:R.drawable.blackstone));
    }

    public void endOfTurn(double score, double enemyScore) {
        addPlayerScore(onTurn, score);
        addPlayerScore(notOnTurn, enemyScore);
        nextPlayer();
    }
}
