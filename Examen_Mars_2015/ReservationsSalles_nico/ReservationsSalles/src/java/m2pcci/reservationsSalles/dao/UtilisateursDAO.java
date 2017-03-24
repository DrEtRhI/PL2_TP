/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.reservationsSalles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import m2pcci.reservationsSalles.model.Utilisateur;

/**
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class UtilisateursDAO {

    /**
     * requête SQL pour récupérer les informations d'un utilisateur à partir de
     * son identifiant.
     */
    private static final String GET_UTILISATEUR_QUERY
            = "SELECT * FROM COMPTES_UTILISATEURS "
            + " WHERE ID_UTILISATEUR=?";

    /**
     * Verifie les informations d'authentification (identifiant et mot de passe)
     * d'un utilisateur, et si l'authentification est correcte retourne un objet
     * Utilisateur correspondant à l'utilisateur authentifié. Si l'identifiant
     * n'est pas connu dans la table COMPTES_UTILISATEUR cette méthode retourne
     * la valeur null. Si l'identifiant est connu mais le mot de passe est
     * incorrect une exception de type MotPasseIncorrectException est levée.
     *
     * @param ds la datasource pour la base de données des utilisateurs
     * @param identifiant l'identifiant de l'utilisateur à récupérer
     * @param motPasse le mot de passe proposé pour l'authentification
     *
     * @return un objet Utilisateur regroupant les informations associées à
     * l'utilisateur (identifiant, nom, prenom…) si l'identifiant existe dans la
     * la table COMPTES_UTILISATEURS et si le mot de passe est correct. Renvoie
     * null si l'identifiant (l'utilsiateur) n'existe pas dans la table
     * COMPTES_UTILISATEUR.
     *
     * @throws MotPasseIncorrectException si l'identifiant existe mais que le
     * mot de passe n'est pas correct
     *
     * @throws SQLException si un problème BD (JDBC) est intervenu lors de
     * l'accès aux données de l'utilisateur
     */
    public static Utilisateur authentifier(DataSource ds, String identifiant,
            String motPasse) throws MotPasseIncorrectException, SQLException {

        Utilisateur user = null;
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(GET_UTILISATEUR_QUERY)) {
                pstmt.setString(1, identifiant);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String mdp = (String) rs.getString("PASSWORD");
                        if (mdp.equals(motPasse)) {
                            user = new Utilisateur(rs.getString("ID_UTILISATEUR"),
                                    rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("EMAIL"));
                        } else {
                            throw new MotPasseIncorrectException();
                        }
                    }
                }
            }
        }
        return user;
    }

}
