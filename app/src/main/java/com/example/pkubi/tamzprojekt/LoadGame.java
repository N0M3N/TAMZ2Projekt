package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoadGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load_game);

        ListView listOfSaves = findViewById(R.id.listOfSaves);
        ArrayList<SaveDescription> list = getLoads();
        String[] array = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i).name;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listOfSaves.setAdapter(adapter);
        listOfSaves.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), Game.class);
            intent.putExtra("saveID", list.get(position).ID);
            startActivity(intent);
        });
    }

    private ArrayList<SaveDescription> getLoads(){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.setAllSaveNames();
        return dbHelper.getAllSavesName();
    }
}
