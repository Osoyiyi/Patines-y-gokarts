
package Pck_Examen;

public class Clientes {
    private String idCliente;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String tipo;
    private String telefono;
    private Fecha fechaNacimiento;

    public Clientes(String idCliente, String nombre, String identificacion, String direccion, 
            String tipo, String telefono, Fecha fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.tipo = tipo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Clientes(String idCliente, String nombre, String identificacion, String direccion, 
            String tipo, String telefono,int dN, int mN, int aN){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.tipo = tipo;
        this.telefono = telefono;
        this.fechaNacimiento = new Fecha(dN, mN, aN);
    }
    
    public Clientes() {
        this(null, null, null, null, null, null, new Fecha());
    }
    
    public boolean setFechaNacimiento (int dN, int mN, int aN){
        fechaNacimiento.setFecha(dN, mN, aN);
        return fechaNacimiento.fechaCorrecta();
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }
    
    public String getDatos(){
        return "CLIENTE------------" +
               "\nIdCliente: " + getIdCliente() +
               "\nNombre: " + getNombre() +
               "\nDirección: " + getDireccion() +
               "\nIdentificación: " + getIdentificacion() +
               "\nTipo: " + getTipo() +
               "\nTeléfono: " + getTelefono() +
               "\nFecha de Nacimiento: " + getFechaNacimiento();
    }
    
}
