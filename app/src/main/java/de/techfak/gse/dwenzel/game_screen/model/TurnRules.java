package de.techfak.gse.dwenzel.game_screen.model;

import android.util.Log;

import de.techfak.gse.dwenzel.exception.InvalidTurnException;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;

/**
 * Model to check is the turn valid to the rules is set.
 */
public class TurnRules {
    private static final String NEIGHBOUR_OR_H = "Das zu makierende Feld muss "
            + "bei der Reihe H anfangen oder in"
            + " Nachbarschaft zu einen bereits makierten Feld sein.";
    private static final String NOT_SAME_COLOR = "Das zu makierende Feld muss "
            + "die selbe Farbe haben wie das zuvor makierte Feld.";
    private final FieldMap fieldMap;
    private static final int NULL_COLOR_INDEX = 6;
    private static final int H_ROW_CORD = 7;

    private AlertBox alertBox;

    /**
     * TurnRules is for checking the current Turn Field choices.
     * You get back a bool.
     *
     * @param alertBox to bring the alertbox to the view.
     * @param fieldMap current Field Map.
     */
    public TurnRules(final AlertBox alertBox, final FieldMap fieldMap) {
        this.alertBox = alertBox;
        this.fieldMap = fieldMap;
    }


    /**
     * This class is to compare all the TurnRules and throw a exception
     * if the turn is not valid return a bool.
     *
     * @param field     current marked field.
     * @param firstMark color index of crossed Fields before.
     * @return is the Turn valid or not.
     */
    public boolean isTurnValid(final AbstractField field, final int firstMark) {
        String rule = null;

        try {
            /*All marked fields are the same as first tap.*/
            if (firstMark != NULL_COLOR_INDEX) {
                if (!isColorValid(field, firstMark)) {
                    rule = NOT_SAME_COLOR;
                    throw new InvalidTurnException(NOT_SAME_COLOR);
                }
            }
            /*if Mark is on Row H.*/
            if (firstMark == NULL_COLOR_INDEX) {
                if (field.getRow() == H_ROW_CORD) {
                    return true;
                }
            }
            /*Checking is a Neighbour field Marked.*/
            if (!isNeighbour(field)) {
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
     * Checking is turnTaps one of them a neighbour of a current Fieldmap crossed field.
     *
     * @param field current marked field.
     * @return is Neighbour Crossed.
     */
    private boolean isNeighbour(final AbstractField field) {
        AbstractField[][] fields = fieldMap.getFields();

        if (field.getRow() > 0) {
            if (fields[field.getRow() - 1][field.getCol()].isCrossed()) {
                return true;
            }
        }
        if (field.getRow() < fieldMap.getMaxRow() - 1) {
            if (fields[field.getRow() + 1][field.getCol()].isCrossed()) {
                return true;
            }
        }
        if (field.getCol() > 0) {
            if (fields[field.getRow()][field.getCol() - 1].isCrossed()) {
                return fields[field.getRow()][field.getCol() - 1].isCrossed();
            }
        }
        if (field.getCol() < fieldMap.getMaxCol() - 1) {
            return fields[field.getRow()][field.getCol() + 1].isCrossed();
        }

        return false;
    }

    /**
     * Checks if current turn has the same color.
     *
     * @param chosenColor is index of chosen Color.
     * @param field       current marked field.
     * @return if correct color is chosen.
     */
    private boolean isColorValid(final AbstractField field, final int chosenColor) {
        return field.getFieldColor() == chosenColor;
    }
}
