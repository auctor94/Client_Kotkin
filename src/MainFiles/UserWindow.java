package MainFiles;
import DataSources.Personnel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserWindow {
    private static Scene viewResult;
    public static void menuUser() {

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(Main.upperLabelBlue("Меню пользователя"), centeralBoxUserMenu(), Main.lowerLineExit(Main.scene1));
        vBox.setPrefSize(Screen.getPrimary().getBounds().getMaxX(), Screen.getPrimary().getBounds().getMaxY());
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        Main.sceneUserMenu = new Scene(vBox);
        Main.window.setScene(Main.sceneUserMenu);
        Main.window.show();
        Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);
        Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);
    }

    private static GridPane centeralBoxUserMenu() {
        VBox vBox = new VBox(15);

        vBox.setPrefSize(600, 400);
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

        Button button1 = new Button("Просмотр графиков");
        button1.setPrefSize(200, 50);
        button1.setOnAction(event -> {
            try {
                Main.objectOutputStream.flush();
                try {
                    Main.objectOutputStream.writeObject(2221);
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

            }
             catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button button2 = new Button("Создание отчётов");
        button2.setPrefSize(200, 50);
        button2.setOnAction(event -> {
            try {
                Main.objectOutputStream.writeObject(2222);
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
        Button button3 = new Button("Просмотр данных");
        button3.setPrefSize(200, 50);
        button3.setOnAction(event -> {

            try {
                Main.objectOutputStream.flush();
                Main.objectOutputStream.writeObject(2223);
                UserWindow.viewTableResult(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        Button button4 = new Button("Поиск и фильтрация");
        button4.setPrefSize(200, 50);
       button4.setOnAction(event -> {
           try {
               Main.objectOutputStream.writeObject(2224);
           } catch (IOException e) {
               e.printStackTrace();
           }
           URL url = null;
           Parent root = null;
           try {
               url = new File("src/Search/Search.fxml").toURL();
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


        Pane pane = new Pane();
        pane.setPrefSize(200, 70);

        gridPane.setPrefSize(600, 400);
        gridPane.add(button1, 1, 0);
        gridPane.add(button2, 1, 1);
        gridPane.add(button3, 1, 2);
        gridPane.add(button4,1,3);

        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(label4,0,3);
        gridPane.add(pane, 2, 0);

        return gridPane;

    }

    public static void viewTableResult(int id) throws IOException, ClassNotFoundException {


        VBox vBox = new VBox();

        //  int id = ConnectSQL.getIdClient();
        if (id > 0 && id < 10)
            vBox.getChildren().addAll(tableView(id), Main.lowerLineExit(Main.sceneAdminMenu));
        else
            vBox.getChildren().addAll(tableView(id), Main.lowerLineExit(Main.sceneUserMenu));


        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        viewResult = new Scene(vBox,850,600);
        Main.window.setScene(viewResult);
        Main.window.show();
        Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);
        Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);

    }

    public static TableView tableView(int id) throws IOException, ClassNotFoundException {
        TableView<Personnel> table;
        TableColumn<Personnel, String> idTabNumColum = null;
        TableColumn<Personnel, String> idSurnameColum = null;
        TableColumn<Personnel, String> idNameColum = null;
        TableColumn<Personnel, String> idLastNameColum = null;
        TableColumn<Personnel, String> idBirthColumn = null;
        TableColumn<Personnel, String> idEducColum = null;
        TableColumn<Personnel, String> idNaimColum = null;
        TableColumn<Personnel, String> idCodeColum = null;

        if (id > 0 && id < 10) {
            idTabNumColum = new TableColumn<>();
            idTabNumColum.setText("Табельный номер");
            idTabNumColum.setMinWidth(16);
            idTabNumColum.setCellValueFactory(new PropertyValueFactory<>("tabNumber"));

            idCodeColum = new TableColumn<>();
            idCodeColum.setText("Код должности");
            idCodeColum.setMinWidth(50);
            idCodeColum.setCellValueFactory(new PropertyValueFactory<>("idPosition"));

        }
        idSurnameColum = new TableColumn<>();
        idSurnameColum.setText("Фамилия");
        idSurnameColum.setMinWidth(30);
        idSurnameColum.setCellValueFactory(new PropertyValueFactory<>("surname"));

        idNameColum = new TableColumn<>();
        idNameColum.setText("Имя");
        idNameColum.setMinWidth(15);
        idNameColum.setCellValueFactory(new PropertyValueFactory<>("name"));

        idLastNameColum = new TableColumn<>();
        idLastNameColum.setText("Отчество");
        idLastNameColum.setMinWidth(50);
        idLastNameColum.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        idBirthColumn = new TableColumn<>();
        idBirthColumn.setText("День Рождения");
        idBirthColumn.setMinWidth(30);
        idBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        idEducColum = new TableColumn<>();
        idEducColum.setText("Образование");
        idEducColum.setMinWidth(30);
        idEducColum.setCellValueFactory(new PropertyValueFactory<>("education"));

        idNaimColum = new TableColumn<>();
        idNaimColum.setText("Дата найма");
        idNaimColum.setMinWidth(50);
        idNaimColum.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        table = new TableView();

        table.setEditable(true);

        ObservableList<Personnel> personnels = FXCollections.observableArrayList();

        while (true) {
            int t = (int) Main.objectInputStream.readObject();
            if (0 != t)
                break;
            int tabelnyj = (int) Main.objectInputStream.readObject();
            String surname = (String) Main.objectInputStream.readObject();
            String name = (String) Main.objectInputStream.readObject();//////////////////////////////////////////////////////////
            String lastname = (String) Main.objectInputStream.readObject();
            LocalDate birthday = (LocalDate) Main.objectInputStream.readObject();
            String education = (String) Main.objectInputStream.readObject();
            LocalDate hiredate = (LocalDate) Main.objectInputStream.readObject();
            int position = (int) Main.objectInputStream.readObject();
            personnels.add(new Personnel(tabelnyj, surname, name, lastname, birthday, education, hiredate, position));
        }

        table.setItems(personnels);

        if (id > 0 && id < 10) {
            table.getColumns().addAll(idTabNumColum, idSurnameColum, idNameColum, idLastNameColum, idBirthColumn, idEducColum, idNaimColum, idCodeColum);
        } else
            table.getColumns().addAll(idSurnameColum, idNameColum, idLastNameColum, idBirthColumn, idEducColum, idNaimColum);
        table.setPrefSize(600, 500);

        return table;
    }
}
