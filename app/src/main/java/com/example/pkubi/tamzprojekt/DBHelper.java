package com.example.pkubi.tamzprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by pkubi on 10-Dec-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    // DB NAME
    public static final String DATABASE_NAME = "saves.db";

    // TABLE NAME
    public static final String TABLE_NAME = "save";

    // COLUMNS NAMES
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String DATA_COLUMN = "data";

    public static ArrayList<SaveDescription> arrayList = new ArrayList<>();
    // QUERIES
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ( "
            + ID_COLUMN + " INTEGER PRIMARY KEY,"
            + NAME_COLUMN + " TEXT,"
            + DATA_COLUMN + " TEXT" + ")";
    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean insertSave(String name, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN, name);
        cv.put(DATA_COLUMN, data);
        db.insert(TABLE_NAME, null, cv);
        return true;
    }

    public String getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = " + id, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(DATA_COLUMN));
    }

    public void setAllSaveNames(){
        arrayList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false){
            arrayList.add(new SaveDescription(res.getString(res.getColumnIndex(NAME_COLUMN)), res.getInt(res.getColumnIndex(ID_COLUMN))));
            res.moveToNext();
        }
    }

    public ArrayList<SaveDescription> getAllSavesName(){
        return arrayList;
    }

    public void removeAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "1", null);
    }

    public boolean remove(int id){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, ID_COLUMN + " = " + id, null);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
