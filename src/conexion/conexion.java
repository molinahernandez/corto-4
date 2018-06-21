/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static com.oracle.nio.BufferSecrets.instance;
import static conexion.conexion.instance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class conexion {

    private String user;
    private String pass;
    private String driver;
    private String url;

    private Connection cnx;
    public static Connection instance;

    public synchronized static conexion conectar() {
        if (instance == null) {
            return new conexion();
        }
        return (conexion) instance;
    }

    private conexion() {
        cargarCredenciales();

        try {
            Class.forName(this.driver);
            cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCredenciales() {
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
    }

    public Connection getCnx() {
        return cnx;
    }

    public void cerrarConexion() {
        instance = null;
    }
}
