package com.example.android.dotsandboxes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.v("GameActivity", "Super onCreate run");
        setContentView(R.layout.game_activity);
        GameCustomView mGameCustomView = findViewById(R.id.game_custom_view);
    }
}

