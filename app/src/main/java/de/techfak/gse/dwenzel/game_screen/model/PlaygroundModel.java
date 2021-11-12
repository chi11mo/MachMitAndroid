package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.ImageButton;

import de.techfak.gse.dwenzel.game_screen.controller.BoardReader;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * Model to describe the field layout from the game board.
 */
public class PlaygroundModel {
    /* board file with InputStream file and maxRow and maxCol. */ BoardFile board;
    /* filled with field information. */                          private final Field[][] field;



    /**
     * Creating PlaygroundModel to safe Playground data.
     *
     * @param board boardFile
     */
    public PlaygroundModel(final BoardFile board) {
        this.board = board;
        final BoardReader boardReader = new BoardReader(board);
        field = boardReader.getFields();

    }

    /**
     * setting up the color of the field.
     *
     * @param button button.
     * @param row    for button location.
     * @param col    for button location.
     */
    public void setFieldButton(final ImageButton button, final int row, final int col) {
        field[row][col].setButton(button);
    }

    /**
     * get the button of the field.
     *
     * @param row for button location.
     * @param col for button location.
     * @return field with the button.
     */
    public ImageButton getFieldButton(final int row, final int col) {
        return field[row][col].getButton();
    }

    /**
     * get the color of the field.
     *
     * @param row for button location.
     * @param col for button location.
     * @return field color.
     */
    public String getFieldColor(final int row, final int col) {
        return field[row][col].getFieldColor();
    }

    /**
     * get information of field is already crossed.
     *
     * @param row for button location.
     * @param col for button location.
     * @return is the field crossed.
     */
    public boolean isFieldCrossed(final int row, final int col) {
        return field[row][col].isCrossed();
    }



}
