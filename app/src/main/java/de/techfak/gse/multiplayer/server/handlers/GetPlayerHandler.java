package de.techfak.gse.multiplayer.server.handlers;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.Player;
import de.techfak.gse.multiplayer.game.PlayerName;
import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.response_body.PlayerListResponse;
import de.techfak.gse.multiplayer.server.response_body.PlayerResponse;
import de.techfak.gse.multiplayer.server.response_body.ResponseObject;

/**
 * Handler to list all players that are playing.
 */
public class GetPlayerHandler extends AbstractPlayerAwareHandler {

    private static final int STATUS_OK = 200;

    public GetPlayerHandler(final BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    protected void handle(final Context ctx, final PlayerName playerName) {
        final List<PlayerResponse> players = new ArrayList<>();
        for (final Player player : game.getPlayers()) {
            players.add(new PlayerResponse(player));
        }
        final ResponseObject response = new PlayerListResponse(players);
        ctx.json(response).status(STATUS_OK);
    }

}
