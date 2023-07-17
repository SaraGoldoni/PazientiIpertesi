package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.awt.event.MouseEvent;

import java.io.IOException;
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
    public Label cfpaz;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    public void displayCF (String codiceFiscale){
        cfpaz.setText(codiceFiscale);
    }



    public String getFarmaco(){
        return selterapia.getValue();
    }
    public void ModificaTerapia(ActionEvent event) throws SQLException, IOException {
       String query2= "UPDATE pazientiipertesi.Terapia SET farmaco = ?, paziente = ?, medico = ?, assunzioni = ?, quantità =?, indicazioni = ?, data_inizio=?, data_fine=? WHERE ((Paziente = ?) AND (farmaco = ?))";

       PreparedStatement p = c.prepareStatement(query2);
    if(d_fine.getValue().isAfter(d_inizio.getValue())){
        c.setAutoCommit(false);
        p.setString(1, getFarmaco());
        p.setString(2, cfpaz.getText());
        p.setString(3, Controller.getCFMedico());
        p.setInt(4,Integer.parseInt(Assunzioni.getText()));
        p.setInt(5, Integer.parseInt(Quantita.getText()));
        p.setString(6, Indicazioni.getText());
        p.setDate(7, Date.valueOf(d_inizio.getValue().toString()));
        p.setDate(8, Date.valueOf(d_fine.getValue().toString()));
        p.setString(9, cfpaz.getText());
        p.setString(10, getFarmaco());
        p.executeUpdate();
        c.commit();
        Controller.Switch("Medico.fxml", event);
    }else{
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setContentText("La data di fine inserita\n è antecedente alla data di inizio");
        al.show();
    }

    }
    public void backToMedico(ActionEvent event) throws IOException {
        Controller.Switch("Medico.fxml", event);
    }

    public void fill() {
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

    /*public void inizializzaTerapiaToMod() throws SQLException, InterruptedException {
        String farmaco = getFarmaco();
        while(farmaco == null) {
            farmaco= getFarmaco();
        }
            String query = ("SELECT *  FROM pazientiipertesi.Terapia where paziente = ? AND farmaco = ?");
            c.setAutoCommit(false);
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, cfpaz.getText());
            p.setString(2, selterapia.getValue());
            ResultSet rs = p.executeQuery();

            Assunzioni.setText(rs.getString(4));
            Quantita.setText(rs.getString(5));
            d_inizio.setAccessibleText(rs.getString(7));
            d_fine.setAccessibleText(rs.getString(8));
            c.commit();
        }*/


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



