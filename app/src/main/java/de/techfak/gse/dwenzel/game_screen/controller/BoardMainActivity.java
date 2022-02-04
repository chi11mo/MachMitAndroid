package de.techfak.gse.dwenzel.game_screen.controller;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.view.GameDisplay;
import de.techfak.gse.dwenzel.server_com.ServerController.GameStatusServerInteraction;
import de.techfak.gse.multiplayer.game.GameStatus;

/**
 * This activity controls all the stuff on the main game card.
 */
public class BoardMainActivity extends AppCompatActivity {
    private GameDisplay gameDisplay;

    /**
     * on Create method for do things when activity starts.
     *
     * @param savedInstanceState instances of activity.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_board);
        String url =getIntent().getStringExtra("Url");
        String name =getIntent().getStringExtra("Name");
        gameDisplay = new GameDisplay(this, getIntent().getStringExtra("File"),url,name);




    }


    /**
     * Listener for android toolbar back listener.
     * give a dialog if you want to go back to game_start_activity.
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zurueck")
                .setCancelable(false)
                .setMessage("Willst du das Spiel verlassen ?")
                .setPositiveButton("ja", (dialog, whichButton) -> {
                    finish();
                    dialog.dismiss();
                })
                .setNegativeButton("Nein", (dialog, whichButton) -> dialog.dismiss());
        builder.show();
    }

    /**
     * action to nextRound button.
     *
     * @param view from xml.
     */
    public void onNextRound(final View view) {
        gameDisplay.nextRound();
    }
}
