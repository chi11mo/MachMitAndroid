package de.techfak.gse.dwenzel.board;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.playground.Playground;
import de.techfak.gse.dwenzel.playground.PlaygroundView;

public class BoardMainActivity extends AppCompatActivity implements Serializable {

    //private static final String TAG = "BoardMainActivity";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        if (getIntent().getExtras() != null) {
            String boardFile = getIntent().getStringExtra("File");

            InputStream file = null;
            try {
                file = getAssets().open(boardFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Playground playground = new Playground(file);
            getGridViewFromPlaygroundView(playground);

        }
    }


    void getGridViewFromPlaygroundView(Playground playground) {

        PlaygroundView playgroundView = new PlaygroundView(getResources().getInteger(R.integer.PlaygroundRow), getResources().getInteger(R.integer.PlaygroundCol));

        //Display display = getWindowManager().getDefaultDisplay();
        GridLayout gridLayout = findViewById(R.id.playground_grid);
        gridLayout.setColumnCount(getResources().getInteger(R.integer.PlaygroundRow));


        Button[][] btn = new Button[getResources().getInteger(R.integer.PlaygroundRow)][getResources().getInteger(R.integer.PlaygroundCol)];
        for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol); i++) {
            for (int k = 0; k < getResources().getInteger(R.integer.PlaygroundRow); k++) {
                btn[k][i] = new Button(this);
                playground.setButton(btn[k][i], k, i);
                Button button = playground.getFieldButton(k, i);
                button.setWidth(1);
                button.setLayoutParams(new LinearLayout.LayoutParams(70, 80));

                button = playgroundView.setFieldColor(btn[k][i], playground.getFieldColor(k, i));
                // btn[k][i].setBackground(getResources().getDrawable(R.drawable.button_dark_gradient));
                gridLayout.addView(button);

                //btn[k][i].setBackgroundColor(getResources().getColor(R.color.black));
            }


        }


    }
}
