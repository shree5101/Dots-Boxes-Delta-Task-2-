package com.example.android.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Inflation of objects
    EditText rowsInput;
    EditText columnsInput;
    EditText numberOfPlayers;
    Button startGame;

    //Declaration of global variables
    public static int inputHeight;
    public static int inputWidth;
    public static int inputPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finding and assigning views
        rowsInput = findViewById(R.id.rows_edit_text_view);
        numberOfPlayers = findViewById(R.id.number_of_players_edit_text);
        columnsInput = findViewById(R.id.column_edit_text_view);
        startGame = findViewById(R.id.start_game_button);

        //StartGame button OnClickListener
        startGame.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                //Usage of Try and Catch to prevent crashing on leaving any editText empty.
                try {
                    //To check if no. of players is in between 2 and 5.
                    if (Integer.parseInt(numberOfPlayers.getText().toString()) >= 2 && Integer.parseInt(numberOfPlayers.getText().toString()) <= 5) {
                        //To check if rows and columns are greater than 2.
                        if (Integer.parseInt(rowsInput.getText().toString()) > 2 && Integer.parseInt(columnsInput.getText().toString()) > 2) {
                            //Assigning the variables values from editText.
                            inputHeight = Integer.parseInt(rowsInput.getText().toString());     //let 3.
                            inputWidth = Integer.parseInt(columnsInput.getText().toString());   //let 3.
                            inputPlayers = Integer.parseInt(numberOfPlayers.getText().toString());  //let 2.
                            Log.v("MainActivity", "Input Height = " + inputHeight);
                            Log.v("MainActivity", "Input Width = " + inputWidth);
                            Log.v("MainActivity", "Input Players = " + inputPlayers);
                        } else {
                            Toast.makeText(MainActivity.this, "Rows and Columns must be greater than 2!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "2-5 players allowed!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Rows and Columns!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Creating a new intent and going to GameActivity.
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });
    }
}
