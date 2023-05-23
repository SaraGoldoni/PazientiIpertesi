package progettoingegneria.pazientiipertesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

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
    private TextField Sintomi;
    private TextField NomeFarmaco;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    public void InsertIntoMemo(ActionEvent event) throws SQLException {

        String query = ("INSERT INTO Memo(SBP, DBP, sintomi, farmaco, quantità, data, ora)\" + \"VALUES (?, ?, ?, ?, ?, ?, ?");
        try (PreparedStatement pstmt = c.prepareStatement(query)) {
            //PreparedStatement pstmt= null;
            String pressioneMassima = SBP.getText();
            String pressioneMinima = DBP.getText();
            String sintomo = Sintomi.getText();
            String Farmaco = NomeFarmaco.getText();
            pstmt.setString(1, pressioneMassima);
            pstmt.setString(2, pressioneMinima);
            pstmt.setString(3, sintomo); // consider setInt() might be more appropriate
            pstmt.setString(4, Farmaco);
            pstmt.setString(5,);
            pstmt.setString(6,);
            pstmt.setString(7,);

            int status = pstmt.executeUpdate();
    }
}
