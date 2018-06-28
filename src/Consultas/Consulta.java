/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Dao.productoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.producto;

/**
 *
 * @author Cristian Hernandez
 */
public class Consulta extends JFrame {

    public JLabel lblNombre, lblCodigo, lblTipo, lblCantidad, lblPrecio, lblDisponibilidad;
    public JTextField nombre, codigo, cantidad, precio;
    public JComboBox tipo;

    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        Formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblCodigo);
        container.add(lblTipo);
        container.add(lblCantidad);
        container.add(lblPrecio);
        container.add(lblDisponibilidad);
        container.add(nombre);
        container.add(codigo);
        container.add(tipo);
        container.add(cantidad);
        container.add(precio);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblCodigo = new JLabel("Codigo: ");
        lblPrecio = new JLabel("precio");
        lblNombre = new JLabel("Nombre: ");
        lblCantidad = new JLabel("cantidad: ");
        lblTipo = new JLabel("tipo"); 
        lblDisponibilidad = new JLabel("en disponibilidad: ");

        lblTipo.setBounds(10, 180, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 60, ANCHOC, ALTOC);
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblCantidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 100, ANCHOC, ALTOC);
        lblDisponibilidad.setBounds(10, 240, ANCHOC, ALTOC);
        
        }

    public final void Formulario() {
        codigo = new JTextField();
        precio = new JTextField();
        nombre = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");
        
        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);

        nombre.setBounds(140, 100, ANCHOC, ALTOC);
        precio.setBounds(140,60,ANCHOC, ALTOC);
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        tipo.setBounds(140, 180, ANCHOC, ALTOC);
        cantidad.setBounds(140, 140, ANCHOC, ALTOC);
        si.setBounds(140, 240, 50, ALTOC);
        no.setBounds(210, 240, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 310, ANCHOC, ALTOC);
        actualizar.setBounds(150, 310, ANCHOC, ALTOC);
        eliminar.setBounds(300, 310, ANCHOC, ALTOC);
        limpiar.setBounds(450, 310, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10,350, 500, 200);
        table.add(new JScrollPane(resultados));

    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Tipo");
        tm.addColumn("Cantidad");
        tm.addColumn("Precio");
        tm.addColumn("Disponibilidad");

        productoDao fd = new productoDao();
        ArrayList<producto> productos = fd.readAll();
        for (producto fi : productos) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.getNombre(), fi.getCantidad(), fi.getDisponibilidad()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd = new productoDao();
                producto f = new producto(nombre.getText(), codigo.getText(), 
                        (String) tipo.getSelectedItem(), Integer.parseInt(cantidad.getText()),
                        Integer.parseInt(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el producto");

                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd = new productoDao();
                producto f = new producto(nombre.getText(), codigo.getText(), 
                        (String) tipo.getSelectedItem(), Integer.parseInt(cantidad.getText()),
                        Integer.parseInt(precio.getText()),true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Producto modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el producto");
                }
            }

        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd = new productoDao();
                if (fd.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el producto");
                }
            }

        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd = new productoDao();
                producto f = fd.read(nombre.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El producto buscado no se ah encontrado");

                } else {
                    nombre.setText(f.getNombre());
                    tipo.setSelectedItem(f.getTipo());
                    codigo.setText(f.getCodigo());

                    if (f.getDisponibilidad()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        nombre.setText("");
        codigo.setText("");
        tipo.setSelectedItem("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
