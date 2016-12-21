package ufrim2ag.m2pcci.poo.comptes;

/**
 * Compte.java<br>
 *
 * Modélise un compte Bancaire.
 *
 * Un compte possède :
 * <ul>
 * <li>un numéro unique. Chaque compte possède un numéro différent fixé à la
 * création du compte.</li>
 * <li>un titulaire (une Personne)</li>
 * <li>un debit maximal autorise</li>
 * <li>un decouvert maximal autorise</li>
 * <li>un solde (solde >= -decouvert maximal autorisé).<li>
 * </ul>
 *
 * @author Philippe Genoud (LIG - STeamer)
 */
public class Compte {

    //--------------------------------------------------------------------------
    // constantes
    //--------------------------------------------------------------------------  
    /**
     * le débit maximal autorisé par défaut
     */
    public static final double DEFAULT_DEBIT_MAX = 1000;
    /**
     * le découvert maximal autorisé par défaut
     */
    public static final double DEFAULT_DECOUVERT_MAX = 800;

    //--------------------------------------------------------------------------
    // attribut de l'objet Class Compte (variable de classe)
    //--------------------------------------------------------------------------
    /**
     * un compteur du nombre de comptes précédemment créés, utilisé pour
     * attribuer les numéros de compte.
     */
    static private int nbreComptesCréés = 0;

    //--------------------------------------------------------------------------
    //attributs (variables d'instance) d'un objet compte
    //--------------------------------------------------------------------------
    /**
     * le numéro du compte
     */
    private final int numero;  // le numero ne peut changer c'est pourquoi il est final
    /**
     * solde du compte (en euros)
     */
    private double solde;
    /**
     * decouvert maximal autorise (en euros)
     */
    private double decouvertMaxAutorisé;
    /**
     * débit maximal autorisé (en euros)
     */
    private double debitMaxAutorisé;
    /**
     * titulaire du compte
     */
    private final Personne titulaire; // le titualire ne peut changer c'est pourquoi il est final

    //--------------------------------------------------------------------------
    // Constructeurs
    //--------------------------------------------------------------------------
    /**
     * Constructeur d'un compte.
     *
     * @param depotInitial le dépot initial sur le compte à sa création.
     * <code>depotInitial</code> doit être positif ou nul. 
     *
     * @param decouvertMaxAutorisé decouvert maximal autorisé pour ce compte. 
     *
     * @param debitMaxAutorisé débit maximal autorisé pour ce compte. 
     *
     * @param titulaire le titulaire du compte
     * 
     * @throws IllegalArgumentException si la valeur du paramètre  dépotInitial 
     *       ou decouvertMaxAutorisé ou  débitMaxAutorisé est incorrecte, c'est 
     *       à direun < 0.
     */
    public Compte(Personne titulaire, double depotInitial, double decouvertMaxAutorisé,
            double debitMaxAutorisé) {
        if (depotInitial < 0 || decouvertMaxAutorisé <0 || debitMaxAutorisé < 0 ) {
            throw new IllegalArgumentException("un compte ne peut être initialisé avec une valeur négative");
        }
        nbreComptesCréés++;
        this.numero = nbreComptesCréés;
        this.solde = depotInitial;
        this.decouvertMaxAutorisé = decouvertMaxAutorisé;
        this.debitMaxAutorisé = debitMaxAutorisé;
        this.titulaire = titulaire;
    }

    /**
     * Constructeur d'un compte. Seuls le titulaire est spécifié. Le solde
     * initial est initialisé à 0 et le decouvert max et le debit max autorisés
     * sont initialisés avec les valeurs par défaut.
     *
     * @param titulaire le titulaire du compte
     */
    public Compte(Personne titulaire) {
        this(titulaire,0, DEFAULT_DECOUVERT_MAX, DEFAULT_DEBIT_MAX);
    }

    //--------------------------------------------------------------------------
    // méthodes
    //--------------------------------------------------------------------------  
    //---------------------- accesseurs ----------------------------------------
    public int getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public double getDecouvertMaxAutorisé() {
        return decouvertMaxAutorisé;
    }

