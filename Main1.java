package application;




import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main1 extends Application {

    @Override
    public void start(Stage primaryStage) {
       
        BorderPane root = new BorderPane();
        Label cityLabel = new Label("Addis Ababa");
        cityLabel.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 24px; -fx-text-fill: #228B22;");
        root.setTop(cityLabel);
        BorderPane.setAlignment(cityLabel, Pos.CENTER);

        
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);

        Label tempLabel = new Label("25°C");
        tempLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 40px; -fx-text-fill: #8B5E3C;");

        Label conditionLabel = new Label("Sunny");
        conditionLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 16px; -fx-text-fill: #87CEEB;");

        
        Polygon leaf = new Polygon();
        leaf.getPoints().addAll(0.0, 0.0, 20.0, 40.0, 40.0, 0.0);
        leaf.setFill(Color.GREEN);

        
        RotateTransition sway = new RotateTransition(Duration.seconds(2), leaf);
        sway.setFromAngle(-5);
        sway.setToAngle(5);
        sway.setCycleCount(Animation.INDEFINITE);
        sway.setAutoReverse(true);
        sway.play();

        centerBox.getChildren().addAll(tempLabel, conditionLabel, leaf);
        root.setCenter(centerBox);

        
        HBox forecastBox = new HBox(20);
        forecastBox.setAlignment(Pos.CENTER);
        forecastBox.getChildren().addAll(
            new Label("Mon: 24°C Sunny"),
            new Label("Tue: 23°C Rain"),
            new Label("Wed: 25°C Cloudy")
        );
        root.setBottom(forecastBox);

       
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("EcoLife Weather Widget");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    }


