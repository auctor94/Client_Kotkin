package MainFiles;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdminWindow {

    private static Scene sceneDelet;
    private static Scene sceneDismiss;

    public static void mainMenuAdmin() {


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(600, 900);


        vBox.getChildren().addAll(Main.upperLabelBlue("Меню администратора"), centeralBoxAdminMenu(), Main.lowerLineExit(Main.scene1));

        Main.sceneAdminMenu = new Scene(vBox);
        Main.window.setScene(Main.sceneAdminMenu);
        Main.window.show();
    }


    public static GridPane centeralBoxAdminMenu() {

        VBox vBox = new VBox(10);

        vBox.setPrefSize(600, 500);
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);


        Label label1 = new Label("1. ");
        label1.setPrefSize(200, 80);
        label1.setAlignment(Pos.CENTER_RIGHT);
        Label label2 = new Label("2. ");
        label2.setPrefSize(200, 80);
        label2.setAlignment(Pos.CENTER_RIGHT);
        Label label3 = new Label("3. ");
        label3.setPrefSize(200, 80);
        label3.setAlignment(Pos.CENTER_RIGHT);
        Label label4 = new Label("4. ");
        label4.setPrefSize(200, 80);
        label4.setAlignment(Pos.CENTER_RIGHT);
        Label label5 = new Label("5. ");
        label5.setPrefSize(200, 80);
        label5.setAlignment(Pos.CENTER_RIGHT);
        Label label6 = new Label("6. ");
        label6.setPrefSize(200, 80);
        label6.setAlignment(Pos.CENTER_RIGHT);
        Label label7 = new Label("7. ");
        label7.setPrefSize(200, 80);
        label7.setAlignment(Pos.CENTER_RIGHT);
        Button button1 = new Button("Редактирование");
        button1.setPrefSize(200, 41);
        button1.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(1114);
            } catch (IOException e) {
                e.printStackTrace();
            }
            URL url = null;
            Parent root = null;
            try {
                url = new File("src/MainFiles/TablePersonnel.fxml").toURL();
                root = FXMLLoader.load(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Scene scene = new Scene(root);

            Main.window.setScene(scene);
            Main.window.show();
        });

        Button button4 = new Button("Уволить рабочего ");
        button4.setPrefSize(200, 41);
        button4.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(1111);
                Main.objectOutputStream.flush();
                dismissObjectWindow();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        Button button2 = new Button("Просмотр данных");
        button2.setPrefSize(200, 41);
        button2.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(1112);
                Main.objectOutputStream.flush();
                UserWindow.viewTableResult(1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        Button button3 = new Button("Удаление рабочего");
        button3.setPrefSize(200, 41);
        button3.setOnAction(event -> {
            try {
                Main.objectOutputStream.flush();
                Main.objectOutputStream.writeObject(1113);
                Main.objectOutputStream.flush();
                deletObjectWindow();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });


        Button button5 = new Button("Окно оперирования премиями");
        button5.setPrefSize(200, 41);
        button5.setOnAction(event -> {
           try {
                Main.objectOutputStream.writeObject(1115);
                Main.objectOutputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            URL url = null;
            Parent root = null;
            try {
                url = new File("src/MainFiles/PrizeInspector.fxml").toURL();
                root = FXMLLoader.load(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Scene scene = new Scene(root);

            Main.window.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

            Main.window.show();
            Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);
            Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);
        });



        Button button6 = new Button("Создание отчетов");
        button6.setPrefSize(200, 41);
        button6.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(1116);
            } catch (IOException e) {
                e.printStackTrace();
            }
            URL url = null;
            Parent root = null;
            try {
                url = new File("src/Reports/mainReport.fxml").toURL();
                root = FXMLLoader.load(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            Main.window.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

            Main.window.show();
            Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);
            Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);
        });

        Button button7 = new Button("Просмотр графиков");
        button7.setPrefSize(200, 41);
        button7.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(1117);
            } catch (IOException e) {
                e.printStackTrace();
            }
            URL url = null;
            Parent root = null;
            try {
                url = new File("src/Charts/firstChart.fxml").toURL();
                root = FXMLLoader.load(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Scene scene = new Scene(root);

            Main.window.setScene(scene);
            Main.window.show();
        });
        Pane pane = new Pane();
        pane.setPrefSize(200, 80);


        gridPane.setPrefSize(600, 600);
        gridPane.add(button1, 1, 0);
        gridPane.add(button2, 1, 1);
        gridPane.add(button3, 1, 2);
        gridPane.add(button4, 1, 3);
        gridPane.add(button5, 1, 4);
        gridPane.add(button6, 1, 5);
        gridPane.add(button7, 1, 6);
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(label4, 0, 3);
        gridPane.add(label5, 0, 4);
        gridPane.add(label6, 0, 5);
        gridPane.add(label7, 0, 6);
        gridPane.add(pane, 2, 0);


        return gridPane;
    }

    private static void dismissObjectWindow() throws IOException, ClassNotFoundException {
        VBox vBox = new VBox();

        vBox.getChildren().addAll(UserWindow.tableView(1), lowerLineExitDismiss(Main.sceneAdminMenu));

        sceneDismiss = new Scene(vBox);
        Main.window.setScene(sceneDismiss);
        Main.window.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);
        Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);
    }

    private static HBox lowerLineExitDismiss(Scene sceneAdminMenu) {
        HBox bottomLabel = new HBox(100);
        bottomLabel.setPrefSize(800, 00);

        TextField field = new TextField();
        TextField field2 = new TextField();
        field.setPromptText("Табельный номер рабочего для увольнения");
        field.setFont(Font.font(15));
        field.setPrefSize(200, 50);
        field2.setPromptText("Причина увольнения");
        field2.setFont(Font.font(15));
        field2.setPrefSize(200, 50);

        Button button2 = new Button("Ввод");
        button2.setOnAction(event -> {
            try {
                Main.window.setScene(sceneAdminMenu);
                if (field.getText().equals("")) {

                    Main.objectOutputStream.writeObject(3333);

                } else {
                    Main.objectOutputStream.writeObject(111300);
                    int idDel = Integer.parseInt(field.getText());
                    String reason = null;
                    if (!(field2.getText().equals(""))) {
                        reason = field2.getText();
                        Main.objectOutputStream.writeObject(idDel);
                        Main.objectOutputStream.writeObject(reason);
                    }
                    Main.objectOutputStream.writeObject(idDel);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button2.setPrefSize(100, 50);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setStyle("-fx-background-color: #34ABF3");
        bottomLabel.getChildren().addAll(field,field2, button2);
        return bottomLabel;
    }

    private static void deletObjectWindow() throws IOException, ClassNotFoundException {
        VBox vBox = new VBox();

        vBox.getChildren().addAll(UserWindow.tableView(1), lowerLineExitDelet(Main.sceneAdminMenu));

        sceneDelet = new Scene(vBox);
        Main.window.setScene(sceneDelet);
        Main.window.show();
    }

    private static HBox lowerLineExitDelet(Scene sceneAdminMenu) {

        HBox bottomLabel = new HBox(300);
        bottomLabel.setPrefSize(600, 100);

        TextField field = new TextField();
        field.setPromptText("Табельный номер объекта для удаления");
        field.setFont(Font.font(15));
        field.setPrefSize(200, 50);

        Button button2 = new Button("Ввод");
        button2.setOnAction(event -> {
            try {
                Main.window.setScene(sceneAdminMenu);
                if (field.getText().equals("")) {

                    Main.objectOutputStream.writeObject(3333);

                } else {
                    Main.objectOutputStream.writeObject(111300);
                    int idDel = Integer.parseInt(field.getText());
                    Main.objectOutputStream.writeObject(idDel);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button2.setPrefSize(100, 50);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setStyle("-fx-background-color: #34ABF3");
        bottomLabel.getChildren().addAll(field, button2);
        return bottomLabel;
    }
}

