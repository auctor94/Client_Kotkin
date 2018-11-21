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
    private TextField surStimulateTextField;
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

    /* @FXML
    void OnChangePercentButtonPushed(ActionEvent event) {

    }

    @FXML
    void OnEnrollButtonPushed(ActionEvent event) {

    }
*/
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


        //load dummy data
        try {
            tableViewStimulating.setItems(getHighTable());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


       /* //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        //   idBuildingColum.setCellFactory(TextFieldTableCell.forTableColumn());
        //   idUserColum.setCellFactory(TextFieldTableCell.forTableColumn());
        damagedItemsColum.setCellFactory(TextFieldTableCell.forTableColumn());
        cityColum.setCellFactory(TextFieldTableCell.forTableColumn());
        streetColum.setCellFactory(TextFieldTableCell.forTableColumn());
        //  houseColum.setCellFactory(TextFieldTableCell.forTableColumn());
*/
/*
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);*/

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
