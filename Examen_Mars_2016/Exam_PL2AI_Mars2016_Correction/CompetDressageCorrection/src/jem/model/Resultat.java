package jem.model;

/**
 * Cette classe permet de définir les résultats d'un cavalier à une epreuve de
 * dressage. Un résultat est constitué de N notes attribuées par N juges. Chaque
 * note est un nombre réél compris entre 0 et 100. La note finale étant la
 * moyenne des N notes. Le nombre de juges peut aller de 2 (pour les épreuves
 * amateurs) à 7 pour les épreuves internationales.
 * 
* @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Resultat implements Comparable<Resultat> {

    /**
     * les notes du cavlier pour chacun des juges
     */
    private final double[] notes;

    /**
     * le cavalier auquel est associé ce résultat
     */
    private final Cavalier cavalier;

    public Resultat(Cavalier c, int nbJuges) {
        notes = new double[nbJuges];
        for (int i = 0; i < nbJuges; i++) {
            notes[i] = -1;
        }
        this.cavalier = c;
    }

    /**
     * nombre de juges contribuant à ce résultat
     *
     * @return le nombre de juges
     */
    public int getNbJuges() {
        return notes.length;
    }

    /**
     * enregistre dans ce résultat la note pour un juge
     *
     * @param noJuge le numéro du juge donnant la note (entier de 1 à nbJuges())
     * @param note la note donné
     */
    void enregistrerNote(int noJuge, double note) {
        notes[noJuge - 1] = note;
    }

    /**
     * teste si le résultat est complet, c'est à dire si tous les juges ont
     * donné une note.
     *
     * @return true si le résultat est complet, false si il manque au moins une
     * note.
     */
    public boolean estComplet() {
        for (double note : notes) {
            if (note == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return le cavalier auquel est associé ce résultat
     */
    public Cavalier getCavalier() {
        return this.cavalier;
    }

    /**
     * retourne la note donnée par un juge
     *
     * @param noJuge le numéro du juge
     * @return la note donnée par ce juge (-1 si pas de note)
     */
    public double getNote(int noJuge) {
        return notes[noJuge - 1];
    }

    /**
     * calcule la note finale asscoiée à ce résultat, c'est à dire la moyenne
     * des notes de chacun des juges.
     *
     * @return la note finale
     *
     * @throws ResultatsIncompletException si le résultat est incomplet (il
     * manque au moins une note)
     */
    public double getNoteFinale() {
        if (!estComplet()) {
            throw new ResultatsIncompletException("le résultat est incomplet");
        }
        double noteFinale = 0;
        for (double note : notes) {
            noteFinale += note;
        }
        return noteFinale / getNbJuges();
    }

    /**
     * comprare ce résultat à un autre résultat.
     *
     * @param o l'autre résultat.
     * @return un nombre négatifsi ce résultat est plus petit que l'autre
     * résultat, 0 si il est égal, un nombre positif si il est plus grand.
     *
     * @throws ResultatsIncompletException si l'un des deux résultats est incomplet (il
     * manque au moins une note).
     */
    @Override
    public int compareTo(Resultat o) {
        return (int) (1000 * (this.getNoteFinale() - o.getNoteFinale()));
    }

}
