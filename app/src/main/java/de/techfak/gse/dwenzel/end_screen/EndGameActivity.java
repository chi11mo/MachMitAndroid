package de.techfak.gse.dwenzel.end_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.start_screen.controller.GameStartActivity;

public class EndGameActivity extends AppCompatActivity {

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_endgame);
        ArrayList<String> endCardList = getIntent().getStringArrayListExtra("EndCardList");
        TextView winnerView = findViewById(R.id.winnerView);

        StringBuilder endCardRankings = new StringBuilder();
        for (String player : endCardList) {
            endCardRankings.append(player).append("\n");
        }

        winnerView.setText(getString(R.string.winner_text)
                + endCardRankings);

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
