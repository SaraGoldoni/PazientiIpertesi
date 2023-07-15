package progettoingegneria.pazientiipertesi;

import java.util.Date;

public class Terapie {
    private String farmaco, medico;
    private int assunzioni, quantita;
    private Date data_inizio, data_fine;

    public Terapie(String farmaco, String medico, int assunzioni, int quantita, Date data_inizio, Date data_fine) {
        this.farmaco = farmaco;
        this.medico = medico;
        this.assunzioni = assunzioni;
        this.quantita = quantita;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public String getMedico() {
        return medico;
    }

    public int getAssunzioni() {
        return assunzioni;
    }

    public int getQuantita() {
        return quantita;
    }

    public Date getData_inizio() {
        return data_inizio;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setAssunzioni(int assunzioni) {
        this.assunzioni = assunzioni;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }
}
