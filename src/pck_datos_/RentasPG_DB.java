
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
}
