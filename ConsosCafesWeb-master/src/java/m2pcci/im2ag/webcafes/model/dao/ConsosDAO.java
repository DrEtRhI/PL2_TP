package m2pcci.im2ag.webcafes.model.dao;

import im2ag.m2pcci.webcafes.model.ListeConsos;
import im2ag.m2pcci.webcafes.model.Programmeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * DAO : data Access Object - géré la persitance des modèles
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class ConsosDAO {

    /**
     * Retourne la liste des consommations pour une semaine donnée.
     * 
     * @param dataSource la source de données
     * @param noSem le numéro de la semaine
     * @return la lsite des consommationsdes la semaine, triée par ordre décroissant.
     * 
     * @throws SQLException si problème avec la BD.
     * 
     */
    public static ListeConsos getConsoParSemaine(DataSource dataSource, int noSem) throws SQLException {
        List<Programmeur> lesConsosDeLaSemaine = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {

            String query = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                    + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                    + " WHERE c.NO_SEMAINE=?  ORDER BY NB_TASSES DESC";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, noSem);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int nbTasses = rs.getInt("NB_TASSES");
                        String nom = rs.getString("NOM");
                        String prenom = rs.getString("PRENOM");
                        lesConsosDeLaSemaine.add(new Programmeur(nom, prenom, nbTasses));
                    }
                } //  try avec ressource , appelle rs.close() dans une clause finally
            } //  try avec ressource , appelle stmt.close() dans une clause finally
        } //  try avec ressource , appelle conn.close() dans une clause finally
        return new ListeConsos(noSem,lesConsosDeLaSemaine);
    }

}
