package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class InserisciFarmacoController implements Initializable {
    @FXML
    private TextField OraFarmaco;
    @FXML
    private TextField quantita;
    @FXML
    private TextField NumeroAssunzioni;
    @FXML
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();

    /**
     * Inizializza il ChoiceBox dell'interfaccia con i farmaci relativi al paziente che ha effettuato il login
     * @param arg0 URL, a null se non si conosce
     * @param arg1, ResourceBundle, a null se non si conosce
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        String query = ("SELECT farmaco, data_fine FROM pazientiipertesi.Terapia WHERE paziente = ? AND data_fine >= CURRENT_DATE");

        try (PreparedStatement pst = c.prepareStatement(query)) {
            String cf_paz_terapia = Controller.getCFPaziente();
            pst.setString(1, cf_paz_terapia);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String farmaco = rs.getString(1);
                choiceBox.getItems().add(farmaco);
                choiceBox.setOnAction(this::getFarmaco);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getFarmaco(ActionEvent event){
        return choiceBox.getValue();
    }
    /**
     * Inserisce i dati relativi alle assunzioni del farmaco selezionato
     * @param event pressione del pulsante
     * @throws IOException
     */
    public void InserisciFarmaco(ActionEvent event) throws SQLException, IOException {
        String queryfarmaco = ("INSERT INTO pazientiipertesi.rilevazionefarmaco VALUES(?, ?, ?, ?, ?, ?)");
        String NomeFarmaco = getFarmaco(event);
        Date dataAssunzione = new Date();
        java.sql.Date sqlDate = new java.sql.Date(dataAssunzione.getTime());

        int quantitaFarmaco = Integer.parseInt(quantita.getText());
        int NAssunzioni= Integer.parseInt(NumeroAssunzioni.getText());

        PreparedStatement stt = c.prepareStatement(queryfarmaco);
        stt.setString(1, NomeFarmaco.toLowerCase());
        stt.setInt(2, quantitaFarmaco);
        stt.setDate(3, sqlDate);
        stt.setInt(4, NAssunzioni);
        stt.setString(5, Controller.getCFPaziente());
        stt.setTime(6, Time.valueOf(OraFarmaco.getText()));
        stt.executeUpdate();
        Controller.Switch("InserimentoFarmaco.fxml", event);
    }
    @FXML
    public void indietro(ActionEvent event) throws IOException {
        Controller.Switch("Paziente.fxml", event);

    }
}
