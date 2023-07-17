package progettoingegneria.pazientiipertesi;

import java.sql.Time;
import java.util.Date;

public class RilevazioneFarmaco {
String nomeFarmaco, paziente;
int quantita, numeroAssunzioni;
Date data;
Time ora;

    public RilevazioneFarmaco(String nomeFarmaco, String paziente, int quantita, int numeroAssunzioni, Date data, Time ora) {
        this.nomeFarmaco = nomeFarmaco;
        this.paziente = paziente;
        this.quantita = quantita;
        this.numeroAssunzioni = numeroAssunzioni;
        this.data = data;
        this.ora = ora;
    }

    public String getNomeFarmaco() {
        return nomeFarmaco;
    }

    public String getPaziente() {
        return paziente;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getNumeroAssunzioni() {
        return numeroAssunzioni;
    }

    public Date getData() {
        return data;
    }

    public Time getOra() {
        return ora;
    }

    public void setNomeFarmaco(String nomeFarmaco) {
        this.nomeFarmaco = nomeFarmaco;
    }

    public void setPaziente(String paziente) {
        this.paziente = paziente;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setNumeroAssunzioni(int numeroAssunzioni) {
        this.numeroAssunzioni = numeroAssunzioni;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }
}
