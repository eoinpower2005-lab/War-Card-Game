import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class War extends Application {

    @Override
    public void start(Stage stage) {
        Button startGame = new Button("Start Game");


        BorderPane pane =  new BorderPane();

        VBox vbox = new VBox(startGame);
        vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateY(70);

        pane.setCenter(vbox);


        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        pane.setBottom(textArea);


        Scene scene = new Scene(pane, 600, 400);
        stage.setTitle("War");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Game game = new Game();
        game.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}