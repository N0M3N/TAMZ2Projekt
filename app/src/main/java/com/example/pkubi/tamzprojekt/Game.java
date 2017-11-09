package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Game extends Activity {
    private int Size;
    private Board Board;
    private Player BlackPlayer;
    private Player WhitePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Size = intent.getIntExtra("Size", 19);
        String load = intent.getStringExtra("Load");

        // TODO: Load data

        if(load==null){
            Board = new Board(Size);
            BlackPlayer = new Player("TodoBlackName", false);// TODO
            WhitePlayer = new Player("TodoWhiteName", true);// TODO
        }
        else {
            Board = new Board(Size, load);
            BlackPlayer = new Player("TodoBlackName", false);// TODO
            WhitePlayer = new Player("TodoWhiteName", true);// TODO
            WhitePlayer.setScore(99.99); // TODO
        }
    }
}
