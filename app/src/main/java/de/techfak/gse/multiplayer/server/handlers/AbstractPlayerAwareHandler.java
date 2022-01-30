package de.techfak.gse.multiplayer.server.handlers;

import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.failure;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Handler;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;

/**
 * Handler to extract the player name from the query parameters.
 */
public abstract class AbstractPlayerAwareHandler implements Handler {

    private static final int STATUS_FORBIDDEN = 403;

    /**
     * The base game to operate on when handling a request.
     */
    protected final BaseGame game;

    /**
     * Initializes the Handler with given base game.
     *
     * @param baseGame the game to play on
     */
    public AbstractPlayerAwareHandler(final BaseGame baseGame) {
        this.game = baseGame;
    }

    @Override
    public final void handle(final Context ctx) {
        final PlayerName playerName = new PlayerName(ctx.queryParam("name"));
        if (!game.containsPlayer(playerName)) {
            ctx.status(STATUS_FORBIDDEN).json(failure("Der Spieler ist nicht angemeldet."));
            return;
        }
        handle(ctx, playerName);
    }

    protected abstract void handle(final Context ctx, final PlayerName player);

}
