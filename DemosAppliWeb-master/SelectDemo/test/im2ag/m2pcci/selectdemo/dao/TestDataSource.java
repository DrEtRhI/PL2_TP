package im2ag.m2pcci.selectdemo.dao;

/**
 *
 * @author Philippe.Genoud@imag.fr
 */
/*
 * TestDataSource.java
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class TestDataSource implements DataSource {

    private static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  //pilote Oracle
    // "org.postgresql.Driver"; // pilote Postgres
    // "com.mysql.jdbc.Driver"; // pilote my_sql


    private static final String DB_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima"; // url oracle
      // "jdbc:postgresql://localhost/NombreCache"; // url postgres
      // "jdbc:mysql://localhost/notes";  //url mysql


    private  String user =  "VOTRE_LOGIN"; //VOTRE_LOGIN;

    private  String passwd = "VOTRE_MOT_DE_PASSE"; //VOTRE_MOT_DE_PASSE;

    
    /** Creates a new instance of TestDataSource */
    public TestDataSource(String user, String passwd) {
        try {
            Class.forName(JDBC_DRIVER);
            this.user = user;
            this.passwd = passwd;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé");
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
        return DriverManager.getConnection(DB_URL, user, passwd);
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
        DataSource ds = new TestDataSource("votreLogin", "votreMOTDePasse");
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

