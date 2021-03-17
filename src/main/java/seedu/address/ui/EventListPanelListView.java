package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;

/**
 * Panel containing the list of persons.
 */
public class EventListPanelListView extends UiPart<Region> {
    private static final String FXML = "EventListPanelListView.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanelListView.class);

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code EventListPanelListView} with the given {@code ObservableList}.
     */
    public EventListPanelListView(ObservableList<Event> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCardListView}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCardListView(event, getIndex() + 1).getRoot());
            }
        }
    }

}
