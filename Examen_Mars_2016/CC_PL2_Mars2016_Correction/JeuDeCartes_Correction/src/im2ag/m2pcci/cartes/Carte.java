package im2ag.m2pcci.cartes;

/**
 * Représente une carte à jouer. Une carte est définie par:
 *
 * - sa couleur (TREFLE, CARREAU, PIQUE ou COEUR)
 *
 * - son rang dans la couleur (le rang allant de 2 à 14, les cartes de rang 11,
 * 12, 13 et 14 correspondant respectivement au Valet (Jack en anglais), à la
 * reine (Queen), au roi (King) et à l'as (Ace)).
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Carte implements Comparable<Carte> {

    /**
     * le rang de la carte
     */
    private final int rang;

    /**
     * la couleur de la carte
     */
    private final Couleur couleur;

    /**
     * création d'une carte d'un rang donné dans une couleur donnée.
     *
     * @param rang le rang , un entier dans l'intervalle [2,14] (les cartes de
     * rang 11, 12, 13 et 14 correspondant respectivement au Valet (Jack en
     * anglais), à la reine (Queen), au roi (King) et à l'as (Ace))
     * @param couleur la couleur de la carte (TREFLE, CARREAU, PIQUE ou COEUR)
     */
    public Carte(int rang, Couleur couleur) {
        if (rang < 2 || rang > 14) {
            throw new IllegalArgumentException("le rang d'une carte doit être dans l'intervalle [2..14]");
        }
        this.couleur = couleur;
        this.rang = rang;
    }

    /**
     * @return le rang de la carte
     */
    public int getRang() {
        return rang;
    }

    /**
     * @return la couleur de la carte
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * compare cette carte avec une autre carte. La comparaison s'effectue
     * d'abord sur la couleur (l'ordre croissant étant TREFLE, CARREAU, PIQUE et
     * COEUR) et ensuite sur le rang dans la couleur. Par exemple la carte Dame
     * de Carreau est plus petite que la carte As de Coeur.
     *
     * @param autreCarte la carte avec laquelle cette carte est comparée.
     * @return une nombre négatif si cette carte est plus petite que l'autre
     * carte, 0 si elle sont identiques, un nombre positif si cette carte est
     * plus grande que l'autre carte.
     */
    @Override
    public int compareTo(Carte autreCarte) {
        if (this.couleur != autreCarte.couleur) {
            return this.couleur.compareTo(autreCarte.couleur);
        } else {
            return this.rang - autreCarte.rang;
        }
    }

    /**
     * renvoie une chaine de caractères indiquant le rang et la couleur de la
     * carte. Le rang est un chifre 2, 3 .... 10 ou une lettre V (Valet), D
     * (Dame), R (Roi) A (as). La couleur est le caractère correspondant au
     * symbole du Trefle, Carreau, Pique ou Coeur.
     *
     * @return la chaine de caractère représentant la carte.
     */
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        // d'abord le rang
        switch (rang) {
            case 11:
                res.append('V');  // valet (Jack)
                break;
            case 12:
                res.append('D'); // Dame (Queen)
                break;
            case 13:
                res.append('R');  // Roi (King)
                break;
            case 14:
                res.append('A');  // As (Ace)
                break;
            default:
                res.append(rang); // carte de rang 2 à 10
                break;
        }
        //ensuite la couleur
        res.append(couleur);

        return res.toString();
    }

    //  un petit programme de test pour vérifier que les cartes s'affiche
    //  correctement
    public static void main(String[] args) {
        Carte carte = new Carte(7, Couleur.COEUR);  // 7 de coeur
        System.out.println(carte);
        carte = new Carte(11, Couleur.CARREAU); // Valet de carreau 
        System.out.println(carte);
        carte = new Carte(12, Couleur.PIQUE);  // Dame de pique
        System.out.println(carte);
        carte = new Carte(13, Couleur.TREFLE);  // Roi de trèfle
        System.out.println(carte);
        carte = new Carte(14, Couleur.COEUR);  // As de coeur
        System.out.println(carte);
    }

}
