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
<<<<<<< Updated upstream
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
=======
import javafx.scene.control.*;
>>>>>>> Stashed changes
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

import static org.controlsfx.tools.ValueExtractor.getValue;

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
<<<<<<< Updated upstream
        choiceBox.getItems().addAll("farmaco1","farmaco2","farmaco3");
        choiceBox.setOnAction(this::getFarmaco);
=======

        String query = ("SELECT farmaco FROM \"Dati\".Terapia WHERE paziente = ?");

            try(PreparedStatement pst = c.prepareStatement(query)){
                String cf_paz_terapia = Controller.getCFPaziente();
                pst.setString(1, cf_paz_terapia);
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                   String farmaco= rs.getString(1);
                    choiceBox.getItems().add(farmaco);
                    choiceBox.setOnAction(this::getFarmaco);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    @FXML
    private TextField OraFarmaco;
    @FXML
    private TextField quantita;
    @FXML
    private TextField NumeroAssunzioni;
    public void InserisciFarmaco(ActionEvent event) throws SQLException, ParseException {
        String queryfarmaco = ("INSERT INTO \"Dati\".rilevazionefarmaco VALUES(?, ?, ?, ?, ?)");
        String NomeFarmaco = getFarmaco(event);
        Time ora = Time.valueOf(OraFarmaco.getText());
        //DateFormat orachange = new SimpleDateFormat("hh:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAssunzione = new Date();
        java.sql.Date sqlDate = new java.sql.Date(dataAssunzione.getTime());

        int quantitaFarmaco = Integer.parseInt(quantita.getText());
        int NAssunzioni= Integer.parseInt(NumeroAssunzioni.getText());



        PreparedStatement stt = c.prepareStatement(queryfarmaco);
        stt.setString(1, NomeFarmaco);
        stt.setInt(2, quantitaFarmaco);
        stt.setDate(3, sqlDate);
        stt.setInt(4, NAssunzioni);
        stt.setTime(5, ora);
        stt.executeUpdate();
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        String query = ("INSERT INTO pressione (SBP, DBP, DATA, IDPAZIENTE) VALUES (?,?,?,?)");
=======
        String query = ("INSERT INTO \"Dati\".Pressione(SBP, DBP, data, idpaziente) VALUES (?, ?, ?, ?)");
>>>>>>> Stashed changes
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


//inserire il campo ora nella tabella memo
/*
            pstmt.setString(1, pressioneMassima);
            pstmt.setString(2, pressioneMinima);
            pstmt.setString(3, sintomo); // consider setInt() might be more appropriate
            pstmt.setString(4, F);
            pstmt.setString(5, pillole);
            pstmt.setString(6, formatter.format(date));
            pstmt.setString(7, Controller.getNomeUtente());
*/
            pstmt.executeUpdate();
            c.commit();

            Controller.Switch("Paziente.fxml",event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void SwitchToSintomo(ActionEvent event) throws IOException {
        Controller.Switch("Sintomi.fxml", event);
        initialize(null,null);
    }

    @FXML
    private DatePicker DataSintomo;
    @FXML
    private TextField NomeSintomo;
    @FXML
    private TextArea DescrizioneSintomo;
    @FXML
    public void InsertSintomo(ActionEvent event) throws SQLException{
        String querySintomo = ("INSERT INTO \"Dati\".sintomo(Paziente, nomesintomo, data, descrizione) VALUES (?, ?, ?, ?)");
        try (PreparedStatement ps = c.prepareStatement(querySintomo)) {
            c.setAutoCommit(false);
            String cf_paz_sintomo = Controller.getCFPaziente();
            String DataS = DataSintomo.getValue().toString();
            String NomeS= NomeSintomo.getText();
            String DescrizioneS= DescrizioneSintomo.getText();

            ps.setString(1, cf_paz_sintomo);
            ps.setString(2, NomeS);
            ps.setDate(3, java.sql.Date.valueOf(DataS));
            ps.setString(4, DescrizioneS);

            ps.executeUpdate();
            c.commit();
        }
    }

}
