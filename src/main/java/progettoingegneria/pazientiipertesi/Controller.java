package progettoingegneria.pazientiipertesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Alert alert = new Alert(Alert.AlertType.ERROR);


    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button Accedi;
    static String nomeutente;





    public static String getNomeUtente() {
        return nomeutente;
    }

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
            //Paziente user1 = new Paziente(nomeutente, "Mario", "Rossi");
            //query
            //SELECT nome, cognome FROM Paziente where nomeutente = 'nomeutente'
            while (rs.next()) {
                if ((rs.getString(1).equals(nomeutente)) && (rs.getString(2).equals(pass))) {
                    if (rs.getInt(3) == 1) {
                        root = FXMLLoader.load(getClass().getResource("Medico.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        root = FXMLLoader.load(getClass().getResource("Paziente.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        //root.setUserData();
                        stage.setScene(scene);
                        stage.show();

                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}

//FXMLLoader loader = new FXMLLoader(getClass().getResource("Paziente.fxml"));
//load(getClass().getResource("Paziente.fxml"));
//PazienteController pazienteController = loader.getController();
//pazienteController.SwitchToMemo(nomeutente);

