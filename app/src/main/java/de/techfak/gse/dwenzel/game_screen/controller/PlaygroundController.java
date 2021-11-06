package de.techfak.gse.dwenzel.game_screen.controller;

import java.io.InputStream;

import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;
import de.techfak.gse.dwenzel.game_screen.view.IPlaygroundView;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

public class PlaygroundController implements IPlaygroundController {
    /**
     * Playground view.
     */
    IPlaygroundView iPlaygroundView;
    /**
     * playgroundModel to fill it with the data.
     */
    PlaygroundModel playgroundModel;

    /**
     * @param iPlaygroundView to send stuff to the view.
     */
    public PlaygroundController(IPlaygroundView iPlaygroundView) {
        this.iPlaygroundView = iPlaygroundView;

    }

    /**
     * @param boardFile InputStream to read data.
     * @param maxRow    maxRow range.
     * @param maxCol    maxCol range.
     */
    @Override
    public void createPlayground(String boardFile, int maxRow, int maxCol) {
        BoardFile board = new BoardFile(boardFile, maxRow, maxCol);
        playgroundModel = new PlaygroundModel(board);


        iPlaygroundView.updatePlayground(playgroundModel);

    }

    /**
     * @return the filled playgroundModel.
     */
    @Override
    public PlaygroundModel getPlayground() {
        return playgroundModel;
    }


}
