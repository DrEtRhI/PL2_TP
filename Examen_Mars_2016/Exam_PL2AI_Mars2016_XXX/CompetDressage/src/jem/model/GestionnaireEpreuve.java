package jem.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;
import javax.security.auth.login.FailedLoginException;

/**
 * Gère une épreuve de dressage equestre. Cette classe permet d'enreigistrer les
 * notes des cavaliers engagés dans l'épreuve au fur et à mesure de leur
 * passage, et de produire à tout moment un classement des cavaliers ayant
 * terminé leur reprise.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class GestionnaireEpreuve {

    /**
     * La file d'attente des cavaliers qui ne sont pas encore entrés en piste.
     */
    private final Queue<Cavalier> cavaliersEnAttente;

    /**
     * le cavalier actuellement en piste
     */
    private Cavalier cavalierEnPiste = null;

    /**
     * le résultat associé au cavalier en piste
     */
    private Resultat resultatCavalierEnPiste;

    /**
     * l'ensemble trié par ordre croissant des résultats de l'épreuve. Si
     * l'épreuve n'est pas terminée, il s'agit du classement provisoire.
     */
    private final TreeSet<Resultat> classement;

    /**
     * la liste des juges
     */
    private final List<Juge> juges;

    /**
     * Constructeur d'un gestionnaire pour une épreuve donnée.
     *
     * @param juges les juges pour l'épreuve.
     * @param engages la liste des cavaliers engagés de l'épreuve.
     */
    public GestionnaireEpreuve(List<Juge> juges, List<Cavalier> engages) {
        this.juges = new ArrayList<>(juges);
        this.cavaliersEnAttente = new LinkedList<>(engages);
        classement = new TreeSet<>();
    }

    /**
     * Le nombre de juges attributant des notes pour cette épreuve
     *
     * @return le nombre de juges
     */
    public int getNbJuges() {
        return juges.size();
    }

    /**
     * @return le cavalier actuellement sur la piste
     */
    public Cavalier getCavalierEnPiste() {
        return cavalierEnPiste;
    }

    /**
     * Retourne le résultat du cavalier actuellement en piste. Ce résultat peut
     * être incomplet si les juges n'ont pas encore tous attribué une note.
     *
     * @return le résultat du cavalier en piste, null si il n'y a pas de
     * cavalier en piste.
     */
    public Resultat getResultatCavalierEnPiste() {
        return resultatCavalierEnPiste;
    }

    /**
     * Renvoie la classement (provisoire) du cavalier en piste.
     *
     * @return le rang dans le classement du cavalier en piste. -1 si le
     * résultat n'a pas encore été enregistré.
     */
    public int getClassementCavalierEnPiste() {

        if (resultatCavalierEnPiste.estComplet()) {
            // le résultat est complet, il a été intégré au classement
            int position = 1;
            for (Resultat res : classement.descendingSet()) {
                if (res.getCavalier() == getCavalierEnPiste()) {
                    return position;
                }
                position++;
            }
            return position;
        } else {
            return -1;
        }

    }

    /**
     * Permet de faire rentrer en piste le prochain cavalier en attente.
     *
     * @return la référence du cavalier qui vient de rentrer en piste. null si
     * il n'y a plus de concurrent en attente.
     *
     * @throws ResultatsIncompletException si il y a déjà un cavalier en piste
     * et que celui-ci n'a pas toutes ses notes
     */
    public Cavalier cavalierSuivant() {
        if (cavalierEnPiste != null && !resultatCavalierEnPiste.estComplet()) {
            throw new ResultatsIncompletException("le cavalier en piste n'a pas encore toutes ses notes");
        }
        if (!cavaliersEnAttente.isEmpty()) {
            this.cavalierEnPiste = cavaliersEnAttente.remove();
            this.resultatCavalierEnPiste = new Resultat(this.cavalierEnPiste, getNbJuges());
            return cavalierEnPiste;
        }
        return null;
    }

    /**
     * Retourne le classement sous la forme d'un tableau. Il s'agit du
     * classement en l'état au moment de l'appel de cette méthode: si l'épreuve
     * n'est pas terminée c'est donc un classement provisoire. Le premier
     * élément du tableau correspond au résultat du cavalier classé premier, le
     * second élément du tableau correspond au résultat du cavalier classé
     * deuxième, etc.
     *
     * @return les tableau trié dans l'ordre décroissant des résultats de
     * cavaliers ayant terminé leur reprise.
     */
    public Resultat[] getClassement() {
        return classement.descendingSet().toArray(new Resultat[0]);
    }

    /**
     * enregistre la note d'un juge pour le cavalier en piste. Si le résultat du
     * cavalier en piste est complet (c'est à dire si tous les autres juges ont
     * déjà donné leur note pour ce cavalier) ce résultat est inséré dans le
     * classement.
     *
     * @param noJuge le numero du juge attribuant la note (un entier entre 1 et
     * nbJuges).
     * @param note la note attribuée par ce juge (un entier entre 0 et 100)
     */
    public void enregistrerNote(int noJuge, double note) {
        resultatCavalierEnPiste.enregistrerNote(noJuge, note);
        if (resultatCavalierEnPiste.estComplet()) {
            classement.add(resultatCavalierEnPiste);
            // comme classement est un ensemble trié, et Resultat implémente
            // l'interface Comparable, le resultat est rangé automatiquement 
            // à la bonne position dans le classement.
        }
    }

    /**
     * teste si l'épreuve est terminée (tous les cavaliers sont passés en piste
     * et ont été notés).
     *
     * @return true si l'épreuve est terminée, false sinon.
     */
    public boolean epreuveTerminee() {
        return cavaliersEnAttente.isEmpty()
                && ((cavalierEnPiste != null) && resultatCavalierEnPiste.estComplet());
    }

    /**
     * retourne la description d'un des juges de l'épreuve de dressage
     * @param noJuge le numéro du juge (1 à 7)
     * @return l'objet représentant ce juge.
     */
    public Juge getJuge(int noJuge) {
        return juges.get(noJuge - 1);
    }

    /**
     * permet d'authentifier un juge de l'épreuve de dressage. Une fois authentifié
     * le juge est marqué comme étant connecté au gestionnaire d'épreuve.
     * @param no le numéro du juge (1 à 7)
     * @param passwd le mot de passe de ce juge
     * @return l'objet représentant ce juge si il le mot de passe est le bon.
     * @throws FailedLoginException si le mot de passe est incorrect, ou si il
     *        n'y a pas de juge correspondant au numéro ou si le juge est déjà
     *        connecté au gestionnaire d'épreuve.
     */
    public synchronized Juge authentifier(int no, String passwd)
            throws FailedLoginException {
        if (no < 1 || no > getNbJuges()) {
             throw new FailedLoginException("n° de juge incorrect");
        }
        Juge juge = getJuge(no);
        if (juge.isConnecte()) {
            throw new FailedLoginException("juge " + no + " déjà connecté");
        }
        if (! passwd.equals(juge.getPassword())) {
            throw new FailedLoginException("mot de passe incorrect");
        }
        juge.setConnecte(true);
        return juge;
    }

}
