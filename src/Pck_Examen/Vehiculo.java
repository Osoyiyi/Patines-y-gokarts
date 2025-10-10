//Elaborado por Papu inc
package Pck_Examen;

public class Vehiculo {
    protected int idVehiculo;
    protected String modelo;
    protected String marca;
    protected int anio;
    protected String color;
    protected float precio;

    public Vehiculo(int idVehiculo, String modelo, String marca, int anio, String color, float precio) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.marca = marca;
        this.anio = anio;
        this.color = color;
        this.precio = precio;
    }
    
    
    public Vehiculo(){
        this(0,null,null,0,null,0.0f);
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAnio() {
        return anio;
    }

    public String getColor() {
        return color;
    }

    public float getPrecio() {
        return precio;
    }
    
    public String getDatos(){
        return "-------- VEHICULO ---------"
                + "\nId del vehiculo: " + getIdVehiculo()
                + "\nModelo: " + getModelo()
                + "\nMarca: " + getMarca()
                + "\nAño: " + getAnio()
                + "\nColor: " + getColor()
                + "\nPrecio: " + getPrecio();
                
    }
}
