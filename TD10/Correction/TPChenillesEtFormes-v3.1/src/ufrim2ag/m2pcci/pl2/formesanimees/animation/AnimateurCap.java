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

package ufrim2ag.m2pcci.pl2.formesanimees.animation;

import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class AnimateurCap extends AnimateurAvecDessin {
    
    /**
     * le cap (exprimé en degrés)
     */
    protected double cap;
    
    /**
     * la distance de déplacement élémentaire dans la direction du cap
     */
    protected int deplacementElem;

    /**
     *
     * @param cap le cap initial
     * @param depElem la distance de déplacement élémentaire
     */
    public AnimateurCap(double cap, int depElem, Dessin d) {
        super(d);
        this.cap = cap;
        this.deplacementElem = depElem;
    }

    /**
     * modifie le cap
     *
     * @param deltaC la valeur à ajouter au cap
     */
    private void devierCap(double deltaC) {
        cap += deltaC;
        cap = normalize(cap);
    }

    /**
     * normalize un angle pour le ramener à un valeur dans l'intervalle [-180°, +180°]
     * @param d la valeur de l'angle en degrés
     * @return langle normalisé
     */
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

    /**
     * modifie le point de référence de la forme de manière à ce que celui-ci
     * soit translaté d'une distance définie par deplacementElemen dans la
     * direction du cap.
     *
     * @param f la forme à déplacer
     */
    private void deplacerSelonCap(IForme f) {
        f.placerA((int) (f.getX() + deplacementElem * Math.cos(Math.PI * cap / 180)),
                (int) (f.getY() + deplacementElem * Math.sin(Math.PI * cap / 180)));
    }

    @Override
    public void animer(IForme f) {
        // calcule un nouveau cap qui garanti que la forme reste dans la zone
        // de dessin
        this.devierCap(-30.0 + Math.random() * 60.0);
        while (!capOK(f)) {
            this.devierCap(10);
        }
        // fait avancer la forme
        this.deplacerSelonCap(f);
    }

    /**
     * teste si le cap actuel garantit que prochain déplacement de la forme
     * selon son cap maintiendra celle-ci entièrement dans la zone de dessin.
     *
     * @param f la forme à deplacer
     *
     * @return true si la boite englobante après une translation de
     * deplacementElem dans la direction du cap est entièrement dans la zone de
     * dessin
     */
    public boolean capOK(IForme f) {
        double demiLargeur = f.getRectEnglobant().getWidth() / 2;
        double demiHauteur = f.getRectEnglobant().getHeight() / 2;
        int x1 = (int) (f.getX() + deplacementElem * Math.cos(Math.PI * cap / 180));
        int y1 = (int) (f.getY() + deplacementElem * Math.sin(Math.PI * cap / 180));

        return x1 >= demiLargeur && x1 <= (d.getLargeur() - demiLargeur)
                && y1 >= demiHauteur & y1 <= (d.getHauteur() - demiHauteur);
    }

}
