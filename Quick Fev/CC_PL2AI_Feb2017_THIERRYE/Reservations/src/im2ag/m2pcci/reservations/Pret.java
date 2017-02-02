package im2ag.m2pcci.reservations;

import java.util.Date;

/**
 * 
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class Pret {

    /**
     * date de debut du prêt
     */
    private Date dateDebut;

    /**
     * date de fin du prêt
     */
    private Date dateFin;

    /**
     * l'employé concerné
     */
    private final Employe employe;
    
    /**
     * le materiel concerné
     */
    private final Materiel materiel;

    /**
     * crée un pret 
     * @param dateDebut la date de début du prêt.
     * @param dateFin la date de fin du prêt.
     * @param employe l'employé concerné par ce prêt.
     * @param materiel le matériel emprunté.
     */
    public Pret(Date dateDebut, Date dateFin, Employe employe, Materiel materiel) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.employe = employe;
        this.materiel = materiel;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Employe getEmploye() {
        return employe;
    }


    public Materiel getMateriel() {
        return materiel;
    }
   
    
    
}
