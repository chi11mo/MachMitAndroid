package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.Button;

public class Field {

    /*field Color in a String (small letter).*/ private String fieldColor;

    /*if a field is validCrossed.*/             private boolean isCrossed;
    /*field buttons*/                           private Button fieldButton;

    /**
     * Creating a field for the Playground.
     *
     * @param color color as a String.
     * @param cross is Field crossed.
     */
    public Field(String color, boolean cross) {
        fieldColor = color;
        isCrossed = cross;

    }

    /**
     * @return to get the field button.
     */
    public Button getButton() {
        return fieldButton;
    }

    /**
     * @param button to set the field Button.
     */
    public void setButton(Button button) {
        fieldButton = button;
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

    /**
     * Setting the color.
     */
    public void setColor(String color) {
        fieldColor = color;
    }
}
