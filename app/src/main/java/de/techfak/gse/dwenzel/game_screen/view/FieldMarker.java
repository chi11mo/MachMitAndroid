package de.techfak.gse.dwenzel.game_screen.view;

import de.techfak.gse.dwenzel.game_screen.map.AbstractField;

public class FieldMarker {
    /**
     * Add current mark to the view.
     *
     * @param field field to mark.
     */
    public void addFieldMark(final AbstractField field) {
        field.setIsCrossed(true);
        field.getButton().setImageDrawable(field
                .getDrawableField(true));
    }

    /**
     * Remove current mark to the view.
     *
     * @param field field to mark.
     */
    public void removeFieldMark(final AbstractField field) {
        field.setIsCrossed(false);
        field.getButton()
                .setImageDrawable(field
                        .getDrawableField(false));
    }
}
