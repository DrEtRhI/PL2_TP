package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.Graphics;

/**
 * @author Philippe GENOUD (UGA - LIG Steamer) 
 */
public class Chenille {

    //------- variables d'instance (attributs) --------------------------
    /**
     * tableau stockant les références des anneaux de la chenille
     */
    private final Anneau[] lesAnneaux;
    /**
     * la tête de la chenille
     */
    protected Tete laTete;
    /**
     * la zône de dessin où se déplace la chenille
     */
    private Dessin dess;

    //-------- Constructeurs ---------------------------------------------
    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     */
    public Chenille(Dessin d, int r, int nbAnneaux) {
        this(d,new Tete(d.getLargeur() / 2, d.getHauteur() / 2, r, 0.0),r,nbAnneaux);
    }

    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param tete la tete de la chenille;
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     */
    protected Chenille(Dessin d, Tete tete, int r, int nbAnneaux) {

        this.dess = d;
        // crée une tête au centre de la fenêtre et de cap 0
        this.laTete = tete;
        int xTete = laTete.getX();
        int yTete = laTete.getY();

        // 1) créer le tableau
        lesAnneaux = new Anneau[nbAnneaux];
        // 2) remplir le tableau en créeant les anneau et en stockant
        //    leur référence dans les éléments du tableau.
        // créé les anneaux, à gauche les uns des autres. Le premier
        // (Anneau n° 0) étant à gauche de la tête
        for (int i = 0; i < lesAnneaux.length; i++) {
            lesAnneaux[i] = new Anneau(xTete - (i + 1) * r, yTete, r);
        }
    }

    /**
     * affiche la chenille.
     *
     * @param g cet objet de classe Graphics passé en paramètre est l'objet qui
     * prend en charge la gestion de l'affichage dans la fenêtre de dessin.
     * C'est cet objet qui gère le "contexte graphique" pour cette fenêtre.
     */
    public void dessiner(Graphics g) {
        // dessiner la tête
        laTete.dessiner(g);
        for (Anneau anneau : lesAnneaux) {
            anneau.dessiner(g);
        }
    }

    /**
     * fait effectuer à la chenille un déplacement élémentaire en avant dans la
     * direction indiquée par son cap. Le cap subit une déviation alétoire d'un
     * angle de plus ou moins 30 degrés. Si la tête de la chenille atteint un
     * des bords , son cap est alors dévié de 90°.
     */
    public void deplacer() {
        if (lesAnneaux.length > 0) {
        // fait avancer les anneaux.
        // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
        // de la tête
        for (int i = lesAnneaux.length - 1; i > 0; i--) {
            lesAnneaux[i].placerA(lesAnneaux[i - 1].getX(), lesAnneaux[i - 1].getY());
        }

        
        lesAnneaux[0].placerA(laTete.getX(), laTete.getY());
        }

        // calcule un nouveau cap qui garanti que la tête reste dans la zone
        // de dessin
        laTete.devierCap(-30.0 + Math.random() * 60.0);
        while (!laTete.capOK(dess.getLargeur(), dess.getHauteur())) {
            laTete.devierCap(10);
        }
        // fait avancer la tête
        laTete.deplacerSelonCap();
    }


}// Chenille
