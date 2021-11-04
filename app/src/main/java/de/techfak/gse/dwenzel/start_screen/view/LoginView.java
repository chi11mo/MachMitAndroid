package de.techfak.gse.dwenzel.start_screen.view;

import java.io.InputStream;

public interface LoginView {
    void OnLoginSucces(String message, InputStream boardFile);
    void OnLoginError(String message);

}
