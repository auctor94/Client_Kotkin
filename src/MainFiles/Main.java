package MainFiles;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {

    public static Stage window;
    public static Scene scene1;
    public static Scene sceneAlternative;
    public static Scene sceneMenuExpert;
    public static Scene sceneMenuRegister;
    public static Scene sceneUserMenu;
    public static Scene sceneAdminMenu;
    public static Socket socket;
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;


    @Override
    public void start(Stage primaryStage) throws Exception{
        socket = new Socket("localhost", 3128);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        window = primaryStage;
        window.setTitle("Вход");
        VBox vBox = new VBox();
            vBox.setStyle("-fx-background-color: #a7d7c5");
        vBox.setPrefSize(50, 400);
        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-background-color: #a7d7c5");
        vBox1.setPrefSize(50, 400);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(upperLabelBlue("Добро пожаловать в приложение зачисления премий"));
        borderPane.setRight(vBox);
        borderPane.setLeft(vBox1);
        borderPane.setBottom(lowerLineExitRegistr());
        borderPane.setCenter(panelInputLogPass());

        scene1 = new Scene(borderPane, 600, 600);
        window.setScene(scene1);
        window.show();

    }

    public static HBox upperLabelBlue(String text) {
        HBox topLabel = new HBox(5);
        topLabel.setPrefSize(600, 100);
        javafx.scene.control.Label label = new Label(text);
        label.setFont(Font.font(20));
        //label.setFont(Font.);
        topLabel.setAlignment(Pos.CENTER);
        topLabel.setStyle("-fx-background-color: #f9f8eb");
        topLabel.getChildren().addAll(label);
        return topLabel;

    }//верхний слой для top


    public static void registerMenu() {

        VBox registeVBox = new VBox(30);
        registeVBox.setPrefSize(600, 600);
        Label label1 = new Label("Регистрация пользователя");
        label1.setFont(Font.font(30));
        Label label2 = new Label("Введите данные нового пользователя");
        label2.setFont(Font.font(15));

        TextField text = new TextField();
        text.setPromptText("Логин");
        text.setFont(Font.font(30));

        PasswordField text1 = new PasswordField();
        // text1.setPromptText("Пароль");
        text1.setFont(Font.font(30));

        Button button1 = new Button("Зарегистрироваться");
        button1.setPrefSize(150, 50);

        registeVBox.setAlignment(Pos.CENTER);
       button1.setOnAction(event -> {
            registerUser(text.getText(), text1.getText());
        });

        registeVBox.getChildren().addAll(label1, label2, text, text1, button1);

        Button buttonExit = new Button("Выход");
        buttonExit.setPrefSize(100, 50);
        buttonExit.setOnAction(event -> {
            try {
                objectOutputStream.writeObject(3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.setScene(scene1);
        });
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        Pane pane = new Pane();
        pane.setPrefSize(50, 50);
        Pane pane1 = new Pane();
        pane1.setPrefSize(600, 5);
        hBox.getChildren().addAll(buttonExit, pane);
        hBox.setPrefSize(600, 50);
        registeVBox.getChildren().addAll(pane1, hBox);
        sceneMenuRegister = new Scene(registeVBox, 600, 600);

        window.setScene(sceneMenuRegister);
        window.show();

    }

    public static void registerUser(String name, String pass) {
        try {
            if (name.isEmpty() || pass.isEmpty()) {
                ErrorWindow.display("Ошибка", "Нет данных");
            } else {
                objectOutputStream.writeObject(888);
                objectOutputStream.writeObject(name);
                objectOutputStream.writeObject(pass);
                int tmp = (int) objectInputStream.readObject();
                if (tmp == 1)
                    ErrorWindow.display(" ", "Круто");
                else
                    ErrorWindow.display("Ошибка", "Данный пользователь уже существует");


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static HBox lowerLineExit(Scene sceneReturn) {
        HBox hBox1 = new HBox();
        hBox1.setPrefSize(600, 100);


        hBox1.setStyle("-fx-background-color: #34abf3");
        javafx.scene.control.Button buttonExit = new Button("Выход");
        buttonExit.setOnAction(event -> {
            window.setScene(sceneReturn);
            try {
                objectOutputStream.writeObject(3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttonExit.setPrefSize(100, 50);
        hBox1.getChildren().add(buttonExit);
        Pane pane = new Pane();
        pane.setPrefSize(20, 20);
        hBox1.getChildren().add(pane);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        return hBox1;
    }

    public static HBox lowerLineExitRegistr() {

        HBox bottomLabel = new HBox(300);
        //   Label label = new Label("NNNNNN");
        bottomLabel.setPrefSize(600, 100);
        Button button1 = new Button("Регистрация");
        button1.setOnAction(event -> {
            try {

                objectOutputStream.flush();
                objectOutputStream.writeObject(999);
                registerMenu();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Button button2 = new Button("Выход");
        button2.setOnAction(event -> {

            window.close();
            try {
                objectOutputStream.writeObject(3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button1.setPrefSize(100, 50);
        button2.setPrefSize(100, 50);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setStyle("-fx-background-color: #5c8d89");
        bottomLabel.getChildren().addAll(button1, button2);
        return bottomLabel;
    }//нижняя часть с конпками bottom

    public static void choiceClient(String string1, String string2) {

        try {

            String logPas[] = new String[2];
            logPas[0] = string1;
            logPas[1] = string2;
            objectOutputStream.writeObject(logPas);

            int result = (int) objectInputStream.readObject();
            if (result == 111) {
                AdminWindow.mainMenuAdmin();
                System.out.println("Admin Menu");
            } else if (result == 222)
               // UserWindow.menuUser();
                System.out.println("UserMenu");
            else
                ErrorWindow.display("Ошибка", "Неверный ввод");
        } catch (IOException e) {
            ErrorWindow.display("Ошибка", "Ошибка с подключением");
        } catch (ClassNotFoundException e) {
            ErrorWindow.display("Ошибка", "Ошибка с подключением");
        }
//        if (!string1.equals("Admin"))
//            Window.FX_popup_Win_2.display("Ошибка", "Неверный логин");
//        else if (!string2.equals("Admin"))
//            Window.FX_popup_Win_2.display("Ошибка", "Неверный пароль");
//        else {
//
//            menuExpert();
//        }
    }


    public static VBox panelInputLogPass() {
        VBox centreMenu = new VBox(30);
        Label label1 = new Label("Авторизация");
        label1.setFont(Font.font(30));

        javafx.scene.control.TextField text = new TextField();
        text.setPromptText("Логин");
        text.setFont(Font.font(30));
        centreMenu.setStyle("-fx-background-color: #a7d7c5");
        PasswordField text1 = new PasswordField();
        text1.setPromptText("Пароль");
        text1.setFont(Font.font(30));
        Button button1 = new Button("Вход");
        button1.setPrefSize(100, 50);
        button1.setStyle("-fx-background-color: #74b49b");
        centreMenu.setAlignment(Pos.CENTER);
        button1.setOnAction(event -> {
            try {
                objectOutputStream.flush();

                objectOutputStream.writeObject(121212);

              choiceClient(text.getText(), text1.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        centreMenu.getChildren().addAll(label1, text, text1, button1);
        return centreMenu;
    }//центральная частьBorderPane



    public static void main(String[] args) {
        launch(args);
    }
}
