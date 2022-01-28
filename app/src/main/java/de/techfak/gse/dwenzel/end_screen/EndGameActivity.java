package de.techfak.gse.dwenzel.end_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.start_screen.controller.GameStartActivity;

public class EndGameActivity extends AppCompatActivity {

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_endgame);

        TextView winnerView = findViewById(R.id.winnerView);
        winnerView.setText(getString(R.string.winner_text)
                + getIntent().getStringExtra("EndPoints"));

    }

    /**
     * If new game ist clicked goes to Gamestart activity.
     *
     * @param view view of endgame.
     */
    public void onClickGamestart(final View view) {
        finishAffinity();
        final Intent myIntent = new Intent(this, GameStartActivity.class);
        startActivity(myIntent);
        finish();
    }

    /**
     * Disable back button.
     */
    @Override
    public void onBackPressed() {
    }
}
