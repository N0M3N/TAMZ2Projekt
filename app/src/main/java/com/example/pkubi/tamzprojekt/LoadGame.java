package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class LoadGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load_game);

        ListView listOfSaves = findViewById(R.id.listOfSaves);
        String[] array = (String[]) getLoads();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_load_game, array);
        listOfSaves.setAdapter(adapter);
    }

    private String[] getLoads(){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        return dbHelper.GetSaves();
    }
}
