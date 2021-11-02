package de.techfak.gse.dwenzel.playground;

import static android.graphics.Color.BLACK;

import android.graphics.Color;
import android.graphics.Insets;
import android.service.quicksettings.Tile;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;

public class PlaygroundView extends AppCompatActivity{


    private final int gridRow, gridCol;
    Playground playground;

    public PlaygroundView(int maxRow, int maxCol) {
        gridRow = maxRow;
        gridCol = maxCol;



    }


    void getGridView(Field field) {


    }

    public GridView setGridView(Playground playground, GridView gridView) {

        return gridView;
    }

    public Button setFieldColor(Button button, String color) {
        if(color.equals("g")) {
            button.setBackgroundColor(Color.GREEN);
            return button;
        }
        else if(color.equals("G")){
            button.setText("X");
            button.setBackgroundColor(Color.GREEN);

            return button;
        }
        if(color.equals("b")||color.equals("B"))
            button.setBackgroundColor(Color.BLUE);
        if(color.equals("y")||color.equals("Y"))
            button.setBackgroundColor(Color.YELLOW);
        if(color.equals("o")||color.equals("O"))
            button.setBackgroundColor(Color.GRAY);
        if(color.equals("r")||color.equals("R"))
            button.setBackgroundColor(Color.RED);


        return button;
    }
}
