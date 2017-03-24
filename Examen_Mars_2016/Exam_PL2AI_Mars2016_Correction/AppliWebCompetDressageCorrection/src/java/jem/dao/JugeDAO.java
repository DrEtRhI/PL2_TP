/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import jem.model.Juge;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class JugeDAO {

    /**
     * requête SQL pour récupérer les informations d'un utilisateur à partir de
     * son identifiant.
     */
    private static final String GET_JUGE_QUERY
            = "SELECT * FROM JUGES WHERE NO_JUGE = ?";
    
    private static final String GET_JUGES
            = "SELECT * FROM JUGES ORDER BY NO_JUGE ASC";

    /**
     * Vérifie que les informations d'authentification fournies pour un juges
     * sont correctes. 
     *
     * @param ds
     * @param id l'identifiant de connexion
     * @param passwd le mot de passe proposé
     * @return L'objet Juge correspondant à cet identifiant si le proposé est le
     * bon
     * @throws FailedLoginException l'authentification a échoué. Le message
     * d'erreur associé à cette exceptio doit indiquer la cause de l'échec :
     * identifiant est inconnu ou mot de passe incorrect.
     * @throws java.sql.SQLException en cas d'erreur avec la base de données.
     */
    public static Juge authentifier(DataSource ds, int no, String passwd)
            throws FailedLoginException, SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(GET_JUGE_QUERY);
            stmt.setInt(1, no);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (!passwd.equals(rs.getString("PASSWORD"))) {
                    throw new FailedLoginException("mot de passe incorrect");
                }
                return new Juge(no, rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("PAYS"),
                        rs.getString("PASSWORD"));
            }
            throw new FailedLoginException("identifiant inconnu");
        }
    }
    
    /**
     * retouren ela lsite des juges enregistrés dans la BD
     * @param ds la datasource pour se connecter
     * @return la liste des juges
     * @throws SQLException si pb avec la BD.
     */
    public static List<Juge> listeDesJuges(DataSource ds) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_JUGES);
            List<Juge> lesJuges = new ArrayList<>();
            while (rs.next()) {
                lesJuges.add(new Juge(rs.getInt("NO_JUGE"), rs.getString("NOM"), 
                        rs.getString("PRENOM"), rs.getString("PAYS"),
                        rs.getString("PASSWORD")));
            }
            return lesJuges;
        }
    }
}
