package de.techfak.gse.dwenzel.start_screen.controller;

import java.io.InputStream;

import de.techfak.gse.dwenzel.start_screen.model.BoardFile;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

public class LoginController implements ILoginController {
    LoginView loginView;
    BoardValidator boardValidator;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void OnLogin(String board, int maxRow, int maxCol) {

        BoardFile boardFile = new BoardFile(board,
                maxRow,maxCol);
        boardValidator = new BoardValidator(boardFile);
        String exception = boardValidator.isValidBoard();

        if (!exception.equals("Valid")) {
            loginView.OnLoginError(exception);
        } else {

           loginView.OnLoginSuccess(exception);
        }
    }
}
