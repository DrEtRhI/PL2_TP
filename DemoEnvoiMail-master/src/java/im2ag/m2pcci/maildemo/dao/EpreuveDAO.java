package im2ag.m2pcci.maildemo.dao;

import im2ag.m2pcci.maildemo.model.Epreuve;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe 'simule' un DAO (Data Acces Object) pour accéder au données 
 * des épreuves sportives. Dans 'la vraie vie', ces données seraient
 * probablement stockées dans une base de données. Ici pour ces raison de 
 * simplicité elles sont définie en mémoire et mémorisées dans un tableau.
 * 
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class EpreuveDAO {
    
    // la 'table' des épreuves
    private static final Epreuve[] EPREUVES = {
        new Epreuve("Descente - Femmes",14,03,2016,10,0,35),
        new Epreuve("Descente - Hommes",15,03,2016,10,0,35),
        new Epreuve("Slalom géant - Femmes",17,03,2016,14,30,35),
        new Epreuve("Slalom géant - Hommes",18,03,2016,14,30,35),
        new Epreuve("Slalom spécial - Femmes",19,03,2016,20,00,40),
        new Epreuve("Slalom spécial - Hommes",20,03,2016,20,00,45),
    };
    
    /**
     * renvoie l'épreuve correspondant à id (numéro d'épreuve) donné
     * @param epreuveId le numéro de l'épreuve
     * @return l'objet Epreuve correspondant
     */
    public static Epreuve getEpreuve(int epreuveId) {
        return EPREUVES[epreuveId];
    }
    
    /**
     * renvoie la liste des épreuves
     * @return la liste de toutes les épreuves
     */
    public static List<Epreuve> getEpreuves() {
        return  Arrays.asList(EPREUVES);
    }

}
