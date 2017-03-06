package m2pcci.im2ag.webcafes.model.dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Mock Object pour simuler la DataSource qui dans l'applciation web est gérée
 * par Tomcat.
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class TestDataSource implements DataSource {

    private static String jdbcDriver = "oracle.jdbc.OracleDriver";  //pilote Oracle
    // "org.postgresql.Driver"; // pilote Postgres
    // "com.mysql.jdbc.Driver"; // pilote my_sql

    private static String dbURL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima"; // url oracle
    // "jdbc:postgresql://localhost/NombreCache"; // url postgres
    // "jdbc:mysql://localhost/notes";  //url mysql

    private String user = "VOTRE_LOGIN"; //VOTRE_LOGIN;

    private String passwd = "VOTRE_MOT_DE_PASSE"; //VOTRE_MOT_DE_PASSE;

    /**
     * Creates a new instance of TestDataSource
     *
     * @param user
     * @param passwd
     */
    public TestDataSource(String user, String passwd) {
        try {
            Class.forName(jdbcDriver);
            this.user = user;
            this.passwd = passwd;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé");
            System.exit(0);
        }
    }

    public TestDataSource() {
        try {
            Properties options = new Properties();
            InputStream in = getClass().getResourceAsStream("jdbc.properties");
            options.load(in);
            jdbcDriver = options.getProperty("jdbcDriver");
            dbURL = options.getProperty("dataBaseUrl");
            user = options.getProperty("userName");
            passwd = options.getProperty("passwd");
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Probblème chargement du fichier jdbc.properties");
            System.exit(0);
        }
    }

    @Override
    public void setLoginTimeout(int seconds) throws java.sql.SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public void setLogWriter(java.io.PrintWriter out) throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public int getLoginTimeout() throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public java.sql.Connection getConnection(String username, String password) throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public Connection getConnection() throws java.sql.SQLException {
        return DriverManager.getConnection(dbURL, user, passwd);
    }

    // methodes ajout?es pour compatibilit? JDK6
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("NYI");
    }

    public static void main(String[] args) throws SQLException {
        DataSource ds = new TestDataSource();
        System.out.println("DataSource OK");
        Connection con = ds.getConnection();
        System.out.println("connexion OK");
        con.close();
        System.out.println("connexion fermée");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
