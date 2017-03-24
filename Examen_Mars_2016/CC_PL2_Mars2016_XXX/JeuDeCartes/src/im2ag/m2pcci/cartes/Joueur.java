package im2ag.m2pcci.cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente un joueur avec les cartes (la main) qu'il possède.
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Joueur {

    /**
     * le nom du joueur
     */
    private final String nom;
    
    /**
     * la main (c'est à dire les cartes) que possède le joueur
     */
    private final List<Carte> main = new ArrayList<>();

    /**
     * Création d'un joueur, avec une main initiale vide
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    
    /**
     * Méthode renvoyant le nom du joueur
     * @return nom joueur
     */
    public String getNom() {
        return nom;
    }
    
    

    /**
     * le joeur prend une carte et l'ajoute à sa main (sa liste de cartes)
     * @param carte 
     */
    public void prendre(Carte carte) {
        main.add(carte);
    }


    /**
     * le joueur pose (retire) l'une des cartes de sonjeu
     * @param index le rang (position dans la main) de la carte à retirer
     * @return la carte retiré
     */
    public Carte poserCarte(int index) {
        return main.remove(index);
    }

    /**
     * @return le nombre de cartes que possède le joueur
     */
    public int nbCartes() {
        return main.size();
    }
    
    /**
     * trie les cartes du joueur
     */
    public void trierCartes() {
        Collections.sort(main);
    }
    
    /**
     * renvoie une chaine de caractères donnant le jeu du joueur
     * et la suite de cartes qu'il a en main (qu'il possède).
     * @return la chaîne avec le nom du joueur et ses cartes
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n");
        for (Carte c : main) {
            res.append(c.toString()).append(",");
        }
        res.deleteCharAt(res.length()-1);
        res.append("\n");
        return res.toString();
    }

}
