package de.techfak.gse.dwenzel.game_screen.controller;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.multiplayer.server.response_body.PlayerResponse;

public class EndCardSorter {

    /**
     * This Method sort player list for the Endcard to show rankings.
     *
     * @param playerResponse list of all players.
     * @return sorted ranking list of players.
     */
    public List<String> sortForEndCard(
            final List<PlayerResponse> playerResponse) {
        int[] array = new int[playerResponse.size()];

        for (int i = 0; i < playerResponse.size(); i++) {
            array[i] = playerResponse.get(i).getPoints();
        }
        bubbleSort(array);
        List<String> endCardList = new ArrayList<String>();
        for (int i = array.length; i > 0; i--) {
            for (int k = 0; k < playerResponse.size(); k++) {
                if (playerResponse.get(k).getPoints() == array[i - 1]) {
                    endCardList.add(playerResponse.get(k).getName()
                            + " : "
                            + playerResponse.get(k).getPoints() + " Punkte");
                    playerResponse.remove(k);
                }
            }
        }
        return endCardList;
    }

    /**
     * This sort Endcard points with the Bubble sort Algo.
     *
     * @param array array with points.
     */
    private void bubbleSort(final int[] array) {
        int smaller;
        int bigger;
        boolean run = true;


        for (int i = 0; i < array.length && run; i++) {
            run = false;

            for (int y = 0; y < array.length - 1; y++) {
                if (array[y] > array[y + 1]) {
                    bigger = array[y];
                    smaller = array[y + 1];
                    array[y] = smaller;
                    array[y + 1] = bigger;
                    run = true;
                }
            }
        }
    }
}
