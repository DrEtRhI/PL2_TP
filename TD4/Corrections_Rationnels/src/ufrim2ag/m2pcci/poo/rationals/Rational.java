package ufrim2ag.m2pcci.poo.rationals;

/**
 * <p>La classe Rationnel représente les nombres rationnles.</p>
 * <p>
 * Les nombres rationnels représentés par un objet Rational sont représentés sous
 * leur forme irréductible (simplifié) et avec un dénominateur positif.
 * </p>
 * <p>
 * Seuls des nombres rationnels valident peuvent être représentés par cette classe.
 * Un objet Rational ne peut avoir un denominateur nul.
 * </p>
 * @author Equipe pédagogique Ensimag - POO
 * @version 1.1 modifications by Philippe Genoud - UGA - LIG STeamer
 */
public class Rational {

    /**
     * numerateur
     */
    private int num;
    /**
     * denominateurr
     */
    private int denom ;

    /**
     * Construit un nouveau rationnel num/denom et le stoke sous sa forme
     * simplifiée.
     *
     * @param num numerator
     * @param denom denominator
     * @throws IllegalArgumentException si denom est nul.
     */
    public Rational(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("dénominateur nul interdit");
        }
        this.num = num;
        this.denom = denom;
        this.simplifier();
    }

    /**
     * Construit le rationnel n/1.
     *
     * @param n numerator
     */
    public Rational(int n) {
        this(n, 1);
    }

    /**
     * Copy constructor: initializes the newly created Rational with the
     * numerator and denominator of the argument.
     *
     * @param other the rational used to initialize the newly created one
     */
    public Rational(Rational other) {
        this(other.num, other.denom);
    }

    /**
     * Retourne le numérateur de ce rationnel.
     *
     * @return le numerateur du rationnel.
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Retourne le dénominateur de ce rationnel.
     *
     * @return le dénominateur du rationnel.
     */
    public int getDenom() {
        return this.denom;
    }

    /**
     * Convertit la valeur de ce rationnel en un double.
     *
     * @return la valeur double précision la plus proche de la valeur réprésentée
     * par ce nombre rationel.
     */
    public double toDouble() {
        return (double) num / denom;
    }

    /**
     * Multiplie ce rationnel (this) avec un autre rationnel.
     *
     * @param r le rationnel avec lequel ce rationel est multiplié.
     */
    public void mult(Rational r) {
        this.num = this.getNum() * r.getNum();
        this.denom = this.getDenom() * r.getDenom();
        this.simplifier();
    }


    /**
     * Ajoute un rationnel à ce rationel (this).
     *
     * @param r le rationnel à ajouter.
     */
    public void add(Rational r) {
        this.num = r.getDenom() * this.getNum() + this.getDenom() * r.getNum();
        this.denom = r.getDenom() * this.getDenom();
        this.simplifier();
    }

    /**
     * simplifie  ce rationel.
     */
    private void simplifier() {
        int pgcd = pgcd(this.num, this.denom);
        this.num /= pgcd;
        this.denom /= pgcd;
        if (this.denom < 0) {
            this.num = -this.num;
            this.denom = -this.denom;
        }
    }

    /**
     * renvoie le plus grand commun diviseur  (pgcd) entre deux entiers
     *
     * @param a le premier entier
     * @param b le second entier
     * @return le pgcd de a et ab
     */
    private int pgcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return pgcd(b, a % b);
    }

    /**
     * donne une représentation du rationel sous forme d'une chaîne de caractères 
     * @return la chaîne représentant le rationel num/denom
     */
    @Override
    public String toString() {
        return this.getNum()
                + (this.getDenom() != 1 ? " / " + this.getDenom() : "");
    }


    /**
     * teste l'égalité de ce rationnel avec un autre rationnel
     * 
     * (on vera plus tard que ce n'est pas tout à fait comme cela que la méthode equals
     * devrait être définie, mais il va falloir d'abord aborder les notions d'héritage
     * et de polymorphisme).
     * 
     * @param other l'autre rationnel.
     * 
     * @return true si ce rationnel a le même numérateur et le même dénonimateur
     * que other, false sinon.
     */
    public boolean equals(Rational other) {

        return (other != null && this.num == other.num && this.denom == other.denom);
    }


    
    
    
}
