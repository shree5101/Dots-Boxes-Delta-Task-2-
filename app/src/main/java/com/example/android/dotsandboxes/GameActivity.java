package com.example.android.dotsandboxes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    //Inflating objects and Layout.
    RelativeLayout gameRelativeLayout;

    //Declaring variables.
    float x_touched, y_touched;
    public static int count = 1;
    //Declaring line and player win arrays.
    public static int[][] horizontal_lines;
    public static int[][] vertical_lines;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //Finding and assigning layouts to the views
        gameRelativeLayout = findViewById(R.id.game_relative_layout);

        count = 0;

        //Assigning sizes to the array
        horizontal_lines = new int[MainActivity.inputWidth - 1][MainActivity.inputHeight];  //[2][3]
        vertical_lines = new int[MainActivity.inputHeight - 1][MainActivity.inputWidth];    //[2][3]

        gameRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Finding x and y co-ordinates of touch.
                x_touched = event.getX();
                y_touched = event.getY();

                Lines lines = new Lines(GameActivity.this,  //Inflating Line objects and sending parameters.
                        gameRelativeLayout, x_touched, y_touched, MainActivity.inputHeight, MainActivity.inputWidth);
                gameRelativeLayout.addView(lines);  //adding lines to game view.
//                playerScore1.setText(String.valueOf(players_win[0]));
//                playerScore2.setText(String.valueOf(players_win[1]));
                return false;   //false is returned because we are just taking the touch coordinates
                //but we don't want to use any touch functionality.
            }
        });

        DotsAndBoxes boardView = new DotsAndBoxes(GameActivity.this,    //Inflating Dots object and sending parameters.
                gameRelativeLayout,
                MainActivity.inputHeight,
                MainActivity.inputWidth);
        gameRelativeLayout.addView(boardView);  //Adding the object to the game view.
    }
}

