package progettoingegneria.pazientiipertesi;
import java.util.Date;
public class Medico {
    final String id, nome, cognome, codiceFiscale, email;
    final Date datanascita;

   /**
   * Costruttore per l'oggetto medico.
   **/
    public Medico(String id, String codiceFiscale, String nome, String cognome, Date data_n, String email ){


        this.id=id;
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita= data_n;
        this.email= email;

    }
    public String getId (){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome (){
        return cognome;
    }

    public String getEmail(){
        return email;
    }

}
