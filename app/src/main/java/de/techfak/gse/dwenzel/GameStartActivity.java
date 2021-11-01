package de.techfak.gse.dwenzel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.techfak.gse.dwenzel.board.BoardMainActivity;
import de.techfak.gse.dwenzel.playground.Field;
import de.techfak.gse.dwenzel.playground.Playground;
import de.techfak.gse.dwenzel.playground.ReadBoardLayout;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity extends AppCompatActivity implements Serializable {
    private static final String TAG = "GameStartActivity";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
    }

    /**
     * Method to Start the game and get Input of playground data.
     *
     * @param view view information
     */

    public void onClickGamestart(View view) {

        TextInputEditText textInputPlaygroundInput = findViewById(R.id.playgroundInput);

        String playgroundInputString = String.valueOf(textInputPlaygroundInput.getText());
        Log.i(TAG, playgroundInputString);


        InputStream file = null;
        try {
            file = getAssets().open(playgroundInputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReadBoardLayout readBoard = new ReadBoardLayout(file);
        if (readBoard.getException() != null) {
            popUpExceptionAlert(readBoard.getException(), view);
        } else {
           Playground playground = readBoard.getPlayground();



            Intent myIntent = new Intent(GameStartActivity.this, BoardMainActivity.class);
            myIntent.putExtra("Board", (Parcelable) playground); //Optional parameters
            startActivity(myIntent);

        }


        //for (Field[] fields : field) {
         //   for (Field value : fields) {
          //      System.out.println(value.getFieldColor());
          //  }
       // }


    }

    /**
     * Pop up if board file isn't valid.
     *
     * @param exception which exception is threw.
     * @param view      view of xml.
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
