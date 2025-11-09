//Creen la BD así, y ya cada quien la usa:

//mysql> CREATE DATABASE rentasvehiculosdb;

//mysql> USE rentasvehiculosdb;

//mysql> CREATE TABLE clientes (
//    ->     idCliente VARCHAR(10) PRIMARY KEY,
//    ->     nombre VARCHAR(100),
//    ->     direccion VARCHAR(150),
//    ->     identificacion VARCHAR(50),
//    ->     tipo VARCHAR(50),
//    ->     telefono VARCHAR(20),
//    ->     fechaNacimiento DATE
//    -> );
//
//mysql> CREATE TABLE vehiculos (
//    ->     idVehiculo INT PRIMARY KEY,
//    ->     modelo VARCHAR(50),
//    ->     marca VARCHAR(50),
//    ->     anio INT,
//    ->     color VARCHAR(30),
//    ->     precio FLOAT
//    -> );
//
//mysql> CREATE TABLE gokarts (
//    ->     idVehiculo INT PRIMARY KEY,
//    ->     cilindrada INT,
//    ->     noLlantas INT,
//    ->     velocidadMaxima FLOAT,
//    ->     FOREIGN KEY (idVehiculo) REFERENCES vehiculos(idVehiculo)
//    -> );
//
//mysql> CREATE TABLE patines (
//    ->     idVehiculo INT PRIMARY KEY,
//    ->     tipo VARCHAR(50),
//    ->     materialBota VARCHAR(50),
//    ->     noRuedas INT,
//    ->     FOREIGN KEY (idVehiculo) REFERENCES vehiculos(idVehiculo)
//    -> );
//
//mysql> CREATE TABLE rentas (
//    ->     idRenta INT PRIMARY KEY,
//    ->     idVehiculo INT,
//    ->     idCliente VARCHAR(10),
//    ->     fechaRenta DATE,
//    ->     horaInicio TIME,
//    ->     horaFinal TIME,
//    ->     FOREIGN KEY (idVehiculo) REFERENCES vehiculos(idVehiculo),
//    ->     FOREIGN KEY (idCliente) REFERENCES clientes(idCliente)
//    -> );
package pck_conexion_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_DB_ {
    static final String URL = "jdbc:mysql://localhost:3306/rentasvehiculosdb";
    static final String USER = "root";
    static final String PASS = "4";
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
