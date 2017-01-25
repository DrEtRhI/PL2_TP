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

import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;

/**
 *
 * @author thierrye
 */
public abstract class AnimateurAvecDessin implements IAnimateur{
    
    Dessin d;
    
    public AnimateurAvecDessin(Dessin d){
        this.d = d;
    }
    
    protected boolean sortAGauche(IForme f){
        Rectangle rect = f.getRectEnglobant();
        return (f.getX() - rect.getWidth() / 2) < 0;
    }
    
    protected boolean sortADroite(IForme f){
        Rectangle rect = f.getRectEnglobant();
        return (f.getX() + rect.getWidth()) > this.d.getWidth();
    }
    
    protected boolean sortEnBas(IForme f){
        Rectangle rect = f.getRectEnglobant();
        return (f.getY() + rect.getHeight())> this.d.getHeight();
    }
    
    protected boolean sortEnHaut(IForme f){
        Rectangle rect = f.getRectEnglobant();
        return (f.getY() - rect.getHeight() / 2) < 0;
    }
    
}
