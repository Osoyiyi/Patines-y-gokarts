
package pck_datos_;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pck_conexion_.Conexion_DB_;


public class RentasPG_DB {
    private PreparedStatement PS;
    private final Conexion_DB_ CN;
    private DefaultTableModel DTM;
    private ResultSet RS;
    
    public RentasPG_DB(){
        this.PS = null;
        this.CN = new Conexion_DB_();
    }
    private DefaultTableModel setTitulosVG(){
        DTM = new DefaultTableModel();
        DTM.addColumn("Id Vehículo");
        DTM.addColumn("Modelo");
        DTM.addColumn("Marca");
        DTM.addColumn("Año");
        DTM.addColumn("Color");
        DTM.addColumn("Precio");
        DTM.addColumn("Cilindrada");
        DTM.addColumn("No LLantas");
        DTM.addColumn("Velocidad Máxima");
        return DTM;
    }
    private DefaultTableModel setTitulosVP(){
        DTM = new DefaultTableModel();
        DTM.addColumn("Id Vehículo");
        DTM.addColumn("Modelo");
        DTM.addColumn("Marca");
        DTM.addColumn("Año");
        DTM.addColumn("Color");
        DTM.addColumn("Precio");
        DTM.addColumn("Tipo");
        DTM.addColumn("Material Bota");
        DTM.addColumn("No Ruedas");
        return DTM;
    }
    public DefaultTableModel getDatosVG(){
        String SQL_SELECT_VG = "SELECT v.idVehiculo, v.modelo, v.marca, v.anio, v.color, v.precio, "
                             + "g.cilindrada, g.noLLantas, g.velocidadMaxima "
                             + "FROM vehiculos v "
                             + "INNER JOIN gokarts g ON v.idVehiculo = g.idVehiculo";
        try{
            this.setTitulosVG();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_VG);
            RS = PS.executeQuery();
            Object[] fila = new Object[9];
            
            while(RS.next()){
                fila[0] = RS.getInt("idVehiculo");
                fila[1] = RS.getString("modelo");
                fila[2] = RS.getString("marca");
                fila[3] = RS.getInt("anio");
                fila[4] = RS.getString("color");
                fila[5] = RS.getFloat("precio");
                fila[6] = RS.getInt("cilindrada");
                fila[7] = RS.getInt("noLLantas");
                fila[8] = RS.getFloat("velocidadMaxima");
                DTM.addRow(fila);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar gokarts: " + e.getMessage(), "Error de consulta", 0);
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    public DefaultTableModel getDatosVP() {
        String SQL_SELECT_VP = "SELECT v.idVehiculo, v.modelo, v.marca, v.anio, v.color, v.precio, "
                             + "p.tipo, p.materialBota, p.noRuedas "
                             + "FROM vehiculos v "
                             + "INNER JOIN patines p ON v.idVehiculo = p.idVehiculo";

        try {
            this.setTitulosVP();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_VP);
            RS = PS.executeQuery();
            Object[] fila = new Object[9];

            while (RS.next()) {
                fila[0] = RS.getInt("idVehiculo");
                fila[1] = RS.getString("modelo");
                fila[2] = RS.getString("marca");
                fila[3] = RS.getInt("anio");
                fila[4] = RS.getString("color");
                fila[5] = RS.getFloat("precio");
                fila[6] = RS.getString("tipo");
                fila[7] = RS.getString("materialBota");
                fila[8] = RS.getInt("noRuedas");
                DTM.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar patines: " + e.getMessage(), "Error de consulta", 0);
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    public int agregarV(int idVehiculo, String modelo, String marca, int anio, String color,
                                 float precio){
        int res = 0;
        String SQL_INSERT = "INSERT INTO vehiculos(idVehiculo, modelo, marca, anio, color, precio) VALUES(?,?,?,?,?,?)";
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setInt(1, idVehiculo);
            PS.setString(2, modelo);
            PS.setString(3, marca);
            PS.setInt(4, anio);
            PS.setString(5, color);
            PS.setFloat(6, precio);
            res = PS.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al agregar vehículo: " + e.getMessage(), "ERROR", 0);
        }finally{
            PS = null;
        }
        return res;
    }
    public int agregarG(int idVehiculo, int cilindrada, int noLLantas, float velocidadMaxima) {
        int res = 0;
        String SQL_INSERT = "INSERT INTO gokarts(idVehiculo, cilindrada, noLLantas, velocidadMaxima) VALUES(?,?,?,?)";
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setInt(1, idVehiculo);
            PS.setInt(2, cilindrada);
            PS.setInt(3, noLLantas);
            PS.setFloat(4, velocidadMaxima);
            res = PS.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar gokart: " + e.getMessage(), "ERROR", 0);
        } finally {
            PS = null;
        }
        return res;
    }
    public int agregarP(int idVehiculo, String tipo, String materialBota, int noRuedas){
        int res = 0;
        String SQL_INSERT = "INSERT INTO patines (idVehiculo, tipo, materialBota, noRuedas) VALUES(?,?,?,?)";
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setInt(1, idVehiculo);
            PS.setString(2, tipo);
            PS.setString(3, materialBota);
            PS.setInt(4, noRuedas);
            res = PS.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar patines: " + e.getMessage(), "ERROR", 0);
        } finally {
            PS = null;
        }
        return res;
    }
    public int agregarRegistroGoKart(int idVehiculo, String modelo, String marca, int anio, String color, float precio,
                                  int cilindrada, int noLLantas, float velocidadMaxima) {
        int v = agregarV(idVehiculo, modelo, marca, anio, color, precio);
        int g = agregarG(idVehiculo, cilindrada, noLLantas, velocidadMaxima);
        
        //CN.close();
        if (v > 0 && g > 0) {
            JOptionPane.showMessageDialog(null, "GoKart registrado correctamente.", "Registro Gokart", 1);
            return 1;
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar GoKart.", "Registro Gokart", 2);
            return 0;
        }
    }
    public int agregarRegistroPatines(int idVehiculo, String modelo, String marca, int anio, String color, float precio,
                                    String tipo, String materialBota, int noRuedas){
        int v = agregarV(idVehiculo, modelo, marca, anio, color, precio);
        int p = agregarP(idVehiculo, tipo, materialBota, noRuedas);
        //CN.close();
        if (v > 0 && p > 0) {
            JOptionPane.showMessageDialog(null, "Patines registrados correctamente.", "Registro Patines", 1);
            return 1;
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar Patines.", "Registro Patines", 2);
            return 0;
        }
    }
    public boolean existeV(int idVehiculo){
        boolean existe = false;
        String SQL_EXISTE = "SELECT idVehiculo FROM vehiculos WHERE idVehiculo = ?";
        try {
            PS = CN.getConnection().prepareStatement(SQL_EXISTE);
            PS.setInt(1, idVehiculo);
            RS = PS.executeQuery();

            if (RS.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar existencia: " + e.getMessage(), "Error", 2);
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        return existe;
    }
}
