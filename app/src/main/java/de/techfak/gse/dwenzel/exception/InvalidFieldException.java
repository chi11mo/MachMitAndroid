package de.techfak.gse.dwenzel.exception;


/**
 * The type Invalid field exception.
 */
public class InvalidFieldException extends Exception {

    private static final long serialVersionUID = 378324;


    /**
     * Instantiates a new Invalid field exception.
     */
    public InvalidFieldException() {
        super("InvalidField");
    }

    /**
     * Instantiates a new Invalid field exception.
     *
     * @param exception the exception
     */
    public InvalidFieldException(final String exception) {
        super(exception);
    }
}