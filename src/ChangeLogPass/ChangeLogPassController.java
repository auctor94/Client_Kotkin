package ChangeLogPass;

import DataSources.Percent;
import MainFiles.ErrorWindow;
import MainFiles.Main;
import javafx.animation.ParallelTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChangeLogPassController implements Initializable {
    @FXML
    private ChoiceBox<Parameter> idCombo;
    @FXML
    private TableView<User> tableView;
    @FXML
    private Button changeButton;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, String> passColumn;
    @FXML
    private Button ExitButton;

    @FXML
    private TextField LoginText;

    @FXML
    private TextField passText;
    @FXML
    private TextField chooseT;
    private int pole;

    public int getPole() {
        return pole;
    }

    public void setPole(int pole) {
        this.pole = pole;
    }

    private String[] massStringId;

    @FXML
    void OnChangePushed(ActionEvent event) throws IOException {
        if (getPole() == 1 && (LoginText.getText().isEmpty() || chooseT.getText().isEmpty())) {
            ErrorWindow.display("Как так то...", "Заполните все поля ввода");
        } else if (getPole() == 2 && (passText.getText().isEmpty() || chooseT.getText().isEmpty())) {
            ErrorWindow.display("Как так то...", "Заполните все поля ввода");
        } else if (getPole() == 3 && (LoginText.getText().isEmpty() || passText.getText().isEmpty() || chooseT.getText().isEmpty())) {
            ErrorWindow.display("Как так то...", "Заполните все поля ввода");
        } else if (existA(chooseT.getText()) == false) {
            ErrorWindow.display("Как так то...", "Вы ввели неверный ID");
        } else {
            if (getPole() == 1) {
                Main.objectOutputStream.writeObject(5556);
                Main.objectOutputStream.writeObject(1);
                Main.objectOutputStream.writeObject(new User(LoginText.getText(), passText.getText(), chooseT.getText()));
                ErrorWindow.display("Успешно...", "Данные изменены");
            } else if (getPole() == 2) {
                Main.objectOutputStream.writeObject(5556);
                Main.objectOutputStream.writeObject(2);
                Main.objectOutputStream.writeObject(new User(LoginText.getText(), passText.getText(), chooseT.getText()));
                ErrorWindow.display("Успешно...", "Данные изменены");
            } else {
                Main.objectOutputStream.writeObject(5556);
                Main.objectOutputStream.writeObject(3);
                Main.objectOutputStream.writeObject(new User(LoginText.getText(), passText.getText(), chooseT.getText()));
                ErrorWindow.display("Успешно...", "Данные изменены");
            }
        }
    }

    @FXML
    void OnExitButton(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if ((int) Main.objectInputStream.readObject() < 10)
            Main.window.setScene(Main.sceneAdminMenu);
        else
            Main.window.setScene(Main.sceneUserMenu);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pole = 0;
        Parameter login = new Parameter("log", "Логин");
        Parameter password = new Parameter("pass", "Пароль");
        Parameter lognpass = new Parameter("lognpass", "Логин и пароль");

        idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        ObservableList<Parameter> parametres = FXCollections.observableArrayList(login, password, lognpass);
        try {
            Main.objectOutputStream.writeObject(5555);
            ObservableList<User> personnels = FXCollections.observableArrayList();
            List<User> usersData = (List<User>) Main.objectInputStream.readObject();
            int a = usersData.size();
            massStringId = new String[a];
            int arr = 0;
            for (User i : usersData) {
                personnels.add(i);
                massStringId[arr] = i.getId();
                arr++;
            }
            for (int i = 0; i < a; i++)
                System.out.println(massStringId[i]);
            tableView.setItems(personnels);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        idCombo.setItems(parametres);
        ChangeListener<Parameter> changeListener = new ChangeListener<Parameter>() {

            @Override
            public void changed(ObservableValue<? extends Parameter> observable, //
                                Parameter oldValue, Parameter newValue) {
                if (newValue.getName() == "Логин") {
                    System.out.println("login");
                    setPole(1);
                    passText.setVisible(false);
                    passText.setText("");
                    LoginText.setVisible(true);
                } else if (newValue.getName() == "Пароль") {
                    System.out.println("password");
                    setPole(2);
                    LoginText.setVisible(false);
                    LoginText.setText("");
                    passText.setVisible(true);
                } else if (newValue.getName() == "Логин и пароль") {
                    System.out.println("lognpass");
                    LoginText.setVisible(true);
                    passText.setVisible(true);
                    setPole(3);
                }
            }
        };

        // Selected Item Changed.
        idCombo.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    private boolean existA(String a) {
        for (String s : massStringId) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }
}


