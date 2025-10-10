//Elaborado por Papu inc
package Pck_Examen;

public class Patines extends Vehiculo {
    private String tipo;
    private String materialBota;
    private int noRuedas;

    public Patines(String tipo, String materialBota, int noRuedas, int idVehiculo, String modelo, String marca, int anio, String color, float precio) {
        super(idVehiculo, modelo, marca, anio, color, precio);
        this.tipo = tipo;
        this.materialBota = materialBota;
        this.noRuedas = noRuedas;
    }
    
    public Patines(){
        this.tipo = null;
        this.materialBota = null;
        this.noRuedas = 0;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMaterialBota(String materialBota) {
        this.materialBota = materialBota;
    }

    public void setNoRuedas(int noRuedas) {
        this.noRuedas = noRuedas;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMaterialBota() {
        return materialBota;
    }

    public int getNoRuedas() {
        return noRuedas;
    }
    
    @Override
    public String getDatos() {
        return "-------- PATINES ---------"
                + "\nId del vehiculo: " + getIdVehiculo()
                + "\nModelo: " + getModelo()
                + "\nMarca: " + getMarca()
                + "\nAño: " + getAnio()
                + "\nColor: " + getColor()
                + "\nTipo: " + getTipo()
                + "\nMaterial de bota: " + getMaterialBota()
                + "\nNumero de ruedas: " + getNoRuedas()
                + "\nPrecio: " + getPrecio();
    }
}
