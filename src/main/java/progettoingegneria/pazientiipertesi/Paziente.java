package progettoingegneria.pazientiipertesi;

import java.sql.*;

public class Paziente {
private String  nome, cognome, codiceFiscalePaz, Referente;
private Date data_nascita;

    public Paziente( String codiceFiscalePaz, String nome, String cognome, Date data_nascita,  String Referente) {

        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscalePaz = codiceFiscalePaz;
        this.Referente = Referente;
        this.data_nascita = data_nascita;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getReferente() {
        return Referente;
    }

    public String getCodiceFiscalePaz() {
        return codiceFiscalePaz;
    }

    public Date getData_nascita() {
        return data_nascita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodiceFiscalePaz(String codiceFiscalePaz) {
        this.codiceFiscalePaz = codiceFiscalePaz;
    }

    public void setReferente(String Referente) {
        this.Referente = Referente;
    }

    public void setData_nascita(Date data_nascita) {
        this.data_nascita = data_nascita;
    }
}
       /*final String id;
        final String Codice;

        final String nome;
        final String cognome;
        final Date data_n;
        final String Referente;


        public Paziente() throws SQLException {
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
            Date data_n = rs.getDate(4);
            String Referente = rs.getString(5);

            this.id=Username;
            this.Codice = Codice;
            this.nome = nome;
            this.cognome = cognome;
            this.data_n= data_n;
            this.Referente= Referente;

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

        public String getReferente(){
            return Referente;
        }



    }

*/