package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsTerapiaController {
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
    DatabaseConnection c = new DatabaseConnection();
    Connection conn = c.link();
    @FXML
    private Label codiceFisc;
    public void displayCF (String codiceFiscale){
        codiceFisc.setText(codiceFiscale);
    }
    public void InserisciTerapia(ActionEvent event) throws SQLException {
        System.out.println(codiceFisc.toString().length());
        String query = ("INSERT INTO pazientiipertesi.Terapia(Farmaco, Paziente, Medico, assunzioni, quantità, indicazioni, data_inizio, data_fine) VALUES (?, ?, ?, ?, ?, ? , ?, ?)");
        try(PreparedStatement prst = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            String F,P, M, I, data_I, data_F;
            int A, Q;
            F = Farmaco.getText();
            P= paz.getText();
            //MedicoController P = new MedicoController();
            //Paziente p = P.getcodicefiscalepaz();
           // String CFpaziente= p.getCf();
            //paz.setText(p);


            //P = paz.getText();
            //String Z = MedicoController.getcodicefiscalepaz();
            //System.out.println(Z);
            M = Controller.getCFMedico();
            A = Integer.parseInt(Assunzioni.getText());
            Q = Integer.parseInt(Quantita.getText());
            I = Indicazioni.getText();
            data_I = d_inizio.getValue().toString();
            data_F = d_fine.getValue().toString();

            prst.setString(1, F);
            prst.setString(2, codiceFisc.getText());
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


}
