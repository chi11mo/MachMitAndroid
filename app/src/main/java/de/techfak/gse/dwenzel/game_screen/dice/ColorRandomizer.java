package de.techfak.gse.dwenzel.game_screen.dice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.techfak.gse.dwenzel.R;

public class ColorRandomizer {
    public static final int COLOR_MAX_INDEX = 5;
    private final Random random;
    private final Context context;

    /**
     * Randomize Color for the dices.
     *
     * @param context app context.
     */
    public ColorRandomizer(final Context context) {
        this.context = context;
        random = new Random();
    }

    /**
     * create the Color list for the view.
     *
     * @return list with all randomized colors.
     */
    public List<Integer> getColorList() {
        List<Integer> colorList = new ArrayList<>();
        colorList.add(getColorIdx(randomNumber()));
        colorList.add(getColorIdx(randomNumber()));
        colorList.add(getColorIdx(randomNumber()));
        return colorList;
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
        int tmpNumber = 0;

        tmpNumber = random.nextInt(COLOR_MAX_INDEX);
        return tmpNumber;
    }
}
