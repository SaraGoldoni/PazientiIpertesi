package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VisDatiController {
@FXML
private Label codicefiscale, nome, cognome, dataDiNascita, labelfattore,  nomesintomo, massima, minima, tipoipertensione, borderline, lieve, moderata, grave;
@FXML
private ListView<String> listafattoririschio, listapatologiepregresse, listapatologieconcomitanti;
@FXML
private TextField nomefattore;
@FXML
private Button inseriscifattore;
@FXML
private TextArea descrizionesegnalazione, pregressedescrizione, concomitantidescrizione;
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
    ps.setString(3, nomefattore.getText().toLowerCase());
    ps.setDate(4, date);
    ps.execute();


    listafattoririschio.getItems().add(nomefattore.getText());
    nomefattore.clear();

}

public void FattoreRischioVisibile(){
    labelfattore.setVisible(true);
    nomefattore.setVisible(true);
    inseriscifattore.setVisible(true);
}
public void inizializzaLista() throws SQLException, IOException {
    String query = ("SELECT nome FROM pazientiipertesi.fattorerischio where paziente= ?");

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
    String patpre;
    String querypatpre = ("SELECT * FROM pazientiipertesi.anamnesipatologie WHERE paziente = ? AND tipo = 'Pregressa'");
    PreparedStatement psst = c.prepareStatement(querypatpre);
    psst.setString(1, codicefiscale.getText());
    ResultSet rs = psst.executeQuery();
    while (rs.next()){
        listapatologiepregresse.getItems().add(rs.getString(1));
    }

}
    public void inizializzadescrizionePregressa() throws SQLException {
        String patpre = listapatologiepregresse.getSelectionModel().getSelectedItem();

        if(!listapatologiepregresse.getItems().isEmpty()){
            String query1 = ("SELECT informazioni FROM pazientiipertesi.anamnesipatologie WHERE paziente = ? AND tipo = 'Pregressa' AND nomepatologia= ?");
            PreparedStatement ps = c.prepareStatement(query1);
            ps.setString(1, codicefiscale.getText());
            ps.setString(2, patpre);
            ResultSet rs1;
            rs1 = ps.executeQuery();
            rs1.next();
            pregressedescrizione.setText(rs1.getString(1));
        }
    }
public void inizilizzapatologiaConc() throws SQLException {
        String querypatconc = ("SELECT * FROM pazientiipertesi.anamnesipatologie WHERE paziente = ? AND tipo = 'Concomitante'");
        PreparedStatement psst = c.prepareStatement(querypatconc);
        psst.setString(1, codicefiscale.getText());
        ResultSet rs = psst.executeQuery();
        while (rs.next()){
            listapatologieconcomitanti.getItems().add(rs.getString(1));
        }

    }
public void inizializzadescrizioneConcomitante() throws SQLException {
        String patconc = listapatologieconcomitanti.getSelectionModel().getSelectedItem();


        if(!listapatologieconcomitanti.getItems().isEmpty()){
            String query1 = ("SELECT informazioni FROM pazientiipertesi.anamnesipatologie WHERE paziente = ? AND tipo = 'Concomitante' AND nomepatologia= ?");
            PreparedStatement ps = c.prepareStatement(query1);
            ps.setString(1, codicefiscale.getText());
            ps.setString(2, patconc);
            ResultSet rs1;
            rs1 = ps.executeQuery();
            rs1.next();
            concomitantidescrizione.setText(rs1.getString(1));
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
            nomesintomo.setText(rs.getString(2));
        }
    }
    @FXML
    public void eliminaFattore() throws SQLException{
        int i = listafattoririschio.getSelectionModel().getSelectedIndex();
        System.out.print(i);
        fattoririschio.remove(i);
        String query = ("DELETE FROM pazientiipertesi.fattorerischio WHERE nome = ? AND paziente = ?");
        PreparedStatement p = c.prepareStatement(query);
        p.setString(1, listafattoririschio.getSelectionModel().getSelectedItem());
        p.setString(2, codicefiscale.getText());
        p.execute();

    }
public boolean inizializzapressioni() throws SQLException {
    boolean b= false;
    String query = ("SELECT * FROM pazientiipertesi.pressione WHERE idpaziente = ? and data = ?");
    c.setAutoCommit(false);
    PreparedStatement pt = c.prepareStatement(query);
    Date data = new Date();
    java.sql.Date sqlDate = new java.sql.Date(data.getTime());
    pt.setString(1, codicefiscale.getText());
    pt.setDate(2, sqlDate);
    ResultSet rs;
    rs= pt.executeQuery();

    c.commit();
    if(!rs.next()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Il paziente non ha ancora inserito le rilevazioni giornaliere");
        alert.setX(1000.);
        alert.show();
    }else{
        massima.setText(rs.getString(1));
        minima.setText(rs.getString(2));
        tipoipertensione.setText(rs.getString(5));
        b=true;
    }
    return b;
}

public void setIpertensione() throws SQLException {
    Alert alertgravita = new Alert(Alert.AlertType.WARNING);
    String query = ("SELECT * FROM pazientiipertesi.pressione WHERE idpaziente = ? and data = ?");
    c.setAutoCommit(false);
    PreparedStatement pt = c.prepareStatement(query);
    Date data = new Date();
    java.sql.Date sqlDate = new java.sql.Date(data.getTime());
    pt.setString(1, codicefiscale.getText());
    pt.setDate(2, sqlDate);
    ResultSet rs;
    rs= pt.executeQuery();
    rs.next();
    if(rs.getString(5).equals("BORDERLINE")){
        alertgravita.setContentText("Ipertensione di tipo BORDERLINE riscontrata");
        alertgravita.show();
        borderline.setVisible(true);
    }

    if(rs.getString(5).equals("LIEVE")){
        alertgravita.setContentText("Ipertensione di tipo LIEVE riscontrata");
        alertgravita.show();
        lieve.setVisible(true);
    }
    if(rs.getString(5).equals("MODERATA")){
        alertgravita.setContentText("Ipertensione di tipo MODERATA riscontrata");
        alertgravita.show();
        moderata.setVisible(true);
    }
    if(rs.getString(5).equals("GRAVE")){
        alertgravita.setContentText("Ipertensione di tipo GRAVE riscontrata");
        alertgravita.show();
        grave.setVisible(true);
    }
}
}

