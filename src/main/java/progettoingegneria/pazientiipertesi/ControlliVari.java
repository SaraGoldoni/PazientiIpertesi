package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlliVari {
    String valore ;

    public String controlloPressione(int max, int min)  {

        if(max >= 140 && max<=149 || min>=90 && min<=94){
            valore = "BORDERLINE";

        } else if (max>=150 && max<=159 || min>=95 && min<=99) {
            valore="LIEVE";

        } else if (max>=160 && max<=179 || min>100 && min<109) {
            valore="MODERATA";

        } else if (max>=180 && max <250 || min >= 110 && min<150) {
            valore = "GRAVE";

        }else if(max<=120 && min<=80){
            valore = "OTTIMALE";
        } else if (max<130 && max>120 || min<=85 && min>80) {
            valore = "NORMALE";

        } else if (max>=250|| min >=150) {
            Alert errore = new Alert(Alert.AlertType.ERROR);
            valore="ERRORE";
            errore.setContentText("ERRORE nell'inserimento dei\n valori della pressione");
            errore.show();
            
        }
        return valore;
    }
    public void indietro(String s ,ActionEvent event) throws IOException {
        Controller.Switch(s, event);
    }

}
