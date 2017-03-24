package im2ag.m2pcci.maildemo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Objet java représentant ue épreuve sportive.
 * La vocation de cette classe est simplement de permettre le transfert de données
 * depuis la base de données (SQL) vers le monde java. On parle parfois de DTO
 * (Data Transfert Object) pour de telles classes dans une application web java.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Epreuve {

    /**
     * nom de l'épreuve
     */
    private final String nom;
    /**
     * date de l'épreuve
     */
    private final Date date;
    /**
     * prix unitaire d'un ticket pour l'épreuve
     */
    private final double prixUnitaire;

    /**
     * Permet de définir une épréuve en donnant son nom, sa date et son horaire.
     * 
     * @param nom    le nom de l'épreuve
     * @param jour   le jour de l'épreuve
     * @param mois   le mois de l'épreuve
     * @param année  l'année de l'épreuve
     * @param heure  l'heure de l'épreuve
     * @param min    les minutes
     * @param prixU  prix unitaire d'un ticket
     * 
     */
    public Epreuve(String nom, int jour, int mois, int année, int heure, int min, double prixU) {
        this.nom = nom;
        GregorianCalendar cal = new GregorianCalendar(année, mois - 1, jour, heure, min);
        this.date = cal.getTime();
        this.prixUnitaire = prixU;
    }

    /**
     * le nom de l'épreuve.
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * la date de l'épreuve.
     * @return la date
     */
    public Date getDate() {
        return date;
    }

    /**
     * la date 'formatée' de l'épreuve. par exemple pour une épreuve dont la date
     * est 15/03/2016 à 10h, la valeur retournée sera :
     * mardi 15 mars 2016 à 10:00
     * @return la date formatée, 
     */
    public String getFormatedDate() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE d MMMM Y 'à' HH:mm");
        return format.format(date);
    }

    /**
     * le rpix unitaire d'un ticket pour cette épreuve
     * @return prix unitaire
     */
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    
    
}
