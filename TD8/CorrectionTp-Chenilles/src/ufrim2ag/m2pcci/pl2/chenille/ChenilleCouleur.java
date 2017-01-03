package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Chenille en couleur
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class ChenilleCouleur extends Chenille {

    /**
     * la couleur de la Chenille
     */
    private final Color coul;

    /**
     * @param coul couleur de la chenille.
     * @param d le dessin où elle se déplace.
     * @param r le rayon des ses annéeaux et de sa tête.
     * @param nbAnneaux le nombre d'anneaux.
     */
    public ChenilleCouleur(Color coul, Dessin d, int r, int nbAnneaux) {
        super(d, r, nbAnneaux);
        this.coul = coul;
    }

    @Override
    public void dessiner(Graphics g) {
        Graphics gd = g.create();   // fait une copie du contexte graphique
        gd.setColor(coul);   // pour que le changement de couleur n'affecte que
        // cette chenille
        super.dessiner(gd);
    }

}
