package MainFiles;

import DataSources.Personnel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TablePersonnelController implements Initializable  {

    @FXML
    private TableView<Personnel> tableView;
    @FXML
    private TableColumn<Personnel, Integer> idTabNumColum;
@FXML
private TableColumn<Personnel, String> idSurnameColum;
@FXML
private TableColumn<Personnel, String> idNameColum;
@FXML
private TableColumn<Personnel, String> idLastNameColum;
@FXML
    private TableColumn<Personnel, LocalDate> idBirthColumn;
@FXML
private TableColumn<Personnel, String> idEducColum;
    @FXML
    private TableColumn<Personnel, LocalDate> idNaimColum;
    @FXML
    private TableColumn<Personnel, Integer> idCodeColum;


    @FXML
    private TextField idTabNumTextField;
    @FXML
    private TextField idSurnameTextField;
    @FXML
    private DatePicker idBirthDatePicker;
    @FXML
    private TextField idNameTextField;
    @FXML
    private TextField idLastNameTextField;
    @FXML
    private TextField idEducTextField;
    @FXML
    private DatePicker idNaimTextField;
    @FXML
    private TextField idCodeTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTabNumColum.setCellValueFactory(new PropertyValueFactory<Personnel, Integer>("tabNumber"));
        idSurnameColum.setCellValueFactory(new PropertyValueFactory<Personnel, String>("surname"));
        idBirthColumn.setCellValueFactory(new PropertyValueFactory<Personnel, LocalDate>("birthday"));
        idNameColum.setCellValueFactory(new PropertyValueFactory<Personnel, String>("name"));
        idLastNameColum.setCellValueFactory(new PropertyValueFactory<Personnel, String>("lastName"));
        idEducColum.setCellValueFactory(new PropertyValueFactory<Personnel, String>("education"));
        idNaimColum.setCellValueFactory(new PropertyValueFactory<Personnel, LocalDate>("hireDate"));
        idCodeColum.setCellValueFactory(new PropertyValueFactory<Personnel, Integer>("idPosition"));

        //load dummy data
        try {
            tableView.setItems(getPersonnel());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        idSurnameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        idEducColum.setCellFactory(TextFieldTableCell.forTableColumn());
        idNameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        idLastNameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
//получение данных с бд, код 3223 для чтения
    private ObservableList<Personnel> getPersonnel() throws IOException, ClassNotFoundException{
        ObservableList<Personnel> personnels = FXCollections.observableArrayList();
        Main.objectOutputStream.writeObject(3223);
        while (true) {
            int t = (int) Main.objectInputStream.readObject();
            if (0 != t)
                break;
            int idTabNumber = (int) Main.objectInputStream.readObject();
            String idSurname =  (String)Main.objectInputStream.readObject();
            String idName = (String) Main.objectInputStream.readObject();
            String idLastName = (String) Main.objectInputStream.readObject();
            LocalDate Birthday = (LocalDate) Main.objectInputStream.readObject();
            String idEducation = (String) Main.objectInputStream.readObject();
            LocalDate idNaim = (LocalDate) Main.objectInputStream.readObject();
            int position = (int) Main.objectInputStream.readObject();

            personnels.add(new Personnel(idTabNumber, idSurname, idName, idLastName, Birthday, idEducation, idNaim, position));

        }
        return personnels;
    }

    //здесь мы меняем поля, а потом записываем новые данные в базу данных
    public void changeidSurnameColumEvent(CellEditEvent edittedCell) throws IOException {
        Main.objectOutputStream.writeObject(3663);
        Personnel personnelSelected = tableView.getSelectionModel().getSelectedItem();
        personnelSelected.setSurname(edittedCell.getNewValue().toString());
        Main.objectOutputStream.writeObject(1);
        Main.objectOutputStream.writeObject(personnelSelected.getTabNumber());//Это нам нужно, для того,
        // чтобы по табельному номеру поменять значение записи
        Main.objectOutputStream.writeObject(edittedCell.getNewValue().toString());
    }

    public void changeidNameColumEvent(CellEditEvent edittedCell) throws IOException {
        Main.objectOutputStream.writeObject(3663);
        Personnel personnelSelected = tableView.getSelectionModel().getSelectedItem();
        personnelSelected.setName(edittedCell.getNewValue().toString());
        Main.objectOutputStream.writeObject(2);
        Main.objectOutputStream.writeObject(personnelSelected.getTabNumber());//Это нам нужно, для того,
        // чтобы по табельному номеру поменять значение записи
        Main.objectOutputStream.writeObject(edittedCell.getNewValue().toString());
    }

    public void changeidLastNameColumEvent(CellEditEvent edittedCell) throws IOException {

        Main.objectOutputStream.writeObject(3663);
        Personnel personnelSelected = tableView.getSelectionModel().getSelectedItem();
        personnelSelected.setLastName(edittedCell.getNewValue().toString());
        Main.objectOutputStream.writeObject(3);
        Main.objectOutputStream.writeObject(personnelSelected.getTabNumber());//Это нам нужно, для того,
        // чтобы по табельному номеру поменять значение записи
        Main.objectOutputStream.writeObject(edittedCell.getNewValue().toString());
    }

    public void changeidEducColumEvent(CellEditEvent edittedCell) throws IOException {
        Main.objectOutputStream.writeObject(3663);
        Personnel personnelSelected = tableView.getSelectionModel().getSelectedItem();
        personnelSelected.setEducation(edittedCell.getNewValue().toString());
        Main.objectOutputStream.writeObject(4);
        Main.objectOutputStream.writeObject(personnelSelected.getTabNumber());//Это нам нужно, для того,
        // чтобы по табельному номеру поменять значение записи
        Main.objectOutputStream.writeObject(edittedCell.getNewValue().toString());
    }
//event на удаление данных из таблицы и всязка с бд


    public void changeScreenButtonPushed(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if ( (int)Main.objectInputStream.readObject()<10 )
            Main.window.setScene( Main.sceneAdminMenu );
        else
            Main.window.setScene(Main.sceneUserMenu);
    }
}
