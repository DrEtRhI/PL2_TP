package im2ag.m2pcci.cartes;

import im2ag.m2pcci.util.Pile;

/**
 * Représente un jeu de cartes, constitué d'une pile de 52 cartes que l'on peut
 * distribuer une par une en les retirant du sommet de la pile, ou que l'on peut
 * tirer au hasard dans la pile.
 * 
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
public class JeuDeCartes {

    /**
     * nombre de cartes par couleur
     */
    public static final int NB_CARTES_COULEUR = 13;
    
    /**
     * la pile des cartes du jeu
     */
    private final Pile<Carte> pileDesCartes = new Pile<>();
    
    /**
     * création du jeu de cartes
     */
    public JeuDeCartes() {
        for (Couleur c : Couleur.values()) {
            for (int i = 0; i < NB_CARTES_COULEUR; i++) {
                pileDesCartes.ajouter(new Carte(i + 2,c));
            }
        }
    }
    
    /**
     * melange les cartes du jeu
     */
    public void melanger() {
        pileDesCartes.melanger();
    }

    /**
     * retire du jeu la carte située en sommet de pile.
     * @return la carte située en sommet de pile, null si la pile est vide
     */
    public Carte distribuerCarte() {
        return pileDesCartes.depiler();
    }
    
    /**
     * nombre de cartes restant dans la pile.
     * @return le nombre de cartes restant.
     */
    public int nbCartes() {
        return pileDesCartes.size();
    }
    
    /**
     * tire une une carte au hasard et la retire la pile.
     * 
     * @return la carte tirée, null si la pile est vide
     */
    public Carte tirerCarte() {
        int nbreCartes = nbCartes();
        if ( nbreCartes > 0) {
            int index =  Math.round((float)Math.random() * nbreCartes);
            return pileDesCartes.retirer(index);
        }
        return null;
    }

}
