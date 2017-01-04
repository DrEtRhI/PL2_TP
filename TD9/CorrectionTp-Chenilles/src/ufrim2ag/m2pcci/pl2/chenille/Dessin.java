package ufrim2ag.m2pcci.pl2.chenille;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Defini le contenu de la fenêtre de l'application d'animation des Chenille.
 * Une zone de dessin est un JPanel qui gère un liste d'objets Chenille.
 * Lorsqu'il se réaffiche l'objet Dessin redessinne les différents objets
 * Chenille contenus dans cette liste.
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class Dessin extends JPanel {

  /**
   * stocke la liste des Chenilles ayant été ajoutées à cette zone de dessin.
   */
  private final List<FormeAnime> listeDesFormes = new ArrayList<>();

  /**
   * retourne la largeur de la zone de dessin.
   *
   * @return la largeur.
   */
  public int getLargeur() {
    return getWidth();
  }

  /**
   * retourne la hauteur de la zone de dessin.
   *
   * @return la hauteur.
   */
  public int getHauteur() {
    return getHeight();
  }

  /**
   * ajoute un Chenille à la zone de dessin.
   *
   * @param ch la Chenille à ajouter au Dessin
   * @see Chenille
   */
  public void ajouterObjet(FormeAnime ch) {

    if (!listeDesFormes.contains(ch)) {
      // l'objet n'est pas déjà dans la liste
      // on le rajoute a la liste des objets du dessin
      listeDesFormes.add(ch);
      // le dessin se réaffiche
      repaint();
      this.pause(10);
    }
  }

  /**
   * temporisation de l'animation.
   *
   * @param duree delai de temporisation en ms.
   */
  public void pause(int duree) {
    try {
      Thread.sleep(duree);
    } catch (InterruptedException e) {
    }
  }

  /**
   * affiche la zone de dessin et son contenu
   *
   * @param g le contexte graphique
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
   //  dessiner les Objets que contient le dessin
    for (FormeAnime f : listeDesFormes) {
      f.dessiner(g);
    }
  }
} // Dessin
