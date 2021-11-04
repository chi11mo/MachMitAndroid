package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.Button;

import de.techfak.gse.dwenzel.game_screen.controller.BoardReader;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

public class PlaygroundModel implements IPlaygroundModel {
    /**
     * filled with field information.
     */
    private final Field[][] field;

    /**
     * boardFile with InputStream file and maxRow and maxCol.
     */
    BoardFile board;


    /**
     * Creating PlaygroundModel to safe Playground data.
     *
     * @param board boardFile
     */
    public PlaygroundModel(BoardFile board) {
        this.board = board;
        BoardReader boardReader = new BoardReader(board);
        field = boardReader.getFields();

    }

    /**
     * @param button button.
     * @param row    for button location.
     * @param col    for button location.
     */
    @Override
    public void setFieldButton(Button button, int row, int col) {
        field[row][col].setButton(button);
    }

    /**
     * @param row for button location.
     * @param col for button location.
     */
    @Override
    public Button getFieldButton(int row, int col) {
        return field[row][col].getButton();
    }

    /**
     * @param row for button location.
     * @param col for button location.
     */
    @Override
    public String getFieldColor(int row, int col) {
        return field[row][col].getFieldColor();
    }

    /**
     * @param row for button location.
     * @param col for button location.
     */
    @Override
    public boolean isFieldCrossed(int row, int col) {
        return field[row][col].isCrossed();
    }


}
