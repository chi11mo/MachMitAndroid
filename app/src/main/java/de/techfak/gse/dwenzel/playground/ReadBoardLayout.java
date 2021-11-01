package de.techfak.gse.dwenzel.playground;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;

import static java.lang.System.exit;

public class ReadBoardLayout {
    /**
     * Exitcode for BoardLayout.
     */
    private static final int EXITCODE = 101;
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
    final Field[][] field = new Field[PLAYGROUND_ROW_SIZE][PLAYGROUND_COL_SIZE];

    /**
     * Board layout file.
     */
    private final InputStream boardFile;

    /**
     * Spielflaeche alls Array.
     */
    private final String[][] pgGrid =
            new String[PLAYGROUND_ROW_SIZE]
                    [PLAYGROUND_COL_SIZE];

    /**
     * save Exceptions.
     * @param file
     */
    String exception;

    public ReadBoardLayout(final InputStream file) {
        boardFile = file;

        pgGrid[0][0] = " ";


        validBoardLayout();
        // let's loop through array to populate board
        for (int row = 0; row < pgGrid.length; row++) {
            for (int col = 0; col < pgGrid[row].length; col++) {
                field[row][col] = new Field(row, col, pgGrid[row][col], Character.isUpperCase(pgGrid[row][col].charAt(0)));
            }
        }


    }


    /**
     * @return all fields from playground.
     */
    public Field[][] getPlayground() {
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
                rowCounter = 0;
                for (int i = 0; i < line.length(); i++) {
                    String letter = String.valueOf(line.charAt(i));


                    if (!letter.matches(".*([bBgGoOrRyY]).*")) {
                        exception= "InvalidFieldException";
                        throw new InvalidFieldException(
                                "InvalidFieldException");
                    }
                    if (Character.isLetter(line.charAt(i)))
                        rowCounter++;

                    //save Fields in 2d array pgGrid.
                    pgGrid[i][colCounter] = String.valueOf(line.charAt(i));


                }

                if (rowCounter != PLAYGROUND_ROW_SIZE) {
                    exception= "InvalidBoardLayout";
                    throw new InvalidBoardLayoutException(
                            "InvalidBoardLayout");
                }


                line = reader.readLine();
                colCounter++;
            }
            if (colCounter != PLAYGROUND_COL_SIZE) {
                exception= "InvalidBoardLayout";
                throw new InvalidBoardLayoutException(
                        "InvalidBoardLayout");
            }

        } catch (Exception e) {
            e.printStackTrace();
           // exit(EXITCODE);
        }


    }
    public String getException(){
        return exception;
    }


}
