package de.techfak.gse.dwenzel.game_screen.rules;

import android.util.Log;

import java.util.List;

import de.techfak.gse.dwenzel.exception.InvalidTurnException;
import de.techfak.gse.dwenzel.game_screen.map.Field;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;

/**
 * Model to check is the turn valid to the rules is set.
 */
public class TurnRules {
    private static final String NEIGHBOUR_OR_H = "Das makierte Feld muss "
            + "bei der Reihe H anfangen oder in"
            + " Nachbarschaft zu einen bereits makierten Feld sein.";

    private static final String NEIGHBOUR_CURRENT_MARKED = "Die Felder muessen"
            + " aneinander liegen.";

    private static final int H_ROW_CORD = 7;
    private FieldMap fieldMap;
    private final AlertBox alertBox;


    /**
     * TurnRules is for checking the current Turn Field choices.
     * You get back a bool.
     *
     * @param alertBox to bring the alertbox to the view.
     */
    public TurnRules(final AlertBox alertBox) {
        this.alertBox = alertBox;
    }

    /**
     * Current Field map setting.
     *
     * @param fieldMap fieldMap;
     */
    public void setFieldMap(final FieldMap fieldMap) {
        this.fieldMap = fieldMap;
    }

    /**
     * This class is to compare all the TurnRules and throw a exception
     * if the turn is not valid return a bool.
     *
     * @param currentTurnTaps current field marks.
     * @return is the Turn valid or not.
     */
    public boolean isTurnValid(final List<Field> currentTurnTaps) {
        String rule = null;
        //Log.d("Test", String.valueOf(isNeigbourCurrentTaps(currentTurnTaps)));
        try {
            /*Checking is a Neighbour field Marked.*/
            if (!isNeigbourCurrentTaps(currentTurnTaps)
                    && currentTurnTaps.size() > 1) {
                rule = NEIGHBOUR_CURRENT_MARKED;
                throw new InvalidTurnException(NEIGHBOUR_CURRENT_MARKED);
            }

            /*Checking is a Neighbour field Marked.*/
            if (!isNeighbour(currentTurnTaps)
                    && !isOnHCoord(currentTurnTaps)) {
                rule = NEIGHBOUR_OR_H;
                throw new InvalidTurnException(NEIGHBOUR_OR_H);
            }

        } catch (InvalidTurnException exp) {

            alertBox.showAlert("Kein valider Zug!", rule);
            //Log.d("Exception", String.valueOf(exp));
            return false;
        }


        return true;
    }

    /**
     * Check if one of the taps is on H Coordinate.
     *
     * @param currentTurnTaps current Marked Taps.
     * @return if some of marked Taps is on H coordinate.
     */
    public boolean isOnHCoord(final List<Field> currentTurnTaps) {
        for (Field field : currentTurnTaps) {
            if (field.getRow() == H_ROW_CORD) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checking is turnTaps one of them a neighbour of a current Fieldmap crossed field.
     *
     * @param currentTurnTaps list of current marked Taps.
     * @return is Neighbour Crossed.
     */
    private boolean isNeighbour(final List<Field> currentTurnTaps) {
        for (Field field : currentTurnTaps) {
            Field[][] fields = fieldMap.getFields();

            if (field.getRow() > 0 && fields[field.getRow() - 1]
                    [field.getCol()].isCrossed()) {
                return true;
            }
            if (field.getRow() < fieldMap.getMaxRow() - 1 && fields[field.getRow() + 1]
                    [field.getCol()].isCrossed()) {
                return true;
            }
            if (field.getCol() > 0 && fields[field.getRow()][field.getCol() - 1].isCrossed()) {
                return fields[field.getRow()][field.getCol() - 1].isCrossed();
            }
            if (field.getCol() < fieldMap.getMaxCol() - 1) {
                return fields[field.getRow()][field.getCol() + 1].isCrossed();
            }
        }

        return false;
    }
/**
 private boolean isNeighbourTest(final List<Field> currentTurnTaps) {


 int tapSize = currentTurnTaps.size();
 //Checks neighbour with just 2 Marked Fields.
 if (tapSize == 2) {
 return Math.abs(currentTurnTaps.get(0).getRow()
 - currentTurnTaps.get(1).getRow()) <= 1
 && Math.abs(currentTurnTaps.get(0).getCol()
 - currentTurnTaps.get(1).getCol()) <= 1;
 }


 int counterNeighbours = 0;
 int counter = 0;

 for (Field field : currentTurnTaps) {
 counterNeighbours = 0;
 for (Field fieldTwo : currentTurnTaps) {
 if (field != fieldTwo) {
 String fieldCoord = String.valueOf(field.getRow()) + " / " + field.getCol();
 String fieldCoord2 = String.valueOf(fieldTwo.getRow()) + " / " + fieldTwo.getCol();
 if (Math.abs(field.getRow() - fieldTwo.getRow()) <= 1 && Math.abs(field.getCol() - fieldTwo.getCol()) <= 1) {
 Log.d("are Neighbour", fieldCoord + fieldCoord2);
 counterNeighbours++;

 } else {
 Log.d("are not Neighbour", fieldCoord + fieldCoord2);
 }

 }

 }
 if (counterNeighbours == 2) {
 counter++;
 }
 if (counterNeighbours == 0) {
 return false;
 }
 }
 return counter < 3;
 }
 **/

    /**
     * This checks if all current marked fields are Neighbours.
     *
     * @param currentTurnTaps list of current Taps.
     * @return is all marked fields Neighbours.
     */
    private boolean isNeigbourCurrentTaps(final List<Field> currentTurnTaps) {
        int tapSize = currentTurnTaps.size();
        int counterNeighbour = 0;
        for (Field field : currentTurnTaps) {
            //  String log = String.valueOf(field.getRow()) + String.valueOf(field.getCol());
            // Log.d("Row ", log);
            for (Field fieldTmp : currentTurnTaps) {
                if (field.getRow() > 0) {
                    if (field.getRow() - 1 == fieldTmp.getRow()
                            && field.getCol() == fieldTmp.getCol()) {
                        counterNeighbour++;
                    }
                }

                if (field.getRow() < fieldMap.getMaxRow() - 1) {
                    if (field.getRow() + 1 == fieldTmp.getRow()
                            && field.getCol() == fieldTmp.getCol()) {
                        counterNeighbour++;
                    }

                }

                if (field.getCol() > 0) {
                    if (field.getCol() - 1 == fieldTmp.getCol()
                            && field.getRow() == fieldTmp.getRow()) {
                        counterNeighbour++;
                    }
                }

                if (field.getCol() < fieldMap.getMaxCol() - 1) {
                    if (field.getCol() + 1 == fieldTmp.getCol()
                            && field.getRow() == fieldTmp.getRow()) {
                        counterNeighbour++;
                    }
                }
            }
        }
        return counterNeighbour >= tapSize;

    }


}
