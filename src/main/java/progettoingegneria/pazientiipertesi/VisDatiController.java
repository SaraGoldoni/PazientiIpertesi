package progettoingegneria.pazientiipertesi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisDatiController implements Initializable {
    @FXML
    private BarChart<?, ?> barchart;
    @FXML
    private LineChart<?, ?> line;
    @FXML
    private TabPane tab;
@FXML
private Tab patologie, terapieconc;
@FXML
private List listaterapie, listapatologie;

    @Override
    public void initialize(URL url, ResourceBundle arg1){


    }


}
