/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.reservationsSalles.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import m2pcci.reservationsSalles.model.Reservation;

/**
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class ReservationsDAO {

    /**
     * requête SQL pour récupérer toutes les réservations effectuées par un
     * utilisateur donnée. Celles-ci sont triées par ordre de date croissant.
     */
    private static final String GET_RESERVATIONS_QUERY
            = "SELECT NO_RESA, NO_SALLE, TYPE_SALLE, DATE_RESA, MATIN FROM SALLES "
            + " NATURAL JOIN RESA WHERE ID_UTILISATEUR='%s' ORDER BY DATE_RESA, MATIN";

    private static final String DELETE_RESA_QUERY
            = "DELETE FROM RESA WHERE NO_RESA = %d";

    /**
     * Construit la liste des réservation d'un utilisateur
     *
     * @param ds la data source pour effectuer les connexions à la BD
     * @param id_utilisateur l'identifiant de l'utilisateur
     * @return la liste des réservations (une liste vide si il n'y a aucune
     * rééservation pour cet utilisateur).
     * @throws SQLException si un problème BD (JDBC) est intervenu lors de
     * l'accès aux réservations de l'utilisateur
     */
    public static List<Reservation> reservations(DataSource ds, String id_utilisateur)
            throws SQLException {
        try (Connection c = ds.getConnection()) {
            Statement stmt = c.createStatement();
            String query = String.format(GET_RESERVATIONS_QUERY, id_utilisateur);
            ResultSet rs = stmt.executeQuery(query);
            List<Reservation> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new Reservation(rs.getInt("NO_RESA"), rs.getInt("NO_SALLE"), rs.getString("TYPE_SALLE"),
                        rs.getDate("DATE_RESA"), rs.getBoolean("MATIN")));
            }
            return res;
        }
    }

    /**
     * Supprime de la table RESA une réservation
     * @param ds la datasource pour effectuer la connexion à la BD
     * @param noResa la reservation à supprimer de la la table RESA
     * @throws SQLException si un problème BD (JDBC) est intervenu lors de
     * la suppression de la reservation
     */
    public static void reservationsSuppression(DataSource ds, int noResa)
            throws SQLException {
        try (Connection c = ds.getConnection()) {
            Statement stmt = c.createStatement();
            String query = String.format(DELETE_RESA_QUERY, noResa);
            try{
                c.setAutoCommit(false);
            stmt.executeUpdate(query);
            c.commit();
            }catch (SQLException ex){
                c.rollback();
                throw ex;
            }finally{
                c.setAutoCommit(true);
            }
        }
    }
}
