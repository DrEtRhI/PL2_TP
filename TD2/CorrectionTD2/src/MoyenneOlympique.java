
import java.util.Scanner;


/**
 * Calcule la moyenne olympique d'une suite de notes.
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class MoyenneOlympique {
    
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * methode utilitaire pour la lecture d'une note.
     * On vérifie que la note est bien dans l'intervalle 0..20 ou que
     * c'est la valeur -1 pour terminer la saisie. Dans ce cas on vérifie
     * que trois notes au moins ont déjà été saisies.
     * Si la note est incorrecte, ou si -1 est rentré et qu'il n'y a pas
     * encore au moins trois notes de saisies, on redemande la saisie de la
     * note.
     *
     * @param nbNotesLues. Le nombre de notes déjà lues.
     *
     * @return la note lue.
     */ 
    public static double lireUneNote(int nbNotesLues) {
        double noteLue;
        boolean noteOK= false;
        do {
            noteOK = false;
            System.out.print("Donner un note  entre 0 et 20 (-1 pour arrêter à partir de la 3ème entrée) : ");
            noteLue = sc.nextDouble();
            if (noteLue < -1 || noteLue > 20)
                System.out.println("Valeur incorrecte ! Donnez une note entre 0 et 20 (-1 pour arrêter)");
            else
                if (noteLue == -1 && nbNotesLues < 3)
                    System.out.println("vous devez rentrez au moins trois notes");
                else
                    noteOK = true;
        } while (!noteOK);
        return noteLue;
        
    }
    
    public static void main(String[] args) {
        double total = 0.0;  // le cumul des notes saisies
        int nbNotes = 0;     // le nombre de notes saisies
        double max = Double.MIN_VALUE;  // la note la plus forte
                 // initialisée avec la plus petite valeur codable pour un double
        double min = Double.MAX_VALUE;  // la note la plus faible
                 // initialisée avec la plus grande valeur codable pour un double
        
        double note = lireUneNote(0);
        
        while (note !=-1 ){
            
            total += note;
            if (max <= note)
                max = note;
            if (min >= note)   // on ne met pas un else if
                min = note;    // car la premiere note sera
                               // à la fois min et max
            nbNotes++;
            
            note = lireUneNote(nbNotes);
        }
        
        System.out.println("Nombre de notes saisies " + nbNotes);
        System.out.println("Note la plus basse: " + min);
        System.out.println("Note la plus haute: " + max);
        System.out.println("Moyenne olympique: " + (total - (min + max)) / (nbNotes - 2));
    }
}
