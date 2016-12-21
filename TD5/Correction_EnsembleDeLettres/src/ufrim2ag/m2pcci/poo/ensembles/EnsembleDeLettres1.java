package ufrim2ag.m2pcci.poo.ensembles;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Représente un ensemble de lettres sans répétition (une lettre n'apparait
 * qu'une seule fois). Les éléments de ces ensembles sont les lettres minuscules
 * de l'alphabet ('a' à 'z').
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class EnsembleDeLettres1 {

    /**
     * constante : nombre de lettres de l'alphabet
     */
    public static final int NB_LETTRES_ALPHABET = 26;

    //--------- Attributs ------------------------------------------------------
    /**
     * Représente l'ensemble.<br>
     * present[i] == true signifie que la ième lettre de l'alphabet appartient à
     * cet ensemble, sinon present[i] = false;
     */
    private final boolean[] present = new boolean[NB_LETTRES_ALPHABET];

    //--------- Constructeurs --------------------------------------------------
    /**
     * Construit un ensembe de lettres vide.
     */
    public EnsembleDeLettres1() {
    }

    /**
     * crée un ensemble de n lettres tiré au hazard
     *
     * @param n le nombre de lettres de l'ensemble
     */
    public EnsembleDeLettres1(int n) {
        if (n == 0) {   // on crée un ensemble vide, rien à faire
            return;
        }
        for (int i = 0; i < n; i++) {
            present[i] = true;
        }

        if (n == NB_LETTRES_ALPHABET) { // l'ensemble contient toutes les lettres, rien de plus à faire
            return;
        }
        // on mélange le tableau, pour cela un algorithme de Fisher–Yates 
        // est utilisé.
        // pour en savoir plus voir: 
        // http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
        // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
        Random rnd = ThreadLocalRandom.current();
        for (int i = present.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            boolean a = present[index];
            present[index] = present[i];
            present[i] = a;
        }
    }

    /**
     * Construit un ensemble de lettres à partir d'une chaîne de caractères.
     * Tous les caractères de la chaîne correspondants à une lettre de
     * l'alphabet sont convertis en minuscule et insérés dans l'ensemble ainsi
     * créé, les autres caractères de la chaîne sont ignorés
     *
     * @param s la chaîne servant à construire l'ensemble de lettres
     */
    public EnsembleDeLettres1(String s) {
        s = s.toLowerCase();  // convertit la chaîne en minuscules
        for (int i = 0; i < s.length(); i++) {
            ajouter(s.charAt(i));
        }
    }

    //----- méthodes privées, ne peuvent être invoquées que dans
    //----- la classe.
    /**
     * renvoie pour un caractère donné sa position dans l'alphabet (la position
     * 0 correspondant à la lettre 'a').
     *
     * @param c le caractère dont on veut la position
     * @return la position de ce caractère dans l'alphabet
     * @see toChar
     */
    private int toCode(char c) {
        return c - 'a';
    }

    /**
     * renvoie pour une position donnée le caractère correspondant dans
     * l'alphabet (le caractère 'a' correspond à la position 0)
     *
     * @param code la position dans le code dont on veut connaître le caractère
     * correspondant
     * @return le caractère correspondant à cette position
     * @see toCode
     */
    private char toChar(int code) {
        return (char) ('a' + code);

    }

    //----- méthodes publiques -------------------------------------------------
    /**
     * ajoute une lettre à l'ensemble
     *
     * @param c la lettre à ajouter à l'ensemble (si c n'est pas une lettre
     * minuscule de l'alphabet cette méthode est sans effet).
     */
    public final void ajouter(char c) {
        int code = toCode(c);
        if ((0 <= code) && (code < NB_LETTRES_ALPHABET)) {
            present[code] = true;
        }
    }

    /**
     * teste l'appartenance d'un caractère à l'ensemble
     *
     * @param c le caractère pour lequel l'appartenance est testée
     * @return true si c appartient à l'ensemble , false sinon
     */
    public boolean contient(char c) {
        int code = toCode(c);
        return (code >= 0 && code < present.length && present[code]);
    }

    /**
     * renvoie une représentation textuelle de cet ensemble. On redéfinit
     * (override) la méthode toString() standard qui renvoie le nom de la classe
     * de l'objet + son "adresse mémoire".
     *
     * @return une chaîne représentant l'ensemble. Par exemple, pour l'ensemble
     * contenant les lettres 'a' 'z', 'e', 'r' 't' 'y' la chaîne retournée sera
     * "{'a','e','r','t','y','z'}", pour l'ensemble vide la chaîne retournée
     * sera "{}".
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{"); // on préfère lorsque que
        // de nombreuses concaténations de chaînes sont effectuées, utiliser
        // un StringBuilder plutôt qu'un String ce qui se révèle beaucoup 
        // plus performant (voir notes de cours).
        boolean dejaElement = false;  // indique si la chaîne résultat contient ou non
        // déjà un élément. Si c'est le cas, il faudra
        // mettre une virgule entre cet élément et le
        // nouvel élément à ajouter.
        for (int i = 0; i < present.length; i++) {
            if (present[i]) {
                if (dejaElement) {
                    res.append(",");
                    res.append(toChar(i));
                } else {
                    res.append(toChar(i));
                    dejaElement = true;
                }
            }
        }
        res.append("}");
        return res.toString();    // convertit l'objet StringBuilder en objet String
    }

    /**
     * Donne le cardinal (nombre d'éléments) de l'ensemble.
     *
     * @return le cardinal de l'ensemble
     */
    public int cardinal() {
        int c = 0;
        // on utilise une boucle foreach
        for (boolean b : present) { // pour chaque élément b du tableau présent
            if (b) {
                c++;   // si b vaut vrai, une lettre est présente, on la compte.
            }
        }
        return c;
//
// avec une boucle for classique cela aurait donné :
//        
//        for (int i = 0; i < present.length; i++) {
//            if (present[i]) {
//                c++;
//            }
//        }
//        return c;
//
// avec une boucle while, je vous en fait cadeau !!! je n'ai pas envie de réfléchir ;-)
    }

    /**
     * Indique si l'ensemble de lettres est vide ou non.
     *
     * @return true si l'ensemble est vide, false sinon
     */
    public boolean estVide() {
        for (boolean b : present) {
            if (b) {   // il y a un booléen à true dans le tableau
                return false;  // donc l'ensemble n'est pas vide
                // inutile d'aller plus loin, on sort de la fonction avec la
                // valeur faux et on retourne au programme appelant.
            }
        }
        // on est sorti de la boucle, on a donc parcouru tout le tableau
        // et aucun n'élément n'a la valeur true : l'ensemble est vide
        return true;
//      
//      même algo avec une boucle for "classique"
//        for (int i = 0; i < present.length; i++) {
//            if (present[i]) { // present[i] == true
//                return false;  // donc l'ensemble n'est pas vide
//            }		       // inutile d'aller plus loin
//        }
//        return true;
    }

    /**
     * Teste l'inclusion de cet ensemble (this) dans un autre ensemble (e).
     *
     * @param e l'ensemble par rapport auquel on teste l'inclusion
     * @return true si l'ensemble est inclus dans e, false sinon
     */
    public boolean estInclus(EnsembleDeLettres1 e) {
        for (int i = 0; i < present.length; i++) {
            if (this.present[i] && !e.present[i]) {
                return false; // l'élément i est présent dans l'ensemble this
            }		      // mais pas dans l'ensemble e, donc this n'est
        }		      // pas inclus dans e.

        // on a pacouru tout l'ensemble (this) et tous les éléments de
        // celui-ci sont aussi éléments de e, donc this est inclus dans e
        return true;
    }

    // pour l'intersection trois algorithmes sont proposés cela nous permettra
    // de discuter performances, et de la bonne utilisation des chaînes de
    // caractères en Java.
    
    /**
     * Construit l'intersection de cet ensemble avec un autre ensemble
     * l’algorithme travaille sur des chaînes de caractères. Il construit une
     * chaîne contenant toute les lettres présentes dans l'ensemble
     * intersection2 et crée ensuite l'ensemble résultat à partir de cette
     * chaîne. L'algorithme utilise uniquement des objets de type String pour
     * construire cette chaîne.
     *
     * @param e l'ensemble par rapport auquel on calcule l'intersection
     * @return l'ensemble correspondant à l'intersection de cet ensemble (this)
     * et de l'ensemble e.
     */
    public EnsembleDeLettres1 intersection1(EnsembleDeLettres1 e) {
        String inter = "";
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans l'intersection si il est dans this ET dans e
            if (this.present[i] && e.present[i]) {
                inter += toChar(i);
            }
        }
        return new EnsembleDeLettres1(inter);
    }

    /**
     * Construit l'intersection de cet ensemble avec un autre ensemble
     * l’algorithme travaille sur des chaînes de caractères. Il construit une
     * chaîne contenant toute les lettres présentes dans l'ensemble intersection
     * et crée ensuite l'ensemble résultat à partir de cette chaîne.
     * 
     * L'algorithme utilise ici un de type StringBuilder pour construire
     * cette chaîne (contrairement à l'algorithme d'intersection1 qui lui est
     * basé sur des objets de type String).
     *
     * @param e l'ensemble par rapport auquel on calcule l'intersection
     * @return l'ensemble correspondant à l'intersection de cet ensemble (this)
     * et de l'ensemble e.
     */
    public EnsembleDeLettres1 intersection2(EnsembleDeLettres1 e) {
        StringBuilder inter = new StringBuilder();
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans l'intersection si il est dans this ET dans e
            if (this.present[i] && e.present[i]) {
                inter.append(toChar(i));
            }
        }
        return new EnsembleDeLettres1(inter.toString());
    }

        /**
     * Construit l'intersection de cet ensemble avec un autre ensemble
     * l’algorithme travaille uniquement sur le tableau de booleens.
     *
     * @param e l'ensemble par rapport auquel on calcule l'intersection
     * @return l'ensemble correspondant à l'intersection de cet ensemble (this)
     * et de l'ensemble e.
     */
    public EnsembleDeLettres1 intersection3(EnsembleDeLettres1 e) {
        EnsembleDeLettres1 res = new EnsembleDeLettres1(); // res == ens vide
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans l'intersection si il est dans this ET dans e
            res.present[i] = this.present[i] && e.present[i];
        }
        return res;
    }
    
    /**
     * Construit l'union de cet ensemble avec un autre ensemble
     *
     * @param e l'ensemble avec lequel on calcule l'union
     * @return l'ensemble correspondant à l'union de cet ensemble (this) et de
     * l'ensemble e.
     */
    public EnsembleDeLettres1 union(EnsembleDeLettres1 e) {
        EnsembleDeLettres1 res = new EnsembleDeLettres1(); // res == ens vide
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans l'union si il est dans this OU dans e
            res.present[i] = this.present[i] || e.present[i];
        }
        return res;
    }

    /**
     * Construit la difference de cet ensemble avec un autre ensemble
     *
     * @param e l'ensemble avec lequel on calcule la différence
     * @return l'ensemble correspondant à la différence de cet ensemble (this)
     * et de l'ensemble e.
     */
    public EnsembleDeLettres1 difference(EnsembleDeLettres1 e) {
        EnsembleDeLettres1 res = new EnsembleDeLettres1(); // res == ens vide
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans dans la difference de this avec e
            // si il est dans this ET n'est pas dans e
            res.present[i] = this.present[i] && !e.present[i];
        }
        return res;
    }

    /**
     * Construit l'union disjointe de cet ensemble avec un autre ensemble
     *
     * @param e l'ensemble avec lequel on calcule l'union disjointe
     * @return l'ensemble correspondant à l'unon disjointe de cet ensemble
     * (this) et de l'ensemble e (c'est à dire l'ensemble contenant les élements
     * de l'union de this avec e - les élements de l'intersection de this avec
     * e).
     */
    public EnsembleDeLettres1 unionDisjointe(EnsembleDeLettres1 e) {

        // une manière de l'écrire tiendrait en une ligne et serait
        //
        // return this.union(e).difference(this.intersection2(e));
        //
        // une autre manière de l'écrire qui évite de créer deux ensembles
        // intermédiaires (l'intersection puis la difference) est la suivante
        EnsembleDeLettres1 res = new EnsembleDeLettres1(); // res == ens vide
        for (int i = 0; i < present.length; i++) {
            // l'element i est dans dans l'union disjointe de this avec e
            // si il est dans this ET n'est pas dans e OU si il est dans e et
            // n'est pas dans this
            res.present[i] = this.present[i] && !e.present[i]
                    || !this.present[i] && e.present[i];
        }
        return res;
    }

    // pour pouvoir tester l'égalité d'un ensemble avec un autre ensemble, il
    // convient de redéfinir la méthode  public boolean equals(Object obj)
    // qui est la méthode standard utilisée en Java pour vérifier l'égalité
    // de deux objets.
    //
    // En particulier, la méthod assertEquals(Object expectedObj, Object actualObj) 
    // de JUnit, teste l'égalité des deux objets via le message (apple de méthode)
    // expectedObj.equals(actualObj);
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnsembleDeLettres1)) {
            return false;
        }
        //une alternative à la ligne ci-dessus pourrait être
        //if ( obj == null || obj.getClass() != this.getClass() ) return false;

        // transtyper (caster) la référence obj en une référence de type EnsembleDeLettres1
        // est à ce stade une opération sûre.
        EnsembleDeLettres1 e = (EnsembleDeLettres1) obj;

        return Arrays.equals(present, e.present);
    }

    // 
    // pour des raisons de cohérence, en particulier parcqu'elles sont utilisées
    // de manière conjointe dans les collections (package java.util, en particulier pour
    // les tables associatives (Maps)), il convient en général de définir équals
    // conjointement avec la méthode hashCode.
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Arrays.hashCode(this.present);
        return hash;
    }

}
