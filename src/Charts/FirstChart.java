package Charts;

import MainFiles.ErrorWindow;
import MainFiles.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FirstChart implements Initializable {


    @FXML
    private BarChart<String, Number> FirstChart;
@FXML
private BarChart<String,Number> secondChart;
    @FXML
    private PieChart ThrdChart;

    public void onExitButtonPushed(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Main.objectOutputStream.writeObject(3333);

        if((int) Main.objectInputStream.readObject()<10)
            Main.window.setScene( Main.sceneAdminMenu );
       else
            Main.window.setScene(Main.sceneUserMenu);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Main.objectOutputStream.writeObject(4444);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        try {
            List<SalaryChart> usersSalaries = (List<SalaryChart>) Main.objectInputStream.readObject();
            for (SalaryChart i : usersSalaries) {
                series1.getData().add(new XYChart.Data(new String(i.getSurname() + i.getName().charAt(0) + "." + i.getLastName().charAt(0)), i.getSalary()));
            }
            FirstChart.getData().addAll(series1);

            List<PercentChart> usersPercents = (List<PercentChart>) Main.objectInputStream.readObject();
            for (PercentChart i : usersPercents) {
                series2.getData().add(new XYChart.Data(new String(i.getSurname() + i.getName().charAt(0) + "." + i.getLastName().charAt(0)), i.getSalary()));
            }
            secondChart.getData().addAll(series2);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            List<HiringChart> usersHire = (List<HiringChart>) Main.objectInputStream.readObject();
            int year2018 = 0, year2017 = 0, year2016 = 0, year2015 = 0, year2014 = 0;
            for (HiringChart i : usersHire) {
                if (i.getHire().getYear() == 2018)
                    year2018++;
                else if (i.getHire().getYear() == 2017)
                    year2017++;
                else if (i.getHire().getYear() == 2016)
                    year2016++;
                else if (i.getHire().getYear() == 2015)
                    year2015++;
                else if (i.getHire().getYear() == 2014)
                    year2014++;
            }
            System.out.println(year2018);
            System.out.println(year2017);
            pieChartData.add(new PieChart.Data("2018", year2018));
            pieChartData.add(new PieChart.Data("2017", year2017));
            pieChartData.add(new PieChart.Data("2016", year2016));
            pieChartData.add(new PieChart.Data("2015", year2015));
            pieChartData.add(new PieChart.Data("2014", year2014));
            ThrdChart.setData(pieChartData);
            ThrdChart.setTitle("Соотношение нанятых людей за последние 5 лет\nВсего нанято: " + (year2014 + year2015 + year2016 + year2017 + year2018));

            /*final Label caption = new Label("");
            caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");

            for (final PieChart.Data data : ThrdChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        });
            }*/
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //series1.setName("2003");


    }


}
