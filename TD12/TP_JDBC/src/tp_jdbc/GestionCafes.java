/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AccessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programme de démonstration de JDBC. Permet de se connecter à une BD Oracle et
 * de tester différentes commandes SQL sur des tables représentant les
 * consommations de café de différents programmeurs.
 *
 * Les différentes opérations possibles sont : <ol> <li>Plus gros consommateurs
 * de café sur une semaine</li> <li>Nombre total de tasse pour un programmeur
 * donné</li> <li>La liste triées des consommations sur une semaine</li>
 * <li>Saisir les consommations de tous les programmeurs pour une semaine
 * donnée</li> <li>Exécuter une requête quelconque saisie au clavier et afficher
 * ses résultats</li> </ol>
 *
 *
 * @author Philippe.Genoud@imag.fr
 */
public class GestionCafes {

    /**
     * le scanner pour la saisie des données au clavier
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * la connexion jdbc utilisée pour effectuer les différentes requêtes
     */
    //private static Connection conn = null;
    /**
     * Affiche le ou les programmeurs ayant consommé le nombre maximum de café
     * en une semaine et leur consommation pour cette semaine.
     *
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void plusGrosConsommateurs(Connection conn) throws SQLException {

        int i = 1;
        System.out.println("Les plus gros consommateurs de cafés sont :");
        String myQuery = "SELECT PROGRAMMEUR, PRENOM, NOM, NB_TASSES, NO_SEMAINE"
                + " FROM CONSOS_CAFE c JOIN PROGRAMMEURS p ON p.ID=c.PROGRAMMEUR"
                + " WHERE c.NB_TASSES = (SELECT MAX(NB_TASSES) FROM CONSOS_CAFE)";
        //Réalisation d'un try-with-ressources pour les objets Statement et ResultSet
        //possible car ces classes implémentent l'interface AutoClosable
        //l'exception SQLexception générée est trhows (sera gérée dans le main)
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(myQuery)) {

            while (rs.next()) {

                int id = rs.getInt("PROGRAMMEUR");
                String prenom = rs.getString("PRENOM");
                String nom = rs.getString("NOM");
                int nbTasses = rs.getInt("NB_TASSES");
                int noSemaine = rs.getInt("NO_SEMAINE");
                System.out.println("ROW" + i + " = " + id + ", " + prenom + ", " + nom + ", " + nbTasses
                        + ", " + noSemaine);
                i++;
            }
        }
    }

    /**
     * Affiche pour une semaine donnée la liste des programmeurs triée dans
     * l'ordre décroissant selon leur nombre de consommations et le nombre total
     * de tasses consommées cette semaine
     *
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void consommationsPourUneSemaine(Connection conn) throws SQLException {

        int i = 1;
        System.out.print("Numéro de la semaine : ");
        int numeroDeSemaine = sc.nextInt();
        String requete = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM \n"
                + "       CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID \n"
                + "       WHERE c.NO_SEMAINE = " + numeroDeSemaine + "\n"
                + "       ORDER BY NB_TASSES DESC";
        //Réalisation d'un try-with-ressources pour les objets Statement et ResultSet
        //possible car ces classes implémentent l'interface AutoClosable
        //l'exception SQLexception générée est trhows (sera gérée dans le main)
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                int id = rs.getInt("PROGRAMMEUR");
                String prenom = rs.getString("PRENOM");
                String nom = rs.getString("NOM");
                int nbTasses = rs.getInt("NB_TASSES");
                System.out.println("ROW" + i + " = " + id + ", " + prenom + ", " + nom + ", " + nbTasses);
                i++;
            }
        }
    }

    /**
     * pour un programmeur donné affiche le nombre total de tasses de café
     * consommées.
     *
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void nbreTotalDeTasses(Connection conn) throws SQLException {

        int i = 1;
        System.out.print("Identifiant du programmeur : ");
        int idProgrammeur = sc.nextInt();
        String requete = "SELECT PRENOM, NOM, SUM(NB_TASSES) as TOT_TASSES FROM CONSOS_CAFE c "
                + "join PROGRAMMEURS p on p.ID = c.PROGRAMMEUR "
                + "WHERE PROGRAMMEUR=" + idProgrammeur + "Group by PRENOM, NOM";

        //Réalisation d'un try-with-ressources pour les objets Statement et ResultSet
        //possible car ces classes implémentent l'interface AutoClosable
        //l'exception SQLexception générée est trhows (sera gérée dans le main)
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(requete)) {
            while (rs.next()) {
                String prenom = rs.getString("PRENOM");
                String nom = rs.getString("NOM");
                int nbTasses = rs.getInt("TOT_TASSES");
                System.out.println("ROW" + i + " = " + prenom + ", " + nom + ", " + nbTasses);
                i++;
            }
        }
    }

    /**
     * Saisit un numéro de semaine et ensuite pour chaque programmeur permet de
     * rentrer le nombre de tasses qu'il a consommées durant cette semaine.
     *
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void sasirConsommations(Connection conn) throws SQLException {

        System.out.print("Numéro de de la semaine : ");
        int noSemaine = sc.nextInt();
        System.out.println("ID du programmeur : ");
        int idProgrammeur = sc.nextInt();
        System.out.println("Nombre de tasses consommées : ");
        int nbTasses = sc.nextInt();
        //Réalisation d'un try-with-ressources pour l'objet PreparedStatement
        //possible car cette classe implémente l'interface AutoClosable
        //l'exception SQLexception générée est trhows (sera gérée dans le main)
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO CONSOS_CAFE (NO_SEMAINE, PROGRAMMEUR, NB_TASSES)\n"
                + "       VALUES(" + noSemaine + "," + idProgrammeur + "," + nbTasses + ")")) {
            ps.executeUpdate();
        }

    }

    /**
     * Exécute une requête libre définie par une chaîne donnée au clavier et
     * affiche les méta données concernant le résultat de cette requête
     * quelconque.<br/> <ul> <li>Si la command renvoie un ResultSet (Query)
     * cette méthode indique : <ul> <li>le nombre de colonnes, et pour chaque
     * colonne le nom et le type de la colonne.</li> <li>le contenu du resultSet
     * est affiché ligne par ligne sur la sortie standard.</li> </ul> </li>
     * <li>Si la commande ne renvoie pas un ResultSet (Update) cette méthode
     * indique le nombre de lignes de la table qui ont été modifiées. <li> </ul>
     *
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void requeteLibreEtMetaDonnees(Connection conn) throws SQLException {

        boolean typeRequete;
        System.out.print("Rentrez votre requête : ");
        String cmd = sc.next() + sc.nextLine();
        //Réalisation d'un try-with-ressources pour les objets Statement et ResultSet
        //possible car ces classes implémentent l'interface AutoClosable
        //l'exception SQLexception générée est trhows (sera gérée dans le main)
        try (Statement stmt = conn.createStatement()) {
            typeRequete = stmt.execute(cmd);
            try (ResultSet rs = stmt.getResultSet()) {
                if (typeRequete) {

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int nbColumn = rsmd.getColumnCount();
                    System.out.println("Le réslutat contient " + nbColumn + " colonne(s)");
                    String[] tabNameColumn = new String[nbColumn];
                    String[] tabTypeColumn = new String[nbColumn];
                    for (int i = 0; i <= nbColumn - 1; i++) {
                        tabNameColumn[i] = rsmd.getColumnName(i + 1);
                        tabTypeColumn[i] = rsmd.getColumnTypeName(i + 1);
                    }
                    for (int i = 0; i <= nbColumn - 1; i++) {

                        System.out.println("------------------------------------------------");
                        System.out.println("Colonne : " + (i + 1));
                        System.out.println("Nom : " + tabNameColumn[i] + ", Type : " + tabTypeColumn[i]);
                        System.out.println("------------------------------------------------");
                    }

                    System.out.println("Résultats de la requête : ");
                    System.out.println("");

                    while (rs.next()) {

                        String[] attribut = new String[nbColumn];
                        for (int j = 0; j <= nbColumn - 1; j++) {
                            attribut[j] = rs.getString(tabNameColumn[j]);
                        }

                        for (int j = 0; j <= nbColumn - 1; j++) {
                            if (j + 1 == nbColumn) {
                                System.out.println(" " + attribut[j]);
                            } else {
                                System.out.print(" " + attribut[j]);
                            }
                        }

                    }

                } else {
                    int nbUpdatedRows = stmt.getUpdateCount();
                    System.out.println("Nombre de lignes modifiées : " + nbUpdatedRows);
                }

            }

        }
    }

    public static void insertionDonneesCSV(Connection conn) throws FileNotFoundException, IOException, SQLException {
        System.out.println("Indiquez le chemin absolu du fichier CSV à utiliser"
                + " pour insérer des données dans la table");
        String fileName = sc.next();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        if (fileName.contains("progs")) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT into PROGRAMMEURS Values (?,?,?,?)");
            String ligne = reader.readLine();
            while (ligne != null) {
                String[] currentLine = ligne.split(",");
                for (String word : currentLine) {
                    
                }
            }
        } else {

        }
    }

    public static void sauvegardeDonneesCSV(Connection conn) {

    }

    /**
     * affiche le menu présentant les différentes opérations possibles
     */
    public static void affMenu() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("1 : Plus gros consommateurs sur une semaine");
        System.out.println("2 : Nombre total de tasses consommées par un programmeur");
        System.out.println("3 : Consommations pour une semaine donnée");
        System.out.println("4 : Sasie des consommations pour une semaine");
        System.out.println("5 : Requête Libre ");
        System.out.println("6 : Insertion de données depuis un fichier (CSV)");
        System.out.println("7 : Sauvegarde de données dans un fichier (CSV)");
        System.out.println("0 : Quitter l'application");
    }

    public static void main(String[] args) {

        // TODO
        // chargement du driver JDBC
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            System.exit(0); //Si on peut pas charger le driver on quitte le système.
        }
        // et ouverture d'une connexion
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", username, password)) {
            System.out.println("Connexion réussie !");
            boolean encore = true;
            do {
                affMenu();
                try {
                    System.out.print("votre choix : ");
                    int rep = sc.nextInt();

                    System.out.println("\n\n");

                    switch (rep) {
                        case 0:
                            System.out.print("voulez-vous vraimment quitter le programme O/N ?");
                            encore = sc.next().toUpperCase().charAt(0) != 'O';
                            break;
                        case 1:
                            plusGrosConsommateurs(conn);
                            break;
                        case 2:
                            nbreTotalDeTasses(conn);
                            break;
                        case 3:
                            consommationsPourUneSemaine(conn);
                            break;
                        case 4:
                            sasirConsommations(conn);
                            break;
                        case 5:
                            requeteLibreEtMetaDonnees(conn);
                            break;
                        case 6:
                            insertionDonneesCSV(conn);
                            break;
                        case 7:
                            sauvegardeDonneesCSV(conn);
                            break;
                        default:
                            System.out.println("valeur erronée !");
                    }  // end switch

                } catch (InputMismatchException ex) {
                    System.out.println("saisie incorrecte\nRecommencez !!");
                    sc.nextLine();   // pour "vider" le scanner
                }
            } while (encore);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // TODO
        // Fermer la connexion à la BD
    }
}
