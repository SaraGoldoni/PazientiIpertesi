package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SintomiController {
    @FXML
    private DatePicker DataSintomo;
    @FXML
    private TextField NomeSintomo;
    @FXML
    private TextArea DescrizioneSintomo;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();

    /**
     * Inserisce la segnalazione di eventuali sintomi nel sistema
     * @param event, pressione del pulsante inserisci
     * @throws SQLException
     */
    @FXML
    public void InsertSintomo(ActionEvent event) throws SQLException {
        //String querySintomo = ("INSERT INTO \"Dati\".sintomo(Paziente, nomesintomo, data, descrizione) VALUES (?, ?, ?, ?)");
        String querySintomo = ("INSERT INTO pazientiipertesi.sintomo(Paziente, nomesintomo, data, descrizione) VALUES (?, ?, ?, ?)");
        try (PreparedStatement ps = c.prepareStatement(querySintomo)) {
            c.setAutoCommit(false);
            String cf_paz_sintomo = Controller.getCFPaziente();
            String DataS = DataSintomo.getValue().toString();
            String NomeS= NomeSintomo.getText();
            String DescrizioneS= DescrizioneSintomo.getText();

            ps.setString(1, cf_paz_sintomo);
            ps.setString(2, NomeS.toLowerCase());
            ps.setDate(3, java.sql.Date.valueOf(DataS));
            ps.setString(4, DescrizioneS);

            ps.executeUpdate();
            c.commit();
        }
    }
}
