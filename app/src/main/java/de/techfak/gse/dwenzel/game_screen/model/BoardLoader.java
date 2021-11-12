package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.techfak.gse.dwenzel.game_screen.controller.PlaygroundController;

/**
 * Load the Board view and Images from assets.
 * set it up in Layouts.
 */
public class BoardLoader {
    /*Char to begin with big Letter ABC */          private static final int AINASCII = 64;
    private final int col;
    private final int row;
    private final Context context;
    private final int buttonSize;
    private final ArrayList<Drawable> pressedButtonImages = new ArrayList<>();
    private final ArrayList<Drawable> noPressedButtonImages = new ArrayList<>();
    private final TextView[][] cordBoard;


    /**
     * Load the Board view and Images from assets.
     *
     * @param context    context from BoardActivity
     * @param row        max Row size of the board.
     * @param col        max Column size of the board.
     * @param buttonSize max Button Size.
     */
    public BoardLoader(final Context context, final int row, final int col, final int buttonSize) {
        this.buttonSize = buttonSize;
        this.context = context;
        this.row = row;
        this.col = col;
        cordBoard = new TextView[row + 1][col + 1];

        loadImages();


    }

    /**
     * Images
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     */
    private void loadImages() {


        final DataLoader dataLoader = new DataLoader();


        pressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_pressed_yellow.png"));
        pressedButtonImages.get(0).setBounds(0, 0, buttonSize, buttonSize);
        noPressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_notPressed_yellow.png"));
        noPressedButtonImages.get(0).setBounds(0, 0, buttonSize, buttonSize);

        pressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_pressed_green.png"));
        pressedButtonImages.get(1).setBounds(0, 0, buttonSize, buttonSize);
        noPressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_notPressed_green.png"));
        noPressedButtonImages.get(1).setBounds(0, 0, buttonSize, buttonSize);


        pressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_pressed_red.png"));
        pressedButtonImages.get(2).setBounds(0, 0, buttonSize, buttonSize);
        noPressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_notPressed_red.png"));
        noPressedButtonImages.get(2).setBounds(0, 0, buttonSize, buttonSize);
        pressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_pressed_orange.png"));
        pressedButtonImages.get(3).setBounds(0, 0, buttonSize, buttonSize);
        noPressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_notPressed_orange.png"));
        noPressedButtonImages.get(3).setBounds(0, 0, buttonSize, buttonSize);
        pressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_pressed_blue.png"));
        pressedButtonImages.get(4).setBounds(0, 0, buttonSize, buttonSize);
        noPressedButtonImages.add(dataLoader.loadDrawableFromAssets(context, "icon_notPressed_blue.png"));
        noPressedButtonImages.get(4).setBounds(0, 0, buttonSize, buttonSize);




    }

    /**
     * Setting up vertical Layout for showing cords on the board.
     *
     * @param verticalLayout layout for  column cords.
     */
    public void setVerticalCoordinateView(final LinearLayout verticalLayout) {

        for (int i = 0; i < col + 1; i++) {
            cordBoard[0][i] = new TextView(context);
            cordBoard[0][i].setLayoutParams(
                    new LinearLayout.LayoutParams(buttonSize,
                            buttonSize));
            cordBoard[0][i].setText(String.valueOf(i));
            verticalLayout.addView(cordBoard[0][i]);
        }
    }

    /**
     * Setting up vertical Layout for showing cords on the board.
     *
     * @param horizontalLayout layout for row cords.
     */
    public void setHorizontalCoordinateView(final LinearLayout horizontalLayout) {
        for (int i = 0; i < row + 1; i++) {
            cordBoard[i][0] = new TextView(context);
            cordBoard[i][0].setLayoutParams(
                    new LinearLayout.LayoutParams(buttonSize,
                            buttonSize));
            cordBoard[i][0].setText(Character.toString((char) (i + AINASCII)));
            horizontalLayout.addView(cordBoard[i][0]);
        }
    }

    /**
     * setting up view in grid for play Buttons.
     * @param gridLayoutButtons grid layout for buttons.
     * @param playgroundController controller to get the values.
     */
    public void setButtonPlayground(final GridLayout gridLayoutButtons, final PlaygroundController playgroundController) {
        final ImageButton[][] playgroundButtons =
                new ImageButton[row]
                        [col];
        for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                playgroundButtons[k][i] = new ImageButton(context);

                playgroundButtons[k][i].setLayoutParams(
                        new LinearLayout.LayoutParams(buttonSize,
                                buttonSize));

                ImageButton button;
                button = getFieldColor(playgroundButtons[k][i],
                        playgroundController, k, i);
                button.setBackground(null);
                button.setAdjustViewBounds(true);
                button.setPadding(0, 0, 0, 0);
                button.setScaleType(ImageButton.ScaleType.FIT_START);
                playgroundController.getPlayground().setFieldButton(button, k, i);
                gridLayoutButtons.addView(button);


            }


        }
    }

    /**
     * setting up the images for buttons.
     * @param imageButton button id.
     * @param playgroundController controller for values.
     * @param rowCord row cord.
     * @param colCord column cord.
     * @return ImageButton with set up image.
     */
    private ImageButton getFieldColor(final ImageButton imageButton,
                                      final PlaygroundController playgroundController,
                                      final int rowCord,
                                      final int colCord) {
        final boolean isPressed = playgroundController.getPlayground().isFieldCrossed(rowCord, colCord);
        final String color = playgroundController.getPlayground().getFieldColor(rowCord, colCord);

        int indexOfColor = 0;

        if (color.equals("Green")) {
            indexOfColor = 1;
        }
        if (color.equals("Red")) {
            indexOfColor = 2;
        }
        if (color.equals("Orange")) {
            indexOfColor = 3;
        }
        if (color.equals("Blue")) {
            indexOfColor = 4;
        }
        if (isPressed) {
            imageButton.setImageDrawable(pressedButtonImages.get(indexOfColor));
        }
        if (!isPressed) {
            imageButton.setImageDrawable(noPressedButtonImages.get(indexOfColor));
        }

        return imageButton;
    }


}
