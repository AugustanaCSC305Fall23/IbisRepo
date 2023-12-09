package edu.augustana.ui;


import edu.augustana.data.Card;

import edu.augustana.data.LessonPlan;
import edu.augustana.data.PrintManager;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class PrintPreviewController {


    @FXML
    private Button btnBack;
    @FXML
    private Group printGroup;
    @FXML
    private FlowPane fpEventLayout;
    @FXML
    private VBox vboxPrintLayout;
    private PrintManager printedVBox;
    @FXML
    private Label eventLabel;


    private LessonPlan currentLessonPlan = MainApp.getCurrentCourse().getOneLessonPlan();

    @FXML
    private void initialize() {
        this.printedVBox = new PrintManager(vboxPrintLayout);
        eventLabel.setText(currentLessonPlan.getOneEvent().getEventTitle());
        for (Card card: currentLessonPlan.getOneEvent().getCards()) {
            ImageView cardImage = new ImageView(card.getImage());

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
            double imageWidth = (primaryScreenBounds.getWidth() - 50) / 4; // 50 is padding & gap width

            cardImage.setFitWidth(imageWidth);
            cardImage.setPreserveRatio(true);
            fpEventLayout.getChildren().add(cardImage);
        }
    }

    @FXML
    private void btnActionPrint() {
        printedVBox.printPage();
    }


    @FXML
    private void switchToNewLessonCreationPage(){
        MainApp.switchToNewLessonCreationPage();
    };

}
