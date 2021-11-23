package de.techfak.gse.dwenzel.game_screen.controller;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
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
        final AbstractField[][] abstractFields = fieldMap.getFields();
        //clear last Layout and update new Board.
        //gridLayout.removeAllViews();
        for (int iRow = 0; iRow < fieldMap.getMaxRow(); iRow++) {
            for (int iCol = 0; iCol < fieldMap.getMaxCol(); iCol++) {
                abstractFields[iRow][iCol].setButton(new ImageButton(context));
                abstractFields[iRow][iCol].setRow(iRow);
                abstractFields[iRow][iCol].setCol(iCol);

                abstractFields[iRow][iCol].getButton().setLayoutParams(
                        new LinearLayout.LayoutParams(context.getResources()
                                .getInteger(R.integer.ButtonSizeLayoutParam),
                                context.getResources()
                                        .getInteger(
                                                R.integer.ButtonSizeLayoutParam)));
                /*setting up single button settings sizes and padding*/

                abstractFields[iRow][iCol].getButton()
                        .setAdjustViewBounds(true);
                abstractFields[iRow][iCol].getButton()
                        .setBackground(null);
                abstractFields[iRow][iCol].getButton()
                        .setPadding(0, 0, 0, 0);
                abstractFields[iRow][iCol].getButton()
                        .setScaleType(ImageButton.ScaleType.FIT_START);
                abstractFields[iRow][iCol].getButton()
                        .setImageDrawable(abstractFields[iRow][iCol]
                                .getDrawableField());
                //if the field is already crossed the field isn't able to tab again.
                if (abstractFields[iRow][iCol].isCrossed()) {
                    abstractFields[iRow][iCol].getButton().setEnabled(false);
                }

                gridLayout.addView(abstractFields[iRow][iCol].getButton());

            }


        }
        return gridLayout;
    }
}
