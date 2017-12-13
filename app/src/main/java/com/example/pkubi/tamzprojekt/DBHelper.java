package com.example.pkubi.tamzprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pkubi on 10-Dec-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    // DB NAME
    private static final String DATABASE_NAME = "saves";

    //DB VERSION
    private static final int DATABASE_VERSION = 1;

    // TABLE NAME
    private static final String TABLE_NAME = "save";

    // COLUMNS NAMES
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DATA_COLUMN = "data";

    // QUERIES
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
            + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_COLUMN + " TEXT NOT NULL,"
            + DATA_COLUMN + "TEXT NOT NULL" + ")";
    private static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // CTOR
    public DBHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    // MIGRATIONS
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    boolean PutSave(String name, String data){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ID_COLUMN + " FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN + " = '" + name + "'", null);
        db = this.getWritableDatabase();
        if(!cursor.moveToFirst()){
            ContentValues values = new ContentValues();
            values.put(NAME_COLUMN, name);
            values.put(DATA_COLUMN, data);
            db.insert(TABLE_NAME, null, values);
        }
        cursor.close();
        return !cursor.moveToFirst();
    }

    String[] GetSaves(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{NAME_COLUMN},null, null, null, null, NAME_COLUMN);
        String[] saves = new String[cursor.getCount()];
        if(cursor.moveToFirst())
            for(int i = 0; i < cursor.getCount(); i++) {
                saves[i] = cursor.getString(i);
                cursor.move(1);
            }
        cursor.close();
        return saves;
    }

    public String GetSave(SQLiteDatabase db, String name){
        String data = null;
        Cursor cursor = db.rawQuery("SELECT " + DATA_COLUMN + " FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN + " = '" + name + "'", null);
        if(cursor.moveToFirst()){
            data = cursor.getString(cursor.getColumnIndex(DATA_COLUMN));
        }
        cursor.close();
        return data;
    }
}
