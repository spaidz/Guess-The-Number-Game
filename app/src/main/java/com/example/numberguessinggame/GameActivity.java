package com.example.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button btnConfirm;
    private EditText guessText;
    private TextView textLast, textRight, textHint;
    Boolean twoDigits, threeDigits, fourDigits;

    Random r = new Random();
    int random;
    int remainingRight = 10;

    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnConfirm = findViewById(R.id.btnConfirm);
        guessText = findViewById(R.id.guessText);
        textLast = findViewById(R.id.textLast);
        textRight = findViewById(R.id.textRight);
        textHint = findViewById(R.id.textHint);

        twoDigits = getIntent().getBooleanExtra("one", false);
        threeDigits = getIntent().getBooleanExtra("two", false);
        fourDigits = getIntent().getBooleanExtra("three", false);

        if (twoDigits)
        {
            random = r.nextInt(90)+10;
        }
        if (threeDigits)
        {
            random = r.nextInt(900)+100;
        }
        if (fourDigits)
        {
            random = r.nextInt(9000)+1000;
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guess = guessText.getText().toString();
                if (guess.equals(""))
                {
                    Toast.makeText(GameActivity.this, "please enter a guess", Toast.LENGTH_LONG).show();

                }
                else {

                    textLast.setVisibility(View.VISIBLE);
                    textRight.setVisibility(View.VISIBLE);
                    textHint.setVisibility(View.VISIBLE);

                    userAttempts++;
                    remainingRight--;

                    int userGuess = Integer.parseInt(guess);
                    guessesList.add(userGuess);

                    textLast.setText("Your last guess is : " +guess);
                    textRight.setText("Your remaining chances : " + remainingRight);

                    if (random == userGuess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations, My guess was " + random
                        + "\n\n you guessed my number in " + userAttempts
                        + "attempts. \n\n your guesses : " + guessesList
                        + "\n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();


                    }
                    if (random < userGuess)
                    {
                        textHint.setText("Decrease your guess");
                    }
                    if (random > userGuess)
                    {
                        textHint.setText("Increase your guess");
                    }
                    if (remainingRight == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, your chances are over."
                                + "\n\n My guess was " + random
                                + "\n\n your guesses : " + guessesList
                                + "\n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();
                    }

                    guessText.setText("");

                }


            }
        });

    }
}