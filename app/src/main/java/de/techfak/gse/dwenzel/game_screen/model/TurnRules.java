package de.techfak.gse.dwenzel.game_screen.model;

import java.util.List;

import de.techfak.gse.dwenzel.exception.InvalidTurnException;
import de.techfak.gse.dwenzel.game_screen.dice.Dice;
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
    private static final String NOT_RIGHT_DICE_EYE = "Die Anzahl "
            + "der makierten Felder sind zu hoch "
            + "oder die Farbe stimmt nicht.Checke die Augenzahlen der Wurfel nochmal.";
    private final FieldMap fieldMap;
    private static final int NULL_COLOR_INDEX = 6;
    private static final int H_ROW_CORD = 7;
    private final AlertBox alertBox;
    private Dice dice;


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
     * @param field           current marked field.
     * @param firstMark       color index of crossed Fields before.
     * @param currentTurnTaps current field marks.
     * @return is the Turn valid or not.
     */
    public boolean isTurnValid(final AbstractField field, final int firstMark
            , final List currentTurnTaps) {
        String rule = null;

        try {
            if (!diceRules(currentTurnTaps, field)) {
                rule = NOT_RIGHT_DICE_EYE;
                throw new InvalidTurnException(NOT_RIGHT_DICE_EYE);
            }
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
     * Creates rules for the dice.
     *
     * @param currentTurnTaps current Turn Taps.
     * @param field           field ti check dice rules with current mark.
     * @return bool if dice is fine with marked fields.
     */
    private boolean diceRules(final List currentTurnTaps, final AbstractField field) {
        return isDiceColor(field) && isDiceEyeNumber(currentTurnTaps);
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

    /**
     * checking turn tap counter with rolled dice eyes.
     *
     * @param currentTurnTaps current taps.
     * @return bool if valid.
     */
    private boolean isDiceEyeNumber(final List currentTurnTaps) {
        final int maxTaps = currentTurnTaps.size();
        int maxDiceEye = 0;
        for (int number : dice.getDiceEyes()) {
            if (number > maxDiceEye) {
                maxDiceEye = number;
            }
        }
        return maxTaps <= maxDiceEye;
    }

    /**
     * checking current turn is same color as one of the rolled dice colors.
     *
     * @param field field to get color.
     * @return is  mark is valid.
     */
    private boolean isDiceColor(final AbstractField field) {
        for (int color : dice.getDiceColors()) {
            if (field.getFieldColor() == color) {
                return true;
            }
        }
        return false;
    }

    /**
     * set dice.
     *
     * @param dice from with all information.
     */
    public void setDice(final Dice dice) {
        this.dice = dice;
    }
}
