/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrational;

/**
 * Une classe permettant de manipuler des rationelles
 * @author thierrye
 */
public class Rational {
    private int num, denom;
    
    public Rational (int x, int y){
        
        num = x;
        denom = y;
        if (denom == 0){
        throw new IllegalArgumentException("Dénominateur nul");
        }
        this.irreductible();
        
        
        
        
    }
    /**
     * Ecrit la fraction sous forme texte "3/2"
     * @return litteral la fraction transformée en String
     */
    @Override
    public String toString(){
        String litteral = this.num + "/" + this.denom;
        return litteral;
    }
    
    /**
     * Permet de réaliser une multiplication de fraction
     * @param f est l'objet Rational à multiplier avec l'écoutant
     */
    public void mult(Rational f){
        this.num = (this.num * f.num);
        this.denom = (this.denom * f.denom);
        this.irreductible();
    }
    
    /**
     * Permet de réaliser une addition de fraction
     * @param f est l'objet Rational à additioner avec l'écoutant
     */
    public void add(Rational f){
        this.num = (this.num * f.denom + f.num * this.denom);
        this.denom = (this.denom * f.denom);
        this.irreductible();
    }
    
    /**
     * Méthode permettant d'appliquer le pgcd
     * s'il existe, elle l'applique au numérateur et dénominateur
     * de l'objet Rationnal
     */
    private void irreductible(){
        
        int pgcd;
        pgcd = pgcd(this.num, this.denom);
        if (pgcd !=1){
            this.num /= pgcd;
            this.denom /= pgcd;
        }
    }
    
    /**
     * Méthode permettant de calculer le pgcd
     * @param num de l'objet Rationnal
     * @param denom de l'objet Rationnal
     * @return le pgcd calculé
     */
    private int pgcd(int num, int denom){
        if (denom == 0){
            return num;
        }else{
            int reste = num % denom;
            return pgcd(denom, reste);
        }
        
    }
}
