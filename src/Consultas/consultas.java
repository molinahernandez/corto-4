/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Dao.FiltroDao;
import filtro.producto;
import java.awt.Container;
import java.awt.TextField;
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

/**
 *
 * @author LN710Q
 */
public class consultas extends JFrame {

    public JLabel lblCodigo, lblPrecio, lblNombre, lblCantidad, lblTipo, lblDisponibilidad;
    public TextField codigo, precio, nombre, cantidad, tipo;
    public JComboBox Nombre;

    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;
    public JButton Buscar, Actualizar, Eliminar, Insertar, Limpiar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public consultas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        Formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblTipo);
        container.add(lblDisponibilidad);
        container.add(codigo);
        container.add(nombre);
        container.add(tipo);
        container.add(si);
        container.add(no);
        container.add(Buscar);
        container.add(Insertar);
        container.add(Actualizar);
        container.add(Eliminar);
        container.add(Limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblCodigo = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblTipo = new JLabel("Tipo");
        lblDisponibilidad = new JLabel("Stock en la tienda");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 100, ANCHOC, ALTOC);
        lblDisponibilidad.setBounds(10, 140, ANCHOC, ALTOC);
    }

    public final void Formulario() {
        codigo = new TextField();
        Nombre = new JComboBox();
        tipo = new TextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        Buscar = new JButton("Buscar");
        Insertar = new JButton("Insertar");
        Eliminar = new JButton("Eliminar");
        Actualizar = new JButton("Actualizar");
        Limpiar = new JButton("Limpiar");

        table = new JPanel();
        nombre.addNotify();
        nombre.addNotify();
        nombre.addNotify();
        nombre.addNotify();
        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);

        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        tipo.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        Buscar.setBounds(300, 10, ANCHOC, ALTOC);
        Insertar.setBounds(10, 210, ANCHOC, ALTOC);
        Actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        Eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        Limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
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
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Tipo");
        tm.addColumn("Tipo en la Sucursal");

        FiltroDao fd = new FiltroDao();
        producto producto = fd.read(fd);
        for (producto fi : producto) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.getNombre(), fi.getTipo(), fi.getDisponibilidad()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {

        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                producto p = new producto(codigo.getInputContext() nombre.getSelectedText(),
                        Integer.parseInt(tipo.getText()), true);

                if (no.isSelected()) {
                    p.setDisponibilidad(false);
                }
                if (fd.update(p)) {
                    JOptionPane.showMessageDialog(null, "Producto modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el producto");
                }
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(codigo.getInputContext())) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el Producto");
                }
            }

        });

        Insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                producto p = new producto(codigo.getInputContext(), nombre.getSelectedText(),
                        Integer.parseInt(tipo.getText()), true);

                if (no.isSelected()) {
                    p.setDisponibilidad(false);
                }

                if (fd.create(p)) {
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el producto");

                }
            }
        });

        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        tipo.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new consultas().setVisible(true);
            }
        });
    }

}
