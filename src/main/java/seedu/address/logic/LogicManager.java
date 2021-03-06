package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.FocurisParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FocurisParser focurisParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        focurisParser = new FocurisParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = focurisParser.parseCommand(commandText);
        commandResult = command.execute(model);
        assert commandResult != null : "There must be a command result whenever a command is executed!";

        try {
            storage.saveEventBook(model.getEventBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public FilteredList<Event> getFilteredListByStatus(EventStatus status) {
        return model.getFilteredListByStatus(status);
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return model.getEventBook();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public FilteredList<Event> getFilteredBacklogList() {
        return model.getFilteredBacklogList();
    }

    @Override
    public FilteredList<Event> getFilteredTodoList() {
        return model.getFilteredTodoList();
    }

    @Override
    public FilteredList<Event> getFilteredInProgressList() {
        return model.getFilteredInProgressList();
    }

    @Override
    public FilteredList<Event> getFilteredDoneList() {
        return model.getFilteredDoneList();
    }

    @Override
    public Path getEventBookFilePath() {
        return model.getEventBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
