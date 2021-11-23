package de.techfak.gse.dwenzel.game_screen.map;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.techfak.gse.dwenzel.R;

public class CoordinateView {
    private static final int AINASCII = 64;
    private static final int TEXT_SIZE = 24;
    private final int maxRow;
    private final int maxCol;
    private final int buttonSize;
    private final TextView[][] cordBoard;
    private final Context context;

    /**
     * This will create the Corrdinate Row and Col for the GameView.
     *
     * @param context get context for main activity.
     */
    public CoordinateView(final Context context) {
        this.context = context;

        maxRow = context.getResources().getInteger(R.integer.PlaygroundRow);
        maxCol = context.getResources().getInteger(R.integer.PlaygroundCol);
        buttonSize = context.getResources().getInteger(R.integer.ButtonSizeLayoutParam);
        cordBoard = new TextView[maxRow + 1][maxCol + 1];
        //context.setContentView(R.layout.activity_board);
    }

    /**
     * Setting up vertical layout for Column coordinates.
     *
     * @param verticalLayout is LinearLayout.
     */
    public void setVerticalCoordinateView(final LinearLayout verticalLayout) {

        for (int i = 0; i < maxCol + 1; i++) {
            cordBoard[0][i] = new TextView(context);
            cordBoard[0][i].setTextSize(TEXT_SIZE);
            cordBoard[0][i].setLayoutParams(
                    new LinearLayout.LayoutParams(buttonSize, buttonSize));
            cordBoard[0][i].setText(String.valueOf(i));
            verticalLayout.addView(cordBoard[0][i]);
        }
    }

    /**
     * Setting up vertical Layout for showing cords on the board.
     *
     * @param horizontalLayout layout for row cords.
     */
    public void setHorizontalCoordinateView(
            final LinearLayout horizontalLayout) {
        for (int i = 0; i < maxRow + 1; i++) {
            cordBoard[i][0] = new TextView(context);
            cordBoard[i][0].setTextSize(TEXT_SIZE);
            cordBoard[i][0].setLayoutParams(
                    new LinearLayout.LayoutParams(buttonSize, buttonSize));
            cordBoard[i][0].setText(Character.toString((char) (i + AINASCII)));
            horizontalLayout.addView(cordBoard[i][0]);
        }
    }
}
