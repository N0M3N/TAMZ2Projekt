package com.example.pkubi.tamzprojekt;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by pkubi on 02-Dec-17.
 */

class PlayerSave implements Serializable {
    String Name;
    double Score;

    PlayerSave(String name, double score){
        Name = name;
        Score = score;
    }
}
class CellSave implements Serializable {
    int X;
    int Y;
    int State; // 0 = empty, 1 = white, 2 = black

    CellSave(int x, int y, int state){
        X = x;
        Y = y;
        State = state;
    }
}
public class Save implements Serializable {
    PlayerSave Players[] = new PlayerSave[2];
    int Size;
    CellSave Board[][];
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
        Players[0] = new PlayerSave(whiteName, whiteScore);
        Players[1] = new PlayerSave(blackName, blackScore);
        Size = size;
        Board = new CellSave[Size][Size];
        WhiteOnTurn = whiteOnTurn;
        for(int y = 0; y < Size; y++){
            for(int x = 0; x < Size; x++){
                Board[x][y] = new CellSave(board[x][y].posX, board[x][y].posY, board[x][y].getState()==CellState.EMPTY?0:board[x][y].getState()==CellState.WHITE?1:2);
            }
        }
    }

    public Save(File file){
        try{
            FileInputStream fileStream = new FileInputStream(file.getPath());
            ObjectInputStream inputStream = new ObjectInputStream(fileStream);
            Save save = (Save) inputStream.readObject();
            inputStream.close();
            fileStream.close();
            this.WhiteOnTurn = save.WhiteOnTurn;
            this.Board = save.Board;
            this.Players = save.Players;
            this.Size = save.Size;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean Serialize(File file){
        try{
            FileOutputStream fileStream = new FileOutputStream(file.getPath(), false);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(this);
            outputStream.close();
            fileStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
