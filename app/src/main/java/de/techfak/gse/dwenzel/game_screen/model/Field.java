package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.Button;

/**
 * Class to describe single fields in the playground.
 */
public class Field {

    /*field Color in a String (small letter).*/ private String fieldColor;

    /*if a field is validCrossed.*/             private boolean crossed;
    /*field buttons*/                           private Button fieldButton;

    /**
     * Creating a field for the Playground.
     *
     * @param color color as a String.
     * @param cross is Field crossed.
     */
    public Field(final String color, final boolean cross) {
        fieldColor = color;
        crossed = cross;

    }

    /**
     * get Button from the single field.
     *
     * @return to get the field button.
     */
    public Button getButton() {
        return fieldButton;
    }

    /**
     * set the Button from the single field.
     *
     * @param button to set the field Button.
     */
    public void setButton(final Button button) {
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
    public void setIsCrossed(final boolean cross) {
        crossed = cross;

    }

    /**
     * Getting value of field isCrossed.
     *
     * @return boolean if field is crossed or not.
     */
    public boolean isCrossed() {
        return crossed;
    }

    /**
     * Setting up the field color.
     * @param color color as a string.
     */
    public void setColor(final String color) {
        fieldColor = color;
    }
}
