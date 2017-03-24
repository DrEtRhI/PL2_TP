package jem.appli;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import jem.model.Cavalier;
import jem.model.Equide;
import jem.model.GestionnaireEpreuve;
import jem.model.Juge;
import jem.model.Resultat;

/**
 * Application simple permettant de gérer une compétition de dressage.
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class DressageApplication {

    ... A COMPLETER ... Scanner sc = new Scanner(System.in);

    /**
     * crée une liste de cavaliers avec leur monture. Les cavaliers de cette
     * liste sont : Prénom1 NOM1 pour Cheval1 Prénom2 NOM2 pour Cheval2 ...
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private static List<Cavalier> creerListeCavaliers(int nbCavaliers) {
        List<Cavalier> res = new ArrayList<>();
        for (int i = 1; i <= nbCavaliers; i++) {
            res.add(new Cavalier("CAVALIER" + i, "Prénom" + i, "FR", new Equide("Cheval" + i, 10 + i)));
        }
        return res;
    }

    /**
     * crée une liste de Juge. Les juges de cette liste sont :<br>
     * <code>
     * ------------------------------------<br>
     * | NO| NOM | PRENOM| PAYS| PASSWORD |<br>
     * ------------------------------------<br>
     * | 1 |JUGE1| Juju1 | USA | password1|<br>
     * ------------------------------------<br>
     * | 2 |JUGE2| Juju2 | USA | password2|<br>
     * ------------------------------------<br>
     * | 3 |JUGE1| Juju3 | ... | ... |<br>
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private static List<Juge> creerListeJuges(int nbJuges) {
        List<Juge> res = new ArrayList<>();
        for (int i = 1; i <= nbJuges; i++) {
            res.add(new Juge(i, "JUGE" + i, "Juju" + i, "USA", "password" + i));
        }
        return res;
    }

    /**
     * lit au clavier la note (un nombre entre 0 et 100) pour un juge donné.
     *
     * @param noJuge le numéro du juge pour lequel la note est lue
     * @return la note lue.
     */
    static double lireNote(Juge juge) {
        double note = -1;
        do {
            try {
                System.out.print("Juge " + juge + " note : ");
                // lire la note au clavier
                note = ... A COMPLETER ...;
                // verifier que la note est valide
                if (... A COMPLETER ...) {
                    System.out.println("note incorrecte, recommencez");
                }
            } catch (InputMismatchException ex) {
                System.out.println("entrez un nombre rééel !");
                sc.next();
            }
        } while (... A COMPLETER ...);
        return note;
    }

    /*
     * le programme principal
     */
    ... A COMPLETER ... {
        // créer un gestionnaire d'épreuve avec 3 juges pour une liste de 3 cavaliers
        GestionnaireEpreuve ge = new GestionnaireEpreuve(creerListeJuges(3), creerListeCavaliers(3));
        while (... A COMPLETER ...) {
            Cavalier cavalierEnPiste = ge.cavalierSuivant();
            System.out.println("----------------------------------------------------------");
            System.out.print("Cavalier en piste: ");
            System.out.println(cavalierEnPiste);

            for (int noJuge = 1; ... A COMPLETER ... ; noJuge++) {
                double note = lireNote(ge.getJuge(noJuge));
                ge.enregistrerNote(noJuge, note);
            }
            System.out.printf("note obtenue %5.2f\n", ... A COMPLETER ...);
            System.out.println("position au classement provisoire: " + ge.getClassementCavalierEnPiste());
        }
        System.out.println("\nL'épreuve est terminée");
        System.out.println("\nClassement final");
        Resultat[] classement = ge.getClassement();
        // afficher le classement final
        ... A COMPLETER ...
    }

}
