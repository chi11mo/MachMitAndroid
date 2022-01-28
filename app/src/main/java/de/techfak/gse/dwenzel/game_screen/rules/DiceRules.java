package de.techfak.gse.dwenzel.game_screen.rules;

import java.util.List;

import de.techfak.gse.dwenzel.exception.InvalidTurnException;
import de.techfak.gse.dwenzel.game_screen.dice.Dice;
import de.techfak.gse.dwenzel.game_screen.map.Field;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;

public class DiceRules {

    private static final String NOT_SAME_COLOR = "Das zu makierende Feld muss "
            + "die selbe Farbe haben wie das zuvor makierte Feld.";
    private static final String NOT_RIGHT_DICE_EYE = "Die Anzahl "
            + "der makierten Felder sind nicht passend "
            + "oder die Farbe stimmt nicht.Checke die Augenzahlen der Wurfel nochmal.";

    private final AlertBox alertBox;
    private Dice dice;

    /**
     * Dice Rules.
     *
     * @param alertBox
     */
    public DiceRules(final AlertBox alertBox) {
        this.alertBox = alertBox;

    }

    /**
     * Check die rules.
     *
     * @param currentTurnTaps current makred fields.
     * @return if all die rules are accepted.
     */
    public boolean checkDiceRules(final List<Field> currentTurnTaps) {
        String rule = null;
        try {
            if (!diceRules(currentTurnTaps)) {
                rule = NOT_RIGHT_DICE_EYE;
                throw new InvalidTurnException(NOT_RIGHT_DICE_EYE);
            }
            /*All marked fields are the same as first tap.*/
            if (isSameColor(currentTurnTaps)) {
                rule = NOT_SAME_COLOR;
                throw new InvalidTurnException(NOT_SAME_COLOR);
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
     * @return bool if dice is fine with marked fields.
     */
    private boolean diceRules(final List<Field> currentTurnTaps) {
        return isDiceColor(currentTurnTaps) && isDiceEyeNumber(currentTurnTaps);
    }

    /**
     * checking turn tap counter with rolled dice eyes.
     *
     * @param currentTurnTaps current taps.
     * @return bool if valid.
     */
    private boolean isDiceEyeNumber(final List currentTurnTaps) {

        final int turnTapsSize = currentTurnTaps.size();

        for (int diceEye : dice.getNumberList()) {
            if (diceEye + 1 == turnTapsSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * checking current turn is same color as one of the rolled dice colors.
     *
     * @param currentTurnTaps all current marked fields.
     * @return is  mark is valid.
     */
    private boolean isDiceColor(final List<Field> currentTurnTaps) {

        for (int color : dice.getColorList()) {
            if (currentTurnTaps.get(0).getFieldIdxColor() == color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checking is the turn in the same color.
     *
     * @param currentTurnTaps current marked taps.
     * @return is all same color.
     */
    private boolean isSameColor(final List<Field> currentTurnTaps) {
        final int currentColor = currentTurnTaps.get(0).getFieldIdxColor();
        if (currentTurnTaps.size() == 1) {
            return false;
        }

        for (Field field : currentTurnTaps) {

            if (currentColor != field.getFieldIdxColor()) {
                return true;
            }
        }

        return false;
    }

    /**
     * setting Current Die.
     *
     * @param dice current die.
     */
    public void setDice(final Dice dice) {
        this.dice = dice;
    }
}
