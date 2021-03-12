package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of an event book
 */
public interface ReadOnlyEventBook {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
