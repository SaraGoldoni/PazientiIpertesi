package progettoingegneria.pazientiipertesi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
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
private Button inseriscifattore, indietro;
@FXML
private TextArea descrizionesegnalazione, pregressedescrizione, concomitantidescrizione;
@FXML
private TableView<Pressione> tabpressioni;
@FXML
        private TableColumn<Pressione,Integer> maxcol;
@FXML
        private TableColumn<Pressione, Integer> mincol;
@FXML
        private TableColumn<Pressione,Date> datacol;
@FXML
        private TableColumn<Pressione, String> gravitacol;
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
    c.setAutoCommit(false);

    PreparedStatement ps = c.prepareStatement(Query);
    ps.setString(1, codicefiscale.getText());
    ps.setString(2, Controller.getCFMedico());
    ps.setString(3, nomefattore.getText().toLowerCase());
    ps.setDate(4, date);
    ps.execute();
    c.commit();

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
    if(patconc == null){
        System.out.print("Riprova");
    }
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
    @FXML
    public void modificaDescrizionePatologiePregresse() throws SQLException {
        String patpre = listapatologiepregresse.getSelectionModel().getSelectedItem();
        String query1 = ("update pazientiipertesi.anamnesipatologie set informazioni=?, WHERE paziente = ? AND tipo = 'Pregressa' AND nomepatologia= ?");
        String query2= ("INSERT INTO pazientiipertesi.modifica(paziente, medico, patologia, dataModifica) VALUES (?,?,?,?)");
        Date data = new Date();
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        c.setAutoCommit(false);
        PreparedStatement ps = c.prepareStatement(query1);
        ps.setString(1, pregressedescrizione.getText());
        ps.setString(2, codicefiscale.getText());
        ps.setString(3, patpre);
        ps.executeUpdate();
        PreparedStatement p2 = c.prepareStatement(query2);
        p2.setString(1, codicefiscale.getText());
        p2.setString(2, Controller.getCFMedico());
        p2.setString(3, patpre);
        p2.setDate(4, sqlDate);
        p2.executeUpdate();
        c.commit();
    }
    @FXML
    public void modificaDescrizionePatologieConcomitanti() throws SQLException {
        String patconc = listapatologieconcomitanti.getSelectionModel().getSelectedItem();
        String query1 = ("UPDATE pazientiipertesi.anamnesipatologie set informazioni = ? WHERE paziente = ? AND tipo = 'Concomitante' AND nomepatologia= ?");
        String query2= ("INSERT INTO pazientiipertesi.modifica(paziente, medico, patologia, dataModifica) VALUES (?,?,?,?)");
        Date data = new Date();
        c.setAutoCommit(false);
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());


        try(PreparedStatement ps = c.prepareStatement(query1)){
            ps.setString(1, concomitantidescrizione.getText());
            ps.setString(2, codicefiscale.getText());
            ps.setString(3, patconc);
            ps.executeUpdate();
            PreparedStatement p2 = c.prepareStatement(query2);
            p2.setString(1, codicefiscale.getText());
            p2.setString(2, Controller.getCFMedico());
            p2.setString(3, patconc);
            p2.setDate(4, sqlDate);
            p2.executeUpdate();
            c.commit();
        } catch(Exception e){
            System.out.println("la query non è andata a buon fine " + e.getMessage());
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
        fattoririschio.remove(i);
        String query = ("DELETE FROM pazientiipertesi.fattorerischio WHERE nome = ? AND paziente = ?");
        c.setAutoCommit(false);
        PreparedStatement p = c.prepareStatement(query);
        p.setString(1, listafattoririschio.getSelectionModel().getSelectedItem());
        p.setString(2, codicefiscale.getText());
        p.execute();
        c.commit();
        listafattoririschio.getItems().remove(i);
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
        alert.setContentText("Il paziente non ha ancora inserito\n le rilevazioni giornaliere");
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
    public void inizializzaallarmeTerapia() throws SQLException {
        String query  =("SELECT data FROM pazientiipertesi.rilevazionefarmaco WHERE paziente =? and data between current_date-3 and current_date");
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, codicefiscale.getText());
        ResultSet rs = ps.executeQuery();
        int counter = 3;
        while(rs.next()){
            counter --;
            System.out.println(counter);
        }
        if (counter==3){
            Alert terapiamancante = new Alert(Alert.AlertType.WARNING);
            terapiamancante.setContentText("il Paziente non inserisce la terapia da 3 giorni");
            terapiamancante.show();
        }

    }
public void pressioniSettimanali() throws SQLException {
    String query = ("SELECT * FROM pazientiipertesi.pressione WHERE idpaziente = ? and data between CURRENT_DATE-7 and CURRENT_DATE");
    PreparedStatement pt = c.prepareStatement(query);
    pt.setString(1, codicefiscale.getText());
    ResultSet rs;
    rs= pt.executeQuery();
    while(rs.next()){
        System.out.println(rs.getDate(3));
    }

    }
    @FXML
    public void indietroalmedico(ActionEvent event) throws IOException {
        Controller.Switch("Medico.fxml", event);
    }

    public void initializzaTabPressioni(){
        String query = ("SELECT * FROM pazientiipertesi.pressione where idpaziente = ? and data between CURRENT_DATE-7 and CURRENT_DATE");
        ObservableList<Pressione> pressioni = FXCollections.observableArrayList();
        try{
            PreparedStatement p = c.prepareStatement(query);
            c.setAutoCommit(false);
            p.setString(1, codicefiscale.getText());
            ResultSet rs = p.executeQuery();
            c.commit();
            while(rs.next()){
                Pressione P = new Pressione(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5),rs.getDate(3));
                pressioni.add(P);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        maxcol.setCellValueFactory(new PropertyValueFactory<>("massima"));
        mincol.setCellValueFactory(new PropertyValueFactory<>("minima"));
        datacol.setCellValueFactory(new PropertyValueFactory<>("data"));
        gravitacol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tabpressioni.getItems().setAll(pressioni);

    }

    /*

    */
}

