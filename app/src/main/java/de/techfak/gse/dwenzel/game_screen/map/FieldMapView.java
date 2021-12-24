package de.techfak.gse.dwenzel.game_screen.map;

import android.app.Activity;
import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.techfak.gse.dwenzel.R;

public class FieldMapView {
    private final Context context;
    public FieldMapView(Context context) {
        this.context = context;
    }


    public void createFieldMap(final GridLayout gridLayout, final FieldMap fieldMap){
            gridLayout.removeAllViews();
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


    }
    /**
     * Add current mark to the view.
     *
     * @param field field to mark.
     */
    public void addField(final AbstractField field) {
            field.setIsCrossed(true);
            field.getButton().setImageDrawable(field
                    .getDrawableField(true));
        }
    /**
     * removel current mark to the view.
     *
     * @param field field to mark.
     */
    public void removeField(final AbstractField field) {
        field.setIsCrossed(false);
        field.getButton().setImageDrawable(field
                .getDrawableField(false));
    }
}
