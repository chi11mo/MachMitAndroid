package de.techfak.gse.dwenzel.exception;


/**
 * The type Invalid turn exception.
 */
public class InvalidTurnException extends Exception {


    private static final long serialVersionUID = 378321;

    /**
     * Instantiates a new Invalid turn exception.
     *
     * @param invalidTurn the invalid turn
     */
    public InvalidTurnException(final String invalidTurn) {

        super(invalidTurn);

    }


}
