package duke.ui;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The UI GUI class handles the displaying of UI elements in the application
 * using a graphical user interface
 *
 * @author Ryan NgWH
 */
public class Gui extends Ui {
    /**
     * Tasklist for the application
     */
    private static TaskList taskList;

    /**
     * The DukeGui class handles displaying of UI elements in the Duke appliation
     * using a GUI implemented via JavaFX
     *
     * @author Ryan NgWH
     */
    public static class DukeGui extends Application {
        /**
         * Default constructor for a Duke GUI
         */
        public DukeGui() {
        }

        @Override
        public void start(Stage stage) {
            // Create container for the chatbox
            ScrollPane scrollPane = new ScrollPane();
            VBox dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            // Create elements for user input
            TextField userInput = new TextField();
            Button sendButton = new Button("Send");

            // Create GUI pane
            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            // Set the scene
            Scene scene = new Scene(mainLayout);

            stage.setScene(scene);

            // Format the GUI
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);

            AnchorPane.setLeftAnchor(userInput, 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);

            // User input functionalities
            sendButton.setOnMouseClicked((event) -> {
                try {
                    dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
                } catch (DukeException e) {
                    dialogContainer.getChildren().add(new Label(String.format("ERROR: %s", e.getMessage())));
                } finally {
                    userInput.clear();
                }
            });

            userInput.setOnAction((event) -> {
                try {
                    dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
                } catch (DukeException e) {
                    dialogContainer.getChildren().add(new Label(String.format("ERROR: %s", e.getMessage())));
                } finally {
                    userInput.clear();
                }
            });

            // Scroll to end every time dialogContainer's height changes
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

            stage.show();
        }

        /**
         * Create a label with the response from the application to the user input
         *
         * @param input User input to be parsed
         *
         * @return Label with the response from the application
         */
        private Label getDialogLabel(String input) throws DukeException {
            // Parse user input
            Command command = Parser.parse(input);

            // Execute command
            String response = command.execute(Gui.taskList);

            // Format response
            Label responseLabel = new Label(response);
            responseLabel.setWrapText(true);

            return responseLabel;
        }
    }

    /**
     * Displays the UI of the application
     *
     * @param taskList Tasklist to use for the application
     * @param args     Arguments for the application
     */
    @Override
    public void startUI(TaskList taskList, String[] args) {
        Gui.taskList = taskList;
        Application.launch(DukeGui.class, args);
    }
}