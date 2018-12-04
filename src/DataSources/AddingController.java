package DataSources;

import ChangeLogPass.User;
import MainFiles.ErrorWindow;
import MainFiles.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.spi.InitialContextFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddingController implements Initializable {

   @FXML
   private TextArea tabArea;
    @FXML
    private Button addButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Position> addTable;
    @FXML
    private TableView<Surcharge> addTable2;
    @FXML
    private TableColumn<Position, String> positionColum;

    @FXML
    private TableColumn<Position, String> namePOsitionColum;

    @FXML
    private TableColumn<Surcharge, String> codeSurColum;

    @FXML
    private TableColumn<Surcharge, String> nameSurColum;
    @FXML
    private TextField tabellnyj;
    @FXML
    private TextField familia;//
    @FXML
    private TextField imya;//
    @FXML
    private TextField otchestvo;//
    @FXML
    private TextField education;//
    @FXML
    private TextField position;//
    @FXML
    private TextField oklad;
    @FXML
    private TextField procent;
    @FXML
    private TextField koddoplaty;
    @FXML
    private DatePicker naima;//
    @FXML
    private DatePicker birth;//

    @FXML
    void OnExitButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if ((int) Main.objectInputStream.readObject() < 10)
            Main.window.setScene(Main.sceneAdminMenu);
        else
            Main.window.setScene(Main.sceneUserMenu);
    }

    @FXML
    void onAddButtonPushed(ActionEvent event) {
        String regex = "^[0-9]*[.,]?[0-9]+$";
        if (tabellnyj.getText().isEmpty() || familia.getText().isEmpty() || imya.getText().isEmpty() || otchestvo.getText().isEmpty() || education.getText().isEmpty() || position.getText().isEmpty() || oklad.getText().isEmpty() || procent.getText().isEmpty()) {
            ErrorWindow.display("OOps...", "Заполните все поля!");
        } else if ((tabellnyj.getText().matches(regex) || position.getText().matches(regex) || oklad.getText().matches(regex) || procent.getText().matches(regex))  == false) {
            ErrorWindow.display("Ooops", "Вводите только цифры в нужные поля");
        }
        else if (koddoplaty.getText().matches(regex) == false && koddoplaty.getText().isEmpty() == false)
        {
            ErrorWindow.display("Ooops", "Вводите только цифры в нужные поля");
        }
            else {
            try {
                Main.objectOutputStream.writeObject(6668);
                Main.objectOutputStream.writeObject(Integer.valueOf(tabellnyj.getText()));
                Main.objectOutputStream.writeObject(familia.getText());
                Main.objectOutputStream.writeObject(imya.getText());
                Main.objectOutputStream.writeObject(otchestvo.getText());
                Main.objectOutputStream.writeObject(birth.getValue());
                Main.objectOutputStream.writeObject(education.getText());
                Main.objectOutputStream.writeObject(naima.getValue());
                Main.objectOutputStream.writeObject(Integer.valueOf(position.getText()));
                Main.objectOutputStream.writeObject(Float.valueOf(oklad.getText()));
                Main.objectOutputStream.writeObject(Float.valueOf(procent.getText()));
                if (koddoplaty.getText().isEmpty()) {
                    Main.objectOutputStream.writeObject(0);
                } else {
                    Main.objectOutputStream.writeObject(1);
                    Main.objectOutputStream.writeObject(Integer.valueOf(koddoplaty.getText()));
                }

                int result = (int) Main.objectInputStream.readObject();
                if (result == 0)
                    ErrorWindow.display("OOps...", "Что-то пошло не так. Данные не занесены в БД. Проверьте правильность введенной информации.");
                else ErrorWindow.display("ура!", "Успешно!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                ErrorWindow.display("Ooops...", "Проверьте правильно введенных данных в числовых полях");
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        positionColum.setCellValueFactory(new PropertyValueFactory<Position, String>("code"));
        namePOsitionColum.setCellValueFactory(new PropertyValueFactory<Position, String>("namePosition"));
        codeSurColum.setCellValueFactory(new PropertyValueFactory<Surcharge, String>("code"));
        nameSurColum.setCellValueFactory(new PropertyValueFactory<Surcharge, String>("nameSurcharge"));
        birth.setValue(LocalDate.now());
        naima.setValue(LocalDate.now());

        try {
            Main.objectOutputStream.writeObject(6666);
            ObservableList<Position> positions = FXCollections.observableArrayList();
            List<Position> positionsData = (List<Position>) Main.objectInputStream.readObject();
            for (Position i : positionsData) {
                positions.add(i);
            }
            addTable.setItems(positions);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Main.objectOutputStream.writeObject(6667);
            ObservableList<Surcharge> surcharges = FXCollections.observableArrayList();
            List<Surcharge> surchargeData = (List<Surcharge>) Main.objectInputStream.readObject();
            for (Surcharge i : surchargeData) {
                surcharges.add(i);
            }
            addTable2.setItems(surcharges);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Main.objectOutputStream.writeObject(6669);
            List<String> surchargeData = (List<String>) Main.objectInputStream.readObject();
            tabArea.appendText("Занятые табельные номера: \n");
            for (String i : surchargeData) {
                tabArea.appendText(i+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
