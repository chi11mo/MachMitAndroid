package de.techfak.gse.dwenzel.playground;


import android.graphics.Color;
import android.widget.Button;

public class PlaygroundView {


    /**
     * maxRow From playground.
     */
    private final int maxRow;
    /**
     * maxCol From playground.
     */
    private final int maxCol;

    public PlaygroundView(int maxRow, int maxCol) {


        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }


    /**
     * Setting up field color after Reading the data.
     *
     * @param button button form playground.
     * @param color  color as aString from playground.
     * @return the colored button.
     */
    public Button setFieldColor(Button button, String color) {
        if (color.equals("g")) {
            button.setBackgroundColor(Color.HSVToColor(new float[]{122f, 95f, 97f}));
            return button;
        }
        if (color.equals("G")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("b")) {
            button.setBackgroundColor(Color.HSVToColor(new float[]{242f, 95f, 97f}));
            return button;
        }
        if (color.equals("B")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }

        if (color.equals("y")) {
            button.setBackgroundColor(Color.HSVToColor(new float[]{60f, 100f, 100f}));
            return button;
        }
        if (color.equals("Y")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("o")) {
            button.setBackgroundColor(Color.HSVToColor(new float[]{40f, 95f, 97f}));
            return button;
        }
        if (color.equals("O")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("r")) {
            button.setBackgroundColor(Color.HSVToColor(new float[]{357f, 95f, 97f}));
            return button;
        }
        if (color.equals("R")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }


        return button;
    }
}
