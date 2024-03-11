package yanlai.chd;


import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Ballcontrol extends Application{
    public void start(Stage pr) {
        Ballpane ballpane=new Ballpane();

        ballpane.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.UP)
                ballpane.increasespeed();
            else if(e.getCode()==KeyCode.DOWN)
                ballpane.decreasespeed();
            else if(e.getCode()==KeyCode.RIGHT)
                ballpane.right();
            else if(e.getCode()==KeyCode.LEFT)
                ballpane.left();
        });

        Button button1 = new Button("Start");
        Button button2 = new Button();
        button2.setText("Pause");
        button1.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        button2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        button1.setOnMouseClicked(event -> {
            ballpane.play();
            System.out.println(button1.getText());
        });
/**        switch (button2.getText()){
            case "Pause": button2.setOnMouseClicked(event -> {
                ballpane.pause();
                System.out.println(button2.getText());
                button2.setText("Resume");
            });
            case "Resume":button2.setOnMouseClicked(event -> {
                ballpane.play();
                System.out.println(button2.getText());
                button2.setText("Pause");
            });
        }*/
        button2.setOnMouseClicked(event -> {
                if (button2.getText() == "Pause") {
                    ballpane.pause();
                    button2.setText("Resume");
                }
                else if (button2.getText() == "Resume") {
                    ballpane.play();
                    button2.setText("Pause");
                }
            });
        Rectangle rect1 = new Rectangle(60,20);
        rect1.setFill(Color.TRANSPARENT);
        Rectangle rect2 = new Rectangle(60,20);
        rect2.setFill(Color.TRANSPARENT);
        HBox hBox = new HBox(10,rect1,button1,button2,rect2);
        hBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);
        BorderPane root = new BorderPane();
        root.setBottom(hBox);
        root.setCenter(ballpane);

        ballpane.setOnMousePressed(e->ballpane.handleMousePressed(e));
        ballpane.setOnMouseDragged(e->ballpane.handleMouseDragged(e));

        Scene scene=new Scene(root,400,350);
        pr.setTitle("弹球");
        pr.setScene(scene);
        pr.show();
        ballpane.requestFocus();
    }

    public static void main(String [] args) {
        Application.launch(args);
    }

}