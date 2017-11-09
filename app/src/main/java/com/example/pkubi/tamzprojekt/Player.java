package com.example.pkubi.tamzprojekt;

/**
 * Created by pkubi on 09-Nov-17.
 */

public class Player {
    private String Name;
    public String getName() { return Name; }

    private double Score;
    public double getScore(){ return Score; }
    public void addScore(double points){ Score+=points; }
    public void setScore(double score) { Score = score; }

    public Player(String name, boolean white){
        Name = name;
        Score = white? 5.5 : 0;
    }
}
