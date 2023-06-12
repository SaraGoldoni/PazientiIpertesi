package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class ResponsabileController {
@FXML
private TextField cf_paz;
@FXML
private TextField nome_paz;
    @FXML
    private TextField cognome_paz;
    @FXML
    private TextField medico_ref;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private DatePicker data_nascita_paz;

    public void NewPaziente(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabilePaziente.fxml",event);
    }
    public void NewMedico(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabileMedico.fxml",event);
    }

    public void InsertPaziente(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String query = ("INSERT INTO pazientiipertesi.Paziente(codicefiscale, nome, cognome, data_nascita, referente) VALUES (?, ?, ?, ?, ?)");
        String query2 = ("INSERT INTO pazientiipertesi.Login(nomeutente, password, tipo, paziente, medico) VALUES (?, ?, ?, ?, ?)");
        PreparedStatement ps = c.prepareStatement(query);
        PreparedStatement ps2 = c.prepareStatement(query2);
        c.setAutoCommit(false);
        String CF, nome, cognome, medico_referente, data_nascita, nome_utente,pass;
        CF= cf_paz.getText();
        nome = nome_paz.getText();
        cognome = cognome_paz.getText();
        data_nascita = data_nascita_paz.getValue().toString();
        medico_referente= medico_ref.getText();
        nome_utente=username.getText();
        pass= password.getText();

        ps.setString(1, CF);
        ps.setString(2, nome);
        ps.setString(3, cognome);
        ps.setDate(4, Date.valueOf(data_nascita));
        ps.setString(5,medico_referente);

        ps2.setString(1, nome_utente);
        ps2.setString(2, pass);
        ps2.setInt(3, 1);
        ps2.setString(4, CF);
        ps2.setNull(5, Types.NULL);

        ps.executeUpdate();
        ps2.executeUpdate();
        c.commit();







        Controller.Switch("Responsabile.fxml",event);
    }
    public void InsertMedico(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml",event);
    }

}

/* @FXML
    private ChoiceBox<String> utente;
    public void initialize(URL arg0, ResourceBundle arg1){
        utente.getItems().addAll("Medico","Paziente");
        utente.setOnAction(this::getUtente);
    }

    public String getUtente(ActionEvent event){
        return utente.getValue();
    }
    public void InsertUtente(ActionEvent event){
        initialize(null, null);
        String user = getUtente(event);
        if (user.equals("Medico")){
            System.out.println("inserisci nella tabella medico i dati");
        }
    }*/