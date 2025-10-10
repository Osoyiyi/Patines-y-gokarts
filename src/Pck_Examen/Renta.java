
package Pck_Examen;

public class Renta {
    private int idRenta;
    private int idVehiculo;
    private String idCliente;
    private Fecha fechaRenta;
    private String horaInicio;
    private String horaFinal;

    public Renta(int idRenta, int idVehiculo, String idCliente, Fecha fechaRenta, String horaInicio, String horaFinal) {
        this.idRenta = idRenta;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.fechaRenta = fechaRenta;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    public Renta(int idRenta, int idVehiculo, String idCliente, int d, int m, int a, String horaInicio, String horaFinal) {
        this.idRenta = idRenta;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.fechaRenta = new Fecha(d,m,a);
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    public Renta(){
        this.idRenta = 0;
        this.idVehiculo = 0;
        this.idCliente = null;
        this.fechaRenta = new Fecha();
        this.horaInicio = null;
        this.horaFinal = null;
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

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
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
        return horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }
    
    public String gtDatos(){
        return "-------- RENTA -------"
               + "Id renta: " + getIdRenta()
               + "Id vehiculo: " + getIdVehiculo()
               + "Id cliente: " + getIdCliente()
               + "Fecha renta: " + getFechaRenta()
               + "Hora de inicio: " + getHoraInicio()
               + "Hora del final: " + getHoraFinal();
    }
}
