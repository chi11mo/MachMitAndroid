package de.techfak.gse.dwenzel.game_screen.dice;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class DiceView {
    private final Context context;

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
}
