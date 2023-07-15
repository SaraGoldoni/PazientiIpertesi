package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class PatologieTerapieController implements Initializable {
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    @FXML
    private TextField patologia;
    @FXML
    private ChoiceBox<String> ChoicePatologia;
    @FXML
    private TextArea PatInformazioni;

    public void InsPatologia(ActionEvent event) throws SQLException {
        String query = ("INSERT INTO pazientiipertesi.AnamnesiPatologie (nomepatologia, tipo, paziente, informazioni) values (?, ?, ?, ?)");
        try (PreparedStatement ps = c.prepareStatement(query)) {
            c.setAutoCommit(false);
            String cf_paz_sintomo = Controller.getCFPaziente();
            String Pat = patologia.getText();
            String InfoPat = PatInformazioni.getText();
            String TipoPatologia=getPatologia(event);

            ps.setString(1, Pat);
            ps.setString(2, TipoPatologia);
            ps.setString(3, cf_paz_sintomo);
            ps.setString(4, InfoPat);

            ps.executeUpdate();
            c.commit();
            Controller.Switch("PatologieTerapie.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField FarmacoTerapia;
    @FXML
    private ChoiceBox<String> ChoiceTerapia;
    @FXML
    private DatePicker TerapiaDataInizio;
    @FXML
    private DatePicker TerapiaDataFine;
    @FXML
    private TextArea InfoTerapia;

    public void InsTerapia(ActionEvent event) throws SQLException {
        String query2 = ("INSERT INTO pazientiipertesi.AnamnesiTerapie (nomefarmaco, tipo, datainizio, datafine, informazioni, paziente) values (?, ?, ?, ?, ?, ?)");
        try (PreparedStatement ps = c.prepareStatement(query2)) {
            c.setAutoCommit(false);
            String cf_paz_sintomo = Controller.getCFPaziente();
            String FarmacoTer = FarmacoTerapia.getText();
            String TipoTerapia=getTerapia(event);
            String DataTerInizio = TerapiaDataInizio.getValue().toString();
            String InfoT = InfoTerapia.getText();
            String DataTerFine = TerapiaDataFine.getValue().toString();
            if(TerapiaDataFine.getValue().isAfter(TerapiaDataInizio.getValue())){
                ps.setString(1, FarmacoTer);
                ps.setString(2, TipoTerapia);
                ps.setDate(4, Date.valueOf(DataTerFine));
                ps.setDate(3, Date.valueOf(DataTerInizio));
                ps.setString(5, InfoT);
                ps.setString(6, cf_paz_sintomo);

                ps.executeUpdate();
                c.commit();
                Controller.Switch("PatologieTerapie.fxml", event);
            }else{
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setContentText("La data di fine inserita\n è antecedente alla data di inizio");
                al.show();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void indietro(ActionEvent event) throws IOException {
        Controller.Switch("Paziente.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoicePatologia.getItems().addAll("Pregressa", "Concomitante");
        ChoicePatologia.setOnAction(this::getPatologia);

        ChoiceTerapia.getItems().addAll("Pregressa", "Concomitante");
        ChoicePatologia.setOnAction(this::getTerapia);
    }
    public String getPatologia(ActionEvent event){
        return ChoicePatologia.getValue();

    }
    public String getTerapia(ActionEvent event){
        return ChoiceTerapia.getValue();

}
}