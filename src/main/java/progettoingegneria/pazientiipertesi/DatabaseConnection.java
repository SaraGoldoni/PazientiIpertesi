package progettoingegneria.pazientiipertesi;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /**
     * Dichiarazione delle variabili
     */
    private String username;
    private String passwd;
    // Gestione degli errori ed eccezioni della connessione con il DB

    /**
     * Metodo che permette di gestire le eccezioni di SQL
     */
    private void manageSQLExeption(Exception e){
        e.printStackTrace();
        System.err.println(e.getClass().getName()+":"+e.getMessage());
        System.exit(0);
    }
    public void Connection(String username, String passwd){
        this.username = username;
        this.passwd = passwd;
    }

    /**
     *  Metodo link per effettuare la connessione tra il software e il database su postgreSQL
     */
    public java.sql.Connection link() {
        java.sql.Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            // primo campo -> link del db
            // secondo campo -> username
            // terzo campo -> password
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/PazientiIpertesi", "postgres", "progetto23");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connessione al database fallita!");
            manageSQLExeption(e);
            //   return c;
            //  throw new RuntimeException(e);
        }
        return c;
    }

    //creare metodo per l'esecuzione di query nel database

}