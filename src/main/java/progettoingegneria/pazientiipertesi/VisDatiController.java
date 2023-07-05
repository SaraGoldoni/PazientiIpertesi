package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
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
private ListView<String> listafattoririschio;
@FXML
private TextField nomefattore;
@FXML
private Button inseriscifattore;
ObservableList<String> fattoririschio = FXCollections.observableArrayList();
ObservableList<String> fattoririschio1 = FXCollections.observableArrayList();
public void displayCF (String codiceFiscale){
        codicefiscale.setText(codiceFiscale);
    }
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
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


}
