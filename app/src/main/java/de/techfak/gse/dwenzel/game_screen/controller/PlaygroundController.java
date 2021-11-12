package de.techfak.gse.dwenzel.game_screen.controller;

import android.widget.ImageButton;

import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;
import de.techfak.gse.dwenzel.game_screen.view.PlaygroundView;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * Controller just for button board. ( called Playground)
 */
public class PlaygroundController {

    /*Playground view.*/                            private final PlaygroundView playgroundView;

    /*playgroundModel to fill it with the data.*/    private PlaygroundModel playgroundModel;

    /**
     * controls the Playground especially Buttons to cross.
     *
     * @param playgroundView to send stuff to the view.
     */
    public PlaygroundController(final PlaygroundView playgroundView) {
        this.playgroundView = playgroundView;

    }

    /**
     * creates the Playground at first.
     *
     * @param boardFile InputStream to read data.
     * @param maxRow    maxRow range.
     * @param maxCol    maxCol range.
     */
    public void createPlayground(final String boardFile, final int maxRow, final int maxCol) {
        final BoardFile board = new BoardFile(boardFile, maxRow, maxCol);
        playgroundModel = new PlaygroundModel(board);


        playgroundView.updatePlayground(playgroundModel);

    }

    /**
     * Get the Playground model to use the information in the activity.
     *
     * @return the filled playgroundModel.
     */
    public PlaygroundModel getPlayground() {
        return playgroundModel;
    }


}
