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

}
