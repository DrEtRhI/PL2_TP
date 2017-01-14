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

import java.awt.Graphics;
import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimateur;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IFormeAnimee;

/**
 *
 * @author thierrye
 */
public class FormeAnimee implements IFormeAnimee {
    
    protected IForme forme;
    protected IAnimateur animateur;
    
    public FormeAnimee(IForme forme, IAnimateur animateur){
        this.forme = forme;
        this.animateur = animateur;
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void placerA(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getRectEnglobant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dessiner(Graphics g) {
        this.forme.dessiner(g);
    }

    @Override
    public void deplacer() {
        this.animateur.animerForme(this.forme);
    }
    
}
