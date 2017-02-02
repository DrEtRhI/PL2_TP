package im2ag.m2pcci.reservations;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public abstract class Materiel {
     int numInv;
     String nomMarque;
     String nomModele;

    public Materiel(int numInv, String nomMarque, String nomModele) {
        this.numInv = numInv;
        this.nomMarque = nomMarque;
        this.nomModele = nomModele;
    }
 
    public String getMarqueEtModele(){
        return this.nomMarque + " & " + this.nomModele;
    }
    public abstract String getCaracteristiques();
    
     @Override
    public String toString(){
        String fiche = "Numéro d'inventaire : " + numInv + "\n"
                + "Marque & modèle : " + this.getMarqueEtModele()+"\n"
                + "Caractéristiques techniques : \n" + this.getCaracteristiques();
        return fiche;
    }
     
}
