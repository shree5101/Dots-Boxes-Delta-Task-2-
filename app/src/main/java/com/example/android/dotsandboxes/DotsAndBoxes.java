package com.example.android.dotsandboxes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;


@SuppressLint("ViewConstructor")
public class DotsAndBoxes extends View {

    //Inflation of Relative Layout
    RelativeLayout Game;
    //Inflation of Paint objects
    Paint dotColor, board_background;

    int grid_width, grid_height;

    public DotsAndBoxes(Context context, RelativeLayout relativeLayout, int gridHeight, int gridWidth) {
        super(context);
        Game = relativeLayout;

        dotColor = new Paint();
        board_background = new Paint();
        dotColor.setColor(Color.BLACK);
        board_background.setColor(Color.YELLOW);

        grid_width = gridWidth - 1; // 2
        grid_height = gridHeight - 1; // 2
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (float i = Game.getWidth()/(grid_width + 2);    //For loop for horizontal dots.
             i <= (grid_width + 1)*(Game.getWidth()/(grid_width + 2));
             i += Game.getWidth()/(grid_width + 2)) {
            for (float j = Game.getHeight()/(grid_height + 2);  //For loop for vertical dots.
                 j <= (grid_height + 1)*(Game.getHeight()/(grid_height + 2));
                 j += Game.getHeight()/(grid_height + 2)) {
                canvas.drawCircle(i, j, 12f, dotColor);     //Drawing dot.
            }
        }
    }
}
