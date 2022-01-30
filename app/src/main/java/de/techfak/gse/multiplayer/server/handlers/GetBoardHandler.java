package de.techfak.gse.multiplayer.server.handlers;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.response_body.BoardResponse;

/**
 * Handler to get the board configuration.
 */
public class GetBoardHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;

    public GetBoardHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName player) {
        final BoardResponse response = new BoardResponse(game.getBoard());
        ctx.status(STATUS_OK).json(response);
    }
}
