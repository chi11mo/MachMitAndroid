package de.techfak.gse.dwenzel.playground;

import android.graphics.Insets;
import android.service.quicksettings.Tile;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

public class PlaygroundView {

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
}
