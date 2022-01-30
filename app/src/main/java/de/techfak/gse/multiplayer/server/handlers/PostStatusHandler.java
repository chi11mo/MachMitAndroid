package de.techfak.gse.multiplayer.server.handlers;

import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.failure;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.nanohttpd.Handler;
import de.techfak.gse.multiplayer.server.request_body.StatusBody;
import de.techfak.gse.multiplayer.server.response_body.StatusResponse;

/**
 * Handler to update the game status.
 */
public class PostStatusHandler implements Handler {

    private static final int STATUS_OK = 200;
    private static final int STATUS_FORBIDDEN = 403;

    private final BaseGame game;

    public PostStatusHandler(final BaseGame game) {
        this.game = game;
    }

    @Override
    public void handle(final Context ctx) {
        final StatusBody statusBody = ctx.bodyAsClass(StatusBody.class);
        if (!game.containsPlayer(new PlayerName(statusBody.getName()))) {
            ctx.status(STATUS_FORBIDDEN).json(failure("Der Spieler ist nicht angemeldet."));
            return;
        }

        game.setGameStatus(statusBody.getStatus());
        final StatusResponse response = new StatusResponse(game.getGameStatus());
        ctx.status(STATUS_OK).json(response);
    }
}
