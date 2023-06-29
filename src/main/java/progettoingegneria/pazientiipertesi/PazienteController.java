package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button DatiGiornalieri;
    @FXML
    private Button SegnalazionePaziente;

    @FXML
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();

    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();

    /**
     * Inizializza il ChoiceBox dell'interfaccia con i farmaci relativi al paziente loggato
     * @param arg0 URL, a null se non si conosce
     * @param arg1, ResourceBundle, a null se non si conosce
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        String query = ("SELECT farmaco FROM pazientiipertesi.Terapia WHERE paziente = ?");

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

    }

    public String getFarmaco(ActionEvent event){
        return choiceBox.getValue();

    }

    /**
     * Cambia la visualizzazione verso l'inserimento delle memorizzazioni giornaliere di pressione
     * @param event
     * @throws IOException
     */
    @FXML
    public void SwitchToMemo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("MemoGiornaliere.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    /**
     * Inserisce i dati relativi alle assunzioni del farmaco selezionato
     * @param event schiacciare il pulsante
     * @throws IOException
     */
    public void SwitchToFarmaco(ActionEvent event) throws IOException {
        Controller.Switch("InserimentoFarmaco.fxml", event);
        initialize(null,null);
    }

    @FXML
    private TextField OraFarmaco;
    @FXML
    private TextField quantita;
    @FXML
    private TextField NumeroAssunzioni;
    public void InserisciFarmaco(ActionEvent event) throws SQLException, ParseException {
        String queryfarmaco = ("INSERT INTO pazientiipertesi.rilevazionefarmaco VALUES(?, ?, ?, ?, ?)");
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
    }

    @FXML
    private TextField SBP;
    @FXML
    private TextField DBP;


    /**
     * Inserisce nella tabella di memorizzazione le rilevazioni giornaliere dell'utente.
     */
    @FXML
    public void InsertIntoMemo(ActionEvent event) throws SQLException {

        String query = ("INSERT INTO pazientiipertesi.Pressione(SBP, DBP, data, idpaziente) VALUES (?, ?, ?, ?)");
        try (PreparedStatement pstmt = c.prepareStatement(query)){
            c.setAutoCommit(false);
            int pressioneMassima = Integer.parseInt(SBP.getText());
            int pressioneMinima = Integer.parseInt(DBP.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();




            pstmt.setInt(1, pressioneMassima);
            pstmt.setInt(2, pressioneMinima);
            pstmt.setString(3, formatter.format(date));
            pstmt.setString(4, Controller.getCFPaziente());

            pstmt.executeUpdate();
            c.commit();

            Controller.Switch("Paziente.fxml",event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void indietro(ActionEvent event) throws IOException {
        Controller.Switch("Paziente.fxml", event);
    }


}






