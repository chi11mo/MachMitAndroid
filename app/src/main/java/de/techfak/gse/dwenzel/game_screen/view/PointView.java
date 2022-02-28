package de.techfak.gse.dwenzel.game_screen.view;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.model.Player;
import de.techfak.gse.dwenzel.game_screen.model.PointChecker;
import de.techfak.gse.multiplayer.server.response_body.PlayerResponse;

public class PointView {
    private static final int COLOR_COUNT = 5;
    private static final int TEXT_SIZE = 24;
    private static final int PADDING_VIEW = 55;
    private static final int FIELD_SIZE = 60;
    private final Context context;
    private final LinearLayout pointLayout;
    private final LinearLayout colorLayout;
    private final TextView playerPointsView;
    private final TextView[] pointViewField;
    private final ImageView[] colorViewField;
    private final int[] pointRow = new int[]{5, 3, 3, 3, 2, 2, 2, 1, 2, 2, 2, 3, 3, 3, 5};


    /**
     * create and update the View for points.
     *
     * @param context context of main app xml.
     */
    public PointView(final Context context) {

        this.context = context;
        pointLayout = ((Activity) context)
                .findViewById(R.id.horizontalPointLayout);
        colorLayout = ((Activity) context)
                .findViewById(R.id.horizontalColorLayout);
        LinearLayout playerLayout = ((Activity) context)
                .findViewById(R.id.playerLayout);


        //set Player Points view

        playerPointsView = new TextView(context);
        playerPointsView.setPadding(PADDING_VIEW, 0, 0, 0);


        pointViewField = new TextView[context.getResources().getInteger(R.integer.PlaygroundRow)];

        colorViewField
                = new ImageView[COLOR_COUNT];

        playerLayout.addView(playerPointsView);


        createRowView();
        createColorView();
    }

    /**
     * First create the color view .
     */
    private void createColorView() {
        List<Integer> cd = new ArrayList<>();
        cd.add(context.getResources().getColor(R.color.yellow));
        cd.add(context.getResources().getColor(R.color.green));
        cd.add(context.getResources().getColor(R.color.red));
        cd.add(context.getResources().getColor(R.color.orange));
        cd.add(context.getResources().getColor(R.color.blue));


        for (int idx = 0; idx < cd.size(); idx++) {
            colorViewField[idx] = new ImageView(context);
            colorViewField[idx].setLayoutParams(
                    new LinearLayout.LayoutParams(FIELD_SIZE, FIELD_SIZE));
            colorViewField[idx].setBackgroundColor(cd.get(idx));
            colorLayout.addView(colorViewField[idx]);
        }


    }

    /**
     * first init RowPointView.
     */
    private void createRowView() {


        for (int iRow = 0; iRow
                < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {

            pointViewField[iRow] = new TextView(context);
            pointViewField[iRow].setTextSize(TEXT_SIZE);
            pointViewField[iRow].setPadding(PADDING_VIEW, 0, 0, 0);
            pointViewField[iRow].setText(String.valueOf(pointRow[iRow]));
            pointLayout.addView(pointViewField[iRow]);
        }
    }

    /**
     * update the given points.
     *
     * @param player       player to get player name and current points.
     * @param pointChecker to get list of marked colors and rows.
     */
    public void setPoints(final Player player, final PointChecker pointChecker) {
        if (player != null) {
            setPlayerPoints(player);
        }
        setRowPoints(pointChecker.getPickedFullRow());
        setColorPoints(pointChecker.getPickedFullColor());
    }

    /**
     * update full colors.
     *
     * @param pickedFullColor list of full colors.
     */
    private void setColorPoints(final List<Integer> pickedFullColor) {
        for (int currentColor : pickedFullColor) {
            colorViewField[currentColor].setBackgroundColor(
                    context.getResources().getColor(R.color.black));
        }
    }

    /**
     * Update full marked row view.
     *
     * @param pickedFullRow list of full marked rows.
     */
    private void setRowPoints(final List<Integer> pickedFullRow) {
        for (int currentRow : pickedFullRow) {
            pointViewField[currentRow].setText("X");
        }
    }

    /**
     * Updates Player points to the view.
     *
     * @param player player to get current points.
     */
    private void setPlayerPoints(final Player player) {


        String playerViewText = player.getPlayerName() + player.getCurrentPoints();
        playerPointsView.setText(playerViewText);


    }

    /**
     * Setting up Player list from server .
     *
     * @param players player list.
     */
    public void setPlayers(final List<PlayerResponse> players) {
        StringBuilder allPlayers = new StringBuilder();
        for (PlayerResponse player : players) {

            allPlayers.append(player.getName());
            allPlayers.append(player.getPoints());
            allPlayers.append("\n");
        }
        playerPointsView.setText(allPlayers.toString());

    }
}
