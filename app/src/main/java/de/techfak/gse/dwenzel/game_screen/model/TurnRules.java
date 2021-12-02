package de.techfak.gse.dwenzel.game_screen.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.exception.InvalidTurnException;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;

public class TurnRules {
    private final FieldMap fieldMap;

    /**
     * TurnRules is for checking the current Turn Field choices.
     * You get back a bool.
     *
     * @param fieldMap current Field Map.
     */
    public TurnRules(final FieldMap fieldMap) {
        this.fieldMap = fieldMap;
    }


    /**
     * This class is to compare all the TurnRules and throw a exception
     * if the turn is not valid return a bool.
     *
     * @param field current marked field.
     * @return is the Turn valid or not.
     */
    public boolean isTurnValid(final AbstractField field, final int firstMark) {


        try {
            //All marked fields are the same as first tap.
            if (firstMark != 6) {
                if (!isColorValid(field, firstMark)) {
                    throw new InvalidTurnException("Invalid Turn!");
                }
            }
            //if Mark is on Row H.
            if (firstMark == 6) {
                if (field.getRow() == 7) {
                    return true;
                }
            }
            //Checking is a Neighbour field Marked.
            if (!isNeighbour(field)) {
                throw new InvalidTurnException("Invalid Turn!");
            }

        } catch (InvalidTurnException exp) {
            Log.d("Exception", String.valueOf(exp));
            return false;
        }


        return true;
    }

    /**
     * Checking is turnTaps one of them a neighbour of a current Fieldmap crossed field.
     *
     * @param field current marked field.
     * @return is Neighbour Crossed.
     */
    private boolean isNeighbour(final AbstractField field) {
        AbstractField[][] fields = fieldMap.getFields();

        if (field.getRow() > 0) {
            if (fields[field.getRow() - 1][field.getCol()].isCrossed()) {
                return true;
            }
        }
        if (field.getRow() < fieldMap.getMaxRow() - 1) {
            if (fields[field.getRow() + 1][field.getCol()].isCrossed()) {
                return true;
            }
        }
        if (field.getCol() > 0) {
            if (fields[field.getRow()][field.getCol() - 1].isCrossed()) {
                return fields[field.getRow()][field.getCol() - 1].isCrossed();
            }
        }
        if (field.getCol() < fieldMap.getMaxCol() - 1) {
            return fields[field.getRow()][field.getCol() + 1].isCrossed();
        }

        return false;
    }

    /**
     * Checks if current turn has the same color.
     *
     * @param field current marked field.
     * @return if correct color is chosen.
     */
    private boolean isColorValid(final AbstractField field, final int chosenColor) {
        return field.getFieldColor() == chosenColor;
    }
}
