
package pck_datos_;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
