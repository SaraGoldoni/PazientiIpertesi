package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller{

    Alert alert = new Alert(Alert.AlertType.ERROR);
    ActionEvent event;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button Accedi;
    static String nomeutente;
    @FXML
            private DialogPane prova;


    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();



    public static String getNomeUtente() {
        return nomeutente;
    }

    public static String getCFPaziente() throws SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String CF=getNomeUtente();
        ResultSet rs;
        String query = ("SELECT paziente FROM PazientiIpertesi.Login where NomeUtente=?");
        PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, CF);
            rs=pstmt.executeQuery();
            rs.next();
            return rs.getString(1);
    }
    public static String getCFMedico() throws SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String CF=getNomeUtente();
        ResultSet rs;
        String query = ("SELECT medico FROM PazientiIpertesi.Login where NomeUtente=?");
        PreparedStatement pstmt = c.prepareStatement(query);
        pstmt.setString(1, CF);
        rs=pstmt.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public static void Switch(String s, ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(Controller.class.getResource(s));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


/**
 * Alla pressione del tasto Login fa il controllo delle credenziali inserite nel form.
 * Se sono corrette controlla se appartengono ad un medico o ad un paziente.
 * Switch alla pagina corretta.
 * @param event oggetto della classe ActionEvent
 *
 */

    @FXML
    private void ControlloLogin(ActionEvent event) throws SQLException {
         DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        try (Statement st = c.createStatement()) {
            ResultSet rs = null;
            rs = st.executeQuery("SELECT * FROM PazientiIpertesi.Login");
            String pass;
            nomeutente = username.getText();
            System.out.println(nomeutente);
            pass = password.getText();
            boolean flag = false;
            while (rs.next()) {
                if (rs.getString(1).equals("00000")&&(rs.getString(2).equals(pass))){
                    Switch("Responsabile.fxml", event);
                    flag = true;

                } else if ((rs.getString(1).equals(nomeutente)) && (rs.getString(2).equals(pass))) {
                    if (rs.getInt(3) == 1) {
                        Switch("Medico.fxml",event);
                        flag = true;

                    } else {

                        Switch("Paziente.fxml", event);
                        flag = true;
                    }
                }

            }
            if (!flag) {
                alert.setHeaderText("Errore di autenticazione");
                alert.setContentText("Nome utente o password errati");
                alert.show();

            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}


