import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class War extends Application {

    @Override
    public void start(Stage stage) {
        Game game = new Game();

        Button startGame = new Button("Start Game");
        startGame.setStyle("-fx-background-color: red; -fx-text-fill: Black; -fx-border-color: black; -fx-font-style: bold; -fx-font-family: 'Bookman Old Style'" );

        Rectangle r1 = new Rectangle(110, 170);
        r1.setFill(Color.WHITE);
        r1.setStroke(Color.BLACK);
        r1.setArcWidth(15);
        r1.setArcHeight(15);

        Rectangle r2 = new Rectangle(110, 170);
        r2.setFill(Color.WHITE);
        r2.setStroke(Color.BLACK);
        r2.setArcWidth(15);
        r2.setArcHeight(15);

        Label l1 = new Label("Player 1");
        l1.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-font-family: 'Bookman Old Style';");

        Label l2 = new Label("Player 2");
        l2.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-font-family: 'Bookman Old Style';");


        Label card1 = new Label("Card 1");
        card1.setStyle("-fx-font-weight: bold;");

        Label card2 = new Label("Card 2");
        card2.setStyle("-fx-font-weight: bold;");

        StackPane stackPane1 = new StackPane();
        stackPane1.getChildren().addAll(r1, card1);

        StackPane stackPane2 = new StackPane();
        stackPane2.getChildren().addAll(r2, card2);

        Text name = new Text("War Card Game");
        name.setStyle("-fx-font-weight: bold; -fx-font-family: 'Bookman Old Style'; -fx-font-size: 24px;");

        Text round = new Text("Round Counter:");
        round.setStyle("-fx-font-weight: bold; -fx-font-family: 'Bookman Old Style'; -fx-font-size: 14px;");

        Text p1Cards = new Text("Player 1 Cards:");
        p1Cards.setStyle("-fx-font-weight: bold; -fx-font-family: 'Bookman Old Style'; -fx-font-size: 14px;");

        Text p2Cards = new Text("Player 2 Cards:");
        p2Cards.setStyle("-fx-font-weight: bold; -fx-font-family: 'Bookman Old Style'; -fx-font-size: 14px;");

        Text roundResult = new Text("Round Result:");
        roundResult.setStyle("-fx-font-weight: bold; -fx-font-family: 'Bookman Old Style'; -fx-font-size: 14px");

        BorderPane pane = new BorderPane();

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(name);
        hbox.setPadding(new Insets(40, 0, 0, 0));

        HBox tBox = new HBox(190);
        tBox.getChildren().addAll(l1, l2);
        tBox.setAlignment(Pos.CENTER);
        tBox.setPadding(new Insets(10,0,0,0));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(name, round, p1Cards, p2Cards, roundResult, tBox);
        pane.setTop(vbox);
        vbox.setAlignment(Pos.CENTER);

        HBox cardBox = new HBox(150);
        cardBox.getChildren().addAll(stackPane1, stackPane2);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(0, 0, 0, 0));
        pane.setCenter(cardBox);

        HBox bBox = new HBox();
        bBox.setAlignment(Pos.CENTER);
        bBox.getChildren().addAll(startGame);
        bBox.setPadding(new Insets(0, 0, 40, 0));
        pane.setBottom(bBox);

        startGame.setOnAction(e -> {
           game.startGame();
           Timeline timeline = new Timeline();
           timeline.getKeyFrames().add(
                   new KeyFrame(Duration.seconds(0.2), ex -> {
                       String result = game.round(card1, card2, p1Cards, p2Cards, roundResult);

                       round.setText(result);

                       if (game.isGameOver()) {
                           timeline.stop();
                           startGame.setDisable(true);
                   }
           })
           );

           timeline.setCycleCount(Timeline.INDEFINITE);
           timeline.play();
        });




        Scene scene = new Scene(pane, 600, 400);
        stage.setTitle("War");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}