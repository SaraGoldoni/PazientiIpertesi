package progettoingegneria.pazientiipertesi;

import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class Paziente {
    private String cf;

    public Paziente(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
    /*
private String nome, cognome,  Referente;
private Date datanascita;

    public Paziente( String nome, String cognome, Date datanascita,  String Referente) {
        this.nome = nome;
        this.cognome = cognome;
        this.Referente = Referente;
        this.datanascita = datanascita;
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



    public Date getdatanascita() {
        return datanascita;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public void setCognome(String cognome) {
        this.cognome= cognome;
    }



    public void setReferente(String Referente) {
        this.Referente=Referente;
    }

    public void setdatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }*/
}
