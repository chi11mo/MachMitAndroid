package de.techfak.gse.dwenzel.playground;

import android.widget.Button;

public class Field {

    /**
     * value of Coordinates.
     */
    private final int rowCord, colCord;

    /**
     * field Color in a String (small letter).
     */
    private String fieldColor;
    /**
     * if a field is validCrossed.
     */
    private boolean isCrossed;
    private Button fieldButton;
    /**
     *
     * @param row row coordinate.
     * @param col col coordinate.
     * @param color color as a String.
     * @param cross is Field crossed.
     */

    public Field(int row, int col, String color, boolean cross) {
        fieldColor = color;
        isCrossed = cross;
        rowCord = row;
        colCord = col;

    }
    public void setButton(Button button){
        fieldButton = button;
    }
    public Button getButton(){
        return fieldButton;
    }

    /**
     * get Field Color as a String.
     *
     * @return String of field color.
     */
    public String getFieldColor() {
        return fieldColor;
    }



    /**
     * setting up Crossed field.
     *
     * @param cross set true if is crossed or false if it isn't crossed field.
     */
    public void setIsCrossed(boolean cross) {
        isCrossed = cross;

    }

    /**
     * Getting value of field isCrossed.
     *
     * @return boolean if field is crossed or not.
     */
    public boolean isCrossed() {
        return isCrossed;
    }

    public void setColor(String s) {
        fieldColor =s;
    }
}
