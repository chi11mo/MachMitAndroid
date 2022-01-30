package de.techfak.gse.multiplayer.game;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a dice roll result.
 * <p>
 * Contains the rolled numbers as well as the rolled colors.
 */
public class DiceResult {

    private final List<DieNumber> rolledNumbers;

    private final List<DieColor> rolledColors;

    public DiceResult() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public DiceResult(final List<DieNumber> rolledNumbers, final List<DieColor> rolledColors) {
        this.rolledNumbers = unmodifiableList(rolledNumbers);
        this.rolledColors = unmodifiableList(rolledColors);
    }

    public List<DieNumber> getNumbers() {
        return rolledNumbers;
    }

    public List<DieColor> getColors() {
        return rolledColors;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final DiceResult that = (DiceResult) other;
        return Objects.equals(rolledNumbers, that.rolledNumbers) && Objects.equals(rolledColors, that.rolledColors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolledNumbers, rolledColors);
    }
}
