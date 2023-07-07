package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VisDatiController {
@FXML
private Label codicefiscale, nome, cognome, dataDiNascita, labelfattore;
@FXML
private ListView<String> listafattoririschio, listapatologiepregresse, listapatologieconcomitanti;
@FXML
private TextField nomefattore;
@FXML
private Button inseriscifattore;
@FXML
private TextArea descrizionesegnalazione;
@FXML
private LineChart<Date , Integer> andaturasettimanale;
ObservableList<String> fattoririschio = FXCollections.observableArrayList();
DatabaseConnection conn = new DatabaseConnection();
Connection c = conn.link();

public void displayCF (String codiceFiscale){
        codicefiscale.setText(codiceFiscale);
    }
public void setInfoGenerali() throws SQLException {
    String Query = ("SELECT * FROM pazientiipertesi.Paziente where codicefiscale = ?");
    PreparedStatement p = c.prepareStatement(Query);
    p.setString(1, codicefiscale.getText());
    ResultSet rs = p.executeQuery();
    rs.next();
    nome.setText(rs.getString(2));
    cognome.setText(rs.getString(3));
    dataDiNascita.setText(rs.getString(4));
}
public void inserisciFattoriRischio() throws SQLException, IOException {
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    String Query = ("INSERT INTO pazientiipertesi.fattorerischio VALUES (?, ?, ?, ?)");
    //c.setAutoCommit(true);

    PreparedStatement ps = c.prepareStatement(Query);
    ps.setString(1, codicefiscale.getText());
    ps.setString(2, Controller.getCFMedico());
    ps.setString(3, nomefattore.getText());
    ps.setDate(4, date);
    ps.execute();


    listafattoririschio.getItems().add(nomefattore.getText());


}

public void FattoreRischioVisibile(){
    labelfattore.setVisible(true);
    nomefattore.setVisible(true);
    inseriscifattore.setVisible(true);
}
public void inizializzaLista() throws SQLException, IOException {
    String query = ("SELECT nome FROM pazientiipertesi.fattorerischio where paziente= ?");
    //fattoririschio.clear();

    PreparedStatement pss = c.prepareStatement(query);
    pss.setString(1, codicefiscale.getText());
    ResultSet rs = pss.executeQuery();
    int counter = 0;
    while(rs.next()){
        fattoririschio.add(rs.getString(1));

    }

   listafattoririschio.getItems().addAll(fattoririschio);



}

public void inizilizzapatologiaPreg() throws SQLException {
    String querypatpre = ("SELECT * FROM pazientiipertesi.anamnesiterapie WHERE paziente = ? AND tipo = 'pregressa'");
    PreparedStatement psst = c.prepareStatement(querypatpre);
    psst.setString(1, codicefiscale.getText());
    ResultSet rs = psst.executeQuery();
    while (rs.next()){
        listapatologiepregresse.getItems().add(rs.getString(1));
    }
}
public void inizilizzapatologiaConc() throws SQLException {
        String querypatconc = ("SELECT * FROM pazientiipertesi.anamnesiterapie WHERE paziente = ? AND tipo = 'concomitante'");
        PreparedStatement psst = c.prepareStatement(querypatconc);
        psst.setString(1, codicefiscale.getText());
        ResultSet rs = psst.executeQuery();
        while (rs.next()){
            listapatologieconcomitanti.getItems().add(rs.getString(1));
            listapatologieconcomitanti.getItems().add(rs.getString(3));
        }
    }
public void inizializzasegnalazione() throws SQLException {
        String querysegnalazione = ("SELECT * FROM pazientiipertesi.sintomo WHERE paziente = ? AND data = ?");
        PreparedStatement psst = c.prepareStatement(querysegnalazione);
        Date d = new Date();
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());
        psst.setString(1, codicefiscale.getText());
        psst.setDate(2, sqlDate);
        ResultSet rs = psst.executeQuery();
        while (rs.next()) {
            descrizionesegnalazione.setText(rs.getString(4));
        }
    }
/*public void inizializzagraficosettimanale(){
    XYChart.Series series = new XYChart.Series<>();
    }*/
}

