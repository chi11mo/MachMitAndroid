package de.techfak.gse.dwenzel.start_screen.controller;

import de.techfak.gse.dwenzel.start_screen.model.BoardFile;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

/**
 * Control the happenings on the login activity.
 */
public class LoginController {
    /*Login View.*/             private final LoginView loginView;

    /**
     * Controls the login route.
     *
     * @param loginView get login view class.
     */
    public LoginController(final LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Method do onCLick login Button.
     * Create Board file and check if board is valid.
     *
     * @param board  board as a string.
     * @param maxRow max row size of the Board.
     * @param maxCol max column size of the Board.
     */
    public void onLogin(final String board, final int maxRow, final int maxCol) {

        final BoardFile boardFile = new BoardFile(board,
                maxRow, maxCol);
        /*Board validator class*/
        final BoardValidator boardValidator = new BoardValidator(boardFile);
        final String exception = boardValidator.boardValidation();

        if (exception.equals("Valid")) {
            loginView.onLoginSuccess(exception);

        } else {
            loginView.onLoginError(exception, "inValid");

        }
    }
}
