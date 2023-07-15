package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
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
     * @throws SQLException
     */
    @FXML
    public void InsertSintomo(ActionEvent event) throws SQLException, IOException {
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
            Controller.Switch("Sintomi.fxml", event);
        }
    }
    public void indietro(ActionEvent event) throws IOException {
        Controller.Switch("Paziente.fxml", event);
    }
}
