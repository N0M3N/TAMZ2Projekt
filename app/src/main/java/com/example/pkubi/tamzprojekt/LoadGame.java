package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class LoadGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        LinearLayout listOfSaves = findViewById(R.id.listOfSaves);
        File folder = new File(getFilesDir() + "/saves");
        File[] files = folder.listFiles((dir, name) -> {
            if(name.endsWith(".xml"))
                return true;
            else return false;
        });
        if(files == null) {
            TextView empty = new TextView(getApplicationContext());
            empty.setText("No files to load game from.");
            listOfSaves.addView(empty);
        }
        else {
            for (File file : files) {
                TextView line = new TextView(getApplicationContext());
                line.setText(file.getName());
                line.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        return true;
                    }
                });
                listOfSaves.addView(line);
            }
        }
    }
}
