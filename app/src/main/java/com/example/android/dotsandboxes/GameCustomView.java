package com.example.android.dotsandboxes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class GameCustomView extends View {

    private Paint mPaintPoint;
    private Paint mCellPaint;
    private Rect mCell;
    private float mPointx, mPointy, mPointRadius = 10f;
    private int row = 3, column = 3;
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

        mCell = new Rect();
        mCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCellPaint.setColor(Color.LTGRAY);
    }

    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = getHeight();
        float width = getWidth();

        //Draw Cells
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                mCell.left = (int) (width / (column + 1)) * j;
                mCell.right = (int) (mCell.left + (width / (column + 1)));
                mCell.top = (int) (height / (row + 1)) * i;
                mCell.bottom = (int) (mCell.top + (height / (row + 1)));
                canvas.drawRect(mCell, mCellPaint);
                cellDimensions.add(new Cell(mCell.left, mCell.right, mCell.top, mCell.bottom));
            }
        }

        //Draw Point Grid
        mPointx = 0;
        mPointy = height / (row + 1);

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                mPointx = width / (column + 1) + mPointx;
                canvas.drawCircle(mPointx, mPointy, mPointRadius, mPaintPoint);
                dotCoordinates.add(new Dot(mPointx, mPointy));
            }
            mPointy = height / (row + 1) + mPointy;
            mPointx = 0;
        }
    }
}
