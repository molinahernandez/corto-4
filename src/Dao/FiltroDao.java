/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import conexion.conexion;
import filtro.producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class FiltroDao {

    private static final String SQL_INSERT = "INSERT INTO filtro_productos (codProducto,precio,nombre,cantidad,tipo,disponibilidad)VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET precio=?, nombre=?,cantidad=?,tipo=?,disponibilidad=? WHERE codProductos=?";
    private static final String SQL_DELETE = "DELETE FROM filtros_producto WHERE codProductos=?";
    private static final String SQL_READ = "SELECT * FROM filtros_productos WHERE codProductos=?";

    private static final conexion con = conexion.conectar();

    public FiltroDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean create(producto g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getTipo());
            ps.setInt(3, g.getCodigo());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    private FiltroDao(Object Key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, Key.toString());

            if (ps.executeUpdate() > 0) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
    }

    public boolean update(producto c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getCodigo());
            ps.setBoolean(3, c.getDisponibilidad());
            ps.setString(4, c.getTipo());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    public producto read(Object key) {
        producto p = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                p = new producto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getBoolean(6));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return p;
    }

    public ArrayList<producto> readAll() {
        ArrayList<producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            String SQL_READALL = null;
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new producto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    public boolean delete(Object Key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, Key.toString());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;
    }
}
