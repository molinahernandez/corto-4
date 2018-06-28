/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

//import com.sun.istack.internal.logging.Logger;
import conexion.conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import modelo.producto;
import java.util.logging.Logger;
/**
 *
 * @author Cristian Hernandez
 */
public class productoDao implements metodos<producto> {

    private static final String SQL_INSERT = "INSERT INTO productos (id,nombre,codigo,tipo,cantidad,precio,disponibilidad))VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATe productos SET nombre =?,codigo=?,tipo=?,cantidad=?,precio=?,disponibilidad=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE id=?";
    private static final String SQL_READALL = "SELECT * FROM productos";

    private static final conexion con = conexion.conectar();

    @Override
    public boolean create(producto g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getId());
            ps.setString(2, g.getNombre());
            ps.setInt(3, g.getCodigo());
            ps.setString(4, g.getTipo());
            ps.setInt(5, g.getCantidad());
            ps.setInt(6, g.getPrecio());
            ps.setBoolean(7, true);
            if (ps.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object Key) {
        PreparedStatement ps;
        try{
            ps= con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, Key.toString());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(producto c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getCodigo());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setInt(3, c.getCodigo());
            ps.setString(4, c.getTipo());
            ps.setInt(5, c.getCantidad());
            ps.setInt(6, c.getPrecio());
            ps.setBoolean(7, c.getDisponibilidad());
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public producto read(Object key) {
                producto f=null;
        PreparedStatement ps;
        ResultSet rs; 
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs=ps.executeQuery();
            
            while(rs.next()){
                f= new producto (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<producto> readAll() {
        ArrayList<producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s= con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7)));
            }
            rs.close();
        }catch (SQLException ex){
            Logger.getLogger(productoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

}
