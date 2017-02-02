package im2ag.m2pcci.projet.groupes;

import im2ag.m2pcci.projet.util.Etudiant;
import im2ag.m2pcci.projet.util.EtudiantsReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Gaston L.
 */
public class RepartionEnGroupes {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args)  {
        // définition des groupes de niveau
        System.out.print("nombre de groupes de niveau: ");
        int nbGroupes = sc.nextInt();
        Groupe[] groupes = new Groupe[nbGroupes];
        int borneInf = 0;
        int borneSup = 0;
        for (int i = 0; i < nbGroupes - 1; i++) {
            while (borneSup <= borneInf) {
                System.out.print("borne supérieure pour groupe " + (i + 1) + " : ");
                borneSup = sc.nextInt();
            }
            groupes[i] = new Groupe(borneInf, borneSup);
            borneInf = borneSup;
        }
        groupes[nbGroupes - 1] = new Groupe(borneInf, 21);

        // lire la liste des étudiants dans le fichier CSV
        List<Etudiant> etudiants = null;

        while (etudiants == null) {
            try {
                System.out.print("nom du fichier de données (CVS): ");
                String nomFichier = sc.next();
                etudiants = EtudiantsReader.readCSVFile(nomFichier);
            } catch (FileNotFoundException e) {
                System.out.println("fichier inconnu !! recommencez");
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

        // repartir les étudiants dans les groupes selon leur note
        for (Etudiant e : etudiants) {
            int noGroupe = 0;
            while (!groupes[noGroupe].accepte(e)) {
                noGroupe++;
            }
            groupes[noGroupe].ajouter(e);
        }

        // afficher les groupes
        for (Groupe grp : groupes){
            afficherGroupe(grp);
        }

    }

    /**
     * affiche un groupe sur la sortie standard.
     * @param g le groupe à afficher.
     */
    private static void afficherGroupe(Groupe g) {
        System.out.println("---------------------------------------");
        System.out.print(" Groupe " + g.noGroupe + " [" + g.borneInf + " ");
        if (g.borneSup < 20) {
            System.out.print(g.borneSup + "[");
        } else {
            System.out.print("20]");
        }
        System.out.println("  effectif: " + g.getEffectif());
        System.out.println("---------------------------------------");
        for (Etudiant e : g) {
            System.out.println(e.getNom() + " " + e.getPrenom() + " " + e.getNote());
        }
        System.out.println();
    }

}
