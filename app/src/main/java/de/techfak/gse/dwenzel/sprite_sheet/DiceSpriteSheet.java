package de.techfak.gse.dwenzel.sprite_sheet;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;

public class DiceSpriteSheet {
    private final DataLoader dataLoader = new DataLoader();
    private final Context context;


    private final List<Integer> listOfDiceColorDrawable = new ArrayList<Integer>();
    private final List<Drawable> listOfDiceNumberDrawable = new ArrayList<Drawable>();


    public DiceSpriteSheet(final Context context) {
        this.context = context;

        listOfDiceColorDrawable.add(
                context.getResources().getColor(R.color.yellow));
        listOfDiceColorDrawable.add(
                context.getResources().getColor(R.color.green));
        listOfDiceColorDrawable.add(
                context.getResources().getColor(R.color.red));
        listOfDiceColorDrawable.add(
                context.getResources().getColor(R.color.orange));
        listOfDiceColorDrawable.add(
                context.getResources().getColor(R.color.blue));
        listOfDiceNumberDrawable.add(getOneDiceDrawable());
        listOfDiceNumberDrawable.add(getTwoDiceDrawable());
        listOfDiceNumberDrawable.add(getThreeDiceDrawable());
        listOfDiceNumberDrawable.add(getFourDiceDrawable());
        listOfDiceNumberDrawable.add(getFiveDiceDrawable());
        listOfDiceNumberDrawable.add(getSixDiceDrawable());

    }

    /**
     * to get the List of Dice Drawables.
     *
     * @return list of Dice drawablews.
     */
    public List<Integer> getListOfDiceColorDrawable() {
        return listOfDiceColorDrawable;
    }

    /**
     * to get the List of Dice Drawables.
     *
     * @return list of Dice drawablews.
     */
    public List<Drawable> getListOfDiceNumberDrawable() {
        return listOfDiceNumberDrawable;
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getOneDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-1.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getTwoDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-2.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getThreeDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-3.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getFourDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-4.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getFiveDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-5.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getSixDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-6.png");
    }
}
