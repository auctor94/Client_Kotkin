package Reports;

import MainFiles.ErrorWindow;
import MainFiles.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportsWindowController implements Initializable {

    @FXML
    private Button firstReport;

    @FXML
    private Button secReport;

    @FXML
    private Button thrdReport;

    @FXML
    private Button fullReport;

    @FXML
    private Button treeReport;

    @FXML
    private Button exitButton;

    @FXML
    void OnExitButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if ( (int)Main.objectInputStream.readObject()<10 )
            Main.window.setScene( Main.sceneAdminMenu );
        else
            Main.window.setScene(Main.sceneUserMenu);
    }

    @FXML
    void OnFullReportPushed(ActionEvent event) throws IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор папки для сохранения отчета");//Заголовок диалога
        File file = fileChooser.showDialog(Main.window.getOwner());
        Main.objectOutputStream.writeObject(8884);
        Main.objectOutputStream.writeObject(file.getAbsolutePath());
    }

    @FXML
    void OnTreeReportPushed(ActionEvent event) {

    }

    @FXML
    void onFirstReportPushed(ActionEvent event) throws IOException {

        DirectoryChooser fileChooser = new DirectoryChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор папки для сохранения отчета");//Заголовок диалога
        File file = fileChooser.showDialog(Main.window.getOwner());
            Main.objectOutputStream.writeObject(8881);
        Main.objectOutputStream.writeObject(file.getAbsolutePath());
    }

    @FXML
    void onSecReportPushed(ActionEvent event) throws IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор папки для сохранения отчета");//Заголовок диалога
        File file = fileChooser.showDialog(Main.window.getOwner());
        Main.objectOutputStream.writeObject(8882);
        Main.objectOutputStream.writeObject(file.getAbsolutePath());
    }

    @FXML
    void onThrdReportPushed(ActionEvent event) throws IOException {
        DirectoryChooser fileChooser = new DirectoryChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Выбор папки для сохранения отчета");//Заголовок диалога
        File file = fileChooser.showDialog(Main.window.getOwner());
        Main.objectOutputStream.writeObject(8883);
        Main.objectOutputStream.writeObject(file.getAbsolutePath());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
