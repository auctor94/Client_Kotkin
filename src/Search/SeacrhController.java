package Search;

import ChangeLogPass.Parameter;
import MainFiles.ErrorWindow;
import MainFiles.Main;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class SeacrhController implements Initializable {


    @FXML
    private ChoiceBox<Parameter> idPoisk;

    @FXML
    private TextField poiskText;

    @FXML
    private TextArea textArea;
    private int pole;
    @FXML
    private Button searchButton;

    public int getPole() {
        return pole;
    }

    public void setPole(int pole) {
        this.pole = pole;
    }

    @FXML
    private Button exitButton;
    @FXML
    private DatePicker idDate;

    @FXML
    void OnSearchButton(ActionEvent event) throws IOException {
        if ((getPole() == 1 || getPole() == 2 || getPole() == 3 || getPole() == 4) && poiskText.getText().isEmpty()) {
            ErrorWindow.display("Как так то...", "Заполните поле ввода");
        } else {
            if (getPole() == 1) {
                Main.objectOutputStream.writeObject(9999);
                Main.objectOutputStream.writeObject(poiskText.getText());
                try {
                    if ((int) Main.objectInputStream.readObject() == 0)
                        ErrorWindow.display("OOps....", "Ничего не найдено");
                    else {
                        String result = (String) Main.objectInputStream.readObject();
                        for (String retval : result.split("%")) {
                            textArea.appendText(retval + " ");
                        }
                        textArea.appendText("\n");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if (getPole() == 2) {
                Main.objectOutputStream.writeObject(9998);
                Main.objectOutputStream.writeObject(poiskText.getText());
                try {
                    if ((int) Main.objectInputStream.readObject() == 0)
                        ErrorWindow.display("OOps....", "Ничего не найдено");
                    else {
                        String result = (String) Main.objectInputStream.readObject();
                        for (String retval : result.split("%")) {
                            textArea.appendText(retval + " ");
                        }
                        textArea.appendText("\n");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (getPole() == 3) {
                Main.objectOutputStream.writeObject(9997);
                Main.objectOutputStream.writeObject(poiskText.getText());
                try {
                    if ((int) Main.objectInputStream.readObject() == 0)
                        ErrorWindow.display("OOps....", "Ничего не найдено");
                    else {
                        String result = (String) Main.objectInputStream.readObject();
                        for (String retval : result.split("%")) {
                            textArea.appendText(retval + " ");
                        }
                        textArea.appendText("\n");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (getPole() == 4) {
                Main.objectOutputStream.writeObject(9996);
                Main.objectOutputStream.writeObject(poiskText.getText());
                try {
                    if ((int) Main.objectInputStream.readObject() == 0)
                        ErrorWindow.display("OOps....", "Ничего не найдено");
                    else {
                        String result = (String) Main.objectInputStream.readObject();
                        for (String retval : result.split("%")) {
                            textArea.appendText(retval + " ");
                        }
                        textArea.appendText("\n");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (getPole() == 5) {
                Main.objectOutputStream.writeObject(9995);
                Main.objectOutputStream.writeObject(idDate.getValue().toString());
                try {
                    if ((int) Main.objectInputStream.readObject() == 0)
                        ErrorWindow.display("OOps....", "Ничего не найдено");
                    else {
                        String result = (String) Main.objectInputStream.readObject();
                        for (String retval : result.split("%")) {
                            textArea.appendText(retval + " ");
                        }
                        textArea.appendText("\n");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    void onExitButton(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if ((int) Main.objectInputStream.readObject() < 10)
            Main.window.setScene(Main.sceneAdminMenu);
        else
            Main.window.setScene(Main.sceneUserMenu);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pole = 0;
        Parameter login = new Parameter("nam", "Имя");
        Parameter password = new Parameter("pass", "Отчество");
        Parameter lognpass = new Parameter("lognpass", "Фамилия");
        Parameter lognpass1 = new Parameter("lognpass1", "Должность");
        Parameter lognpass2 = new Parameter("lognpass2", "День рождения");
        idDate.setValue(LocalDate.now());
        idDate.setVisible(false);
        poiskText.setEditable(false);

        ObservableList<Parameter> parametres = FXCollections.observableArrayList(login, password, lognpass, lognpass1, lognpass2);
        idPoisk.setItems(parametres);
        ChangeListener<Parameter> changeListener = new ChangeListener<Parameter>() {

            @Override
            public void changed(ObservableValue<? extends Parameter> observable, //
                                Parameter oldValue, Parameter newValue) {
                if (newValue.getName() == "Имя") {
                    poiskText.setEditable(true);
                    poiskText.setText("");
                    System.out.println("имя");
                    idDate.setVisible(false);
                    setPole(1);
                } else if (newValue.getName() == "Отчество") {
                    System.out.println("отчество");
                    poiskText.setText("");
                    poiskText.setEditable(true);
                    idDate.setVisible(false);
                    setPole(2);
                } else if (newValue.getName() == "Фамилия") {
                    poiskText.setText("");
                    System.out.println("Фамилия");
                    poiskText.setEditable(true);
                    idDate.setVisible(false);
                    setPole(3);
                } else if (newValue.getName() == "Должность") {
                    poiskText.setText("");
                    System.out.println("Должность");
                    poiskText.setEditable(true);
                    idDate.setVisible(false);
                    setPole(4);
                } else if (newValue.getName() == "День рождения") {
                    poiskText.setText("");
                    idDate.setVisible(true);
                    poiskText.setEditable(false);
                    System.out.println("День рождения");
                    setPole(5);
                }
            }
        };
        idPoisk.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }
}
