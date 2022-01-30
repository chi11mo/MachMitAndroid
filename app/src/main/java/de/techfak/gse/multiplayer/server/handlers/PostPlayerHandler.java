package de.techfak.gse.multiplayer.server.handlers;

import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.failure;
import static de.techfak.gse.multiplayer.server.response_body.ResponseObject.successful;

import org.jetbrains.annotations.NotNull;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.nanohttpd.Handler;
import de.techfak.gse.multiplayer.server.request_body.RegisterBody;

/**
 * Handler to register a new player.
 */
public class PostPlayerHandler implements Handler {

    private static final int STATUS_OK = 200;
    private static final int STATUS_CONFLICT = 409;

    private final BaseGame game;

    public PostPlayerHandler(final BaseGame game) {
        this.game = game;
    }

    @Override
    public void handle(@NotNull final Context ctx) {
        final RegisterBody registerBody = ctx.bodyAsClass(RegisterBody.class);
        final PlayerName playerName = new PlayerName(registerBody.getName());
        final boolean added = game.addPlayer(playerName);
        if (!added) {
            ctx.status(STATUS_CONFLICT).json(failure("Der Name ist bereits vergeben."));
            return;
        }
        ctx.status(STATUS_OK).json(successful("Du kannst mitspielen!"));
    }

}
