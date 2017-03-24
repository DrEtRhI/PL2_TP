package m2pcci.reservationsSalles.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class Reservation {
    
    private final int noReservation;
    private final int noSalle;
    private final String typeSalle;
    private final Date dateResa;
    private final boolean matin;
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

    public Reservation(int noReservation,int noSalle, String typeSalle, Date dateResa, boolean matin) {
        this.noReservation = noReservation;
        this.noSalle = noSalle;
        this.typeSalle = typeSalle;
        this.dateResa = dateResa;
        this.matin = matin;
    }

    public int getNoSalle() {
        return noSalle;
    }

    public String getTypeSalle() {
        return typeSalle;
    }

    public Date getDateResa() {
        return dateResa;
    }

    public boolean isMatin() {
        return matin;
    }

    public int getNoReservation() {
        return noReservation;
    }

    
    @Override
    public String toString() {
        return "" + noSalle + " " + typeSalle + " " + getDateAsString() 
                + " " + getMomentAsString();
    }

    /**
     * renvoie la date de la réservation sous la forme d'une chaîne 
     * au format jj/mm/aaaa
     * @return la date au format jj/mm/aaaa
     */
    public String getDateAsString() {
        return dateFormat.format(dateResa);
    }
    
    /**
     * renvoie le moment de la réservation sous la forme d'une chaîne ("Matin"
     * ou "Après-midi")
     * @return la chaîne indiquant le moment de la réservation.
     */
    public String getMomentAsString() {
        return ((matin)?"Matin":"Après-midi");
    }
    
    /**
     * indique si la réservation est passée ou non (c'est à dire si la
     * réservation est avant ou après la date courante)
     * @return true si la réservation est après la date courante
     *         false sinon.
     */
    public boolean nestPasPassee() {
        return dateResa.compareTo(new Date()) > 0;
    }
}
