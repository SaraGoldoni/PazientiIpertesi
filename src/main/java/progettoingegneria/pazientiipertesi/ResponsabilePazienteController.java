package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ResponsabilePazienteController implements Initializable {
    @FXML
    private TextField cf_paz;
    @FXML
    private TextField nome_paz;
    @FXML
    private TextField cognome_paz;
    @FXML
    private ChoiceBox<String> medico_ref;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private DatePicker data_nascita_paz;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
    String Query = ("SELECT CodiceFiscale FROM pazientiipertesi.Medico");
    try {
        PreparedStatement p = c.prepareStatement(Query);
        ResultSet rs;
        rs = p.executeQuery();
        while(rs.next()){
            medico_ref.getItems().add(rs.getString(1));
            medico_ref.setOnAction(this::getCodice);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public String getCodice(ActionEvent event){
        return medico_ref.getValue();
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
        medico_referente= getCodice(event);
        nome_utente=username.getText();
        pass= password.getText();

        ps.setString(1, CF.toUpperCase());
        ps.setString(2, nome);
        ps.setString(3, cognome);
        ps.setDate(4, Date.valueOf(data_nascita));
        ps.setString(5,medico_referente.toUpperCase());

        ps2.setString(1, nome_utente);
        ps2.setString(2, pass);
        ps2.setInt(3, 0);
        ps2.setString(4, CF.toUpperCase());
        ps2.setNull(5, Types.NULL);
        if(CF.length()==16){
            ps.executeUpdate();
            ps2.executeUpdate();
            c.commit();
            Controller.Switch("ResponsabilePaziente.fxml",event);
        }else{
            Alert a =new Alert(Alert.AlertType.ERROR);
            a.setContentText("Il codice fiscale deve essere di 16 caratteri");
            a.show();
        }



    }
    public void turnBack(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml", event);
    }
}
