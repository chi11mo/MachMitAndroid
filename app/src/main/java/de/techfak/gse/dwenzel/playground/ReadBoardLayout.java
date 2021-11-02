package de.techfak.gse.dwenzel.playground;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;

public class ReadBoardLayout {

    /**
     * Max Row Size.
     */

    private static final int PLAYGROUND_ROW_SIZE
            = 15;
    /**
     * Max Col Size.
     */

    private static final int PLAYGROUND_COL_SIZE
            = 7;


    /**
     * field array for playground description.
     */
    private static final Field[][] field = new Field[PLAYGROUND_ROW_SIZE][PLAYGROUND_COL_SIZE];

    /**
     * Board layout file.
     */
    private final InputStream boardFile;

    /**
     * Playground als Array.
     */
    private final String[][] pgGrid =
            new String[PLAYGROUND_ROW_SIZE]
                    [PLAYGROUND_COL_SIZE];

    /**
     * save Exceptions.
     */
    String exception;

    public ReadBoardLayout(final InputStream file) {
        boardFile = file;

        pgGrid[0][0] = " ";

        Field newField;

        validBoardLayout();
        // let's loop through array to populate board
        for (int row = 0; row < pgGrid.length; row++) {
            for (int col = 0; col < pgGrid[row].length; col++) {

                char letter = pgGrid[row][col].charAt(0);

                field[row][col] = new Field(row,col,pgGrid[row][col],Character.isUpperCase(letter));//.addField(row, col, pgGrid[row][col], Character.isUpperCase(letter));

            }
        }


    }


    /**
     * @return all fields from playground.
     */
    public Field[][] getFields() {
        return field;
    }

    /**
     * board and field validation.
     * Saves fields in 2d Array pgGrid[][].
     */
    void validBoardLayout() {
        BufferedReader reader;
        int rowCounter;
        int colCounter = 0;
        try {

            reader = new BufferedReader(new InputStreamReader(boardFile));
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {

                    pgGrid[i][colCounter] = String.valueOf(line.charAt(i));


                }
                line = reader.readLine();
                colCounter++;
            }


        } catch (Exception e) {
            e.printStackTrace();
            // exit(EXITCODE);
        }


    }



}
