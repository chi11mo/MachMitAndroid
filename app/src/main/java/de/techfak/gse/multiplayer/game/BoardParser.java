package de.techfak.gse.multiplayer.game;

import java.io.IOException;

import de.techfak.gse.multiplayer.game.exceptions.InvalidBoardLayoutException;
import de.techfak.gse.multiplayer.game.exceptions.InvalidFieldException;

/**
 * The interface to parse a board based on a file or a string.
 */
public interface BoardParser {

    Board parse(final String board) throws InvalidBoardLayoutException, InvalidFieldException, IOException;
}
