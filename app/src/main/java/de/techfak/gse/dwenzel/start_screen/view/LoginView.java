package de.techfak.gse.dwenzel.start_screen.view;

import java.io.InputStream;

public interface LoginView {
    void OnLoginSuccess(String message);
    void OnLoginError(String message);

}
