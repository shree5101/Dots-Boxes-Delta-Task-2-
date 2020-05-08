package com.example.android.dotsandboxes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class GameCustomView extends View {

    public static final float LINE_WIDTH = 8f;
    private Paint mPaintPoint;
    private Paint mLinePaint;
    private Paint mCellPaint;
    private Rect mCell;
    private float mPointx, mPointy, mPointRadius = 10f;
    private float lineStartx, lineStarty, lineEndx, lineEndy;
    final ArrayList<Dot> dotCoordinates = new ArrayList<>();
    final ArrayList<Cell> cellDimensions = new ArrayList<>();


    public GameCustomView(Context context) {
        super(context);
        init(null);
    }

    public GameCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GameCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public GameCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    private void init(@Nullable AttributeSet set) {
        mPaintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintPoint.setColor(Color.BLACK);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStrokeWidth(LINE_WIDTH);

        mCell = new Rect();
        mCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCellPaint.setColor(Color.parseColor("#f8ede4"));
    }

    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = getHeight();
        float width = getWidth();

        //Draw Cells
        for (int i = 1; i < MainActivity.inputRows; i++) {
            for (int j = 1; j < MainActivity.inputColumns; j++) {
                mCell.left = (int) (width / (MainActivity.inputColumns + 1)) * j;
                mCell.right = (int) (mCell.left + (width / (MainActivity.inputColumns + 1)));
                mCell.top = (int) (width / (MainActivity.inputRows + 1)) * i;
                mCell.bottom = (int) (mCell.top + (width / (MainActivity.inputRows + 1)));
                canvas.drawRect(mCell, mCellPaint);
                cellDimensions.add(new Cell(mCell.left, mCell.right, mCell.top, mCell.bottom));
            }
        }

        //Draw Vertical Lines
        lineStartx = width / (MainActivity.inputColumns + 1);
        lineStarty = width / (MainActivity.inputRows + 1);
        lineEndx = lineStartx;
        lineEndy = lineStarty + width / (MainActivity.inputRows + 1);
        for (int j = 1; j <= MainActivity.inputColumns; j++) {
            for (int i = 1; i <= MainActivity.inputRows - 1; i++) {
                canvas.drawLine(lineStartx, lineStarty, lineEndx, lineEndy, mLinePaint);
                lineStarty = lineEndy;
                lineEndy = lineEndy + width / (MainActivity.inputRows + 1);
            }
            lineStartx = lineStartx + width / (MainActivity.inputColumns + 1);
            lineEndx = lineStartx;
            lineStarty = width / (MainActivity.inputRows + 1);
            lineEndy = lineStarty + width / (MainActivity.inputRows + 1);
        }

        //Draw Horizontal Lines
        lineStartx = width / (MainActivity.inputColumns + 1);
        lineStarty = width / (MainActivity.inputRows + 1);
        lineEndx = lineStartx + width / (MainActivity.inputColumns + 1);
        lineEndy = lineStarty;
        for (int j = 1; j <= MainActivity.inputRows; j++) {
            for (int i = 1; i <= MainActivity.inputColumns - 1; i++) {
                canvas.drawLine(lineStartx, lineStarty, lineEndx, lineEndy, mLinePaint);
                lineStartx = lineEndx;
                lineEndx = lineEndx + width / (MainActivity.inputColumns + 1);
            }
            lineStartx = width / (MainActivity.inputColumns + 1);
            lineEndx = lineStartx + width / (MainActivity.inputColumns + 1);
            lineStarty = lineStarty + width / (MainActivity.inputRows + 1);
            lineEndy = lineEndy + width / (MainActivity.inputRows + 1);
        }


        //Draw Point Grid
        mPointx = 0;
        mPointy = width / (MainActivity.inputRows + 1);

        for (int i = 1; i <= MainActivity.inputRows; i++) {
            for (int j = 1; j <= MainActivity.inputColumns; j++) {
                mPointx = width / (MainActivity.inputColumns + 1) + mPointx;
                canvas.drawCircle(mPointx, mPointy, mPointRadius, mPaintPoint);
                dotCoordinates.add(new Dot(mPointx, mPointy));
            }
            mPointy = width / (MainActivity.inputRows + 1) + mPointy;
            mPointx = 0;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        Log.v("GameActivity", "" + value);
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (touchX >= lineStartx && touchX <= lineEndx ) {
                    if (touchY <= lineStarty  && touchY <= lineEndy) {
                        switchColor();
                        postInvalidate();
                        return true;
                    }
                }
            }
        }

        return value;
    }

    private void switchColor() {
        mLinePaint.setColor(mLinePaint.getColor() == Color.BLUE ? Color.RED : Color.BLUE);
    }
}
