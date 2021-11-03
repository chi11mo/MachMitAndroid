package de.techfak.gse.dwenzel.playground;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This Class read all the Stuff from valid data board file.
 */
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


    public ReadBoardLayout(final InputStream file) {
        boardFile = file;

        pgGrid[0][0] = " ";


        validBoardLayout();
        // let's loop through array to populate board
        for (int row = 0; row < pgGrid.length; row++) {
            for (int col = 0; col < pgGrid[row].length; col++) {

                char letter = pgGrid[row][col].charAt(0);

                field[row][col] = new Field(row, col, pgGrid[row][col], Character.isUpperCase(letter));

            }
        }


    }


    /**
     * get the fields for Playground class from data file.
     *
     * @return all fields from playground.
     */
    public Field[][] getFields() {
        return field;
    }

    /**
     * board and field validation.
     * Saves fields in 2d Array pgGrid[][].
     */
    private void validBoardLayout() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(boardFile));

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


}
