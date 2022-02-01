package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private RadioButton radiobtn1, radiobtn2, radiobtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        radiobtn1 = findViewById(R.id.radioBtn1);
        radiobtn2 = findViewById(R.id.radioBtn2);
        radiobtn3 = findViewById(R.id.radioBtn3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                if (!radiobtn1.isChecked() && !radiobtn2.isChecked() && !radiobtn3.isChecked())
                {
                    Snackbar.make(v, "Please select a number of digits.", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (radiobtn1.isChecked())
                    {
                        intent.putExtra("one", true);
                    }
                    if (radiobtn2.isChecked())
                    {
                        intent.putExtra("two", true);
                    }
                    if (radiobtn3.isChecked())
                    {
                        intent.putExtra("three", true);
                    }

                    startActivity(intent);
                }
            }
        });

    }
}