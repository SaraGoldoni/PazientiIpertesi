package progettoingegneria.pazientiipertesi;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ResponsabileController {
    public void NewPaziente(ActionEvent event) throws IOException {
        Controller.Switch("ResponsabilePaziente.fxml",event);
    }
    public void NewMedico(ActionEvent event) throws IOException {
        Controller.Switch("progettoingegneria/pazientiipertesi/ResponsabileMedico.fxml", event);
    }
<<<<<<< Updated upstream
    public void InsertPaziente(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml",event);
    }
    public void InsertMedico(ActionEvent event) throws IOException {
        Controller.Switch("Responsabile.fxml",event);
=======

    public void InsertPaziente(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String query = ("INSERT INTO \"Dati\".Paziente(codicefiscale, nome, cognome, data_nascita, referente) VALUES (?, ?, ?, ?, ?)");
        String query2 = ("INSERT INTO \"Dati\".Login(nomeutente, password, tipo, paziente, medico) VALUES (?, ?, ?, ?, ?)");
        PreparedStatement ps = c.prepareStatement(query);
        PreparedStatement ps2 = c.prepareStatement(query2);
        c.setAutoCommit(false);
        String CF, nome, cognome, medico_referente, data_nascita, nome_utente,pass;
        CF= cf_paz.getText();
        nome = nome_paz.getText();
        cognome = cognome_paz.getText();
        data_nascita = data_nascita_paz.getValue().toString();
        medico_referente= medico_ref.getText();
        nome_utente=username.getText();
        pass= password.getText();

        ps.setString(1, CF);
        ps.setString(2, nome);
        ps.setString(3, cognome);
        ps.setDate(4, Date.valueOf(data_nascita));
        ps.setString(5,medico_referente);

        ps2.setString(1, nome_utente);
        ps2.setString(2, pass);
        ps2.setInt(3, 0);
        ps2.setString(4, CF);
        ps2.setNull(5, Types.NULL);

        ps.executeUpdate();
        ps2.executeUpdate();
        c.commit();

        Controller.Switch("ResponsabilePaziente.fxml",event);
    }
    public void InsertMedico(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String query1 = ("INSERT INTO \"Dati\".Medico(codicefiscale, nome, cognome, data_nascita, email) VALUES (?, ?, ?, ?, ?)");
        String query3= ("INSERT INTO \"Dati\".Login(nomeutente, password, tipo, paziente, medico) VALUES (?, ?, ?, ?, ?)");
        String CF_med, N, C, D_N, email, username_med, pass_medico;
        CF_med=cf_medico.getText();
        N= nome_med.getText();
        C = cognome_med.getText();
        D_N= data_nascita_med.getValue().toString();
        email= mail.getText();
        username_med= nome_utente_med.getText();
        pass_medico= pass_med.getText();
        PreparedStatement ps1= c.prepareStatement(query1);
        PreparedStatement ps3= c.prepareStatement(query3);
        c.setAutoCommit(false);
        ps1.setString(1, CF_med);
        ps1.setString(2, N);
        ps1.setString(3, C);
        ps1.setDate(4, Date.valueOf(D_N));
        ps1.setString(5,email);

        ps3.setString(1,username_med);
        ps3.setString(2,pass_medico);
        ps3.setInt(3, 1);
        ps3.setNull(4, Types.NULL);
        ps3.setString(5, CF_med);

        ps1.executeUpdate();
        ps3.executeUpdate();
        c.commit();
        Controller.Switch("ResponsabileMedico.fxml",event);
>>>>>>> Stashed changes
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