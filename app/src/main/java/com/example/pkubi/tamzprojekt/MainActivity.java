package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "load button pressed", Toast.LENGTH_SHORT).show());

        findViewById(R.id.newGameButton).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NewGame.class)));
    }
}
