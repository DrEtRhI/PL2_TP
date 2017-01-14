/*
 * Copyright (C) 2017 thierrye
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

/**
 *
 * @author thierrye
 */
public class Cercle extends FormeCirculaire {

    public Cercle(int x, int y, int r, float epTrait, Color cTrait, Color cRemp) {
        super(epTrait, cTrait, cRemp, x, y, r);
    }

    @Override
    public void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2 = (Graphics2D) g.create();


        // on dessine le contour de la forme
        // avec la couleur de trait spécifiée ou la couleur courante
        // du contexte graphique sinon
        if (couleurTrait != null) {
            g2.setColor(couleurTrait);

        }
        g2.setStroke(new BasicStroke(epaisseurTrait));
        g2.drawOval(x, y, rayon * 2, rayon * 2);

        // on dessine l'intérieur de la forme 
        if (couleurRemplissage != null) {
            g2.setColor(couleurRemplissage);
            g2.fillOval(x, y, rayon * 2, rayon * 2);

        }
    }
}
