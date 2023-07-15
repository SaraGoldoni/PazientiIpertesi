package progettoingegneria.pazientiipertesi;

import java.util.Date;

public class TerapiePregConc {
    private String nomefarmaco, tipo, informazioni, paziente;
    private Date data_inizio, data_fine;

    public TerapiePregConc(String nomefarmaco, String tipo, String informazioni, String paziente, Date data_inizio, Date data_fine) {
        this.nomefarmaco = nomefarmaco;
        this.tipo = tipo;
        this.informazioni = informazioni;
        this.paziente = paziente;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }

    public String getNomefarmaco() {
        return nomefarmaco;
    }

    public void setNomefarmaco(String nomefarmaco) {
        this.nomefarmaco = nomefarmaco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInformazioni() {
        return informazioni;
    }

    public void setInformazioni(String informazioni) {
        this.informazioni = informazioni;
    }

    public String getPaziente() {
        return paziente;
    }

    public void setPaziente(String paziente) {
        this.paziente = paziente;
    }

    public Date getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }
}
