package com.example.pkubi.tamzprojekt;

import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Player {
    private String Name;
    private double Score;
    private TextView scoreBoard;

    public String getName() {
        return Name;
    }
    public double getScore() {
        return Score;
    }

    public void addScore(double points) {
        Score += points;
        scoreBoard.setText(String.valueOf(Score));
    }

    public Player(String name, TextView scoreBoard, boolean white) {
        this.scoreBoard = scoreBoard;
        Name = name;
        Score = white ? 7.5 : 0;
        addScore(0);
    }
}
