package com.example.android.dotsandboxes;

public class Dot {

    private static float xOfDot;
    private static float yOfDot;

    public Dot(float x, float y) {
        xOfDot = x;
        yOfDot = y;
    }

    public static float getxOfDot() {
        return xOfDot;
    }

    public static float getyOfDot() {
        return yOfDot;
    }
}
