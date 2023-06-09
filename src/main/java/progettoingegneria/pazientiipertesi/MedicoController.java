package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MedicoController {

    public void InserisciTerapia(ActionEvent event) throws IOException {
        Controller.Switch("InsTerapia.fxml", event);
    }
    public void ModificaTerapia(ActionEvent event) throws IOException {
        Controller.Switch("ModTerapia.fxml", event);
    }
    public void VisualizzaDati(ActionEvent event) throws IOException {
        Controller.Switch("VisDati.fxml", event);
    }
}
