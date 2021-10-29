package de.techfak.gse.dwenzel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity extends AppCompatActivity {
    private static final String TAG = "GameStartActivity";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
    }

    /**
     * Method to Start the game and get Input of playground data.
     * @param view
     */
    public void onClickGamestart(View view) {

        TextInputEditText textInputPlaygroudInput = findViewById(R.id.playgroundInput);

        String playgroundInputString = String.valueOf(textInputPlaygroudInput.getText());
        Log.i(TAG, playgroundInputString);

    }
}
