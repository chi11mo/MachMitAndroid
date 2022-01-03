package de.techfak.gse.dwenzel.game_screen.rules;

import java.util.List;

import de.techfak.gse.dwenzel.game_screen.dice.Dice;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;

public class Rules {
    private final AlertBox alertBox;
    private TurnRules turnRules;
    private DiceRules diceRules;

    /**
     * Model to check all the defined Game Rules for marked fields.
     * @param alertBox Box to show if rules arent accepted.
     */
    public Rules(final AlertBox alertBox) {
        this.alertBox = alertBox;

        turnRules = new TurnRules(alertBox);
        diceRules = new DiceRules(alertBox);
    }

    /**
     * Checks all Rules and if List is Empty rules are also true.
     * @param currentTurnTaps list of current marked fields.
     * @return if all rules are accepted.
     */
    public boolean checkRules(final List<AbstractField> currentTurnTaps) {
        return true;
       // return currentTurnTaps.isEmpty() || turnRules.isTurnValid(currentTurnTaps)
         //       && diceRules.checkDiceRules(currentTurnTaps);
        //  return diceRules.checkDiceRules(currentTurnTaps);
    }

    /**
     * set current dice.
     * @param dice current die.
     */
    public void setDice(final Dice dice) {
        diceRules.setDice(dice);
    }

    /**
     * set current fieldMap.
     * @param fieldMap current fieldMap.
     */
    public void setFieldMap(final FieldMap fieldMap) {
        turnRules.setFieldMap(fieldMap);
    }

}
