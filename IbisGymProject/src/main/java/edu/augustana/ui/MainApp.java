package edu.augustana.ui;
import com.opencsv.exceptions.CsvValidationException;
import edu.augustana.data.CardDatabase;
import edu.augustana.data.Course;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class MainApp extends Application {
    private static Scene scene;

    private static Course currentCourse = new Course();
    private static File currentCourseFile = null;

    //HELLO THUNDERDOMEApp
    @Override
    public void start(Stage stage) throws IOException, CsvValidationException {
        CardDatabase.addCardsFromCSVFile("DEMO1.csv");

        scene = new Scene(loadFXML("mainHomepage"), 640, 480);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static void switchToView(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxmlFileName));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            System.err.println("Can't find FXML file " + fxmlFileName);
            ex.printStackTrace();
        }
    }

    public static void switchToNewLessonCreationPage() {
        switchToView("newLessonCreationPage.fxml");
    }

    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void saveCurrentCourseToFile(File chosenFile) throws IOException {
        currentCourse.saveToFile(chosenFile);
        currentCourseFile = chosenFile;
    }

    public static void main(String[] args) {
        launch();
    }

    }