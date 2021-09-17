package setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class Conexao {

    public static String USUARIO = "postgres";
    public static String SENHA = "admin";
    public static String SERVIDOR = "127.0.0.1";
    public static String PORTA = "5432";
    public static String NOME_BANCO = "siscv";
    public static String SGBD = "postgresql";
    public static String CONECTOR = "jdbc";
    public static String DRIVER_POSTGRES_JDBC = "org.postgresql.Driver";
    private EntityManager em;

    public Conexao(EntityManager em) {
        this.em = em;
    }

    public void insert(Object obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public void update(Object obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public void delete(Object obj) {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_POSTGRES_JDBC);
        return DriverManager.getConnection(getUrl(), USUARIO, SENHA);
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getUrl() {
        return CONECTOR + ":" + SGBD + "://" + SERVIDOR + ":" + PORTA + "/" + NOME_BANCO;
    }
}
