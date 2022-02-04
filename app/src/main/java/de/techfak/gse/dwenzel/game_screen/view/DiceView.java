package de.techfak.gse.dwenzel.game_screen.view;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.model.Dice;
import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;
import de.techfak.gse.multiplayer.game.DieColor;
import de.techfak.gse.multiplayer.game.DieNumber;
import de.techfak.gse.multiplayer.server.response_body.DiceResponse;

public class DiceView {
    private static final int THREE_EYE = 3;
    private static final int FOUR_EYE = 4;
    private static final int FIVE_EYE = 5;
    private final Context context;
    private Dice dice;

    /**
     * Dice view to update the view of the dices.
     *
     * @param context context form main app.
     */
    public DiceView(final Context context) {
        this.context = context;
    }

    /**
     * Setting the image views for dices.
     * and create the die animation.
     *
     * @param numberList dice array with drawables from eye number.
     * @param colorList  all randomized color integer.
     */
    public void setDice(final List<Integer> numberList, final List<Integer> colorList) {
        DiceSpriteSheet diceSpriteSheet = new DiceSpriteSheet(context);
        final Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        ((Activity) context)
                .findViewById(R.id.ColorOne).startAnimation(animation);
        ((Activity) context)
                .findViewById(R.id.ColorTwo).startAnimation(animation);
        ((Activity) context)
                .findViewById(R.id.ColorThree).startAnimation(animation);
        ((Activity) context)
                .findViewById(R.id.DiceOne).startAnimation(animation);
        ((Activity) context)
                .findViewById(R.id.DiceTwo).startAnimation(animation);
        ((Activity) context)
                .findViewById(R.id.DiceThree).startAnimation(animation);


        ((Activity) context)
                .findViewById(R.id.ColorOne)
                .setBackgroundColor(
                        diceSpriteSheet.getListOfDiceColorDrawable().get(colorList.get(0)));
        ((Activity) context)
                .findViewById(R.id.ColorTwo)
                .setBackgroundColor(
                        diceSpriteSheet.getListOfDiceColorDrawable().get(colorList.get(1)));
        ((Activity) context)
                .findViewById(R.id.ColorThree)
                .setBackgroundColor(
                        diceSpriteSheet.getListOfDiceColorDrawable().get(colorList.get(2)));
        ((Activity) context)
                .findViewById(R.id.DiceOne)
                .setBackgroundDrawable(
                        diceSpriteSheet.getListOfDiceNumberDrawable().get(numberList.get(0)));

        ((Activity) context)
                .findViewById(R.id.DiceTwo)
                .setBackgroundDrawable(
                        diceSpriteSheet.getListOfDiceNumberDrawable().get(numberList.get(1)));
        ((Activity) context)
                .findViewById(R.id.DiceThree)
                .setBackgroundDrawable(
                        diceSpriteSheet.getListOfDiceNumberDrawable().get(numberList.get(2)));
    }

    /**
     * Get information from server Response.
     *
     * @param diceResponse body DiceResponse.
     */
    public void setDiceFromServer(final DiceResponse diceResponse) {
        final List<Integer> numberList = new ArrayList<>();
        numberList.add(convertDieNumberToInt(diceResponse.getNumbers().get(0)));
        numberList.add(convertDieNumberToInt(diceResponse.getNumbers().get(1)));
        numberList.add(convertDieNumberToInt(diceResponse.getNumbers().get(2)));

        final List<Integer> colorList = new ArrayList<>();

        colorList.add(convertDieColorToInt(diceResponse.getColors().get(0)));
        colorList.add(convertDieColorToInt(diceResponse.getColors().get(1)));
        colorList.add(convertDieColorToInt(diceResponse.getColors().get(2)));
        setDiceFor(numberList, colorList);
        setDice(numberList, colorList);

    }

    /**
     * getDice.
     * @return dice from sever.
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * set Dice From server.
     * @param numberList number list.
     * @param colorList color list.
     */
    public void setDiceFor(final List<Integer> numberList, final List<Integer> colorList) {
        dice = new Dice();
        dice.setList(colorList, numberList);
    }

    /**
     * Convert server params to local Params.
     *
     * @param dieColor server param.
     * @return local param.
     */
    private Integer convertDieColorToInt(final DieColor dieColor) {
        if (dieColor == DieColor.YELLOW) {
            return 0;
        }
        if (dieColor == DieColor.GREEN) {
            return 1;
        }
        if (dieColor == DieColor.RED) {
            return 2;
        }
        if (dieColor == DieColor.ORANGE) {
            return THREE_EYE;
        }
        if (dieColor == DieColor.BLUE) {
            return FOUR_EYE;
        }
        return 0;
    }

    /**
     * Convert server params to local Params.
     *
     * @param dieNumber server param.
     * @return local param.
     */
    private int convertDieNumberToInt(final DieNumber dieNumber) {
        if (dieNumber == DieNumber.ONE) {
            return 1;
        }
        if (dieNumber == DieNumber.TWO) {
            return 2;
        }
        if (dieNumber == DieNumber.THREE) {
            return THREE_EYE;
        }
        if (dieNumber == DieNumber.FOUR) {
            return FOUR_EYE;
        }
        if (dieNumber == DieNumber.FIVE) {
            return FIVE_EYE;
        }
        return 0;

    }
}
