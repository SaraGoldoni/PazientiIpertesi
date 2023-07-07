package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class ResponsabileMedicoController {
    @FXML
    private TextField cf_medico;
    @FXML
    private TextField nome_med;
    @FXML
    private TextField cognome_med;
    @FXML
    private TextField mail;
    @FXML
    private TextField nome_utente_med;
    @FXML
    private TextField pass_med;
    @FXML
    private DatePicker data_nascita_med;
    public void InsertMedico(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String query1 = ("INSERT INTO pazientiipertesi.Medico(codicefiscale, nome, cognome, data_nascita, email) VALUES (?, ?, ?, ?, ?)");
        String query3= ("INSERT INTO pazientiipertesi.Login(nomeutente, password, tipo, paziente, medico) VALUES (?, ?, ?, ?, ?)");
        String CF_med, N, C, D_N, email, username_med, pass_medico;
        CF_med=cf_medico.getText();
        N= nome_med.getText();
        C = cognome_med.getText();
        D_N= data_nascita_med.getValue().toString();
        email= mail.getText();
        username_med= nome_utente_med.getText();
        pass_medico= pass_med.getText();
        PreparedStatement ps1= c.prepareStatement(query1);
        PreparedStatement ps3= c.prepareStatement(query3);
        c.setAutoCommit(false);
        ps1.setString(1, CF_med.toUpperCase());
        ps1.setString(2, N);
        ps1.setString(3, C);
        ps1.setDate(4, Date.valueOf(D_N));
        ps1.setString(5,email);

        ps3.setString(1,username_med);
        ps3.setString(2,pass_medico);
        ps3.setInt(3, 1);
        ps3.setNull(4, Types.NULL);
        ps3.setString(5, CF_med.toUpperCase());

        ps1.executeUpdate();
        ps3.executeUpdate();
        c.commit();
        Controller.Switch("ResponsabileMedico.fxml",event);
    }
    public void turnBack(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml", event);
    }
}
