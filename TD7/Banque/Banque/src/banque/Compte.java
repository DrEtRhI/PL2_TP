/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banque;

/**
 *
 * @author thierrye
 */
public class Compte {

    private final int noCompte;
    private static int nbCompte = 0;
    private final String nom;
    private final String prenom;
    private double solde = 0;
    private double decouvertMax = 800;
    private double retraitMax = 1000;
    private boolean etat = true;

    /**
     * Constructeur 1 Permet de créer un compte standard avec un prénom et nom
     *
     * @param prenom du titulaire
     * @param nom du titutlaire
     */
    public Compte(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        nbCompte++;
        this.noCompte = nbCompte;

    }

    /**
     * Constructeur 2 Permet de créer un compte avec un prénom et nom et un
     * solde donné
     *
     * @param prenom du titulaire
     * @param nom du titutlaire
     * @param solde montant crédité sur le compte
     */
    public Compte(String prenom, String nom, double solde) {
        this.nom = nom;
        this.prenom = prenom;
        nbCompte++;
        this.noCompte = nbCompte;
        this.solde = solde;

    }

    /**
     * Constructeur 3 Permet de créer un compte avec un prénom et nom, un solde
     * donné, un découvert max donné et un retrait max donné
     *
     * @param prenom du titulaire
     * @param nom du titutlaire
     * @param solde montant crédité sur le compte
     * @param decouvertMax fixe la limite du découvert
     * @param retraitMax fixe la limite d'un retrait
     */
    public Compte(String prenom, String nom, double solde, double decouvertMax, double retraitMax) {
        this.nom = nom;
        this.prenom = prenom;
        nbCompte++;
        this.noCompte = nbCompte;
        this.solde = solde;
        this.retraitMax = retraitMax;
        this.decouvertMax = decouvertMax;

    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setDecouvertMax(double decouvertMax) {
        this.decouvertMax = decouvertMax;
    }

    public void setRetraitMax(double retraitMax) {
        this.retraitMax = retraitMax;
    }

    public double getDecouvert() {
        if (this.solde < 0) {
            return Math.abs(solde);
        } else {
            return 0;
        }
    }

    public int getNoCompte() {
        return noCompte;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getSolde() {
        return solde;
    }

    public double getDecouvertMax() {
        return decouvertMax;
    }

    public double getRetraitMax() {
        return retraitMax;
    }
    
    /**
     * Fonction qui vérifie si le compte est à découvert
     * @return revoie true si le compte est créditeur et false s'il est débiteur
     */
    public boolean isEtat() {
                this.etat = !(this.solde < 0);
        return etat;
    }

    /**
     * Fonction permettant de retirer une somme du compte Le débit est possible
     * seulement si la somme retirée est inférieur au retrait max et que le
     * solde du compte après retrait est supérieur ou égal au découvert max.
     * Dans le cas ou le retrait n'est pas possible un message indique que
     * l'opération est impossible et pourquoi. La fonction vérifie que le
     * paramètre x est une valeur positive > 0. Si ce n'est pas le cas la
     * fonction ne modifie pas le compte et affiche un message dans la console.
     *
     * @param x somme à débiter du compte
     * @return true si le virement a pu être réalisé, false dans le cas
     * contraire
     */
    public boolean debiter(double x) {
        double soldeAvantVirement = this.solde;
        if (x > 0) {
            if (x < this.retraitMax) {
                double testRetrait = this.solde - x;
                if (testRetrait > (-this.decouvertMax)) {
                    this.solde -= x;
                } else {
                    System.out.println("Opération impossible : le compte n° " + this.noCompte + " dépassera la limite de découvert maximale après opération.\n"
                            + "Le compte a actuellement un solde de  : " + this.solde + "€.");
                }
            } else {
                System.out.println("Opération impossible : la somme (" + x + ") à débiter dépasse le plafond maximal de retrait qui est actuellement fixé"
                        + "à : " + this.retraitMax);
            }
        } else {
            System.out.println("Opération impossible : la somme (" + x + ") indiquée pour le débit doit être strictement supérieure à 0");
        }
        return !(soldeAvantVirement == this.solde);
    }

    /**
     * Fonction permettant de crediter une somme du compte La fonction vérifie
     * que le paramètre x est une valeur positive > 0. Si ce n'est pas le cas la
     * fonction ne modifie pas le compte et affiche un message dans la console.
     *
     * @param x somme à créditer sur le compte
     */
    public void crediter(double x) {
        if (x > 0) {
            this.solde += x;
        } else {
            System.out.println("Opération impossible : la somme (" + x + ") indiquée pour le crédit doit être strictement supérieure à 0");
        }
    }

    /**
     * La fonction permet de réaliser un virement d'une somme x de ce compte sur
     * le compte c. Il faut vérifier pour ce compte que le virement est possible
     * seulement si la somme retirée est inférieur au retrait max et que le
     * solde du compte après retrait est supérieur ou égal au découvert max. La
     * fonction vérifie que le paramètre x est une valeur positive > 0. Si ce
     * n'est pas le cas la fonction ne modifie pas le compte et affiche un
     * message dans la console.
     *
     * @param c compte qui reçoit le virement
     * @param x la somme du virement
     */
    public void virementSur(Compte c, double x) {
        if (this.debiter(x)) {
            c.crediter(x);
        } else {
            System.out.println("OPÉRATION ANNULÉE");
        }
    }

    /**
     * Fonction qui retourne une chaine de caractère contenant toutes les
     * informations sur le compte.
     *
     * @return Une string contenant les infos du compte
     */
    @Override
    public String toString() {
        String info;
        String etatCompte;
        if (this.isEtat()){
            etatCompte = "le compte est créditeur.";
        }else{
            etatCompte = "le compte est débiteur.";
        }
        double debitAutorise;
            if (Math.abs(this.solde - this.retraitMax) > this.decouvertMax){
                debitAutorise = this.retraitMax;
            }else{
                debitAutorise = this.decouvertMax - Math.abs(this.solde);
            } 
            
        info = "Numéro de compte : " + this.noCompte + "\n"
                + "Titulaire du compe : " + this.prenom + " " + this.nom + "\n"
                + "Découvert maximal autorisé : " + this.decouvertMax + "\n"
                + "Plafond de retrait maximal : " + this.retraitMax + "\n"
                + "Situation du compte : " + etatCompte + "\n"
                + "Débit maximal autorisé : " + debitAutorise +"€";
        return info;
    }
}
