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

    /**
     * Color Green for Button.
     */
    private final float[] floatGreenHSV = new float[]{122f, 95f, 97f};
    /**
     * Color Blue for Button.
     */
    private final float[] floatBlueHSV = new float[]{242f, 95f, 97f};
    /**
     * Color yellow for Button.
     */
    private final float[] floatYellowHSV = new float[]{60f, 100f, 100f};
    /**
     * Color Red for Button.
     */
    private final float[] floatRedHSV = new float[]{357f, 95f, 97f};
    /**
     * Color Orange for Button.
     */
    private final float[] floatOrangeHSV = new float[]{40f, 95f, 97f};

    /**
     * Playground View for GUI Playground.
     *
     * @param maxRow get MaxRow.
     * @param maxCol get MaxCol.
     */
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
            button.setBackgroundColor(Color.HSVToColor(floatGreenHSV));
            return button;
        }
        if (color.equals("G")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("b")) {
            button.setBackgroundColor(Color.HSVToColor(floatBlueHSV));
            return button;
        }
        if (color.equals("B")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }

        if (color.equals("y")) {
            button.setBackgroundColor(Color.HSVToColor(floatYellowHSV));
            return button;
        }
        if (color.equals("Y")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("o")) {
            button.setBackgroundColor(Color.HSVToColor(floatOrangeHSV));
            return button;
        }
        if (color.equals("O")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("r")) {
            button.setBackgroundColor(Color.HSVToColor(floatRedHSV));
            return button;
        }
        if (color.equals("R")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }


        return button;
    }
}
