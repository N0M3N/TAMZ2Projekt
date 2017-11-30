package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "load button pressed", Toast.LENGTH_SHORT).show());

        findViewById(R.id.newGameButton).setOnClickListener(v -> startActivity(new Intent(this.getApplicationContext(), NewGame.class)));
    }
}
