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
public class Precision extends Generale {

    String villeNaissance;
    int age;
    int taille;
    int poids;
    String profession;

    public Precision(Generale g, String villeNaissance, int age, int taille, int poids, String profession) {
        super(g.prenom, g.nom, g.numSecu);
        this.villeNaissance = villeNaissance;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.profession = profession;
    }

    
    @Override
    public String toString() {
        return super.toString() + "\nVille de Naissance : " + this.villeNaissance
                + "\nAge : " + this.age
                + "\nTaille : " + this.taille
                + "\nPoids : " + this.poids
                + "\nProfession : " + this.profession;
    }
}
