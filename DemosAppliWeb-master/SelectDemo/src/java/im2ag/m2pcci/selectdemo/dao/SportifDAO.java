package im2ag.m2pcci.selectdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Méthodes d'accès aux données stockées dans la table Pays
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class SportifDAO {

    private static final String LISTE_SPORTIFS
            = "SELECT ID_SPORTIF, CONCAT(NOM ,CONCAT (' ',PRENOM)) FROM SPORTIFS WHERE Pays= ? and Sexe = ?";

    /**
     * Retourne le code HTML correspondant à une liste d'options pour les
     * différents sportifs d'un pays donné et d'un genre (Masculin ou Féminin) donné. 
     * 
     * Chaque ligne du résultat de la requêt SQL correspondante est retournée
     * sous la forme d'une option avec comme valeur l'ID du sportif
     * et comme contenu le nom sportif suivi de son prénom. 
     *
     *
     * @param ds la source de données pour l'accès à la Base de Données
     * @param pays l'id du pays par exemple "FR" pour la France
     * @param sexe le sexe des sportifs "M" masculin ou "F" Féminin
     * @return la liste des options
     * @throws SQLException si pb avec la BD
     */
    public static String listeSportifs(DataSource ds, String pays, String sexe) throws SQLException {
        try (Connection conn = ds.getConnection()) {

            // on préfère passer par un PreparedStatement pour éviter l'injection de code SQL
            // qui pose des problèmes de sécurité
            PreparedStatement stmt = conn.prepareStatement(LISTE_SPORTIFS);
            // substitution des paramètes pays et sexe dans le prepared statement
            stmt.setString(1, pays);
            stmt.setString(2, sexe);
            // exécution de la requête
            ResultSet rs = stmt.executeQuery();
            // construction de la liste d'options
            return DBUtilDAO.resultSet2OptionList(rs, ("M").equals(sexe) ? "un sportif" : "une sportive");
        }

    }

}
