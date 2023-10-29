package edu.augustana.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DragAndDrop extends MainApp {

    @Override
    public void start(Stage primaryStage) {
        ListView<Label> targetListView = new ListView<>();
        targetListView.setPrefSize(200, 200);

        HBox sourceBox = new HBox();
        sourceBox.setSpacing(10);

        // Creating some draggable cards
        for (int i = 0; i < 3; i++) {
            Label card = createCard("Card " + (i + 1));
            sourceBox.getChildren().add(card);

            // Enable dragging
            card.setOnDragDetected(event -> {
                Dragboard dragboard = card.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(card.getText());
                dragboard.setContent(content);
                event.consume();
            });
        }

        // Setting up the scene
        HBox root = new HBox(20, sourceBox, targetListView);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Drag and Drop Example");
        primaryStage.show();

        // Allowing the ListView to accept the cards
        targetListView.setOnDragOver(event -> {
            if (event.getGestureSource() != targetListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        targetListView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                Label card = createCard(dragboard.getString());
                targetListView.getItems().add(card);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private Label createCard(String text) {
        Label card = new Label(text);
        card.setPrefSize(80, 120);
        card.setStyle("-fx-background-color: lightblue; -fx-border-color: gray;");
        return card;
    }
}