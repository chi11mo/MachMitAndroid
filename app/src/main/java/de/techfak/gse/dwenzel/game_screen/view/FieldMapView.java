package de.techfak.gse.dwenzel.game_screen.view;

import static android.graphics.Color.BLACK;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.model.FieldMap;
import de.techfak.gse.dwenzel.sprite_sheet.ButtonSpriteSheet;

public class FieldMapView {
    private final Context context;
    private final ButtonSpriteSheet buttonSpriteSheet;


    private ImageButton[][] buttonGameGroupe;

    /**
     * Field Map view .
     *
     * @param context view from main Activity.
     */
    public FieldMapView(final Context context) {
        this.context = context;
        buttonSpriteSheet = new ButtonSpriteSheet(context);
        buttonGameGroupe =
                new ImageButton[context.getResources().getInteger(R.integer.PlaygroundRow)]
                        [context.getResources().getInteger(R.integer.PlaygroundRow)];


    }

    /**
     * this method creates the Button field map for the Android view.
     * it creates row and col ImageButtons and sort it in a gridlayout.
     * Defines Button settings and set up the Drawables for each button.
     *
     * @param fieldMap current fieldMap.
     */
    public void createFieldMapGameboard(final FieldMap fieldMap) {

        GridLayout gridLayout =
                (GridLayout) ((Activity) context).findViewById(R.id.playground_grid);
        gridLayout.removeAllViews();
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                buttonGameGroupe[iRow][iCol] = new ImageButton(context);
                Drawable currentButtonDrawable =
                        getColorButtonDrawable(fieldMap.getFields()[iRow][iCol].getFieldIdxColor(),
                                fieldMap.getFields()[iRow][iCol].isCrossed());
                buttonGameGroupe[iRow][iCol].setImageDrawable(currentButtonDrawable);
                buttonGameGroupe[iRow][iCol].setLayoutParams(
                        new LinearLayout.LayoutParams(context.getResources()
                                .getInteger(R.integer.ButtonSizeLayoutParam),
                                context.getResources()
                                        .getInteger(
                                                R.integer.ButtonSizeLayoutParam)));

                buttonGameGroupe[iRow][iCol]
                        .setAdjustViewBounds(true);
                buttonGameGroupe[iRow][iCol]
                        .setBackground(null);
                buttonGameGroupe[iRow][iCol]
                        .setPadding(0, 0, 0, 0);
                buttonGameGroupe[iRow][iCol]
                        .setScaleType(ImageButton.ScaleType.FIT_START);

                if (fieldMap.getFields()[iRow][iCol].isCrossed()) {
                    buttonGameGroupe[iRow][iCol].setEnabled(false);
                }

                gridLayout.addView(buttonGameGroupe[iRow][iCol]);
            }
        }
    }

    /**
     * Helps to get right Drawable for play Buttons.
     *
     * @param fieldIdxColor idx of the color eg. (0 is yellow)
     * @param crossed       is the button already crissed.
     * @return the right drawable.
     */
    private Drawable getColorButtonDrawable(final int fieldIdxColor, final boolean crossed) {
        if (crossed) {
            return buttonSpriteSheet.getListOfButtonDrawableCrossed().get(fieldIdxColor);
        } else {
            return buttonSpriteSheet.getListOfButtonDrawable().get(fieldIdxColor);
        }
    }


    /**
     * Mark current taps.
     *
     * @param iRow row of tap
     * @param iCol col of tap
     */
    public void addFieldMark(final int iRow, final int iCol) {
        buttonGameGroupe[iRow][iCol].setBackgroundColor(BLACK);
    }

    /**
     * unMark current tabs.
     *
     * @param iRow row of tab.
     * @param iCol col of tab.
     */
    public void removeFieldMark(final int iRow, final int iCol) {
        buttonGameGroupe[iRow][iCol].setBackgroundColor(0);
    }

    /**
     * get buttons for integrate clickerListener.
     *
     * @return the Buttongroupe.
     */
    public ImageButton[][] getButtonGameGroupe() {
        return buttonGameGroupe;
    }

    /**
     * update current fieldMap.
     *
     * @param fieldMap current fieldMap.
     */
    public void updateFieldMap(final FieldMap fieldMap) {
        createFieldMapGameboard(fieldMap);
    }

    /**
     * This Method delete list with all marked tabs after a valid
     * or unvalid turn.
     *
     * @param fieldMap current fieldMap.
     */
    public void removeAllMarks(final FieldMap fieldMap) {
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                buttonGameGroupe[iRow][iCol].setBackgroundColor(0);
            }
        }
    }
}