    public double getDebitMaxAutorisé() {
        return debitMaxAutorisé;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    /**
     * renvoie la situation de decouvert du compte.
     *
     * @return <code>true</code> si le compte est à découvert,
     * <code>false</code> sinon.
     */
    public boolean estAdecouvert() {
        return (this.solde < 0);
    }

    /**
     * renvoie le decouvert du compte.
     *
     * @return 0 si le solde du compte est positif ou nul, la valeur absolue du
     * solde si il est negatif.
     */
    public double getDecouvert() {
        return (this.solde >= 0) ? 0 : -solde;
    }
    
    /**
     * Calcule le débit autorisé en fonction du solde courant du compte, 
     * du découvertMaxAutorisé et du debitMaxAutorisé.
     * @return débit autorisé = Min(solde + decouvertMaxAutorisé, debitMaxAutorisé)
     */
    public double getDebitAutorise() {
        return Math.min(solde + decouvertMaxAutorisé, debitMaxAutorisé);
    }

    //---------------------- modifieurs ----------------------------------------
    // uniquement pour les attributs decouvertMaxAutorisé et debitMaxAutorisé,
    // le solde lui sera modifié par les opérations de débit, crédit et transfert
    // et les autres attributs (titulaire et numero) sont finaux, ils ne peuvent
    // être modifiés.
    /**
     * fixe le decouvert maximal autorisé.
     *
     * @param decouvertMaxAutorisé nouvelle valeur pour le découvert maximal.
     * @throws IllegalArgumentException si l'opération n'a pu être effectuée car
     * decouvertMaxAutorisé < 0
     */
    public void setDecouvertMaxAutorisé(double decouvertMaxAutorisé) {
        if (decouvertMaxAutorisé < 0) {
            throw new IllegalArgumentException("Decouvert Maximum Autorisé doit être positif ou nul");
        }
        this.decouvertMaxAutorisé = decouvertMaxAutorisé;
    }

    /**
     * fixe le débit maximal autorisé.
     *
     * @param debitMaxAutorisé nouvelle valeur pour le débit maximal.
     * @throws IllegalArgumentException si l'opération n'a pu être effectuée car
     * debitMaxAutorisé <=0
     */
    public void setDebitMaxAutorisé(double debitMaxAutorisé) {
        if (debitMaxAutorisé <= 0) {
            throw new IllegalArgumentException("Débit Maximum Autorisé doit être positif");
        }
        this.debitMaxAutorisé = debitMaxAutorisé;
    }

    //--- opérations sur le compte ---------------------------------------------
    /**
     * depose la somme s sur le compte.
     *
     * @param s la somme à déposer. s doit être > 0.
     * @throws IllegalArgumentException si s <= 0
     */
    public void crediter(double s) {
        if (s < 0) {
            throw new IllegalArgumentException("Un compte ne peut être crédité avec une somme négative");
        }
        solde = solde + s;
    }

    /**
     * débite le compte d'un montant donné.
     *
     * @param r la somme à retirer
     *
     * @throws IllegalArgumentException si le montant spécifié pour le retrait
     * est supérieur au retrait maximal possible ou inférieur à 0.
     */
    public void debiter(double r) {
        if (r < 0) {
            throw new IllegalArgumentException("On ne peut débiter un compte avec une somme négative");
        }
        if (r > getDebitAutorise()) {
            throw new IllegalArgumentException("Somme à débiter supérieure au retrait maximum possible");
        }
        solde = solde - r;
    }

    /**
     *
     * effectue un virement vers un autre compte.
     *
     * @param c compte vers lequel le virement est effectué.
     * @param r le montant du virement (la somme a retirer de ce compte (this)
     * et à déposer sur l'autre compte (c)).
     * 
     * @throws IllegalArgumentException si le montant spécifié pour le retrait
     * est supérieur au retrait maximal possible ou inférieur à 0.
     */
    public void virerVers(Compte c, double r) {
        this.debiter(r);
        c.crediter(r);
    }
}
