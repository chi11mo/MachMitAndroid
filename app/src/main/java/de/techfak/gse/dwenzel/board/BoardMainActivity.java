package de.techfak.gse.dwenzel.board;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.playground.Field;
import de.techfak.gse.dwenzel.playground.Playground;
import de.techfak.gse.dwenzel.playground.PlaygroundView;

public class BoardMainActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = "BoardMainActivity";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        if (getIntent().getExtras() != null) {
            Playground playground = (Playground) getIntent().getParcelableExtra("Board");

            getGridViewFromPlaygroundView(playground);
        }
    }

    void getGridViewFromPlaygroundView(Playground playground) {

        PlaygroundView playgroundView = new PlaygroundView(playground.getMaxRow(), playground.getMaxCol());

        GridLayout gridLayout = (GridLayout) findViewById(R.id.playground_grid);
        gridLayout.setColumnCount(playground.getMaxCol());

        Button[][] btn = new Button[playground.getMaxRow()][playground.getMaxCol()];
        for (int i = 0; i < playground.getMaxCol(); i++) {
            for (int k = 0; k < playground.getMaxRow(); k++) {
                btn[k][i] = new Button(this);
                btn[k][i].setWidth(100);
                gridLayout.addView(btn[k][i]);
            }

            //btn[i]= new Button(this);
            // btn[i].setWidth(106);
            // listmainLayoutHorizontal.addView(btn[i]);

        }


    }
}
