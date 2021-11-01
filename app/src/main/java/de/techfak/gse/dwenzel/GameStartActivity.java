package de.techfak.gse.dwenzel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;

import de.techfak.gse.dwenzel.playground.Field;
import de.techfak.gse.dwenzel.playground.ReadBoardLayout;

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
     *
     * @param view
     */

    public void onClickGamestart(View view) {

        TextInputEditText textInputPlaygroudInput = findViewById(R.id.playgroundInput);

        String playgroundInputString = String.valueOf(textInputPlaygroudInput.getText());
        Log.i(TAG, playgroundInputString);


        InputStream file = null;
        try {
            file = getAssets().open(playgroundInputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReadBoardLayout readBoard = new ReadBoardLayout(file);
         if(readBoard.getException()!=null){
        popUpExceptionAlert(readBoard.getException(), view);
         }

        Field[][] field = readBoard.getPlayground();

        for (Field[] fields : field) {
            for (Field value : fields) {
                System.out.println(value.getFieldColor());
            }
        }


    }

    /**
     * Pop up if board file isnt valid.
     * @param exception
     * @param view
     */
    private void popUpExceptionAlert(String exception, View view) {
        if (exception.equals("InvalidBoardException"))
            exception = "Board Structure is Invalid :  do 15 rows and 7 columns.";
        if (exception.equals("InvalidFieldException"))
            exception = "Board Structure is Invalid :   do b, B, g, G, o, O, r, R, y, Y ";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Board Layout Try Another txt Data.")
                .setCancelable(false)
                .setMessage(exception)
                .setPositiveButton("Okay", null);
        builder.show();
    }
}
