package progettoingegneria.pazientiipertesi;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PazienteController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button DatiGiornalieri;
    @FXML
    private Button SegnalazionePaziente;

    @FXML
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        choiceBox.getItems().addAll("farmaco1","farmaco2","farmaco3");
        choiceBox.setOnAction(this::getFarmaco);
    }

    public String getFarmaco(ActionEvent event){
        return choiceBox.getValue();

    }


    @FXML
    public void SwitchToMemo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("MemoGiornaliere.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        initialize(null,null);

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
    private TextField OraFarmaco;


    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();

    /**
     * Inserisce nella tabella di memorizzazione le rilevazioni giornaliere dell'utente.
     */
    @FXML
    public void InsertIntoMemo(ActionEvent event) throws SQLException {

        String query = ("INSERT INTO pazientiipertesi.memo(SBP, DBP, sintomi, farmaco, quantità, data, idpaziente) VALUES (?, ?, ?, ?, ?, ?, ?)");
        try (PreparedStatement pstmt = c.prepareStatement(query)){
            c.setAutoCommit(false);
            String pressioneMassima = SBP.getText();
            String pressioneMinima = DBP.getText();
            String sintomo = Sintomi.getText();
            //String Farmaco = NomeFarmaco.getText();
            String pillole = quantita.getText();
            String F = getFarmaco(event);
            //System.out.println(F);
            //java.sql.Date d = java.sql.Date.valueOf(DataFarmaco.getValue());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String ora = OraFarmaco.getText();




            pstmt.setString(1, pressioneMassima);
            pstmt.setString(2, pressioneMinima);
            pstmt.setString(3, sintomo); // consider setInt() might be more appropriate
            pstmt.setString(4, F);
            pstmt.setString(5, pillole);
            pstmt.setString(6, formatter.format(date));
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








