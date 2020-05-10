package com.example.android.dotsandboxes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


@SuppressLint("ViewConstructor")
public class Lines extends View {
    RelativeLayout Game;
    Paint[] lineColorArray;
    float x_touched, y_touched;
    int k = 0, l = 0;
    int grid_width, grid_height;
    boolean out = false;

    public Lines(Context context, RelativeLayout relativeLayout, float x_touch, float y_touch, int gridHeight, int gridWidth) {
        super(context);

        Game = relativeLayout;

        x_touched = x_touch;
        y_touched = y_touch;

        init();

        grid_width = gridWidth - 1;     // 2
        grid_height = gridHeight - 1;   // 2
    }

    void init() {
        lineColorArray = new Paint[5];      //Declaring Paint array 0f size five.

        for (int i = 0; i < 5; i++) {       //For loop for adding a paint object in each cell of array.
            lineColorArray[i] = new Paint();
            lineColorArray[i].setStrokeWidth(10);
        }

        //Assigning color to lines for each player.
        lineColorArray[0].setColor(Color.BLUE);
        lineColorArray[1].setColor(Color.RED);
        lineColorArray[2].setColor(Color.YELLOW);
        lineColorArray[3].setColor(Color.GRAY);
        lineColorArray[4].setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (float i = Game.getWidth() / (grid_width + 2);        //For detecting horizontal line touch.
             i <= (grid_width + 1) * (Game.getWidth() / (grid_width + 2));
             i += Game.getWidth() / (grid_width + 2)) {
            for (float j = Game.getHeight() / (grid_height + 2);      //For detecting vertical line touch.
                 j <= (grid_height + 1) * (Game.getHeight() / (grid_height + 2));
                 j += Game.getHeight() / (grid_height + 2)) {
                if (x_touched > Game.getWidth() / (grid_width + 2) && y_touched > Game.getHeight() / (grid_height + 2) - 10) {
                    k = (int) ((i - Game.getWidth() / (grid_width + 2)) / (Game.getWidth() / (grid_width + 2)));
                    Log.v("InGame Horizontal Lines", "k = " + k);
                    l = (int) ((j - Game.getHeight() / (grid_height + 2)) / (Game.getHeight() / (grid_height + 2)));
                    Log.v("InGame Horizontal Lines", "l = " + l);
                    if (x_touched > i && x_touched < i + Game.getWidth() / (grid_width + 2) &&
                            (y_touched > j - 50 && y_touched < j + 50) && (GameActivity.horizontal_lines[k][l] == 0)) {
                        GameActivity.horizontal_lines[k][l] = 1;        // k = 0; l = 0;
                        canvas.drawLine(i, j, i + Game.getWidth() / (grid_width + 2), j,
                                lineColorArray[GameActivity.count % MainActivity.inputPlayers]);    // 0 % 2 = 0 | so lineColorArray[0] is Paint for this line.
                        out = true;
                        if (GameActivity.count >= 0) {
                            if (l != grid_height && l != 0) {       //So that boxes are not made outside.
                                if (GameActivity.horizontal_lines[k][l] == 1 &&     //
                                        GameActivity.horizontal_lines[k][l + 1] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                    if (GameActivity.horizontal_lines[k][l] == 1 &&
                                            GameActivity.horizontal_lines[k][l - 1] == 1 &&
                                            GameActivity.vertical_lines[l - 1][k] == 1 &&
                                            GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                        canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                                j - Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                    }

                                } else if (GameActivity.horizontal_lines[k][l] == 1 &&
                                        GameActivity.horizontal_lines[k][l - 1] == 1 &&
                                        GameActivity.vertical_lines[l - 1][k] == 1 &&
                                        GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                            j - Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                } else {
                                    GameActivity.count++;
                                }
                            } else if (l == grid_height) {
                                if (GameActivity.horizontal_lines[k][l] == 1 &&
                                        GameActivity.horizontal_lines[k][l - 1] == 1 &&
                                        GameActivity.vertical_lines[l - 1][k] == 1 &&
                                        GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                            j - Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);

                                } else {
                                    GameActivity.count++;
                                }
                            } else {
                                if (GameActivity.horizontal_lines[k][l] == 1 &&
                                        GameActivity.horizontal_lines[k][l + 1] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + i + Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                } else {
                                    GameActivity.count++;
                                }
                            }
                        }
                        break;
                    }
                    if (out) {
                        break;
                    }
                }
            }
        }
        for (float i = Game.getWidth() / (grid_width + 2);
             i <= (grid_width + 1) * (Game.getWidth() / (grid_width + 2));
             i += Game.getWidth() / (grid_width + 2)) {
            for (float j = Game.getHeight() / (grid_height + 2);
                 j <= (grid_height + 1) * (Game.getHeight() / (grid_height + 2));
                 j += Game.getHeight() / (grid_height + 2)) {
                if (x_touched > Game.getWidth() / (grid_width + 2) && y_touched > Game.getHeight() / (grid_height + 2) - 10) {
                    k = (int) ((i - Game.getWidth() / (grid_width + 2)) / (Game.getWidth() / (grid_width + 2)));
                    Log.v("InGame Vertical Lines", "k = " + k);
                    l = (int) ((j - Game.getHeight() / (grid_height + 2)) / ((Game.getHeight() / (grid_height + 2))));
                    Log.v("InGame Vertical Lines", "l = " + l);
                    if (y_touched > j && y_touched < j + Game.getHeight() / (grid_height + 2) &&
                            (x_touched > i - 50 && x_touched < i + 50) &&
                            (GameActivity.vertical_lines[l][k] == 0)) {
                        GameActivity.vertical_lines[l][k] = 1;
                        canvas.drawLine(i, j, i, j + Game.getHeight() / (grid_height + 2),
                                lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                        out = true;
                        if (GameActivity.count >= 0) {
                            if (k != grid_width && k != 0) {
                                if (GameActivity.horizontal_lines[k][l] == 1 &&
                                        GameActivity.horizontal_lines[k][l + 1] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                    if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 &&
                                            GameActivity.horizontal_lines[k - 1][l] == 1 &&
                                            GameActivity.vertical_lines[l][k] == 1 &&
                                            GameActivity.vertical_lines[l][k - 1] == 1) {
                                        canvas.drawRect(i, j, i - Game.getWidth() / (grid_width + 2),
                                                j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);

                                    }
                                } else if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 &&
                                        GameActivity.horizontal_lines[k - 1][l] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k - 1] == 1) {
                                    canvas.drawRect(i, j, i - Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);

                                } else {
                                    GameActivity.count++;
                                }
                            } else if (k == grid_width) {
                                if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 &&
                                        GameActivity.horizontal_lines[k - 1][l] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k - 1] == 1) {
                                    canvas.drawRect(i, j, i - Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                } else {
                                    GameActivity.count++;
                                }
                            } else {
                                if (GameActivity.horizontal_lines[k][l] == 1 &&
                                        GameActivity.horizontal_lines[k][l + 1] == 1 &&
                                        GameActivity.vertical_lines[l][k] == 1 &&
                                        GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + Game.getWidth() / (grid_width + 2),
                                            j + Game.getHeight() / (grid_height + 2), lineColorArray[GameActivity.count % MainActivity.inputPlayers]);
                                } else {
                                    GameActivity.count++;
                                }
                            }
                        }
                        break;
                    }
                    if (out) {
                        break;
                    }
                }
            }
        }
    }
}
