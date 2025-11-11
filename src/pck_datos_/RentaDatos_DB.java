package pck_datos_;
import java.sql.PreparedStatement;
import Pck_Conexion_.Conexion_DB_;
import java.sql.Date;
import java.sql.Time;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class RentaDatos_DB {
    private PreparedStatement PS;
    private final Conexion_DB_ CN;
    private DefaultTableModel DTM;
    private ResultSet RS;
    
    public RentaDatos_DB(){
        this.PS = null;
        this.CN = new Conexion_DB_();
    }
    
    private DefaultTableModel setTitulos(){
        DTM = new DefaultTableModel();
        DTM.addColumn("ID Renta");
        DTM.addColumn("ID Vehiculo");
        DTM.addColumn("ID Cliente");
        DTM.addColumn("Hora Inicio");
        DTM.addColumn("Hora Final");
        DTM.addColumn("Fecha renta");
        
        return DTM;
    }
    
    public DefaultTableModel getDatos(){
        String SQL_SELECT = "SELECT * FROM rentas";
        
        try{
            this.setTitulos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getTime(5);
                fila[4] = RS.getTime(6);
                fila[5] = RS.getDate(4);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al consultar datos\n" + e.getMessage(),
                    "Datos de una renta", 2);
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    
    public int agregarRegistro(int idRenta, int idVehiculo, String idCliente, Date fechaRenta, Time horaInicio, Time horaFinal){
        int res = 0;
        String SQL_INSERT = "INSERT INTO rentas(idRenta, idVehiculo, idCliente, fechaRenta, horaInicio, horaFinal)" +
                            " VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setInt(1, idRenta);
            PS.setInt(2, idVehiculo);
            PS.setString(3, idCliente);
            PS.setDate(4, fechaRenta);
            PS.setTime(5, horaInicio);
            PS.setTime(6, horaFinal);
            
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Registro de Renta exitoso", "Alta de Renta", 1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar el registro de la Renta: \n" + e.getMessage(),
                                          "Alta de Renta", 2);
        }finally{
            PS = null;
            CN.close();
        }
        return res;
    }
    
    public DefaultTableModel buscarRenta(int criterio, String parametro){
        String SQL_BUSCAR ="";
        
        switch(criterio){
            case 0:
                SQL_BUSCAR = "SELECT * FROM rentas WHERE idRenta = " + parametro;
                break;
            case 1:
                SQL_BUSCAR = "SELECT * FROM rentas WHERE idCliente LIKE '" + parametro + "%'";
                break;
            case 2:
                SQL_BUSCAR = "SELECT * FROM rentas WHERE idVehiculo = " + parametro;
                break;
            default:
                SQL_BUSCAR = "SELECT * FROM rentas";
                break;   
        }
        try{
            this.setTitulos();
            PS = CN.getConnection().prepareStatement(SQL_BUSCAR);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getInt(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getTime(5);
                fila[4] = RS.getTime(6);
                fila[5] = RS.getDate(4);
                DTM.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consultar los datos: \n" +
                    e.getMessage(),"Error de consulta", 2);
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DTM;
    }
    
    public int actualizarRegistro(int idRenta, int idVehiculo, String idCliente, 
            Date fechaRenta,Time horaInicio, Time horaFinal){
        int res = 0;
        String SQL_UPDATE = "UPDATE rentas SET idVehiculo=?, idCliente=?, fechaRenta=?, horaInicio=?, horaFinal=? WHERE idRenta=?";
        try{
            PS = CN.getConnection().prepareStatement(SQL_UPDATE);
            PS.setInt(1, idVehiculo);
            PS.setString(2, idCliente);
            PS.setDate(3, fechaRenta);
            PS.setTime(4, horaInicio);
            PS.setTime(5, horaFinal);
            PS.setInt(6, idRenta);
            
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Registro de Renta ha sido actualizado con éxito", "Actualización de Renta", 1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro de la Renta: \n" + 
                    e.getMessage(), "Actualización de Renta", 2);
        }finally{
            PS =null;
            CN.close();
        }
        return res;
    }
    
    public int eliminarRegistro(int idRenta){
        String SQL_DELETE = "DELETE FROM rentas WHERE idRenta = ?";
        int res = 0;
        try{
            PS = CN.getConnection().prepareStatement(SQL_DELETE);
            PS.setInt(1, idRenta);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "El registro se eliminó con éxito", "Eliminar Registro", 1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro: \n" + 
                    e.getMessage(), "Eliminar Registro", 2);
        }finally{
            PS = null;
            CN.close();
        }
        return res;
    }
}
