package de.techfak.gse.dwenzel.game_screen.model;

import java.util.List;
import java.util.Observable;


public class FieldMap extends Observable {

    private final int maxRow;
    private final int maxCol;

    private final MapLayout mapLayout;
    private final Field[][] fields;

    /**
     * field map is the map of the Field buttons to cross.
     *
     * @param boardLayout loaded layout for begin the board.
     * @param maxRow      mac row cords.
     * @param maxCol      mx column cords.
     */
    public FieldMap(
            final String boardLayout, final int maxRow, final int maxCol) {


        this.maxRow = maxRow;
        this.maxCol = maxCol;
        mapLayout = new MapLayout(boardLayout, maxRow, maxCol);
        fields = new Field[maxRow][maxCol];
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
                // Log.d("init Field map field map row", String.valueOf(iRow));
                fields[iRow][iCol] = new Field(isCrossed[iRow][iCol], layout[iRow][iCol]);
                fields[iRow][iCol].setRow(iRow);
                fields[iRow][iCol].setCol(iCol);

            }
        }
    }

    /**
     * get method to get the fields as a2d array.
     *
     * @return 2d array of all fields.
     */
    public Field[][] getFields() {
        return fields;
    }

    /**
     * get max Row coordinate.
     *
     * @return row cord.
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * get max Column cord.
     * ¬
     *
     * @return max row cord.
     */
    public int getMaxCol() {
        return maxCol;
    }

    /**
     * update fieldMap if valid turn is over.
     * Setting Buttons to unable to click.
     *
     * @param currentTurnTaps current marked fields.
     */
    public void updateFieldMap(final List<Field> currentTurnTaps) {
        for (Field field : currentTurnTaps) {
            field.setIsCrossed(true);
        }


        setChanged();

        notifyObservers();

    }


}
