
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
}
