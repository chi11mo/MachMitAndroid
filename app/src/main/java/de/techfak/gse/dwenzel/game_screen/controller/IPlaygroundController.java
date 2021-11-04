package de.techfak.gse.dwenzel.game_screen.controller;

import java.io.InputStream;

import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;

public interface IPlaygroundController {
void createPlayground(InputStream boardFile, int maxRow, int maxCol);
PlaygroundModel getPlayground();
}
