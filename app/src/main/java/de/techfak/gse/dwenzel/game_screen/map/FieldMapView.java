package de.techfak.gse.dwenzel.game_screen.map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.sprite_sheet.ButtonSpriteSheet;

public class FieldMapView {
    private final Context context;
    private final ButtonSpriteSheet buttonSpriteSheet;
    private FieldMap fieldMap;


    private ImageButton[][] buttonGameGroupe;

    /**
     * Field Map view .
     *
     * @param context  view from main Activity.
     * @param fieldMap
     */
    public FieldMapView(final Context context, final FieldMap fieldMap) {
        this.context = context;
        this.fieldMap = fieldMap;
        buttonSpriteSheet = new ButtonSpriteSheet(context);
        buttonGameGroupe = new ImageButton[fieldMap.getMaxRow()][fieldMap.getMaxCol()];

        createFieldMapGameboard(fieldMap);
    }

    private void createFieldMapGameboard(final FieldMap fieldMap) {

        GridLayout gridLayout = (GridLayout) ((Activity) context).findViewById(R.id.playground_grid);
        gridLayout.removeAllViews();
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                buttonGameGroupe[iRow][iCol] = new ImageButton(context);
                Drawable currentButtonDrawable = getColorButtonDrawable(fieldMap.getFields()[iRow][iCol].getFieldIdxColor()
                        , fieldMap.getFields()[iRow][iCol].isCrossed());
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
        } else
            return buttonSpriteSheet.getListOfButtonDrawable().get(fieldIdxColor);
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
     * unMark current taps.
     *
     * @param iRow row of tap
     * @param iCol col of tap
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

    public void updateFieldMap(final FieldMap fieldMap) {
        createFieldMapGameboard(fieldMap);
    }


    public void removeAllMarks() {
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                buttonGameGroupe[iRow][iCol].setBackgroundColor(0);
            }
        }
    }
}
