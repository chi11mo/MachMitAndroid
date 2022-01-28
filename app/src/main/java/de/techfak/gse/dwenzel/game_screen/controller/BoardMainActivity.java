package de.techfak.gse.dwenzel.game_screen.view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;

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
        gameDisplay = new GameDisplay(this, getIntent().getStringExtra("File"));

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
