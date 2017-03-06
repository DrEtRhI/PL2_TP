
import java.sql.Time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thierrye
 */
public class Test {
    
    public static void main(String[] args) {
        Time horaire = new Time(20,0,0);
        int minutes;
        int heures;

        minutes = horaire.getMinutes();
        if (minutes == -1){
            minutes = 59;
        }
        heures = horaire.getHours();
        
        System.out.println(heures + ":" + minutes);
        minutes = horaire.getMinutes() - 1;
        System.out.println(heures + ":" + minutes);
    }
    
}
