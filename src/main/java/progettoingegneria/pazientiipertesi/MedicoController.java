package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MedicoController {
    @FXML
    private Button A, B, C;
@FXML
private TextField paz;
@FXML
private TextField Farmaco;
@FXML
private TextField Assunzioni;
@FXML
private TextField Quantita;
@FXML
private TextArea Indicazioni;
@FXML
private DatePicker d_inizio;
@FXML
private DatePicker d_fine;
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
        Controller.Switch("InsTerapia.fxml", event);

    }
    public void InserisciTerapia(ActionEvent event) throws SQLException {
        String query = ("INSERT INTO pazientiipertesi.Terapia(Farmaco, Paziente, Medico, assunzioni, quantità, indicazioni, data_inizio, data_fine) VALUES (?, ?, ?, ?, ?, ? , ?, ?)");
        try(PreparedStatement prst = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            String F, P, M, I, data_I, data_F;
            int A, Q;
            F = Farmaco.getText();
            P = paz.getText();
            M = Controller.getCFMedico();
            A = Integer.parseInt(Assunzioni.getText());
            Q = Integer.parseInt(Quantita.getText());
            I = Indicazioni.getText();
            data_I = d_inizio.getValue().toString();
            data_F = d_fine.getValue().toString();

            prst.setString(1, F);
            prst.setString(2, P);
            prst.setString(3, M);
            prst.setInt(4, A);
            prst.setInt(5, Q);
            prst.setString(6, I);
            prst.setDate(7, Date.valueOf(data_I));
            prst.setDate(8, Date.valueOf(data_F));

            prst.executeUpdate();

            conn.commit();
            Controller.Switch("Medico.fxml",event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ModificaTerapia(ActionEvent event) throws IOException {
        Controller.Switch("ModTerapia.fxml", event);
    }

    public void VisualizzaDati(ActionEvent event) throws IOException {
        Controller.Switch("VisDati.fxml", event);
        //initialize(null,null);
    }

    /*@Override
    public void initialize(URL arg0, ResourceBundle arg1){
        String query = "SELECT * FROM pazientiipertesi.paziente ORDER BY nome, cognome";
        ObservableList<Paziente> pazienti = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()){
                Paziente A = new Paziente(rs.getString(1));//,rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
                pazienti.add(A);

            }
            System.out.println(pazienti.get(0).getCf());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CF.setCellValueFactory(new PropertyValueFactory<>("cf"));
        //Nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        //Cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        //referente.setCellValueFactory(new PropertyValueFactory<>("Referente"));
        //System.out.println(CF.getText());
        tabPazienti.setItems(pazienti);
    }*/

}
