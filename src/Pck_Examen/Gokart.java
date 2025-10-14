//Godínez Hernández Alberto Carlos
//González Flor José Luis
//Lara Hernandez Alexis Arath
//Redondo Perez Rainy
//Trejo Hernández Joshua

package Pck_Examen;

import java.io.Serializable;

public class Gokart extends Vehiculo implements Serializable{
    private static final long serialVersionUID = 2L;
    private int cilindrada;
    private int noLlantas;
    private float velocidadMaxima;

    public Gokart(int idVehiculo, String modelo, String marca, int anio, String color, float precio ,int cilindrada, int noLlantas, float velocidadMaxima) {
        super(idVehiculo, modelo, marca, anio, color, precio);
        this.cilindrada = cilindrada;
        this.noLlantas = noLlantas;
        this.velocidadMaxima = velocidadMaxima;
    }
    
    public Gokart(){
        this.cilindrada = 0;
        this.noLlantas = 0;
        this.velocidadMaxima = 0.0f;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setNoLlantas(int noLlantas) {
        this.noLlantas = noLlantas;
    }

    public void setVelocidadMaxima(float velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public int getNoLlantas() {
        return noLlantas;
    }

    public float getVelocidadMaxima() {
        return velocidadMaxima;
    }
    
    @Override
    public String getDatos(){
         return "-------- GOKART ---------"
                + "\nId del vehiculo: " + getIdVehiculo()
                + "\nModelo: " + getModelo()
                + "\nMarca: " + getMarca()
                + "\nAño: " + getAnio()
                + "\nColor: " + getColor()
                + "\nCilindrada: " + getCilindrada()
                + "\nNumero de llantas: " + getNoLlantas()
                + "\nVelocidad Maximo: " + getVelocidadMaxima()
                + "\nPrecio: " + getPrecio();
    }
}
