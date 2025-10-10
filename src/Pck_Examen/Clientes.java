
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
    
//    public boolean setFechaNacimiento (int dN, int mN, int aN){
////        fechaElaboracion.setFecha(d, m, a);
////        if(fechaElaboracion.fechaCorrecta()){
////            return true;
////        }else{
////            return false;
////        }
//    }
    
    public Clientes() {
        this(null, null, null, null, null, null, new Fecha());
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
    
    
}
