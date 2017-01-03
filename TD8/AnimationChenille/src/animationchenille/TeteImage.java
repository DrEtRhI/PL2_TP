/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animationchenille;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 *
 * @author EtRhI_PC
 */
public class TeteImage extends Tete {

    BufferedImage img;
    
    public TeteImage(int x, int y, int r, double c, BufferedImage img) {
        super(x, y, r, c);
        this.img = img;        
    }
    
    @Override
    public void dessiner(Graphics g){
        g.drawImage(img, x - 20, y - 20, r * 2, r * 2, null);
    }

}
