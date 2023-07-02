package progettoingegneria.pazientiipertesi;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MedicoController implements Initializable{
    @FXML
    private Button A, B, C;

    @FXML
    private TableView<Paziente> tabPazienti;
    @FXML
    private TableColumn<Paziente,String> CF;
    @FXML
    private TableColumn<Paziente, String> Nome;
    @FXML
    private TableColumn<Paziente,String> Cognome;
    @FXML
    private TableColumn<Paziente,String> referente;

    DatabaseConnection c = new DatabaseConnection();
    Connection conn = c.link();

    /**
     * Metodo per effettuare lo Switch alla pagina di inserimento di una nuova terapia per il paziente selezionato nella tabella.
     * @param event, evento di selezione del bottone "Inserisci Terapia"
     * @throws IOException, eccezione che viene lanciata in caso di errore
     */
    public void SwitchToTerapia(ActionEvent event) throws IOException {

        String cfpaztab = tabPazienti.getSelectionModel().getSelectedItem().getCf();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InsTerapia.fxml"));
        Parent root = loader.load();
        InsTerapiaController controller = loader.getController();
        controller.displayCF(cfpaztab);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void ModificaTerapia(ActionEvent event) throws IOException {
        Controller.Switch("ModTerapia.fxml", event);
    }

    public void VisualizzaDati(ActionEvent event) throws IOException {
        Controller.Switch("VisDati.fxml", event);
        //initialize(null,null);
    }

    /**
     * Metodo per l'inizializzazione della tabella di pazienti vista dal medico
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
   @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        String query = "SELECT * FROM pazientiipertesi.paziente ORDER BY nome, cognome";
        ObservableList<Paziente> pazienti = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()){
                Paziente A = new Paziente(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
                pazienti.add(A);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CF.setCellValueFactory(new PropertyValueFactory<>("cf"));
        Nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        Cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        referente.setCellValueFactory(new PropertyValueFactory<>("Referente"));

        tabPazienti.getItems().setAll(pazienti);


    }
    public void Visibile(){
        A.setVisible(true);
        B.setVisible(true);
        C.setVisible(true);
    }

}
