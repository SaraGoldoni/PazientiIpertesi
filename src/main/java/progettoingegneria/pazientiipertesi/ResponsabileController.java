package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ResponsabileController {

    public void NewPaziente(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabilePaziente.fxml",event);
    }
    public void NewMedico(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabileMedico.fxml",event);
    }

    public void InsertPaziente(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml",event);
    }
    public void InsertMedico(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml",event);
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