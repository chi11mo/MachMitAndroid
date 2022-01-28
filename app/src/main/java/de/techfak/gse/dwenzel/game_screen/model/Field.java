package de.techfak.gse.dwenzel.game_screen.model;

/**
 * Class to describe single fields in the playground.
 */
public class Field {


    /*field Color in a String (small letter).*/ private int fieldColor;
    /*if a field is validCrossed.*/             private boolean crossed;
    private int idxColor;

    /*field row cord*/                          private int row;
    /*field col cord*/                          private int col;

    /**
     * Creating a field for the Playground.
     *
     * @param cross    is Field crossed.
     * @param idxColor idx to color
     */
    public Field(final boolean cross, final int idxColor) {
        crossed = cross;

        this.idxColor = idxColor;
    }


    /**
     * get Field Color as a String.
     *
     * @return String of field color.
     */
    public int getFieldIdxColor() {
        return idxColor;
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
     * Images.
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     *
     * @param color color as a string.
     */
    public void setIdxColor(final int color) {
        fieldColor = color;
    }


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
}
