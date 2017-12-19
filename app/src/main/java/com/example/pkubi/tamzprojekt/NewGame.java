package com.example.pkubi.tamzprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class NewGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_game);

        RadioGroup radioGroup = findViewById(R.id.SizeGroup);
        radioGroup.check(R.id.radioButton19);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            EditText whiteNameTE = findViewById(R.id.whitePlayerName);
            EditText blackNameTE = findViewById(R.id.blackPlayerName);
            Intent intent = new Intent(v.getContext(), Game.class);
            Bundle extras = new Bundle();
            extras.putString("whiteName", whiteNameTE.getText().toString());
            extras.putString("blackName", blackNameTE.getText().toString());
            RadioButton selectedSize = findViewById(radioGroup.getCheckedRadioButtonId());

            int size = selectedSize.getText().toString().endsWith("19")
                    ? 19
                    : selectedSize.getText().toString().endsWith("13")
                    ? 13
                    : 9;

            extras.putInt("size", size);
            extras.putInt("saveID", 0);
            intent.putExtras(extras);
            startActivity(intent);
        });

    }
}
