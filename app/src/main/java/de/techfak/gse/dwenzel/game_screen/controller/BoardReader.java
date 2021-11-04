package de.techfak.gse.dwenzel.game_screen.controller;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.game_screen.model.Field;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

public class BoardReader implements IBoardReader {

    /**
     * field array for playground description.
     */
    private final Field[][] field;
    /**
     * Playground als Array.
     */
    private final String[][] pgGrid;
    /**
     * Board file with file and maxRow and Col range.
     */
    BoardFile board;

    public BoardReader( BoardFile board) {
        this.board = board;

        field = new Field[board.getMaxRow()][board.getMaxCol()];
        pgGrid = new String[board.getMaxRow()][board.getMaxCol()];

        validBoardLayout();
        fieldInit();
    }

    /**
     * Init the fields.
     */
    private void fieldInit(){
        // let's loop through array to populate board
        for (int row = 0; row < pgGrid.length; row++) {
            for (int col = 0; col < pgGrid[row].length; col++) {

                char letter = pgGrid[row][col].charAt(0);

                field[row][col] = new Field(row, col, pgGrid[row][col], Character.isUpperCase(letter));

            }
        }
    }

    /**
     * Read the lines from data and save it.
     */
    private void validBoardLayout() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(board.getBoardFile()));

        int colCounter = 0;
        try {

            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {

                    pgGrid[i][colCounter] = String.valueOf(line.charAt(i));


                }
                line = reader.readLine();
                colCounter++;
            }


        } catch (Exception e) {
            Log.d("Exception", String.valueOf(e));

        } finally {
            try {
                reader.close(); //Here it says unreported exception IOException; must be caught or declared to be thrown
            } catch (Exception exp) {
                Log.d("Exception", String.valueOf(exp));
            }
        }


    }

    /**
     * to get the saved fields.
     * @return saved fields from data.
     */
    @Override
    public Field[][] getFields() {
        return field;
    }
}
