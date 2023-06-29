package progettoingegneria.pazientiipertesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Medico {
    final String id, nome, cognome, codiceFiscale, email;
    final Date datanascita;

   /**
   * Costruttore per l'oggetto medico. L'oggetto medico viene istanziato con i valori presenti nella corrispondente tabella sql, che fanno riferimento al
    * codice fiscale dell'utente che ha appena effettuato il login.
   * */
    public Medico() throws SQLException {
        String query = ("SELECT * FROM pazientiipertesi.paziente WHERE codicefiscale = ?");
        //connessione al db
        DatabaseConnection conn = new DatabaseConnection();
        Connection c = conn.link();
        String CF = Controller.getCFPaziente(); //recupero il codice fiscale del paziente loggato
        String Username = Controller.getNomeUtente(); //recupero il nome utente del paziente loggato

        PreparedStatement st = c.prepareStatement(query);
        st.setString(1, CF);
        ResultSet rs;
        rs= st.executeQuery();
        rs.next();
        String Codice = rs.getString(1);
        String nome = rs.getString(2);
        String cognome = rs.getString(3);
        java.sql.Date data_n = rs.getDate(4);
        String email = rs.getString(5);

        this.id=Username;
        this.codiceFiscale = Codice;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita= data_n;
        this.email= email;

    }
    public String getId (){
        return id;
    }

    public String getNome (){
        return nome;
    }

    public String getCognome (){
        return cognome;
    }

    public String getEmail(){
        return email;
    }

}
