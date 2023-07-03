package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ModTerapiaController implements Initializable {

    @FXML
    public ChoiceBox<String> selterapia = new ChoiceBox<>();
    @FXML
    public TextField Assunzioni;
    @FXML
    public TextField Quantita;
    @FXML
    public TextArea Indicazioni;
    @FXML
    public DatePicker d_inizio;
    @FXML
    public DatePicker d_fine;
    @FXML
    private Label assgiornaliere, quantita, data_i, data_f, indicazioni;
    @FXML
    private TextField cfpaz;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    public void displayCF (String codiceFiscale){
        cfpaz.setText(codiceFiscale);
        //System.out.println(cfpaz.getText());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //String query = ("SELECT farmaco FROM pazientiipertesi.Terapia WHERE paziente ="/ +/cfpaz.getText());
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                //System.out.println(rs.getString(1));
                String farmaco= rs.getString(1);
                selterapia.getItems().add(farmaco);
                selterapia.setOnAction(this::getFarmaco);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }





    }
    public String getFarmaco(ActionEvent event){
        return selterapia.getValue();
    }
    public void ModificaTerapia(ActionEvent event) throws SQLException {
       String query2= "UPDATE TABLE pazientiipertesi.Terapia SET farmaco = ?, paziente = ?, medico = ?, assunzioni = ?, quantità =?, indicazioni = ?, data_inizio=?, data_fine=? WHERE Paziente = ? AND farmaco = ?";

       PreparedStatement p = c.prepareStatement(query2);

       p.setString(1, getFarmaco(event));
       p.setString(2, cfpaz.getText());
       p.setString(3, Controller.getCFMedico());
       //p.setString(4,ass);
    }
   /* public void Visibile(){
        System.out.println(cfpaz.getText());
        Assunzioni.setVisible(true);
        Quantita.setVisible(true);
        Indicazioni.setVisible(true);
        d_inizio.setVisible(true);
        d_fine.setVisible(true);
        assgiornaliere.setVisible(true);
        quantita.setVisible(true);
        data_i.setVisible(true);
        data_f.setVisible(true);
        indicazioni.setVisible(true);

    }*/
    /*public void metododiprova(ActionEvent event){
        String F = getFarmaco(event);

    }*/

}

