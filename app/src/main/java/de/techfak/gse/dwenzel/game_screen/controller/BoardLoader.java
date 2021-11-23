package de.techfak.gse.dwenzel.game_screen.controller;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.Field;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;

public class BoardLoader {
    private Context context;


    public BoardLoader(final Context context) {
        this.context = context;
    }

    /**
     * This method update Button field after cross
     * it and generate after start the Game.
     *
     * @param fieldMap   field Map with all fields.
     * @param gridLayout where the buttons get sorted.
     * @return a layout with all field buttons.
     */
    public GridLayout updateFieldMap(
            final GridLayout gridLayout, final FieldMap fieldMap) {
        final Field[][] fields = fieldMap.getFields();
        //clear last Layout and update new Board.
        //gridLayout.removeAllViews();
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                fields[iRow][iCol].setButton(new ImageButton(context));
                fields[iRow][iCol].setRow(iRow);
                fields[iRow][iCol].setCol(iCol);

                fields[iRow][iCol].getButton().setLayoutParams(
                        new LinearLayout.LayoutParams(context.getResources()
                                .getInteger(R.integer.ButtonSizeLayoutParam),
                                context.getResources()
                                        .getInteger(
                                                R.integer.ButtonSizeLayoutParam)));
                /*setting up single button settings sizes and padding*/

                fields[iRow][iCol].getButton()
                        .setAdjustViewBounds(true);
                fields[iRow][iCol].getButton()
                        .setBackground(null);
                fields[iRow][iCol].getButton()
                        .setPadding(0, 0, 0, 0);
                fields[iRow][iCol].getButton()
                        .setScaleType(ImageButton.ScaleType.FIT_START);
                fields[iRow][iCol].getButton()
                        .setImageDrawable(fields[iRow][iCol]
                                .getDrawableField());
                //if the field is already crossed the field isn't able to tab again.
                if (fields[iRow][iCol].isCrossed()) {
                    fields[iRow][iCol].getButton().setEnabled(false);
                }

                gridLayout.addView(fields[iRow][iCol].getButton());

            }


        }
        return gridLayout;
    }
}
