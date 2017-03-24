package im2ag.m2pcci.cartes;

/**
 * Type énuméré definissant les couleurs possibles pour les cartes.
 * A savoir TREFLE (CLUB en anglais), CARREAU (DIAMMOND), PIQUE (SPADE) et COEUR (HEART).
 * 
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
public enum Couleur {
     
    // les valeurs possibles pour les couleurs de carte
    TREFLE, CARREAU, PIQUE, COEUR;

    /**
     * renvoie le caractère spécial correspondant au symbole de la couleur
     * @return le symbole de la couleur
     */
    @Override
    public String toString() {
        switch (this) {
            case PIQUE:
                return "" + ((char) '\u2660');
            case CARREAU:
                return "" + ((char) '\u2666');
            case TREFLE:
                return "" + ((char) '\u2663');
            case COEUR:
            default:
                return "" + ((char) '\u2764');
        }
    }

}
