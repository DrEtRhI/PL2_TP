package im2ag.m2pcci.selectdemo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * Méthodes d'accès aux données stockées dans la table Pays
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class PaysDAO {

    // tous les pays
    private static final String LISTE_PAYS = "SELECT ID,NOM FROM PAYS ORDER BY NOM";

    // pays pour lesquels il existe au moins un sportif
    private static final String LISTE_PAYS_AVEC_SPORTIF
            = "SELECT DISTINCT p.ID,p.NOM FROM PAYS p, SPORTIFS s WHERE p.ID = s.pays ORDER BY p.NOM";

    // pays pour lesquels il n'y a aucun sportif
    private static final String LISTE_PAYS_SANS_SPORTIF
            = "SELECT DISTINCT ID,p.NOM FROM PAYS p  WHERE NOT EXISTS (SELECT * FROM SPORTIFS s WHERE p.ID = s.pays) ORDER BY p.NOM";

    /**
     * Retourne le code HTML correspondant à une liste d'options pour les
     * différents pays contenus dans la table PAYS. Chaque entrée de la table
     * est retournée sous la forme d'une option avec comme valeur l'ID du pays
     * et comme contenu le nom du pays. Par exemple pour la France: &lt;option
     * value="FR"&gt;France&lt;/option&gt;
     *
     * @param ds la source de données pour l'accès à la Base de Données
     * @return la liste de options
     * @throws SQLException si pb avec la BD.
     */
    public static String listePays(DataSource ds) throws SQLException {
        try (Connection conn = ds.getConnection()) {

            Statement stmt = conn.createStatement();
            // exécution de la requête
            ResultSet rs = stmt.executeQuery(LISTE_PAYS);
            // construction de la liste d'options
            return DBUtilDAO.resultSet2OptionList(rs, "un pays");
        }
    }

    /**
     * Retourne le code HTML correspondant à une liste d'options pour les
     * différents pays contenus dans la table PAYS en tenant compte si il s'agit
     * des pays pour lesquels il existe au moins un sportif ou si il s'agit
     * des pays sans aucun sportif.
     * Chaque entrée de la table PAYS correspondant à ce critère de sélection
     * est retournée sous la forme d'une option avec comme valeur l'ID du pays
     * et comme contenu le nom du pays. Par exemple pour la France: &lt;option
     * value="FR"&gt;France&lt;/option&gt;
     *
     * @param ds la source de données pour l'accès à la Base de Données
     * @param avecSportifs peremt de choisir entre la liste des pays avec sportif
     * et la liste des pays sans sportif
     * @return la liste de options
     * @throws SQLException si pb avec la BD.
     */
    public static String listePaysSportif(DataSource ds, boolean avecSportifs) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            // exécution de la requête
            ResultSet rs = stmt.executeQuery((avecSportifs)?LISTE_PAYS_AVEC_SPORTIF:LISTE_PAYS_SANS_SPORTIF);
            // construction de la liste d'options
            return DBUtilDAO.resultSet2OptionList(rs, "un pays");
        }
    }
}
