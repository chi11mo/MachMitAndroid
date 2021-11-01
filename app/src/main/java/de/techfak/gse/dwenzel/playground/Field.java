package de.techfak.gse.dwenzel.playground;

public class Field {

    /**
     * value of Coordinates.
     */
    private final int rowCord, colCord;

    /**
     * field Color in a String (small letter).
     */
    private final String fieldColor;
    /**
     * if a field is validCrossed.
     */
    private boolean isCrossed;

    public Field(int row, int col, String color, boolean cross) {
        fieldColor = color;
        isCrossed = cross;
        rowCord = row;
        colCord = col;

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
}
