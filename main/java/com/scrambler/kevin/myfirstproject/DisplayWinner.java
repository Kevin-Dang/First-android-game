package com.scrambler.kevin.myfirstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.scrambler.kevin.myfirstproject.R;

public class DisplayWinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_winner);
    }

    public void restart(View view)
    {
        Intent restartIntent = new Intent(this,TitleScreen.class);
        startActivity(restartIntent);

    }
}
