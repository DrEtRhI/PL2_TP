package m2pcci.im2ag.webcafes.model.dao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * <p>
 * Cette clase propose au travers de méthodes statiques des services pour manipuler
 * des tables de la base de données.
 * 
 * -  exporter/importer des tables vers/depuis un fichier texte au format CSV.
 * 
 * - effacer le contenu d'une table
 * </p>
 * <p>
 * Le format des fichiers CSV est le suivant :
 * <p>
 * <ul>
 * <li>
 * La première ligne contient la description des colonnes de la table sous la
 * forme <em>NomDeLaColonne:TypeSQLDeLaColonne</em> séparées par des virgules.
 * Par exemple pour la table PROGRAMMEURS on aura :
 * <pre>
 * ID:NUMBER,NOM:VARCHAR2,PRENOM:VARCHAR2,BUREAU:NUMBER
 * </pre>
 * </li>
 * <li>
 * Les lignes suivantes contiennent les valeurs des enregistrements présents
 * dans la table, valeurs séparées par des virgules. Par exemple un
 * enregistrement dans la table PROGRAMMEURS sera écrit sous la forme :
 * <pre>
 * 3,FAUVET,MARIE-CHRISTINE,501
 * </pre>
 * </li>
 * </ul>
 *
 *
 * @author Philippe Genoud - UGA Université Grenoble-Alpes - Lab LIG-Steamer
 */
public class TableUtils {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * Exporte une table vers un fichier CSV
     *
     * @param ds la datasource pour obtenir une connexion connexion jdbc
     * @param tableName le nom de la table dans la base de données
     * @param filePath le nom du fichier vers lequel les données seront
     * exportées
     *
     * @throws IOException si un problème à lieu lors de l'écriture vers le
     * fichier
     * @throws SQLException si un problème a lieu pour la lecture des données de
     * la table
     */
    public static void exportTable(DataSource ds, String tableName, String filePath)
            throws
            IOException, SQLException {

        Path path = Paths.get(filePath);
        // try avec ressources (Java7+)
        // garantit que le fichier et le statement jdbc seront fermés
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING);
                Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            StringBuilder ligneEnTete = new StringBuilder();
            for (int i = 1; i <= numberOfColumns; i++) {
                ligneEnTete.append(rsmd.getColumnName(i));
                ligneEnTete.append(":");
                ligneEnTete.append(rsmd.getColumnTypeName(i));
                if (i != numberOfColumns) {
                    ligneEnTete.append(",");
                }
            }
            writer.write(ligneEnTete.toString());
            writer.newLine();

            StringBuilder ligneData = new StringBuilder();
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    ligneData.append(rs.getString(i));
                    if (i != numberOfColumns) {
                        ligneData.append(",");
                    }
                }
                writer.write(ligneData.toString());
                writer.newLine();
                // on efface le buffer ligneData
                ligneData.delete(0, ligneData.length());
            }
        }  // end try avec ressource
    }  // writeTable

    /**
     * Importe une table depuis un fichier CSV
     *
     * @param ds la datasource pour obtenir une connexion connexion jdbc
     * @param filePath le nom du fichier vers lequel les données seront
     * exportées
     * @param tableName le nom de la table dans la base de données
     * @throws IOException
     * @throws SQLException
     */
    public static void importTable(DataSource ds, String filePath, String tableName) throws
            IOException, SQLException {

        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING);
                Connection conn = ds.getConnection()) {
            // lecture de la ligne d'en tête (noms et types des colonnes
            String line = reader.readLine();
            String[] colDescriptions = line.split(",| |:");
            StringBuilder insertStatement = new StringBuilder("INSERT INTO ");
            StringBuilder values = new StringBuilder(" VALUES (");
            insertStatement.append(tableName).append(" (");
            for (int i = 0; i < colDescriptions.length; i += 2) {
                insertStatement.append(colDescriptions[i]);
                values.append("?");
                if (i < colDescriptions.length - 2) {
                    insertStatement.append(",");
                    values.append(",");
                }
            }
            values.append(")");
            insertStatement.append(") ").append(values);
            System.out.println("La req " + insertStatement);

            try (PreparedStatement pstmt = conn.prepareStatement(insertStatement.toString())) {
                conn.setAutoCommit(false);  // passage en mode transactionnel
                while ((line = reader.readLine()) != null) {
                    //process each line in some way
                    String[] colValues = line.split(",| ");
                    for (int i = 0; i < colDescriptions.length / 2; i++) {
                        switch (colDescriptions[i * 2 + 1]) {
                            case "VARCHAR2":
                            case "VARCHAR" :
                                pstmt.setString(i + 1, colValues[i]);
                                break;
                            case "NUMBER":
                            case "INTEGER":
                                pstmt.setInt(i + 1, Integer.parseInt(colValues[i]));
                                break;
                            default:
                                throw new SQLException("Type SQL non supporté " + colDescriptions[i * 2]);
                        }
                    }
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();   // annulation de la transaction
                throw ex;
            } finally {
                conn.setAutoCommit(true);  // rétablissement de la connexion en mode autocomit (non transactionnel)
            }
        }
    }
    
    /**
     * Efface le contenu d'une table
     * @param ds la source de données pour les connexions JDBC
     * @param tableName le nom de la table
     * @throws SQLException si il y a eu un problème
     */
    public static void eraseTable(DataSource ds,String tableName) throws SQLException {
        try (Connection conn = ds.getConnection(); 
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + tableName);
        }
    }
}
