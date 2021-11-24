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
    private Game game;

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
        game = new Game(this, getIntent().getStringExtra("File"));

    }


    /**
     * Listener for android toolbar back listener.
     * give a dialog if you want to go back to game_start_activity.
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Back")
                .setCancelable(false)
                .setMessage("You want to Exit your current game ?")
                .setPositiveButton("Yes", (dialog, whichButton) -> {
                    finish();
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, whichButton) -> dialog.dismiss());
        builder.show();
    }

    /**
     * action to nextRound button.
     *
     * @param view from xml.
     */
    public void onNextRound(View view) {
        game.nextRound();
    }
}
