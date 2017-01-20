/*
 * Copyright (C) 2016 Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ufrim2ag.m2pcci.pl2.formesanimees.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.IDessinable;

/**
 * Une forme régulière dont les sommets sont répartis de façon uniforme sur le
 * cercle où elle est inscrite. Une forme régulière peut être dessinée avec ou
 * sans remplissage. Il est possible de fixer la couleur du contour différemment
 * de la couleur de remplissage de la forme.
 *
 * @author Philippe Genoud - Universté Grenoble Alpes - Lab LIG-Steamer
 *
 */
public abstract class FormeCirculaireReguliere extends FormeCirculaire implements IDessinable {

    /**
     * l'objet Path correspondant au contour de la forme régulière, exprimé dans
     * le repère dont l'origine est le centre du cercle où elle est inscrite.
     */
    protected Path2D contour;

    /**
     * Constructeur.
     *
     * @param nbSommets le nombre de sommets du polygone regulier
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param r rayon
     * @param epTrait l'epaisseur du trait de contour
     * @param cTrait couleur du trait de contour
     * @param cRemp couleur remplissage
     */
    protected FormeCirculaireReguliere(int nbSommets, int x, int y, int r,
            float epTrait, Color cTrait, Color cRemp) {
        super(epTrait, cTrait, cRemp, x, y, r);
        

        // calcul des sommets du polygone régulier
        float deltaAngle = 360f / nbSommets;
        float angle = -90;
        Point2D.Float[] sommets = new Point2D.Float[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            sommets[i] = new Point2D.Float(
                    (float) Math.cos(Math.toRadians(angle)) * r,
                    (float) Math.sin(Math.toRadians(angle)) * r);
            angle += deltaAngle;
        }
        // construction du chemin reliant les points
        this.contour = new Path2D.Float();
        calculerContour(sommets, this.contour);
    }

    /**
     * Cette méthode abstraite appelée dans le constructeur construit le contour
     * de la forme (Path2D) à partir des sommets calculé sur le cercle. L'appel
     * d'une méthode dans un constructeur n'étant pas recommandé du fait des
     * effets indésirables pouvant exister en cas de redéfinition, il est
     * recommandé de redéfinir cette méthode comme étant finale.
     *
     * @param tabSommets le tableau des sommets sur le cercle.
     * @param contour le contour à défiir à partir de ces sommets
     */
    protected abstract void calculerContour(Point2D.Float[] tabSommets, Path2D contour);
    

    @Override
    public void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2 = (Graphics2D) g.create();
        // on applique la transformation de coordonnées por placer
        // le centre du cercle en (x, y)
        g2.translate(x, y);

        // on dessine le contour de la forme
        // avec la couleur de trait spécifiée ou la couleur courante
        // du contexte graphique sinon
        if (couleurTrait != null) {
            g2.setColor(couleurTrait);

        }
        g2.setStroke(new BasicStroke(epaisseurTrait));
        g2.draw(contour);

        // on dessine l'intérieur de la forme 
        if (couleurRemplissage != null) {
            g2.setColor(couleurRemplissage);
            g2.fill(contour);
        }
    }

}
