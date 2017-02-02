package im2ag.m2pcci.projet.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pour lire une lsite d'étudiants dans un fichier CSV.
 * @author Gaston L.
 */
public class EtudiantsReader {

    public static List<Etudiant> readCSVFile(String fileName) throws IOException, FileNotFoundException {
        List<Etudiant> listeEtudiants = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // try avec ressources, qui permet de fermer automatiquement le reader
            String ligne; // chaîne contenant la ligne courante.
            ligne = reader.readLine();
            while (ligne != null) {
                ligne = ligne.trim(); // enlève les espaces en début et fin de ligne
                if (!"".equals(ligne) && !ligne.startsWith("#")) {
                    // ligne non vide et non commentaire
                    // recupération dans un tableau de chaînes des différents élements de la ligne
                    String[] tokens = ligne.split(","); // la chaine est découpée en éléments
                    // séparés par une virgule
                    listeEtudiants.add(new Etudiant(tokens[0],tokens[1],Integer.parseInt(tokens[2])));
                }
                ligne = reader.readLine();  // passe à la ligen suivante
            }
        }
        return listeEtudiants;
    }
}
