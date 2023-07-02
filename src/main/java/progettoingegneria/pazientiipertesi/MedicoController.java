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



    public void SwitchToTerapia(ActionEvent event) throws IOException {
        String cfpaztab = tabPazienti.getSelectionModel().getSelectedItem().getCf();
        //System.out.println(cfpaztab);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InsTerapia.fxml"));
        Parent root = loader.load();
        InsTerapiaController controller = loader.getController();
        controller.displayCF(cfpaztab);
        //Controller.Switch("InsTerapia.fxml", event);
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
            System.out.println(pazienti.get(0).getCf());
            System.out.println(pazienti.size());

            //System.out.println(this.CF.getText());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CF.setCellValueFactory(new PropertyValueFactory<>("cf"));
        Nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        Cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        referente.setCellValueFactory(new PropertyValueFactory<>("Referente"));

        tabPazienti.getItems().setAll(pazienti);


    }
//Static Paziente paz =
   /* public String getcodicefiscalepaz (){
        Integer Indice = tabPazienti.getSelectionModel().getSelectedIndex();

        System.out.println(cfpaz);
        return cfpaz;
    }*/



       /* public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            //Check whether item is selected and set value of selected item to Label
            if(tabPazienti.getSelectionModel().getSelectedItem() != null)
            {
                TableView.TableViewSelectionModel selectionModel = tabPazienti.getSelectionModel();
                ObservableList selectedCells = selectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(newValue);
                System.out.println("Selected Value" + val);
            }
        }*/

}
