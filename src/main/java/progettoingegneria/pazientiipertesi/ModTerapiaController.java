package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ModTerapiaController {

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
    public TextField cfpaz;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    public void displayCF (String codiceFiscale){
        cfpaz.setText(codiceFiscale);
    }

    public void prova(){
        System.out.println(cfpaz.getText());
    }

   /* public void fill(MouseEvent event){
        if(event.getClickCount()==1) {
            String query = ("SELECT farmaco FROM pazientiipertesi.Terapia WHERE paziente = ?");
            ObservableList<String> list = FXCollections.observableArrayList();
            try (PreparedStatement p = c.prepareStatement(query)) {
                c.setAutoCommit(false);
                p.setString(1, cfpaz.getText());
                ResultSet rs = p.executeQuery();
                c.commit();
                while (rs.next()) {
                    list.addAll(rs.getString(1));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            selterapia.getItems().addAll(list);
        }
    }*/
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

    public void fill(javafx.scene.input.MouseEvent event) {
      ObservableList<String> list = FXCollections.observableArrayList();
      if (selterapia.getItems().isEmpty()){
            String query = ("SELECT farmaco FROM pazientiipertesi.Terapia WHERE paziente = ?");

            try (PreparedStatement p = c.prepareStatement(query)) {

                p.setString(1, cfpaz.getText());
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    list.addAll(rs.getString(1));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            selterapia.getItems().addAll(list);

      }
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

