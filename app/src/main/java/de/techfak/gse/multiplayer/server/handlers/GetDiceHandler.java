package de.techfak.gse.multiplayer.server.handlers;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.response_body.DiceResponse;

/**
 * Handler to get the current rolled dice.
 */
public class GetDiceHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;

    public GetDiceHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName player) {
        final DiceResponse response = new DiceResponse(game.getDiceResult());
        ctx.status(STATUS_OK).json(response);
    }
}
