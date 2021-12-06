package de.techfak.gse.dwenzel.game_screen.map;

import de.techfak.gse.dwenzel.sprite_sheet.ButtonSpriteSheet;

public class FieldMap {

    private final int maxRow;
    private final int maxCol;
    private final ButtonSpriteSheet buttonSpriteSheet;
    private MapLayout mapLayout;
    private AbstractField[][] abstractFields;

    /**
     * field map is the map of the Field buttons to cross.
     *
     * @param buttonSpriteSheet sheet to load png for the  button.
     * @param boardLayout       loaded layout for begin the board.
     * @param maxRow            mac row cords.
     * @param maxCol            mx column cords.
     */
    public FieldMap(final ButtonSpriteSheet buttonSpriteSheet,
                    final String boardLayout, final int maxRow, final int maxCol) {

        this.buttonSpriteSheet = buttonSpriteSheet;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        mapLayout = new MapLayout(boardLayout, maxRow, maxCol);
        abstractFields = new AbstractField[maxRow][maxCol];
        initializeFieldMap();
    }

    /**
     * init first time field map.
     */
    private void initializeFieldMap() {
        final int[][] layout = mapLayout.getLayout();
        final boolean[][] isCrossed = mapLayout.getIsCrossed();

        for (int iRow = 0; iRow < maxRow; iRow++) {
            for (int iCol = 0; iCol < maxCol; iCol++) {
                // Log.d("init FIeld map field map row", String.valueOf(iRow));
                abstractFields[iRow][iCol] = AbstractField.getField(layout[iRow][iCol],
                        buttonSpriteSheet, isCrossed[iRow][iCol]);
                abstractFields[iRow][iCol].setColor(layout[iRow][iCol]);
            }
        }
    }

    /**
     * get method to get the fields as a2d array.
     *
     * @return 2d array of all fields.
     */
    public AbstractField[][] getFields() {
        return abstractFields;
    }

    /**
     * get max Row coord.
     *
     * @return row cord.
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * get max Column cord.
     *
     * @return max row cord.
     */
    public int getMaxCol() {
        return maxCol;
    }
}
