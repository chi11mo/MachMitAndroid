package de.techfak.gse.dwenzel.exception;


/**
 * The type Invalid board layout exception.
 */
public class InvalidBoardLayoutException extends Exception {

    private static final long serialVersionUID = 378322;

    /**
     * Instantiates a new Invalid board layout exception.
     */
    public InvalidBoardLayoutException() {
        super("InvalidBoardLayout \n");
    }

    /**
     * Instantiates a new Invalid board layout exception.
     *
     * @param exception the exception
     */
    public InvalidBoardLayoutException(final String exception) {
        super(exception);
    }
}