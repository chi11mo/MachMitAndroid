package de.techfak.gse.dwenzel.game_screen.dice;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;
import java.util.Observable;

import de.techfak.gse.dwenzel.R;

public class DiceView{
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
     * @param dice      dice array with drawables from eye number.
     * @param colorList all randomized color integer.
     */
    public void setDice(final AbstractDice[] dice, final List colorList) {

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
                .findViewById(R.id.ColorOne).setBackgroundColor((Integer) colorList.get(0));
        ((Activity) context)
                .findViewById(R.id.ColorTwo).setBackgroundColor((Integer) colorList.get(1));
        ((Activity) context)
                .findViewById(R.id.ColorThree).setBackgroundColor((Integer) colorList.get(2));
        ((Activity) context)
                .findViewById(R.id.DiceOne).setBackgroundDrawable(dice[0].getDrawableDice());

        ((Activity) context)
                .findViewById(R.id.DiceTwo).setBackgroundDrawable(dice[1].getDrawableDice());
        ((Activity) context)
                .findViewById(R.id.DiceThree).setBackgroundDrawable(dice[2].getDrawableDice());
    }
}
