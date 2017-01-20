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

package ufrim2ag.m2pcci.pl2.formesanimees.animation;

import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class AnimateurRebond extends AnimateurAvecDessin {
    
    private int dx;
    private int dy;

    public AnimateurRebond(int dx, int dy, Dessin d) {
        super(d);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void animer(IForme f) {
        Rectangle rectEnglobant = f.getRectEnglobant();
        if (bordGaucheOuDroit(rectEnglobant)) {
            this.dx = -this.dx;
        }
        if (bordHautOuBas(rectEnglobant)) {
            this.dy = - this.dy;
        }
        f.placerA(f.getX() + dx, f.getY() + dy);
    }
    
    

}
