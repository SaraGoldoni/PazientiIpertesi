package progettoingegneria.pazientiipertesi;

import java.util.Date;

public class Pressione {
    private int Massima, Minima;
    private String idPaziente;
    private String tipo;
    private Date data;

    public Pressione(int massima, int minima, String idPaziente, String tipo, Date data) {
        Massima = massima;
        Minima = minima;
        this.idPaziente = idPaziente;
        this.tipo = tipo;
        this.data = data;
    }

    public int getMassima() {
        return Massima;
    }

    public int getMinima() {
        return Minima;
    }

    public String getIdPaziente() {
        return idPaziente;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public void setMassima(int massima) {
        Massima = massima;
    }

    public void setMinima(int minima) {
        Minima = minima;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
