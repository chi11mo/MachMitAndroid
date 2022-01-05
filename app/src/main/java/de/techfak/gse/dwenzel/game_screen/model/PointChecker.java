package de.techfak.gse.dwenzel.game_screen.model;


import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;

public class PointChecker {
    private static final int ROW_POINTS[] = {5, 3, 3, 3, 2, 2, 2, 1, 2, 2, 2, 3, 3, 3, 5};

    private List<Integer> pickedFullColor = new ArrayList<Integer>();


    private List<Integer> pickedFullRow = new ArrayList<Integer>();

    /**
     * This Model will scan the field map.
     * Save correct crossed full Rows and Full color.
     */
    public PointChecker() {

    }

    /**
     * Checking all Rows and Colors Crossed.
     *
     * @param fieldMap        current list of fields.
     * @param currentTurnTaps current crossed Fields.
     */
    public void checkPoints(final FieldMap fieldMap, final List<AbstractField> currentTurnTaps) {

        if (currentTurnTaps.isEmpty()) {
            return;
        }
        checkFullColor(fieldMap, currentTurnTaps.get(0).getFieldColor());
        checkFullRow(fieldMap, currentTurnTaps);
    }

    /**
     * This checks if there a full Row and saved it in a list.
     *
     * @param fieldMap        current list of fields.
     * @param currentTurnTaps current crossed Fields.
     */
    private void checkFullRow(final FieldMap fieldMap, final List<AbstractField> currentTurnTaps) {
        AbstractField[][] fields = fieldMap.getFields();

        LabelDonatCross:
        for (AbstractField field : currentTurnTaps) {
            int iRow = field.getRow();
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                if (!fields[iRow][iCol].isCrossed()) {
                    continue LabelDonatCross;
                }
            }

            if (!pickedFullRow.contains(iRow)) {
                pickedFullRow.add(iRow);
            }

        }

    }

    /**
     * This checks if there a full Color and saved it in a list.
     *
     * @param fieldMap   current list of fields.
     * @param fieldColor current crossed Color.
     */
    private void checkFullColor(final FieldMap fieldMap, final int fieldColor) {
        AbstractField[][] fields = fieldMap.getFields();

        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                if (!fields[iRow][iCol].isCrossed()
                        && fields[iRow][iCol].getFieldColor() == fieldColor) {
                    return;
                }
            }
        }
        pickedFullColor.add(fieldColor);
    }

    /**
     * gets the list of full crossed Colors.
     *
     * @return List full colors.
     */
    public List<Integer> getPickedFullColor() {
        return pickedFullColor;
    }

    /**
     * gets the list of full crossed Rows.
     *
     * @return list full Row.
     */
    public List<Integer> getPickedFullRow() {
        return pickedFullRow;
    }

    /**
     * to get current points for the view
     *
     * @return int with current points.
     */
    public int getPoints() {
        int currentPoints = 0;
        for (int idxRow : pickedFullRow) {
            currentPoints = currentPoints + ROW_POINTS[idxRow];
        }
        if (pickedFullColor.size() > 0) {
            int tmp = ROW_POINTS[0] * pickedFullColor.size();
            return currentPoints + tmp;
        }
        return currentPoints;
    }

    /**
     * Checks if 2 Colors are crossed as a win condition.
     * @return is 2 colors crossed.
     */
    public boolean isGameEnd() {
        return pickedFullColor.size() > 1;
    }
}
