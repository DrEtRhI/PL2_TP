/*
 * Copyright (C) 2017 laura
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
package ufrim2ag.m2pcci.pl2.formesanimees.animation;

import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;

/**
 *
 * @author laura
 */
public class AnimateurCap extends AnimateurAvecDessin{

    private double cap;
    Rectangle rect;
    
    public AnimateurCap(Dessin d, double cap) {
        super(d);
        this.cap = cap;
    }
    
    /**
     * Modifie le cap de la forme. Le cap courant de la forme est modifié
     * en lui ajoutant une variation de cap passée en paramètre.
     *
     * @param deltaC la variation à appliquer au cap de la forme (en degrés).
     */
    public void devierCap(double deltaC, IForme f) {
        rect = f.getRectEnglobant();
        cap += deltaC;
        cap = normalize(cap);
    }
    
    /**
     * deplace le centre de la forme de manière à ce que le déplacement
     * effectué corresponde à un déplacement d'une distance egale au rayon de de
     * la tête dans la direction indiquée par le cap.
     */
    public void deplacerSelonCap(IForme f) {
        rect = f.getRectEnglobant();
        f.placerA((int) (f.getX() + ((rect.width / 2) * Math.cos(Math.PI * cap / 180))), (int) (f.getY() + (rect.height / 2) * Math.sin(Math.PI * cap / 180)));
    }
    
    /**
     * teste si le cap actuel garantit que prochain déplacement de la tête selon
     * son cap maintiendra celle-ci entièrement dans la zone de dessin.
     *
     * @param xMax la taille en x de la zone de dessin
     * @param yMax la taille en y de la zone de dessin
     *
     * @return true le point (x',y') défini par x' = x + R * cos(cap) et 
     *         y' = y + R * sin(cap) est à une distance >= R de chacun des
     *         bords de la zone.
     */ 
    public boolean capOK(int xMax, int yMax, IForme f) {
        rect = f.getRectEnglobant();
        int x1 = (int) (f.getX() + (rect.width / 2) * Math.cos(Math.PI * cap / 180));
        int y1 = (int) (f.getY() + (rect.height / 2) * Math.sin(Math.PI * cap / 180));

        return x1 >= rect.width / 2 && x1 <= (xMax - rect.width / 2) && y1 >= rect.height / 2 & y1 <= (yMax - rect.height / 2);
    }
    
    private double normalize(double d) {
        double res = Math.abs(d) % 360;
        if (d < 0) {
            if (res > 180) {
                res = 360 - res;
            } else {
                res = -res;
            }
        } else {
            if (res > 180) {
                res = -(360 - res);
            }
        }
        return res;
    }

    @Override
    public void animerForme(IForme forme) {
//        if (lesAnneaux.length > 0) {
//        // fait avancer les anneaux.
//        // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
//        // de la tête
//        for (int i = lesAnneaux.length - 1; i > 0; i--) {
//            lesAnneaux[i].placerA(lesAnneaux[i - 1].getX(), lesAnneaux[i - 1].getY());
//        }
//
//        
//        lesAnneaux[0].placerA(laTete.getX(), laTete.getY());
//        }

        // calcule un nouveau cap qui garanti que la forme reste dans la zone
        // de dessin
        devierCap(-30.0 + Math.random() * 60.0, forme);
        while (!capOK(d.getLargeur(), d.getHauteur(), forme)) {
            devierCap(-30.0 + Math.random() * 60.0, forme);
        }
        // fait avancer la forme
        deplacerSelonCap(forme);
    }
}
