package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.HelpExplanation;
import seedu.address.model.itineraryitem.accommodation.Accommodation;


/**
 * An UI component that displays information of a {@code Contact}.
 */
public class HelpCard extends UiPart<Region> {

    private static final String FXML = "HelpCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Planner level 4</a>
     */
    private final HelpExplanation help;

    @FXML
    private Label name;
    @FXML
    private Label syntax;
    @FXML
    private Label example;

    public HelpCard(HelpExplanation help) {
        super(FXML);
        this.help = help;
        this.name.setText(help.getName() + ":");
        this.name.setStyle("-fx-font-weight:bold;");
        this.syntax.setText("Syntax: " + help.getSyntax());
        this.example.setText("Example: " + help.getExample());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCard)) {
            return false;
        }

        // state check
        HelpCard card = (HelpCard) other;
        return help.equals(card.help);
    }
}
