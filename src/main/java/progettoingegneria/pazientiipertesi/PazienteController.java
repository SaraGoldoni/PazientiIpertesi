package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PazienteController implements Initializable{
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();


    @FXML
    private Label MailMedico;
    private String CFMedico;

    /**
     * Inizializza la scena con il contatto del medico di riferimento
     * @param arg0, URL
     * @param arg1, ResourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        String query1 = ("SELECT referente FROM pazientiipertesi.Paziente WHERE codicefiscale=?");
        try (PreparedStatement pst = c.prepareStatement(query1)) {
            String cf_paz_terapia = Controller.getCFPaziente();
            pst.setString(1, cf_paz_terapia);
            ResultSet rs = pst.executeQuery();

            rs.next();
            CFMedico=rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query2=("SELECT email FROM pazientiipertesi.Medico WHERE codicefiscale=?");
        try (PreparedStatement pst = c.prepareStatement(query2)) {
            pst.setString(1, CFMedico);
            ResultSet rs = pst.executeQuery();
            rs.next();
            MailMedico.setText(rs.getString(1));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Cambia la visualizzazione verso l'inserimento delle memorizzazioni giornaliere di pressione
     * @param event
     * @throws IOException
     */
    @FXML
    public void SwitchToMemo(ActionEvent event) throws IOException {
       Controller.Switch("MemoGiornaliere", event);
    }

    /**
     * Switch alla pagina di inserimento della terapia per il paziente
     * @param event, pressione del bottone
     * @throws IOException
     */
    public void SwitchToFarmaco(ActionEvent event) throws IOException {
        Controller.Switch("InserimentoFarmaco.fxml", event);
    }

    /**
     * Switch alla pagina di inserimento di sintomi e segnalazioni
     * @param event, pressione del pulsante
     * @throws IOException
     */
    public void SwitchToSintomo(ActionEvent event) throws IOException {
        Controller.Switch("Sintomi.fxml", event);
    }

    /**
     * Switch alla pagina di inserimento di patologie e terapie concomitanti
     * @param event, pressione del pulsante
     * @throws IOException
     */
    public void InsPatologieTerapie(ActionEvent event) throws IOException {
        Controller.Switch("PatologieTerapie.fxml", event);
}


}