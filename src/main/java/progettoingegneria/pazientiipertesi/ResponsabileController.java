package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResponsabileController {
    public void NewPaziente(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabilePaziente.fxml",event);
    }
    public void NewMedico(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabileMedico.fxml", event);
    }

}

/* @FXML
    private ChoiceBox<String> utente;
    public void initialize(URL arg0, ResourceBundle arg1){
        utente.getItems().addAll("Medico","Paziente");
        utente.setOnAction(this::getUtente);
    }

    public String getUtente(ActionEvent event){
        return utente.getValue();
    }
    public void InsertUtente(ActionEvent event){
        initialize(null, null);
        String user = getUtente(event);
        if (user.equals("Medico")){
            System.out.println("inserisci nella tabella medico i dati");
        }
    }*/