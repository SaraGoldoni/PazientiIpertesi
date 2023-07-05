package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;


public class PatologieTerapieController {


    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    @FXML
    public TextField patologia;
    @FXML
    private RadioButton PatPregressa;
    @FXML
    private RadioButton PatConcomitante;
    @FXML
    private TextArea PatInformazioni;

    public void InsPatologia(ActionEvent event) throws SQLException {
        String query = ("INSERT INTO pazientiipertesi.AnamnesiPatologie (nomepatologia, tipo, paziente, informazioni) values (?, ?, ?, ?)");
        try (PreparedStatement ps = c.prepareStatement(query)) {
            c.setAutoCommit(false);
            String cf_paz_sintomo = Controller.getCFPaziente();
            String Pat = patologia.getText();
            String PatP = PatPregressa.getText();
            String PatC = PatConcomitante.getText();
            String InfoPat = PatInformazioni.getText();

            ps.setString(1, Pat);
            if (PatPregressa.isSelected()) {
                ps.setString(2, PatP);
            } else {
                ps.setString(2, PatC);
            }
            ps.setString(3, cf_paz_sintomo);
            ps.setString(4, InfoPat);

            ps.executeUpdate();
            c.commit();
        }
    }

    @FXML
    private TextField FarmacoTerapia;
    @FXML
    private RadioButton TerPregressa;
    @FXML
    private RadioButton TerConcomitante;
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
            String TerP = TerPregressa.getText();
            String TerC = TerConcomitante.getText();
            String DataTerInizio = TerapiaDataInizio.getValue().toString();
            String InfoT = InfoTerapia.getText();

            ps.setString(1, FarmacoTer);
            if (TerPregressa.isSelected()) {
                ps.setString(2, TerP);
                String DataTerFine = TerapiaDataFine.getValue().toString();
                ps.setDate(4, java.sql.Date.valueOf(DataTerFine));
            } else {
                ps.setString(2, TerC);
                ps.setNull(4, Types.NULL);
            }
            ps.setDate(3, java.sql.Date.valueOf(DataTerInizio));
            ps.setString(5, InfoT);
            ps.setString(6, cf_paz_sintomo);

            ps.executeUpdate();
            c.commit();
}
}
}