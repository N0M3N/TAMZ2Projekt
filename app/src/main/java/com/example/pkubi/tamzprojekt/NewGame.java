package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Button StartButton = findViewById(R.id.StartButton);
        StartButton.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), Game.class);
            RadioGroup sizesRG = findViewById(R.id.sizeSelection);
            RadioButton sizeRB = findViewById(sizesRG.getCheckedRadioButtonId());
            int size = Integer.parseInt(sizeRB.getText().toString().substring(0, 2));
            intent.putExtra("Size", size);
        });
    }
}
