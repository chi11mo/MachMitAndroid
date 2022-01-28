package de.techfak.gse.dwenzel.sprite_sheet;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.game_screen.model.DataLoader;

public class ButtonSpriteSheet {
    private final DataLoader dataLoader = new DataLoader();


    private final List<Drawable> listOfButtonDrawableCrossed = new ArrayList<Drawable>();
    private final List<Drawable> listOfButtonDrawable = new ArrayList<Drawable>();
    private final Context context;

    /**
     * loader for buttons sprites.
     *
     * @param context context from board view.
     */
    public ButtonSpriteSheet(final Context context) {
        this.context = context;
        listOfButtonDrawable.add(
                dataLoader.loadDrawableFromAssets(context, "icon_notPressed_yellow.png"));
        listOfButtonDrawable.add(
                dataLoader.loadDrawableFromAssets(context, "icon_notPressed_green.png"));
        listOfButtonDrawable.add(
                dataLoader.loadDrawableFromAssets(context, "icon_notPressed_red.png"));
        listOfButtonDrawable.add(
                dataLoader.loadDrawableFromAssets(context, "icon_notPressed_orange.png"));
        listOfButtonDrawable.add(
                dataLoader.loadDrawableFromAssets(context, "icon_notPressed_blue.png"));

        listOfButtonDrawableCrossed.add(
                dataLoader.loadDrawableFromAssets(context, "icon_pressed_yellow.png"));
        listOfButtonDrawableCrossed.add(
                dataLoader.loadDrawableFromAssets(context, "icon_pressed_green.png"));
        listOfButtonDrawableCrossed.add(
                dataLoader.loadDrawableFromAssets(context, "icon_pressed_red.png"));
        listOfButtonDrawableCrossed.add(
                dataLoader.loadDrawableFromAssets(context, "icon_pressed_orange.png"));
        listOfButtonDrawableCrossed.add(
                dataLoader.loadDrawableFromAssets(context, "icon_pressed_blue.png"));
    }

    /**
     * to get the List of Button Drawables.
     *
     * @return list of Button drawablews.
     */
    public List<Drawable> getListOfButtonDrawableCrossed() {
        return listOfButtonDrawableCrossed;
    }

    /**
     * to get the List of Button Drawables.
     *
     * @return list of Button drawablews.
     */
    public List<Drawable> getListOfButtonDrawable() {
        return listOfButtonDrawable;
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @param isPressed information is the button pressed.
     * @return the loaded drawable.
     */
    public Drawable getYellowFieldDrawable(final boolean isPressed) {
        if (isPressed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_yellow.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_yellow.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @param isCrossed information is the button pressed.
     * @return the loaded drawable.
     */
    public Drawable getGreenFieldDrawable(final boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_green.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_green.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @param isCrossed information is the button pressed.
     * @return the loaded drawable.
     */
    public Drawable getRedFieldDrawable(final boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_red.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_red.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @param isCrossed information is the button pressed.
     * @return the loaded drawable.
     */
    public Drawable getOrangeFieldDrawable(final boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_orange.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_orange.png");
    }

    /**
     * get the right drawable and loaded it from png resources.
     *
     * @param isCrossed information is the button pressed.
     * @return the loaded drawable.
     */
    public Drawable getBlueFieldDrawable(final boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_blue.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_blue.png");
    }
}
