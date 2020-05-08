package com.example.android.dotsandboxes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        GameCustomView mGameCustomView = findViewById(R.id.game_custom_view);
        TextView player1Score = findViewById(R.id.player_1_score);
        TextView player2Score = findViewById(R.id.player_2_score);
    }
}

