package de.techfak.gse.multiplayer.server.handlers;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.game.Round;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.response_body.RoundResponse;

/**
 * Handler to get the current round of the game.
 */
public class GetRoundHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;

    public GetRoundHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName name) {
        final Round round = game.getRound();
        final RoundResponse response = new RoundResponse(round);
        ctx.status(STATUS_OK).json(response);
    }

}
