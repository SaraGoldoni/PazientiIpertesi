package progettoingegneria.pazientiipertesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class PazienteController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void SwitchToMemo(javafx.event.ActionEvent event) throws IOException {
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
    private DatePicker DataFarmaco;
    @FXML
    private TextField OraFarmaco;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();


    @FXML
    private void InsertIntoMemo() throws SQLException {
        String query = ("INSERT INTO Memo(SBP, DBP, sintomi, farmaco, quantità, data, ora)\" + \"VALUES (?, ?, ?, ?, ?, ?, ?");
        try (PreparedStatement pstmt = c.prepareStatement(query)) {

            String pressioneMassima = SBP.getText();
            String pressioneMinima = DBP.getText();
            String sintomo = Sintomi.getText();
            String Farmaco = NomeFarmaco.getText();
            String pillole = quantita.getText();
            LocalDate d= DataFarmaco.getValue();
            String ora = OraFarmaco.getText();
            pstmt.setString(1, pressioneMassima);
            pstmt.setString(2, pressioneMinima);
            pstmt.setString(3, sintomo); // consider setInt() might be more appropriate
            pstmt.setString(4, Farmaco);
            pstmt.setInt(5, Integer.parseInt(pillole));
            pstmt.setDate(6, Date.valueOf(d));
            pstmt.setTime(7, Time.valueOf(ora));

            int status = pstmt.executeUpdate();
        }
    }

    public void immetti(MouseEvent mouseEvent) throws SQLException {
        InsertIntoMemo();
    }
}

/*try ( PreparedStatement pst = con.prepareStatement (
                    " INSERT INTO Spese (id, data , voce , importo ) VALUES (?, ? , ? , ?), (?, ? ,? ,?) ,(?, ? ,? ,?) ,(?, ? ,? ,?); " ) ) {

            pst.clearParameters () ;
            pst.setDate ( 1 , new Date ( sdf . parse ( " 24/02/2016 ") . getTime () ) ) ;
            pst.setString ( 2 , " Stipendio " ) ;
            pst.setBigDecimal ( 3 , new BigDecimal (  "0.1") ) ;
            pst.setDate ( 4 , new Date ( sdf . parse ( " 24/02/2016 ") . getTime () ) ) ;
            pst.setString ( 5 , " Stipendio " Bis " " ) ;
            pst.setBigDecimal ( 6 , new BigDecimal ( " 0.1 " ) ) ; */
