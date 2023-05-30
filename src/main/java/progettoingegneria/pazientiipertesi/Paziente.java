package progettoingegneria.pazientiipertesi;

public class Paziente {

        private String id;
        private String nome;
        private String cognome;


        public Paziente(String id, String nome, String cognome){
            this.id = id;
            this.nome = nome;
            this.cognome = cognome;
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

        public void setId (String id){
            this.id = id;
        }

        public void setNome (String nome){
            this.nome = nome;
        }

        public void setCognome (String cognome){
            this.cognome = cognome;
        }
    }

