package com.example.pkubi.tamzprojekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by pkubi on 02-Dec-17.
 */

public class Save {
    String WhitePlayerName;
    String BlackPlayerName;
    double WhitePlayerScore;
    double BlackPlayerScore;
    int Size;
    int WhiteStones[][];
    int BlackStones[][];
    boolean WhiteOnTurn;

    public Save(
            String whiteName,
            String blackName,
            double whiteScore,
            double blackScore,
            int size,
            BoardCell[][] board,
            boolean whiteOnTurn)
    {
        WhitePlayerName = whiteName;
        BlackPlayerName = blackName;
        WhitePlayerScore = whiteScore;
        BlackPlayerScore = blackScore;
        Size = size;
        WhiteOnTurn = whiteOnTurn;

        ArrayList<int[]> whiteStonesList = new ArrayList<>();
        ArrayList<int[]> blackStonesList = new ArrayList<>();

        for(int y = 0; y < Size; y++){
            for(int x = 0; x < Size; x++){
                if(board[x][y].getState()==CellState.WHITE){
                    whiteStonesList.add(new int[]{x, y});
                }
                else if(board[x][y].getState()==CellState.BLACK){
                    blackStonesList.add(new int[]{x, y});
                }
            }
        }
        WhiteStones = new int[whiteStonesList.size()][2];
        for(int i = 0; i< whiteStonesList.size(); i++){
            WhiteStones[i] = whiteStonesList.get(i);
        }
        BlackStones = new int[blackStonesList.size()][2];
        for(int i = 0; i < blackStonesList.size(); i++){
            BlackStones[i] = blackStonesList.get(i);
        }
    }

    public Save(String data){
        FromJson(data);
    }

    private void FromJson(String data){
        Gson gson = new Gson();
        Save save = gson.fromJson(data, this.getClass());
        this.BlackPlayerScore = save.BlackPlayerScore;
        this.WhitePlayerScore = save.WhitePlayerScore;
        this.BlackPlayerName = save.BlackPlayerName;
        this.WhitePlayerName = save.WhitePlayerName;
        this.WhiteStones = save.WhiteStones;
        this.BlackStones = save.BlackStones;
        this.WhiteOnTurn = save.WhiteOnTurn;
        this.Size = save.Size;
    }

    private String ToJson(){
        Gson gson = new Gson();
        return gson.toJson(this).toString();
    }

    private boolean SaveToDB(Context context, String name){
        DBHelper dbHelper = new DBHelper(context);
        return dbHelper.insertSave(name, this.ToJson());
    }

    public boolean Save(Context applicationContext, String saveName) {
        return this.SaveToDB(applicationContext, saveName);
    }
}
