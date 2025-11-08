
package pck_conexion_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_DB_ {
    static final String URL = "";
    static final String USER = "root";
    static final String PASS = "";
    private static Connection con;
    
    public Conexion_DB_(){
        con = null;
    }
    
    public Connection getConnection(){        
        try{
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Conexión exitosa.");
        }catch(SQLException e){
           System.out.println("Conexión no exitosa: " + e.getMessage());  
        }
        return con;
    }
    public void close(){
        try{
            con.close();
        }catch(SQLException e){
           System.out.println("Error al cerrar conexión a la BD: " + e.getMessage());  
        }
    }
}
