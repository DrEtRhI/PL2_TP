package ufrima2g.m2pcci.pl2.tpExceptions.reader;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurAvecDessin;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCap;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCercle;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimateur;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.Chenille;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.IDessinable;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Disque;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Etoile;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimee;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeCirculaire;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.PolygoneRegulier;

/**
 * Cette classe permet de lire un fichier texte contenant la description des
 * différents objets à afficher et à animer dans la fenêtre de l'application.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class DessinFileReader {

    /**
     *
     * @param fileName le nom (en fait le chemin) du fichier texte
     * @param dessin le dessin auquel les objets créés à partir des descriptions
     * contenues dans le fichier sont ajoutés
     *
     * @throws FileNotFoundException si fileName ne correspond pas un fichier
     * existant
     * @throws IOException en cas d'erreur de lecture (au niveau système)
     * @throws UnknownObjectException en cas de type d'objet non supporté
     * @throws UnknownFormeException en cas de type de forme non supporté
     * @throws NombreArgumentsIncorrect quand le nombre d'arguments d'une
     * description ne correspond pas au nombre d'arguments attendus.
     * @throws NombreArgumentsAnimationIncorrect en cas d'erreur pour les arguments de l'animation
     * @throws UnknownAnimateurException en cas d'utilisation d'un animateur inconnu
     */
    public static void chargerDessinables(String fileName, Dessin dessin)
            throws FileNotFoundException, IOException, UnknownObjectException, UnknownFormeException,
            NombreArgumentsIncorrect, NombreArgumentsAnimationIncorrect, UnknownAnimateurException {
        boolean fileOK = false;
        boolean argsOK = false;
        boolean argsAnimationOK = false;
        while (!fileOK || !argsOK) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                // try avec ressources, qui permet de fermer automatiquement le reader
                fileOK = !fileOK;

                //String ligne; // chaîne contenant la ligne courante.
                int noLigne = 0;    // numéro de la ligne en cours d'analyse.
                IForme forme; // la dernière forme créée
                IAnimateur animator = null; // le dernier animateur lu
                String ligne = reader.readLine();
                while (ligne != null) {

                    noLigne++;
                    System.out.println(ligne);

                    if (!"".equals(ligne) && !ligne.startsWith("#")) {

                            // ligne non vide et non commentaire
                        // recupération dans un tableau de chaînes des différents élements de la ligne
                        String[] tokens = ligne.toUpperCase().split(" ");

                        switch (tokens[0]) {
                            case "F":

                                try {
                                    forme = lireForme(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length));
                                    argsOK = true;
                                    System.out.println("--> forme créée");
                                    if (animator != null) {
                                        dessin.ajouterObjet(new FormeAnimee(forme, animator));
                                        animator = null;
                                        System.out.println("ok");
                                    } else {
                                        dessin.ajouterObjet(forme);
                                    }
                                } catch (NombreArgumentsIncorrect | NumberFormatException | UnknownFormeException e) {
                                    ligne = "#" + ligne;
                                }
                                break;

                            case "A":
                                try{
                                System.out.println("Animateur");
                                animator = lireAnimator(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length), dessin);
                                argsAnimationOK = true;
                                }catch(UnknownAnimateurException|NombreArgumentsAnimationIncorrect|NumberFormatException e){
                                    ligne = "#" + ligne;
                                }
                                break;
                            case "C":
                                System.out.println("Chenille");
                                dessin.ajouterObjet(lireChenille(Arrays.copyOfRange(tokens, 1, tokens.length), dessin));
                                break;
                            default:
                                throw new UnknownObjectException(tokens[0]);
                        }

                    } // fin du if (! ligne.equals(""))

                    // on passe à la ligne suivante
                    ligne = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                Scanner sc = new Scanner(System.in);
                e.getMessage();
                System.out.println("Entrez un nouveau nom de fichier : ");
                fileName = sc.next();
            }
        }
    }

    /**
     *
     * @param typeForme le type de la forme
     * @param paramsForme le tableau des paramètres de la forme
     *
     * @return la référence d'un objet forme correspondant à la description
     *
     * @throws UnknownFormeException si le type de forme n'est pas reconnu.
     *
     * @throws NombreArgumentsIncorrect si le nombre de paramètres de
     * description n'est pas le nombre attendu.
     */
    private static IForme lireForme(String typeForme, String[] paramsForme) throws
            UnknownFormeException, NombreArgumentsIncorrect {
        switch (typeForme) {
            case "POLYR":
                return lirePolyRegulier(paramsForme);
            case "ETOILE":
                return lireEtoileOuDisque(paramsForme, true);
            case "DISQUE":
                return lireEtoileOuDisque(paramsForme, false);
            default:
                throw new UnknownFormeException(typeForme);
        }
    }

    /**
     *
     * @param params les paramètres permettant de décrire un polygone régulier
     *
     * @return la référence d'un objet PolygoneRegulier correspondant à ces
     * paramètres
     *
     * @throws NombreArgumentsIncorrect si le nombre de paramètres de
     * description n'est pas le nombre attendu (ici 7).
     */
    private static PolygoneRegulier lirePolyRegulier(String[] params) throws NombreArgumentsIncorrect {

        if (params.length != 7) {
            throw new NombreArgumentsIncorrect("POLYR", params.length, 7);
        }
        int x = Integer.parseInt(params[0]);
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        int n = Integer.parseInt(params[3]);
        Color c = new Color(Integer.parseInt(params[4]), Integer.parseInt(params[5]),
                Integer.parseInt(params[6]));
        return new PolygoneRegulier(n, x, y, r, 1.0f, c, c);
        
    }
    
    /**
     * 
     * @param params paramètres de la forme
     * @param choixForme si true on aura une étoile, si false on aura un disque
     * @return la forme voulu en fonction du booléen
     * @throws NombreArgumentsIncorrect 
     */
    private static FormeCirculaire lireEtoileOuDisque(String[] params, boolean choixForme) throws NombreArgumentsIncorrect {
        FormeCirculaire forme;
        if (params.length != 6) {
            if (choixForme){
            throw new NombreArgumentsIncorrect("ETOILE", params.length, 6);
            }else{
                throw new NombreArgumentsIncorrect("DISQUE", params.length, 6);
            }
        }
        int x = Integer.parseInt(params[0]);
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        Color c = new Color(Integer.parseInt(params[3]), Integer.parseInt(params[4]),
                Integer.parseInt(params[5]));
        if (choixForme){
            forme = new Etoile(x, y, r, 1.0f, c, c);
        }else{
            forme = new Disque(x, y, r, 1.0f, c, c);
        }
        return forme;
    }

    private static IAnimateur lireAnimator(String typeAnimateur, String[] tokens, Dessin d) throws NombreArgumentsAnimationIncorrect, UnknownAnimateurException{
        switch (typeAnimateur){
            case "CAPA":
                return lireAnimationCap(tokens, d);
            case "CERCLEA":
                return lireAnimationCercle(tokens);
            default : throw new UnknownAnimateurException(typeAnimateur); 
        }
    }
    
    private static AnimateurAvecDessin lireAnimationCap(String[] paramsAnimation, Dessin d) throws NombreArgumentsAnimationIncorrect{
        if (paramsAnimation.length != 2){
            throw new NombreArgumentsAnimationIncorrect("CAPA", paramsAnimation.length, 2);
        }
        double cap =  Double.parseDouble(paramsAnimation[0]);
        int deltaAngle = Integer.parseInt(paramsAnimation[1]);
        return new AnimateurCap(cap, deltaAngle, d);
    }
    
    private static IAnimateur lireAnimationCercle(String[] paramsAnimation) throws NombreArgumentsAnimationIncorrect{
        if (paramsAnimation.length != 5){
            throw new NombreArgumentsAnimationIncorrect("CERCLEA", paramsAnimation.length, 5);
        }
        int r = Integer.parseInt(paramsAnimation[0]);
        int x = Integer.parseInt(paramsAnimation[1]);
        int y = Integer.parseInt(paramsAnimation[2]);
        int angleDep = Integer.parseInt(paramsAnimation[3]);
        int deltaAngle = Integer.parseInt(paramsAnimation[4]);
        
        return new AnimateurCercle(r, x, y, angleDep, deltaAngle);            
    }


    private static IDessinable lireChenille(String[] params, Dessin d) throws NombreArgumentsIncorrect{
        if (params.length != 2){
            throw new NombreArgumentsIncorrect("CHENILLE", params.length, 2);
        }
        int r = Integer.parseInt(params[0]);
        int nbAnneaux = Integer.parseInt(params[1]);
        
        return new Chenille(d, r, nbAnneaux);
        
    }

}
