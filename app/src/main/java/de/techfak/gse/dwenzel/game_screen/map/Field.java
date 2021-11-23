package de.techfak.gse.dwenzel.game_screen.map;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import de.techfak.gse.dwenzel.game_screen.model.ButtonSpriteSheet;

/**
 * Class to describe single fields in the playground.
 */
public abstract class Field {


    ButtonSpriteSheet buttonSpriteSheet;
    /*field Color in a String (small letter).*/ private String fieldColor;
    /*if a field is validCrossed.*/             private boolean crossed;
    /*field buttons*/                           private ImageButton fieldButton;
    /*field row cord*/                          private int row;
    /*field col cord*/                          private int col;

    /**
     * Creating a field for the Playground.
     *
     * @param cross             is Field crossed.
     * @param buttonSpriteSheet
     */
    public Field(final boolean cross, final ButtonSpriteSheet buttonSpriteSheet) {
        this.buttonSpriteSheet = buttonSpriteSheet;
        crossed = cross;

    }

    /**
     * get field gets the field with curren index
     * and color of the field as enum.
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     *
     * @param idxFieldType      is index of field color
     * @param buttonSpriteSheet sprite cheat to get loaded pngs.
     * @param isCrossed         is the field crossed.
     * @return the Created field.
     */
    public static Field getField(final int idxFieldType,
                                 final ButtonSpriteSheet buttonSpriteSheet,
                                 final boolean isCrossed) {
        switch (FieldType.values()[idxFieldType]) {
            case YELLOW_FIELD:
                return new YellowField(isCrossed, buttonSpriteSheet);
            case GREEN_FIELD:
                return new GreenField(isCrossed, buttonSpriteSheet);
            case RED_FIELD:
                return new RedField(isCrossed, buttonSpriteSheet);
            case ORANGE_FIELD:
                return new OrangeField(isCrossed, buttonSpriteSheet);
            case BLUE_FIELD:
                return new BlueField(isCrossed, buttonSpriteSheet);
            default:
                return null;
        }
    }

    /**
     * get Button from the single field.
     *
     * @return to get the field button.
     */
    public ImageButton getButton() {
        return fieldButton;
    }

    /**
     * set the Button from the single field.
     *
     * @param button to set the field Button.
     */
    public void setButton(final ImageButton button) {
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
     *
     * @param color color as a string.
     */
    public void setColor(final String color) {
        fieldColor = color;
    }

    /**
     * get the drawable of the field.
     *
     * @return drawable of the current field.
     */
    public abstract Drawable getDrawableField();

    /**
     * get the drawable of the field.
     *
     * @param isCrossed with cross information.
     * @return drawable of the current field.
     */
    public abstract Drawable getDrawableField(boolean isCrossed);

    /**
     * row setter to set row cord.
     *
     * @return row cord from field.
     */
    public int getRow() {
        return row;
    }

    /**
     * row setter to set row cord.
     *
     * @param row cord from field.
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * get field column cord.
     *
     * @return column cord.
     */
    public int getCol() {
        return col;
    }

    /**
     * setting up field column cord.
     *
     * @param col column cord of the field.
     */
    public void setCol(final int col) {
        this.col = col;
    }

    /**
     * Images.
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     */
    public enum FieldType {
        YELLOW_FIELD,
        GREEN_FIELD,
        RED_FIELD,
        ORANGE_FIELD,
        BLUE_FIELD
    }
}
