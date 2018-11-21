package MainFiles;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {

    public static void display(String title,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);    //блокировка взаимодействия пользователя с другими окнами
        window.setTitle(title);
        window.setMinWidth(350);                            // мин  размеры
        window.setMinHeight(200);

        Label label = new Label(message);
        label.setFont(Font.font(20));               //размер шрифта

        Button closebutton = new Button("ОК");
        closebutton.setOnAction(event -> window.close());

        VBox layout =  new VBox(20);
        layout.getChildren().addAll(label,closebutton);
        layout.setAlignment(Pos.CENTER);                       //всё по центру
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
