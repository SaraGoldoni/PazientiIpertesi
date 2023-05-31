package progettoingegneria.pazientiipertesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PazienteController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button DatiGiornalieri;

    @FXML
    private Button SegnalazionePaziente;

    @FXML
    public void SwitchToMemo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("MemoGiornaliere.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField SBP;
    @FXML
    private TextField DBP;
    @FXML
    private TextField Sintomi;
    @FXML
    private TextField NomeFarmaco;
    @FXML
    private TextField quantita;
    @FXML
    private TextField DataFarmaco;
    @FXML
    private TextField OraFarmaco;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();

    /**
     * Inserisce nella tabella di memorizzazione le rilevazioni giornaliere dell'utente.
     */
    @FXML
    public void InsertIntoMemo() throws SQLException {

        String query = ("INSERT INTO pazientiipertesi.memo(SBP, DBP, sintomi, farmaco, quantità, data, idpaziente) VALUES (?, ?, ?, ?, ?, ?, ?)");
        try (PreparedStatement pstmt = c.prepareStatement(query)){
            c.setAutoCommit(false);
            String pressioneMassima = SBP.getText();
            String pressioneMinima = DBP.getText();
            String sintomo = Sintomi.getText();
            String Farmaco = NomeFarmaco.getText();
            String pillole = quantita.getText();
            //java.sql.Date d = java.sql.Date.valueOf(DataFarmaco.getValue());
            String data = DataFarmaco.getText();
            String ora = OraFarmaco.getText();



            pstmt.setString(1, pressioneMassima);
            pstmt.setString(2, pressioneMinima);
            pstmt.setString(3, sintomo); // consider setInt() might be more appropriate
            pstmt.setString(4, Farmaco);
            pstmt.setString(5, pillole);
            pstmt.setString(6, data);
            pstmt.setString(7, Controller.getNomeUtente());

            pstmt.executeUpdate();
            c.commit();
        }
    }


}
/*
* con.setAutoCommit(false);
stmt = con.createStatement();
stmt.executeUpdate(query);
con.commit();
* */


