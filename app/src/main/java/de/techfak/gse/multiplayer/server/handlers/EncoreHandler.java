package de.techfak.gse.multiplayer.server.handlers;

import org.jetbrains.annotations.NotNull;

import de.techfak.gse.multiplayer.server.nanohttpd.Context;
import de.techfak.gse.multiplayer.server.nanohttpd.Handler;


/**
 * Handler to response with plain text "Encore".
 */
public class EncoreHandler implements Handler {
    @Override
    public void handle(@NotNull final Context context) {
        context.result("Encore");
    }
}
