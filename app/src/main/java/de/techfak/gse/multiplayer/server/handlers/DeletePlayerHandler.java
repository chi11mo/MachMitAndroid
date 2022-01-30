package de.techfak.gse.multiplayer.server.handlers;

import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.failure;
import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.successful;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;

/**
 * Handler to delete a player by name.
 */
public class DeletePlayerHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;
    private static final int STATUS_FORBIDDEN = 403;

    public DeletePlayerHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName playerName) {
        final boolean removed = game.removePlayer(playerName);
        if (removed) {
            ctx.status(STATUS_OK).json(successful("Du hast dich erfolgreich abgemeldet."));
        } else {
            ctx.status(STATUS_FORBIDDEN).json(failure("Der Spieler ist nicht angemeldet."));
        }
    }

}
