package de.techfak.gse.dwenzel.start_screen.controller;

import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;

public interface LoginControllerInterface {
    void OnLogin(InputStream boardFile, int maxRow, int maxCol);
}
