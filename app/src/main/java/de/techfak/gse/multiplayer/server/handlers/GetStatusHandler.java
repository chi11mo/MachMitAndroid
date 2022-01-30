package de.techfak.gse.multiplayer.server.handlers;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.response_body.StatusResponse;

/**
 * Handler to get the current status of the game.
 */
public class GetStatusHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;

    public GetStatusHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName name) {
        final StatusResponse response = new StatusResponse(game.getGameStatus());
        ctx.status(STATUS_OK).json(response);
    }
}
