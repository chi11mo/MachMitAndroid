package de.techfak.gse.dwenzel.game_screen.dice;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public abstract class AbstractDice {

    private final DiceSpriteSheet diceSpriteSheet;
    private int diceIndex = 0;

    public AbstractDice(final DiceSpriteSheet diceSpriteSheet) {
        this.diceSpriteSheet = diceSpriteSheet;
    }

    /**
     * get the drawable of the field.
     *
     * @return drawable of the current field.
     */
    public abstract Drawable getDrawableDice();

    /**
     * set index of Dices.
     *
     * @param diceIndex idx of dices.
     */
    public void setDiceIndex(final int diceIndex) {
        this.diceIndex = diceIndex;
    }

    /**
     * get number of dices.
     *
     * @return int index of dices.
     */
    public int getDiceIndex() {
        return diceIndex;
    }

    /**
     * Dice to checkout number and color for turn rules.
     *
     * @param idxDiceType     index of dice type.
     * @param diceSpriteSheet sprite sheet to get drawables for the dice.
     * @return the dice type.
     */
    public static AbstractDice getDice(final int idxDiceType,
                                       final DiceSpriteSheet diceSpriteSheet) {
        switch (DiceType.values()[idxDiceType]) {
            case ONE_DICE:
                return new OneAbstractDice(diceSpriteSheet);
            case TWO_DICE:
                return new TwoAbstractDice(diceSpriteSheet);
            case THREE_DICE:
                return new ThreeAbstractDice(diceSpriteSheet);
            case FOUR_DICE:
                return new FourAbstractDice(diceSpriteSheet);
            case FIVE_DICE:
                return new FiveAbstractDice(diceSpriteSheet);
            case SIX_DICE:
                return new SixAbstractDice(diceSpriteSheet);
            default:
                return null;
        }
    }

    /**
     * diceTypes.
     */
    public enum DiceType {
        ONE_DICE,
        TWO_DICE,
        THREE_DICE,
        FOUR_DICE,
        FIVE_DICE,
        SIX_DICE
    }
}
