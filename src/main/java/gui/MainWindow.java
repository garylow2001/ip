package gui;

import chewy.Chewy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for Gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chewy chewy;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/personicon.jpg"));
    private final Image chewyImage = new Image(this.getClass().getResourceAsStream("/images/chewbaccaicon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChewy(Chewy chewy) {
        this.chewy = chewy;
    }

    /**
     * Creates the two dialog boxes that are shown at the start of the program.
     * One of them is the welcome message and the other is the load task status.
     */
    public void onStart() {
        dialogContainer.getChildren().addAll(
                DialogBox.getChewyDialog(this.chewy.showWelcome(), chewyImage),
                DialogBox.getChewyDialog(this.chewy.loadTasks(), chewyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chewy's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chewy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChewyDialog(response, chewyImage)
        );
        userInput.clear();
    }
}
