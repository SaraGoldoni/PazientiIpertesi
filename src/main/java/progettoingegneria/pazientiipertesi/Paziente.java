package progettoingegneria.pazientiipertesi;

import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class Paziente {
private SimpleStringProperty nome, cognome, CodiceFiscale, Referente;
private Date datanascita;

    public Paziente( String CodiceFiscale, String nome, String cognome, Date datanascita,  String Referente) {

        this.CodiceFiscale = new SimpleStringProperty(CodiceFiscale);
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.Referente = new SimpleStringProperty(Referente);
        this.datanascita = datanascita;
    }

    public String getNome() {
        return nome.get();
    }

    public String getCognome() {
        return cognome.get();
    }

    public String getReferente() {
        return Referente.get();
    }

    public String getCodiceFiscale() {
        return CodiceFiscale.get();
    }

    public Date getdatanascita() {
        return datanascita;
    }

    public void setNome(String nomepaz) {
        nome.set(nomepaz);
    }

    public void setCognome(String cognomepaz) {
        cognome.set(cognomepaz);
    }

    public void setCodiceFiscale(String CodiceFiscalepaz) {
        CodiceFiscale.set(CodiceFiscalepaz);
    }

    public void setReferente(String Referentepaz) {
        Referente.set(Referentepaz);
    }

    public void setdatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }
}
