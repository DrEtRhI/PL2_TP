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
package ufrim2ag.m2pcci.pl2.formesanimees.animation;

import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;

/**
 *
 * @author thierrye
 */
public class AnimateurRebond extends AnimateurAvecDessin{
    int dx;
    int dy;
    
    public AnimateurRebond(Dessin d, int dx, int dy){
        super(d);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void animerForme(IForme forme) {
        if (sortADroite(forme) || sortAGauche(forme)){
            dx = -dx;
        }
        if (sortEnBas(forme) || sortEnHaut(forme)){
            dy = -dy;
        }
        
        int newX = forme.getX() + dx;
        int newY = forme.getY() + dy;
        forme.placerA(newX, newY);
       
    }
}
