package de.techfak.gse.multiplayer.game;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import de.techfak.gse.multiplayer.game.exceptions.MissingNameException;
import de.techfak.gse.multiplayer.game.exceptions.PlayerNoRegisteredException;

/**
 * A collection of players.
 */
/* default */ class Players implements Iterable<PlayerImpl> {

    private final List<PlayerImpl> playerList = new ArrayList<>();

    public List<Player> getPlayers() {
        return  new ArrayList<>(playerList);
    }

    public PlayerImpl getRegisteredPlayerByName(final PlayerName playerName) {
        final Optional<PlayerImpl> opt = playerList.stream()
                .filter(player -> player.getName().equals(playerName)).findFirst();
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new PlayerNoRegisteredException();
        }
    }

    public boolean addPlayer(final PlayerName name) {
        if (containsPlayer(name)) {
            return false;
        }
        return playerList.add(new PlayerImpl(name));
    }

    public boolean containsPlayer(final PlayerName name) {
        if (name == null) {
            throw new MissingNameException();
        }
        return playerList.stream().anyMatch(player -> player.getName().equals(name));
    }

    public boolean removePlayer(final PlayerName name) {
        return playerList.removeIf(player -> player.getName().equals(name));
    }

    @NotNull
    @Override
    public Iterator<PlayerImpl> iterator() {
        return playerList.iterator();
    }

    @Override
    public void forEach(final Consumer<? super PlayerImpl> action) {
        playerList.forEach(action);
    }
}
