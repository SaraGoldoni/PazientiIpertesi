package progettoingegneria.pazientiipertesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlliVari {
    String valore ;

    public String controlloPressione(int max, int min)  {

        if(max >= 140 && max<=149 || min>=90 && min<=94){
            valore = "BORDERLINE";

        } else if (max>=150 && max<=159 || min>=95 && min<=99) {
            valore="LIEVE";

        } else if (max>=160 && max<=179 || min>100 && min<109) {
            valore="MODERATA";

        } else if (max>=180 && min >= 110) {
            valore = "GRAVE";

        }
        return valore;
    }

}
