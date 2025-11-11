/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pck_Datos_;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import Pck_Conexion_.Conexion_DB_;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author joseluisgonzalezflor
 */
public class ClientesPG_DB {

    private PreparedStatement PS;
    private final Conexion_DB_ CN;
    private DefaultTableModel DTM;
    private ResultSet RS;

    public ClientesPG_DB() {
        this.PS = null;
        this.CN = new Conexion_DB_();
    }

    private DefaultTableModel setTitulosCliente() {
        DTM = new DefaultTableModel();
        DTM.addColumn("id Cliente");
        DTM.addColumn("nombre");
        DTM.addColumn("direccion");
        DTM.addColumn("identificacion");
        DTM.addColumn("tipo");
        DTM.addColumn("telefono");
        DTM.addColumn("fechaNacimiento");
        return DTM;
    }

    public DefaultTableModel getDatosCleinte() {
        String SQL_SELECT_CLIENTE = "SELECT idCliente, nombre, direccion, identificacion, tipo, telefono, fechaNacimiento "
                + "FROM clientes ";
        try {
            this.setTitulosCliente();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_CLIENTE);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];

            while (RS.next()) {
                fila[0] = RS.getString("idCliente");
                fila[1] = RS.getString("nombre");
                fila[2] = RS.getString("direccion");
                fila[3] = RS.getString("identificacion");
                fila[4] = RS.getString("tipo");
                fila[5] = RS.getString("telefono");
                fila[6] = RS.getDate("fechaNacimiento");
                DTM.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar el cliente" + e.getMessage(), "Error de consulta", 2);
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }

    public int agregarCliente(String idCliente, String nombre, String direccion, String identificacion, String tipo, String telefono, Date fechaNacimiento) {
        int res = 0;
        String SQL_INSERT_CLIENTE = "INSERT INTO clientes(idCliente, nombre, direccion, identificacion, tipo, telefono, fechaNacimiento)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_CLIENTE);
            PS.setString(1, idCliente);
            PS.setString(2, nombre);
            PS.setString(3, direccion);
            PS.setString(4, identificacion);
            PS.setString(5, tipo);
            PS.setString(6, telefono);
            PS.setDate(7, fechaNacimiento);
            res = PS.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar un cliente" + e.getMessage(), "Error al agregar", 2);
        } finally {
            PS = null;
            CN.close();
        }
        return res;
    }

    public DefaultTableModel buscarCliente(int criterio, String parametro) {
        String SQL_BUSCAR_CLIENTE;

        if (criterio == 0) {
            SQL_BUSCAR_CLIENTE = "SELECT * FROM clientes WHERE idCliente =" + parametro;
        } else {
            SQL_BUSCAR_CLIENTE = "SELECT * FROM clientes WHERE nombre like '" + parametro + "%'";
        }
        try {
            this.setTitulosCliente();
            PS = CN.getConnection().prepareStatement(SQL_BUSCAR_CLIENTE);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            
            while(RS.next()){
            fila[0] = RS.getString("idCliente");
            fila[1] = RS.getString(2);
            fila[2] = RS.getString(3);
            fila[3] = RS.getString(4);
            fila[4] = RS.getString(5);
            fila[5] = RS.getString(6);
            fila[6] = RS.getDate(7);
            DTM.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos...o" + e.getMessage(), "Error de consulta", 2);
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }

    public int actualizarRegistroCliente(String idCliente, String nombre, String direccion, String identificacion,
            String tipo, String telefono, Date fechaNacimiento) {
        int res = 0;
        String SQL_UPDATE_CLIENTE = "UPDATE clientes SET nombre='" + nombre + "',direccion='" + direccion
                + "',identificacion='" + identificacion + "',tipo='" + tipo + "',telefono='"
                + telefono + "',fechaNacimiento='" + fechaNacimiento + "' WHERE idCliente=" + idCliente;
        try {
            PS = CN.getConnection().prepareStatement(SQL_UPDATE_CLIENTE);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitoso", "Actualizar registro", 3);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos" + e.getMessage(), "Error al actualizar", 2);
        } finally {
            PS = null;
            CN.close();
        }
        return res;
    }

    public int eliminarCliente(String id) {
        String SQL_DELETE_CLIENTE = "DELETE FROM clientes WHERE idCliente = ?";
        int res = 0;

        try {
            PS = CN.getConnection().prepareStatement(SQL_DELETE_CLIENTE);
            PS.setString(1, id);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado con exito", "Eliminar cliente", 3);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente" + e.getMessage(), "Error al eliminar", 2);
        } finally {
            PS = null;
            CN.close();
        }
        return res;
    }
    public boolean idDuplicado(String id) {
        String SQL_ID = "SELECT idCliente FROM clientes WHERE idCliente = ?";

        try {
            PS = CN.getConnection().prepareStatement(SQL_ID);
            PS.setString(1, id);
            RS = PS.executeQuery(); //hace un SELECT en la base de datos y te regresa un ResultSet
            return RS.next();       // true si ya existe un id duplicado previamente
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el id" + e.getMessage(), "Error", 2);
            return false;
        }
    }
}
