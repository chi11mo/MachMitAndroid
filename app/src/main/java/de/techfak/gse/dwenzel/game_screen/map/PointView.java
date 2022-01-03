package de.techfak.gse.dwenzel.game_screen.map;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;


import de.techfak.gse.dwenzel.R;

public class PointView {
    private static final int TEXT_SIZE = 24;
    private static final int PADDING_VIEW = 55;
    private final Context context;
    private final LinearLayout pointLayout;
    private final int[] pointRow = new int[]{5, 3, 3, 3, 2, 2, 2, 1, 2, 2, 2, 3, 3, 3, 5};
    private final int textViewSize;

    public PointView(final Context context) {
        this.context = context;
        pointLayout = ((Activity) context)
                .findViewById(R.id.horizontalPointLayout);
        createView();
        textViewSize = context.getResources().getInteger(R.integer.ButtonSizeLayoutParam);
    }

    private void createView() {
        final TextView[] pointViewField
                = new TextView[context.getResources().getInteger(R.integer.PlaygroundRow)];

        for (int iRow = 0; iRow < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {

            pointViewField[iRow] = new TextView(context);
            pointViewField[iRow].setTextSize(TEXT_SIZE);
            pointViewField[iRow].setPadding(PADDING_VIEW, 0, 0, 0);
            pointViewField[iRow].setText(String.valueOf(pointRow[iRow]));
            pointLayout.addView(pointViewField[iRow]);
        }
    }
}
