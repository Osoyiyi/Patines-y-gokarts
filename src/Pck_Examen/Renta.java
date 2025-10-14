//Godínez Hernández Alberto Carlos
//González Flor José Luis
//Lara Hernandez Alexis Arath
//Redondo Perez Rainy
//Trejo Hernández Joshua


package Pck_Examen;
import Pck_Fecha.Fecha;


public class Renta {
    private int idRenta;
    private int idVehiculo;
    private String idCliente;
    private Fecha fechaRenta;
    private Hora horaInicio;
    private Hora horaFinal;

    public Renta(int idRenta, int idVehiculo, String idCliente, Fecha fechaRenta, Hora horaInicio, Hora horaFinal) {
        this.idRenta = idRenta;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.fechaRenta = fechaRenta;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    public Renta(int idRenta, int idVehiculo, String idCliente, int d, int m, int a, int nHI1, int nHF1, int nHI2, int nHF2) {
        this.idRenta = idRenta;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.fechaRenta = new Fecha(d,m,a);
        this.horaInicio = new Hora(nHI1, nHF1);
        this.horaFinal = new Hora(nHI2, nHF2);
    }
    
    public Renta(){
        this.idRenta = 0;
        this.idVehiculo = 0;
        this.idCliente = null;
        this.fechaRenta = new Fecha();
        this.horaInicio = new Hora();
        this.horaFinal = new Hora();
    }

    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    public boolean setFechaRenta(int d, int m, int a){
        fechaRenta.setFecha( d, m, a);
        return fechaRenta.fechaCorrecta();
    }

    public void setFechaRenta(Fecha fechaRenta) {
        this.fechaRenta = fechaRenta;
    }
    
    public void setHoraInicio(Hora horaInicio){
        this.horaInicio = horaInicio;
    }
    
    public void setHoraFinal(Hora horaFinal) {
        this.horaFinal = horaFinal;
    }

    public boolean setHoraInicio(int nHI1, int nHF1) {
        horaInicio.setHora(nHI1, nHF1);
        return horaInicio.horaCorrecta();    
    }
    
    public boolean setHoraFinal(int nHI2, int nHF2) {
        horaFinal.setHora(nHI2, nHF2);
        return horaFinal.horaCorrecta();
        
    }

    public int getIdRenta() {
        return idRenta;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public Fecha getFechaRenta() {
        return fechaRenta;
    }

    public String getHoraInicio() {
        return horaInicio.getHora();
    }

    public String getHoraFinal() {
        return horaFinal.getHora();
    }
    
    //comentario para un push de prueba
    public String getDatos(){
        return "-------- RENTA -------\n"
               + "Id renta: " + getIdRenta()
               + "\nId vehiculo: " + getIdVehiculo()
               + "\nId cliente: " + getIdCliente()
               + "\nFecha renta: " + getFechaRenta()
               + "\nHora de inicio: " + getHoraInicio()
               + "\nHora del final: " + getHoraFinal();
    }
}
