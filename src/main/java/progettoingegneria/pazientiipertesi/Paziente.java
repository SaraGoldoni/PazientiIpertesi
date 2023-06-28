package progettoingegneria.pazientiipertesi;

import java.sql.*;

public class Paziente {

        private String id;
        private String Codice;

        private String nome;
        private String cognome;
        private Date data_n;
        private String Referente;


/**
 * Costruttore dell'oggetto Paziente con i dati contenuti nella riga della tabella del database
 */
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

