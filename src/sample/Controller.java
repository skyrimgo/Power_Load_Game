package sample;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.szy.ChartDraw;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView search;
    @FXML
    private ImageView dashboard;
    @FXML
    private ImageView projects;
    @FXML
    private ImageView plant;
    @FXML
    private ImageView supplier;

    @FXML
    private ImageView users;
    @FXML
    private ImageView setting;
    @FXML
    private ImageView add;
    @FXML
    private ImageView quit;
    @FXML
    private AnchorPane root;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private JFXSpinner circle1;
    @FXML
    private JFXSpinner circle2;
    @FXML
    private VBox dashboard1;
    @FXML
    private VBox dashboard2;
    @FXML
    private VBox dashboard3;
    @FXML
    private VBox dashboard4;
    @FXML
    private VBox dashboard5;
    @FXML
    private VBox dashboard6;
    @FXML
    private JFXTextField plant_peak_load_quantity;
    @FXML
    private JFXTextField plant_medium_load_quantity;
    @FXML
    private JFXTextField plant_low_load_quantity;
    @FXML
    private JFXTextField supply_low_load_price;
    @FXML
    private JFXTextField supply_medium_load_price;
    @FXML
    private JFXTextField supply_peak_load_price;
    @FXML
    private JFXTextField times;

    private double xOffset;
    private double yOffset;
    private Stage stage;
    private double data_plant_low_load_quantity;
    private double data_plant_meadium_load_quantity;
    private double data_plant_peak_load_quantity;
    private double data_supply_low_load_price;
    private double data_supply_medium_load_price;
    private double data_supply_peak_load_price;
    private int data_times;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\search.png"));
        plant.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\elef.png"));
        supplier.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\eleg.png"));
        dashboard.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\dashboard.png"));
        users.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\users.png"));
        setting.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\search.png"));
        add.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\add.png"));
        projects.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\projects.png"));
        quit.setImage(new Image("file:C:\\Users\\shinelon\\IdeaProjects\\electric_gameUI\\lib\\pic\\quit.png"));
    }

    private class Calculate {
        private void getCalculate() {
            ChartDraw chartDraw = new ChartDraw();
            //List<XYChart.Series<String, Number>> list = new ArrayList<>(0);
            data_plant_low_load_quantity = Double.parseDouble(plant_low_load_quantity.getText());
            data_plant_meadium_load_quantity = Double.parseDouble(plant_medium_load_quantity.getText());
            data_plant_peak_load_quantity = Double.parseDouble(plant_peak_load_quantity.getText());
            data_supply_low_load_price = Double.parseDouble(supply_low_load_price.getText());
            data_supply_medium_load_price = Double.parseDouble(supply_medium_load_price.getText());
            data_supply_peak_load_price = Double.parseDouble(supply_peak_load_price.getText());
            data_times = Integer.parseInt(times.getText());
            List<List<Double>> res =
                    chartDraw.run(data_plant_low_load_quantity,
                            data_plant_meadium_load_quantity,
                            data_plant_peak_load_quantity,
                            data_supply_low_load_price,
                            data_supply_medium_load_price,
                            data_supply_peak_load_price,
                            data_times
                    );
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            for (int i = 1; i <= res.get(0).size(); i += 1) {
                series1.getData().add(
                        new XYChart.Data<>(String.valueOf(i), res.get(0).get(i - 1))

                );
                series2.getData().add(
                        new XYChart.Data<>(String.valueOf(i), res.get(1).get(i - 1))

                );
            }

            lineChart.getData().addAll(series1, series2);
        }

    }

    public void init() {
        stage = (Stage) root.getScene().getWindow();
    }

    public void press(MouseEvent event) {

        xOffset = event.getSceneX();
        yOffset = event.getSceneY();

    }

    public void drag(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void drawChart() {
        Calculate calculate = new Calculate();
        calculate.getCalculate();
        stoproll();
        //dashboard1.setVisible(false);
        //lineChart.getData().addAll(list.get(0), list.get(1));
        //lineChart.getData().add(series1);
    }

    @FXML
    private void showFirstDashboard() {
        dashboard2.setVisible(false);
        dashboard3.setVisible(false);
        dashboard4.setVisible(false);
        dashboard5.setVisible(false);
        dashboard6.setVisible(false);
        dashboard1.setVisible(true);
    }

    @FXML
    private void showSecondDashboard() {
        dashboard1.setVisible(false);
        dashboard3.setVisible(false);
        dashboard4.setVisible(false);
        dashboard5.setVisible(false);
        dashboard6.setVisible(false);
        dashboard2.setVisible(true);
    }

    @FXML
    private void showThirdDashboard() {
        dashboard1.setVisible(false);
        dashboard2.setVisible(false);
        dashboard4.setVisible(false);
        dashboard5.setVisible(false);
        dashboard6.setVisible(false);
        dashboard3.setVisible(true);
        System.out.println(Double.parseDouble(plant_low_load_quantity.getText()));
        System.out.println(Double.parseDouble(plant_medium_load_quantity.getText()));
        System.out.println(Double.parseDouble(plant_peak_load_quantity.getText()));

    }

    @FXML
    private void showForthDashboard() {
        dashboard1.setVisible(false);
        dashboard2.setVisible(false);
        dashboard3.setVisible(false);
        dashboard5.setVisible(false);
        dashboard6.setVisible(false);
        dashboard4.setVisible(true);
        System.out.println(Double.parseDouble(supply_low_load_price.getText()));
        System.out.println(Double.parseDouble(supply_medium_load_price.getText()));
        System.out.println(Double.parseDouble(supply_peak_load_price.getText()));
    }

    @FXML
    private void showFifthDashboard() {
        dashboard1.setVisible(false);
        dashboard2.setVisible(false);
        dashboard3.setVisible(false);
        dashboard4.setVisible(false);
        dashboard6.setVisible(false);
        dashboard5.setVisible(true);
    }

    @FXML
    private void showSixthDashboard() {
        dashboard1.setVisible(false);
        dashboard2.setVisible(false);
        dashboard3.setVisible(false);
        dashboard4.setVisible(false);
        dashboard5.setVisible(false);
        dashboard6.setVisible(true);
        System.out.println(Double.parseDouble(times.getText()));

    }

    private void stoproll() {
        circle1.setProgress(1);
        circle2.setProgress(1);

    }

    public void quit() {
        stage.close();
    }

}
