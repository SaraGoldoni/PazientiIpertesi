package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RilevazionePressioneController {
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    @FXML
    private TextField SBP;
    @FXML
    private TextField DBP;


    /**
     * Inserisce le rilevazioni di pressione giornaliere nel sistema
     * @param event, pressione del tasto Inserisci
     * @throws SQLException, errore nell'esecuzione della Query
     */
    @FXML
    public void InsertIntoMemo(ActionEvent event) throws SQLException {

        String query = ("INSERT INTO pazientiipertesi.Pressione(SBP, DBP, data, idpaziente, tipoipertensione) VALUES (?, ?, ?, ?, ?)");
        try (PreparedStatement pstmt = c.prepareStatement(query)){
            c.setAutoCommit(false);
            int pressioneMassima = Integer.parseInt(SBP.getText());
            int pressioneMinima = Integer.parseInt(DBP.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            ControlliVari valore = new ControlliVari();
            String v  = valore.controlloPressione(pressioneMassima,pressioneMinima);


            pstmt.setInt(1, pressioneMassima);
            pstmt.setInt(2, pressioneMinima);
            pstmt.setString(3, formatter.format(date));
            pstmt.setString(4, Controller.getCFPaziente());
            pstmt.setString(5, v);
            pstmt.executeUpdate();
            c.commit();

            Controller.Switch("Paziente.fxml",event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
