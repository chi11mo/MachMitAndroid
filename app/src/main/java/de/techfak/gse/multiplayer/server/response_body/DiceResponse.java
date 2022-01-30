package de.techfak.gse.multiplayer.server.response_body;

import java.util.List;

import de.techfak.gse.multiplayer.game.DieColor;
import de.techfak.gse.multiplayer.game.DieNumber;
import de.techfak.gse.multiplayer.game.DiceResult;

/**
 * The response containing dice.
 */
public class DiceResponse extends ResponseObject {
    private List<DieColor> colors;
    private List<DieNumber> numbers;

    public DiceResponse() {
        super();
    }

    /**
     * Initializes a server response containing the dice result.
     *
     * @param diceResult the result of the dice
     */
    public DiceResponse(final DiceResult diceResult) {
        super(true, "Alea iacta est.");
        this.colors = diceResult.getColors();
        this.numbers = diceResult.getNumbers();
    }

    public List<DieColor> getColors() {
        return colors;
    }

    public List<DieNumber> getNumbers() {
        return numbers;
    }

    public void setColors(final List<DieColor> colors) {
        this.colors = colors;
    }

    public void setNumbers(final List<DieNumber> numbers) {
        this.numbers = numbers;
    }
}
