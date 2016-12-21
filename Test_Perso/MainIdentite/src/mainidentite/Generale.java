/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainidentite;

/**
 *
 * @author laura
 */
public class Generale {
    
    protected String prenom;
    protected String nom;
    protected long numSecu;
    
    public Generale(String prenom, String nom, long numSecu){
        this.prenom = prenom;
        this.nom = nom;
        this.numSecu = numSecu;
    }
    
    @Override
    public String toString(){
        return "Prenom : " + this.prenom + "\nNom : " + this.nom + "\nNuméro Sécu : " + this.numSecu;
    }
}
