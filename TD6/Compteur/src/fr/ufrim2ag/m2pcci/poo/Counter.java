package fr.ufrim2ag.m2pcci.poo;

/**
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 *
 * Définit un compteur entier avec des opérations permettant de l'incrémenter
 * et de le décrémenter. Ce classe a pour but de permettre aux étudiants
 * d'expérimenter avec JUnit.
 */
public class Counter {

    /**
     * la valeur du compteur
     */
    private int count;

    /**
     * crée un compteur initialisé à 0
     */
    public Counter() {
        count = 0;
    }

    /**
     * crée un compteur en spécifiant une valeur initiale
     *
     * @param c la valeur initiale du compteur
     */
    public Counter(int c) {
        count = c;
    }

    /**
     * incremente le compteur et retourne sa nouvelle valeur.
     *
     * @return la nouvelle valeur du compteur après incrémentation
     */
    public int increment() {
        return ++count;
    }

    /**
     * décrémente le compteur et retourne sa nouvelle valeur
     *
     * @return la nouvelle valeur du compteur après décrémentation
     */
    public int decrement() {
        return --count;
    }

    /**
     * retourne la valeur du compteur
     *
     * @return la valeur du compteur
     */
    public int getCount() {
        return count;
    }

    /**
     * Création d'un nouveau compteur dont la valeur est la somme de la valeur
     * de ce compteur avec celle du compteur passé en paramètre
     *
     * @param c le compteur à ajouter à ce compteur
     * @return le compteur créé
     */
    public Counter add(Counter c) {
        return new Counter(this.count + c.count);

    }

    /**
     * Création d'un nouveau compteur dont la valeur est la différence entre la
     * valeur de ce compteur et celle du compteur passé en paramètre
     *
     * @param c le compteur à soustraire à ce compteur
     * @return le compteur créé
     */
    public Counter sub(Counter c) {
        return new Counter(this.count - c.count);

    }

}
