//Elabora por Papu inc
package Pck_Examen;


public class Cliente {
    private String idCliente;
    private String nombre;
    private String direccion;
    private String identificacion;
    private String tipo;
    private String telefono;
    private Fecha fechaNacimiento;

    public Cliente(String idCliente, String nombre, String direccion, String identificacion, String tipo, String telefono, Fecha fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.tipo = tipo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Cliente(String idCliente, String nombre, String direccion, String identificacion, String tipo, String telefono, int d, int m, int a) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.tipo = tipo;
        this.telefono = telefono;
        this.fechaNacimiento = new Fecha(d, m, a);
    }
    
    public Cliente(){
       this.idCliente = null;
        this.nombre = null;
        this.direccion = null;
        this.identificacion = null;
        this.tipo = null;
        this.telefono = null;
        this.fechaNacimiento = new Fecha();
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean setFechaNacimiento(int d, int m, int a) {
        fechaNacimiento.setFecha(d, m, a);
        return fechaNacimiento.fechaCorrecta();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String getDatos(){
        return "---------CLIENTE---------"
               + "\nId cliente: " + getIdCliente()
               + "\nNombre: " + getNombre()
               + "\nIdentificacion: " + getIdentificacion()
               + "\nTipo: " + getTipo()
               + "\nTelefono: " + getTelefono()
               + "\nFecha de nacimiento: " + getFechaNacimiento();
    }
}
