package de.techfak.gse.dwenzel.playground;

public class Field {

    /**
     * value of Cordinates.
     */
    int rowCord,colCord;

    /**
     * field Color in a String (small letter).
     */
    final String fieldColor;
    /**
     * if a field is validCrossed.
     */
    boolean isCrossed;

    public Field(int row, int col, String color, boolean cross) {
        fieldColor = color;
        isCrossed = cross;
        rowCord = row;
        colCord = col;

    }

    /**
     * get Field Color as a String.
     * @return
     */
    public String getFieldColor() {
        return fieldColor;
    }

    /**
     * setting up Corssedfield.
     * @param cross
     */
    public void setIsCrossed(boolean cross) {
        isCrossed = cross;

    }

    /**
     * Getting value of field isCrossed.
     * @return
     */
    public boolean isCrossed() {
        return isCrossed;
    }
}
