package de.techfak.gse.dwenzel.game_screen.model.rules;

import java.util.List;
import java.util.Observable;

import de.techfak.gse.dwenzel.game_screen.model.Dice;
import de.techfak.gse.dwenzel.game_screen.model.Field;
import de.techfak.gse.dwenzel.game_screen.model.FieldMap;

public class Rules extends Observable {
    private final TurnRules turnRules;
    private final DiceRules diceRules;


    private String missedRuleInfo;

    /**
     * Model to check all the defined Game Rules for marked fields.
     */
    public Rules() {
        missedRuleInfo = null;
        turnRules = new TurnRules();
        diceRules = new DiceRules();
    }

    /**
     * Checks all Rules and if List is Empty rules are also true.
     *
     * @param currentTurnTaps list of current marked fields.
     * @return if all rules are accepted.
     */
    public boolean checkRules(final List<Field> currentTurnTaps) {
        if (currentTurnTaps.isEmpty()) {
            return true;
        }
        boolean isTurnRule = turnRules.isTurnValid(currentTurnTaps);
        // boolean isTurnRule = true;
        boolean isDiceRule = diceRules.checkDiceRules(currentTurnTaps);
        // Log.d("Turn Rules accept", String.valueOf(isTurnRule));
        // Log.d("Dice Rules accept", String.valueOf(isDiceRule));
        if (!isDiceRule) {
            missedRuleInfo = diceRules.getMissedRule();
            setChanged();
            notifyObservers();
            return false;
        }
        if (!isTurnRule) {
            missedRuleInfo = turnRules.getMissedRule();
            setChanged();
            notifyObservers();
            return false;
        }
        missedRuleInfo = null;
        return true;
    }

    /**
     * set current dice.
     *
     * @param dice current die.
     */
    public void setDice(final Dice dice) {
        diceRules.setDice(dice);
    }

    /**
     * set current fieldMap.
     *
     * @param fieldMap current fieldMap.
     */
    public void setFieldMap(final FieldMap fieldMap) {
        turnRules.setFieldMap(fieldMap);
    }


    public String getMissedRuleInfo() {
        return missedRuleInfo;
    }

}
