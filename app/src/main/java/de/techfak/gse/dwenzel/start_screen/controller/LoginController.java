package de.techfak.gse.dwenzel.start_screen.controller;

import de.techfak.gse.dwenzel.start_screen.model.BoardFile;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

public class LoginController {
    /*Login View.*/             private final LoginView loginView;
    /*Board validator class*/   private BoardValidator boardValidator;

    /**
     * Controls the login route.
     *
     * @param loginView get login view class.
     */
    public LoginController(LoginView loginView) {
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
    public void OnLogin(String board, int maxRow, int maxCol) {

        BoardFile boardFile = new BoardFile(board,
                maxRow, maxCol);
        boardValidator = new BoardValidator(boardFile);
        String exception = boardValidator.isValidBoard();

        if (!exception.equals("Valid")) {
            loginView.OnLoginError(exception);
        } else {

            loginView.OnLoginSuccess(exception);
        }
    }
}
