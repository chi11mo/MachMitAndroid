package de.techfak.gse.dwenzel.start_screen.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;


public class FirstStart extends AppCompatActivity {
    /**
     * Creates activity on first start.
     *
     * @param savedInstanceState instances saved .
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    /**
     * Start single player.
     *
     * @param view view.
     */
    public void onStartSingleGame(final View view) {
        finish();
        final Intent myIntent = new Intent(this, GameStartSingleActivity.class);
        startActivity(myIntent);
    }

    /**
     * Start multi player.
     *
     * @param view view.
     */
    public void onStartMultiGame(final View view) {
        finish();
        final Intent myIntent = new Intent(this, GameStartActivity.class);
        startActivity(myIntent);
    }
}
