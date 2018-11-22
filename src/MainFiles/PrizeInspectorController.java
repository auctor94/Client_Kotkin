package MainFiles;
import DataSources.MainWorkerWageInformation;
import DataSources.Percent;
import DataSources.PrizeEnrollInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class PrizeInspectorController implements Initializable {

    @FXML
    private TableView<MainWorkerWageInformation> tableViewStimulating;
    @FXML
    private TableView<PrizeEnrollInformation> tableViewEncouraging;
    @FXML
    private TableView<Percent> tableViewPercent;
    @FXML
    private TextField surEncTextField;
    @FXML
    private TextField moneyTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField percentTextField;
    @FXML
    private TableColumn<MainWorkerWageInformation, String> idFIOColum;
    @FXML
    private TableColumn<MainWorkerWageInformation, String> idPostionColum;
    @FXML
    private TableColumn<MainWorkerWageInformation, Float> idWageColum;
    @FXML
    private TableColumn<MainWorkerWageInformation, Float> idPrizeColum;
    @FXML
    private TableColumn<PrizeEnrollInformation, String> idfioColum;
    @FXML
    private TableColumn<PrizeEnrollInformation, String> idDescColum;
    @FXML
    private TableColumn<PrizeEnrollInformation, Float> idPrizeSizeColum;
    @FXML
    private TableColumn<PrizeEnrollInformation, LocalDate> idMonthColum;
    @FXML
    private TableColumn<Percent, String> idNameColum;
    @FXML
    private TableColumn<Percent, Float> idPercentColum;

     @FXML
    public void OnChangePercentButtonPushed(ActionEvent event) throws IOException {
        if (percentTextField.getText().isEmpty()) {
            ErrorWindow.display(" ", "Заполните поле ввода процента");
        }
        else {
            Main.objectOutputStream.writeObject(3663);
            Percent percentSelected = tableViewPercent.getSelectionModel().getSelectedItem();
            percentSelected.setPercent(Float.valueOf(percentTextField.getText()));
            Main.objectOutputStream.writeObject(1);
            Main.objectOutputStream.writeObject(percentSelected.getFIO());//Это нам нужно, для того,
            // чтобы по табельному номеру поменять значение записи
            Main.objectOutputStream.writeObject(percentTextField.getText());

            tableViewPercent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            try {
                tableViewStimulating.setItems(getHighTable());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void OnEnrollButtonPushed(ActionEvent event) throws IOException, ParseException, ClassNotFoundException {
        if (surEncTextField.getText().isEmpty() || moneyTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) {
            ErrorWindow.display("Как так то...", "Заполните все поля ввода");
            datePicker.setValue(LocalDate.now());
        }
        else {
        Main.objectOutputStream.writeObject(3663);
            Main.objectOutputStream.writeObject(2);
            Main.objectOutputStream.writeObject(surEncTextField.getText());
            if ((int)Main.objectInputStream.readObject() == 0)
            {
                ErrorWindow.display("Oops","Не найдено рабочего с такой фамилией");
            }
            else {
                Main.objectOutputStream.writeObject(moneyTextField.getText());
                Main.objectOutputStream.writeObject(descriptionTextArea.getText());
                Main.objectOutputStream.writeObject(datePicker.getValue());
                try {
                    tableViewEncouraging.setItems(getMiddleTable());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void changeScreenButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if((int) Main.objectInputStream.readObject()<10)
            Main.window.setScene( Main.sceneAdminMenu );
        else
            Main.window.setScene(Main.sceneUserMenu);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idFIOColum.setCellValueFactory(new PropertyValueFactory<MainWorkerWageInformation, String>("FIOWorker"));
        idPostionColum.setCellValueFactory(new PropertyValueFactory<MainWorkerWageInformation, String>("positionWorker"));
        idWageColum.setCellValueFactory(new PropertyValueFactory<MainWorkerWageInformation, Float>("wagesWorker"));
        idPrizeColum.setCellValueFactory(new PropertyValueFactory<MainWorkerWageInformation, Float>("prizeWorker"));


        idfioColum.setCellValueFactory(new PropertyValueFactory<PrizeEnrollInformation,String>("fioWorker"));
        idDescColum.setCellValueFactory(new PropertyValueFactory<PrizeEnrollInformation,String>("descriptionPrize"));
        idPrizeSizeColum.setCellValueFactory(new PropertyValueFactory<PrizeEnrollInformation, Float>("prizeEnroll"));
        idMonthColum.setCellValueFactory(new PropertyValueFactory<PrizeEnrollInformation, LocalDate>("dateEnroll"));

        idNameColum.setCellValueFactory(new PropertyValueFactory<Percent, String>("FIO"));
        idPercentColum.setCellValueFactory(new PropertyValueFactory<Percent, Float>("percent"));

        //load dummy data
        try {
            tableViewStimulating.setItems(getHighTable());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            tableViewEncouraging.setItems(getMiddleTable());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            tableViewPercent.setItems(getLowTable());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Percent> getLowTable() throws IOException, ClassNotFoundException {
        ObservableList<Percent> lowTable = FXCollections.observableArrayList();
        Main.objectOutputStream.writeObject(7779);
        while (true) {
            int t = (int) Main.objectInputStream.readObject();
            if (0 != t)
                break;
            String idSurname =  (String)Main.objectInputStream.readObject();
            float idPrize = (float) Main.objectInputStream.readObject();

            lowTable.add(new Percent(idSurname,idPrize));

        }
        return lowTable;
    }

    private ObservableList<PrizeEnrollInformation> getMiddleTable() throws IOException, ClassNotFoundException {
        ObservableList<PrizeEnrollInformation> middleTable = FXCollections.observableArrayList();
        Main.objectOutputStream.writeObject(7778);
        while (true) {
            int t = (int) Main.objectInputStream.readObject();
            if (0 != t)
                break;
            String idSurname =  (String)Main.objectInputStream.readObject();
            String idDesc = (String) Main.objectInputStream.readObject();
            float idPrize = (float) Main.objectInputStream.readObject();
            LocalDate date = (LocalDate) Main.objectInputStream.readObject();

            middleTable.add(new PrizeEnrollInformation(idSurname, idDesc, idPrize, date));

        }
        return middleTable;
    }

    private ObservableList<MainWorkerWageInformation> getHighTable() throws IOException, ClassNotFoundException {
        ObservableList<MainWorkerWageInformation> highTable = FXCollections.observableArrayList();
        Main.objectOutputStream.writeObject(7777);
        while (true) {
            int t = (int) Main.objectInputStream.readObject();
            if (0 != t)
                break;
            String idSurname =  (String)Main.objectInputStream.readObject();
            String idPosition = (String) Main.objectInputStream.readObject();
            float idWages = (float) Main.objectInputStream.readObject();
            float idPrize = (float) Main.objectInputStream.readObject();

            highTable.add(new MainWorkerWageInformation(idSurname, idPosition, idWages, idPrize));

        }
        return highTable;
    }


}
