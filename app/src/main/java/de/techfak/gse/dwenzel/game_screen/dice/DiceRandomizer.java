package de.techfak.gse.dwenzel.game_screen.dice;

import java.util.Random;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class DiceRandomizer {
    public static final int DICE_MAX_INDEX = 6;
    private final DiceSpriteSheet diceSpriteSheet;
    private final DiceCreator diceCreator;
    private Random random;
    private int[] diceNumbers;

    /**
     * randomize dices.
     *
     * @param diceSpriteSheet spriteSheet dices.
     */
    public DiceRandomizer(final DiceSpriteSheet diceSpriteSheet) {
        this.diceSpriteSheet = diceSpriteSheet;
        diceCreator = new DiceCreator(diceSpriteSheet);
        random = new Random();
        diceNumbers = new int[3];
    }

    /**
     * Create a array with all crates dices.
     *
     * @return array with dices.
     */
    public AbstractDice[] getDices() {
        AbstractDice[] dices;
        dices = diceCreator.getDice();
        diceNumbers[0] = randomNumber();
        diceNumbers[1] = randomNumber();
        diceNumbers[2] = randomNumber();
        dices[0] = diceCreator.getDice()[diceNumbers[0]];
        dices[1] = diceCreator.getDice()[diceNumbers[1]];
        dices[2] = diceCreator.getDice()[diceNumbers[2]];

        return dices;
    }

    /**
     * get rolled dice numbers.
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
        int tmpNumber = 0;

        do {

            tmpNumber = random.nextInt(DICE_MAX_INDEX);
        } while (tmpNumber == 0);
        return tmpNumber;
    }
}
