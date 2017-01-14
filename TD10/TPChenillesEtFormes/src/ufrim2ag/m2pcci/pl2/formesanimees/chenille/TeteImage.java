/*
 * Copyright (C) 2017 Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
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
package ufrim2ag.m2pcci.pl2.formesanimees.chenille;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Tête défine par une image
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class TeteImage extends Tete {

    /**
     * l'image a afficher pour la tête
     */
    private final BufferedImage img;
    
    /**
     * 
     * @param xInit abscisse initial du centre de la tête  
     * @param yInit ordonnées intiial du centre de la tête
     * @param cap le cap initial de la tête
     * @param img l'image de la tête.
     */
    public TeteImage(int xInit, int yInit, int cap, BufferedImage img) {
        super(xInit, yInit, img.getWidth() /2 , cap);
        this.img = img;
    }

    // redéfinition de la méthode dessiner
    @Override
    public void dessiner(Graphics g) {
        g.drawImage(img, 
                this.x - r, this.y - r, this.x + r, this.y + r,
                0, 0, 63, 63, null);
    }
    
}
