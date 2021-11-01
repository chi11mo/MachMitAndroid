package de.techfak.gse.dwenzel.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.playground.Field;
import de.techfak.gse.dwenzel.playground.Playground;

public class BoardMainActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = "BoardMainActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        if(getIntent().getExtras() != null) {
            Playground playground = (Playground) getIntent().getParcelableExtra("Board");

            System.out.println(playground);
        }
    }
}
