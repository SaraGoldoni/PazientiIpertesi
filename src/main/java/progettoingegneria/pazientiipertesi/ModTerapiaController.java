package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModTerapiaController implements Initializable {
    @FXML
    public ChoiceBox<String> selterapia = new ChoiceBox<>();
    public TextField Assunzioni;
    public TextField Quantita;
    public TextArea Indicazioni;
    public DatePicker d_inizio;
    public DatePicker d_fine;
    @FXML
    private Label assgiornaliere, quantita, data_i, data_f, indicazioni, cfpaz;
    DatabaseConnection conn = new DatabaseConnection();
    Connection c = conn.link();
    public void displayCF (String codiceFiscale){
        cfpaz.setText(codiceFiscale);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String query = ("SELECT farmaco FROM pazientiipertesi.Terapia WHERE paziente = ?");
        try(PreparedStatement pst = c.prepareStatement(query)){
            //System.out.println(cfpaz.getText());
            pst.setString(1, cfpaz.getText());
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String farmaco= rs.getString(1);
                selterapia.getItems().add(farmaco);
                selterapia.setOnAction(this::getFarmaco);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String getFarmaco(ActionEvent event){
        return selterapia.getValue();
    }
    public void Visibile(){
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

    }

}

