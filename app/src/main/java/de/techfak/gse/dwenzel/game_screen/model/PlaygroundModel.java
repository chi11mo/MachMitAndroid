package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.Button;

import de.techfak.gse.dwenzel.game_screen.controller.BoardReader;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * Model to describe the field layout from the game board.
 */
public class PlaygroundModel {

    /*filled with field information.*/                          private final Field[][] field;


    /*boardFile with InputStream file and maxRow and maxCol.*/ BoardFile board;


    /**
     * Creating PlaygroundModel to safe Playground data.
     *
     * @param board boardFile
     */
    public PlaygroundModel(final BoardFile board) {
        this.board = board;
        BoardReader boardReader = new BoardReader(board);
        field = boardReader.getFields();

    }

    /**
     * setting up the color of the field.
     * @param button button.
     * @param row    for button location.
     * @param col    for button location.
     */
    public void setFieldButton(Button button, int row, int col) {
        field[row][col].setButton(button);
    }

    /**
     * get the button of the field.
     * @param row for button location.
     * @param col for button location.
     */
    public Button getFieldButton(int row, int col) {
        return field[row][col].getButton();
    }

    /**
     * get the color of the field.
     * @param row for button location.
     * @param col for button location.
     */
    public String getFieldColor(int row, int col) {
        return field[row][col].getFieldColor();
    }

    /**
     * get information of field is already crossed.
     * @param row for button location.
     * @param col for button location.
     */
    public boolean isFieldCrossed(int row, int col) {
        return field[row][col].isCrossed();
    }


}
