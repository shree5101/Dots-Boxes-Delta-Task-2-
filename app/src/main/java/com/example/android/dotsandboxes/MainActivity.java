package com.example.android.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText rowsInput;
    EditText columnsInput;
    Button startGame;
    public static int inputRows;
    public static int inputColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowsInput = findViewById(R.id.rows_edit_text_view);
        columnsInput = findViewById(R.id.column_edit_text_view);
        startGame = findViewById(R.id.start_game_button);

        startGame.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                try {
                    inputRows = Integer.parseInt(rowsInput.getText().toString());
                    inputColumns = Integer.parseInt(columnsInput.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Rows and Columns!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                gameIntent.putExtra("input_rows", inputRows);
                gameIntent.putExtra("input_columns", inputColumns);
                startActivity(gameIntent);
            }
        });
    }
}
