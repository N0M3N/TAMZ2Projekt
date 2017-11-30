package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_game);
        Button button = findViewById(R.id.button);
        String[] items = {"9x9", "13x13", "19x19"};
        Spinner spinner = findViewById(R.id.boardSizeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);

        button.setOnClickListener((v) -> {
            EditText whiteNameTE = findViewById(R.id.whitePlayerName);
            EditText blackNameTE = findViewById(R.id.blackPlayerName);
            Intent intent = new Intent(v.getContext(), Game.class);
            Bundle extras = new Bundle();
            extras.putString("whiteName", whiteNameTE.getText().toString());
            extras.putString("blackName", blackNameTE.getText().toString());
            extras.putInt("size", spinner.getSelectedItemPosition()==0?9:spinner.getSelectedItemPosition()==1?13:19);
            extras.putString("load", null);
            intent.putExtras(extras);
            startActivity(intent);
        });

    }
}
