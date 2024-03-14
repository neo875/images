  package com.example.demo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final String[] Images = {
            "p1.jpg",
            "p.jpg",
            "p2.jpg",
            "p3.jpg",
            "p4.jpg",
            "p5.jpg"

    };
    private int currentIndex = 0;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.setLeft(createImageGrid());
        Pane pane = new Pane();
        root.setCenter(pane);
        GridPane gridPane = createImageGrid();
        Label label = new Label();
        gridPane.getChildren().add(label);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private GridPane createImageGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(10);

        for (int i = 0; i < Images.length; i++) {
            Image thumbnail = loadImage(Images[i]);
            if (thumbnail != null) {
                ImageView imageView = new ImageView(thumbnail);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.getStyleClass().add("thumbnail");
                int colum = i % 2;
                int row = i / 2;

                gridPane.add(imageView, colum, row);

                int finalI = i;
                imageView.setOnMouseClicked(event -> showFullSizeImage(finalI));

            } else {
                System.err.println("Error loading image: " + Images[i]);
            }

        }
        return gridPane;
    }

    private void showFullSizeImage(int finalI) {
        if (finalI >=0 && finalI < Images.length)
        {
            Image image = loadImage(Images[finalI]);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            Pane pane = new Pane(imageView);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(pane));
            newStage.show();

            currentIndex = finalI;

        }
    }

    private Image loadImage(String imageUrl) {
        try {
            return new Image(getClass().getResourceAsStream(imageUrl));
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}