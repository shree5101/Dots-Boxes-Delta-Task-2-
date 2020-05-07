package com.example.android.dotsandboxes;

public class Cell {

    private int cellLeft;
    private int cellRight;
    private int cellTop;
    private int cellBottom;

    public Cell(int left, int right, int top, int bottom) {
        cellLeft = left;
        cellBottom = bottom;
        cellRight = right;
        cellTop = top;
    }

    public int getCellTop() {
        return cellTop;
    }

    public int getCellBottom() {
        return cellBottom;
    }

    public int getCellLeft() {
        return cellLeft;
    }

    public int getCellRight() {
        return cellRight;
    }
}
