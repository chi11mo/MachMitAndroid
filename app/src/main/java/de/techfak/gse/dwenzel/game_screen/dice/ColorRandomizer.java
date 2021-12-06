package de.techfak.gse.dwenzel.game_screen.dice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.techfak.gse.dwenzel.R;

public class ColorRandomizer {
    public static final int COLOR_MAX_INDEX = 5;
    public static final int DICE_MAX_COUNT = 3;
    private final Random random;
    private final Context context;
    private int[] diceColorIndex;

    /**
     * Randomize Color for the dices.
     *
     * @param context app context.
     */
    public ColorRandomizer(final Context context) {
        this.context = context;
        random = new Random();
        diceColorIndex = new int[DICE_MAX_COUNT];
    }

    /**
     * create the Color list for the view.
     *
     * @return list with all randomized colors.
     */
    public List<Integer> getColorList() {
        List<Integer> colorList = new ArrayList<>();

        diceColorIndex[0] = randomNumber();
        diceColorIndex[1] = randomNumber();
        diceColorIndex[2] = randomNumber();
        colorList.add(getColorIdx(diceColorIndex[0]));
        colorList.add(getColorIdx(diceColorIndex[1]));
        colorList.add(getColorIdx(diceColorIndex[2]));
        return colorList;
    }

    /**
     * get the color indexes.
     * Yellow index 0
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     *
     * @return index of rolled colors.
     */
    public int[] getColorNumbers() {
        return diceColorIndex;
    }

    /**
     * Return color from right index.
     * Yellow index 0
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     *
     * @param randomNumber randomized Number to specified color.
     * @return color index.
     */
    private int getColorIdx(final int randomNumber) {
        List<Integer> colorList = new ArrayList<>();
        colorList.add(context.getResources().getColor(R.color.yellow));
        colorList.add(context.getResources().getColor(R.color.green));
        colorList.add(context.getResources().getColor(R.color.red));
        colorList.add(context.getResources().getColor(R.color.orange));
        colorList.add(context.getResources().getColor(R.color.blue));
        return colorList.get(randomNumber);
    }


    /**
     * Creates DiceNumber != 0.
     *
     * @return get DiceNumber.
     */
    private int randomNumber() {
        return random.nextInt(COLOR_MAX_INDEX);
    }
}
