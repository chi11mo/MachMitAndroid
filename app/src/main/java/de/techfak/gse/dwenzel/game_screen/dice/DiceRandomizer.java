package de.techfak.gse.dwenzel.game_screen.dice;

import java.util.Random;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class DiceRandomizer {
    public static final int DICE_MAX_INDEX = 6;
    public static final int DICE_MAX_COUNT = 3;
    private final DiceCreator diceCreator;
    private final Random random;
    private final int[] diceNumbers;

    /**
     * randomize dices.
     *
     * @param diceSpriteSheet spriteSheet dices.
     */
    public DiceRandomizer(final DiceSpriteSheet diceSpriteSheet) {
        diceCreator = new DiceCreator(diceSpriteSheet);
        random = new Random();
        diceNumbers = new int[DICE_MAX_COUNT];
    }

    /**
     * Create a array with all crates dices.
     *
     * @return array with dices.
     */
    public AbstractDice[] getDices() {
        AbstractDice[] dices;
        dices = diceCreator.getDice();
        diceNumbers[0] = randomNumber() + 1;
        diceNumbers[1] = randomNumber() + 1;
        diceNumbers[2] = randomNumber() + 1;
        dices[0] = diceCreator.getDice()[diceNumbers[0] - 1];
        dices[1] = diceCreator.getDice()[diceNumbers[1] - 1];
        dices[2] = diceCreator.getDice()[diceNumbers[2] - 1];

        return dices;
    }

    /**
     * get rolled dice numbers.
     *
     * @return dice rolled.
     */
    public int[] getDiceNumbers() {
        return diceNumbers;
    }

    /**
     * Creates DiceNumber != 0.
     *
     * @return get DiceNumber.
     */
    private int randomNumber() {
        return random.nextInt(DICE_MAX_INDEX);
    }

}
