//Godínez Hernández Alberto Carlos
//González Flor José Luis
//Lara Hernández Alexis Arath
//Redondo Perez Rainy
//Trejo Hernández Joshua

package Pck_Examen;

import Pck_Fecha.Fecha;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Menu_Renta_Patines_Gokarts {

    public static void main(String args[]) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Renta> rentas = new ArrayList<>();
        ArrayList<Vehiculo> lVehiculos = new ArrayList<>();
        ArrayList<Cliente> lClientes = new ArrayList<>();
        ArrayList<Renta> lRentas = new ArrayList<>();
        int idVehiculo, anio, cilindrada, noLlantas, noRuedas, idRenta, op, idEx = -1, idEx2 = -1, cont = -1,
                posPatines, posGokart, posCliente, posRenta,IdVeRenta, posVe, posCli;
        int dc = 0, mc = 0, ac = 2011, num1, num2;
        String modelo, marca, color, tipoPatin, materialBota, idCliente, nombre,
                direccion, identificacion, tipoCliente, telefono, auxID, DatosVe,
                idClienteRenta, datosCliente, datosRenta, msjFinal, lista = "";
        float precio, velocidadMaxima;
        Fecha fechaNacimiento, fechaRenta;
        Hora horaInicio = new Hora(), horaFinal = new Hora();
        boolean stval, valH;
        //MENU-------------------------------------------
        String menu = "----- RENTA DE PATINES Y GOKARTS -----"
                + "\n1) Alta de un par de patines"
                + "\n2) Alta de un gokart"
                + "\n3) Alta de un cliente"
                + "\n4) Alta de una renta"
                + "\n5) Listar patines"
                + "\n6) Listar gokarts"
                + "\n7) Listar clientes"
                + "\n8) Listar rentas"
                + "\n9) Ver detalle de un par de patines"
                + "\n10) Ver detalle de un gokart"
                + "\n11) Ver detalle de un cliente"
                + "\n12) Ver detalle de una renta"
                + "\n13) Eliminar un par de patines"
                + "\n14) Eliminar un gokart"
                + "\n15) Eliminar un cliente"
                + "\n16) Eliminar una renta"
                + "\n17) Salir"
                + "\nElige una opción: ";
        //Aquí va el proceso de lectura 
                vehiculos = lectura("Vehiculos.txt");
                clientes  = lectura("Clientes.txt");
                rentas    = lectura("Rentas.txt");
        do {
            do {
                op = 0;
                try {
                    op = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "MENU", -1));
                    if (op <= 0) {
                        JOptionPane.showMessageDialog(null, "La opción debe ser positiva", "Error de entrada", 2);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La opción debe ser numérica",
                            "Error de entrada", 2);
                }

            } while (op <= 0);

            switch (op) {
                //PATINES---------------------------------
                case 1:
                    //Id Vehiculo******************
                    do {
                        idVehiculo = 0;
                        try {
                            do {
                                idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id-Vehiculo: ",
                                        "Alta de un par de patines", 3));
                                idEx = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                                if (idEx != -1) {
                                    JOptionPane.showMessageDialog(null, "Ese id de Vehiculo ya ah sido registrado..", "Warning", 2);
                                }
                            } while (idEx != -1);

                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El id-Vehiculo debe ser positivo", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id-Vehiculo debe ser numerico ", "Error de entrada", 0);
                        }

                    } while (idVehiculo <= 0);
                    idEx = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if (idEx != -1) {
                        JOptionPane.showMessageDialog(null, "Ese id de Vehiculo ya ah sido registrado..", "Warning", 2);
                        break;
                    }

                    Patines pat = new Patines();
                    pat.setIdVehiculo(idVehiculo);
                    //Modelo************************
                    do {
                        modelo = JOptionPane.showInputDialog(null, "Ingresa el modelo: ", "Alta de un par de patines", 3);
                        stval = false;
                        if (modelo == null || modelo.trim().isEmpty()
                                || !modelo.matches("^[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$")) {
                            //La expresión permite nombres como: 
                            //CX-5, A4, GT-R, Corolla Cross
                            //La expresión no permite nombres como:
                            //-GT, Focus--RS, Civic@2024
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de modelo valido",
                                    "Warning", 2);
                        } else {
                            pat.setModelo(modelo);
                            stval = true;
                        }
                    } while (stval == false);
                    //Marca***********************
                    do {
                        marca = JOptionPane.showInputDialog(null, "Ingresa la marca: ", "Alta de un par de patines", 3);
                        stval = false;
                        if (marca == null || marca.trim().isEmpty()
                                || !marca.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Ford, Mercedes-Benz, Land Rover
                            //La expresión no permite nombres como:
                            //-Ford, BMW-, Audi@@
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de marca valido",
                                    "Warning", 2);
                        } else {
                            pat.setMarca(marca);
                            stval = true;
                        }
                    } while (stval == false);
                    //Año**********************
                    do {
                        anio = 0;
                        try {
                            anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el año: ",
                                    "Alta de un par de patines", 3));
                            if (anio < 2000 || anio > 2025) {
                                JOptionPane.showMessageDialog(null, "No corresponde a un año válido (2000-2025)",
                                        "Warning", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El año debe ser numerico ", "Error de entrada", 0);
                        }
                    } while (anio < 2000 || anio > 2025);
                    pat.setAnio(anio);
                    //Color********************
                    do {
                        color = JOptionPane.showInputDialog(null, "Ingresa el color: ", "Alta de un par de patines", 3);
                        stval = false;
                        if (color == null || color.trim().isEmpty()
                                || !color.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Rojo, Rojo-Mate, Azul Marino, Gris-Plata
                            //La expresión no permite nombres como:
                            //-Rojo, -Rojo, Verde--Lima, " "Rojo-Mate
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de color valido",
                                    "Warning", 2);
                        } else {
                            pat.setColor(color);
                            stval = true;
                        }
                    } while (stval == false);
                    //Precio********************
                    do {
                        precio = 0.0f;
                        try {
                            precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa el precio",
                                    "Alta de un par de patines", 3));
                            if (precio <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "El precio de ser mayor a 0", "WARNING", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser un numero", "ERROR", 0);
                        }
                    } while (precio <= 0.0f);
                    pat.setPrecio(precio);
                    //Tipo*********************
                    do {
                        tipoPatin = JOptionPane.showInputDialog(null, "Ingresa el tipo: ", "Alta de un par de patines", 3);
                        stval = false;
                        if (tipoPatin == null || tipoPatin.trim().isEmpty()
                                || !tipoPatin.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Inline, Roller, Roller-Inline, Patín de hielo
                            //La expresión no permite nombres como:
                            //-Inline, Roller-, Patín123, " "Inline
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de tipo de patín valido",
                                    "Warning", 2);
                        } else {
                            pat.setTipo(tipoPatin);
                            stval = true;
                        }
                    } while (stval == false);
                    //Material de Bota********
                    do {
                        materialBota = JOptionPane.showInputDialog(null, "Ingresa el Material de la bota: ", "Alta de un par de patines", 3);
                        stval = false;
                        if (materialBota == null || materialBota.trim().isEmpty()
                                || !materialBota.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -/][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Cuero, Cuero Sintético, Nylon/Poliéster, Cuero-Plástico
                            //La expresión no permite nombres como:
                            //-Cuero, Cuero-, Nylon//Poliéster, Cuero123
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de material de bota valido",
                                    "Warning", 2);
                        } else {
                            pat.setMaterialBota(materialBota);
                            stval = true;
                        }
                    } while (stval == false);
                    //NoRuedas****************
                    do {
                        noRuedas = -1;
                        try {
                            noRuedas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el num de ruedas: ",
                                    "Alta de un par de patines", 3));
                            if (noRuedas < 0 || noRuedas > 8) {
                                JOptionPane.showMessageDialog(null, "El num de ruedas debe estar entre (0-8)", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El num de ruedas debe ser numerico ", "Error de entrada", 0);
                        }

                    } while (noRuedas < 1 || noRuedas > 8);
                    pat.setNoRuedas(noRuedas);
                    //Se agrega al ArrayList de Vehículos
                    vehiculos.add(pat);
                    //Se escribe los patines en el archivo vehículos, mientras comentado para no generar nada
                    escritura(vehiculos, "Vehiculos.txt");
                    break;
                case 2:
                    //NUEVO ID DEL GOKART 
                    do {
                        idVehiculo = 0;
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el ID del vehiculo: ",
                                    "Alta de un Gokart", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID debe ser positivo", "Error de entrada", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID debe contener valores numericos positivos ", "ERROR", 0);
                        }
                    } while (idVehiculo <= 0);

                    idEx = buscarIdVehiculo(idVehiculo, vehiculos, 2);
                    if (idEx != -1) {
                        JOptionPane.showMessageDialog(null, "El ID ingresado ya ha sido registrado", "WARNING", 2);
                        break;
                    }

                    Gokart gok = new Gokart();
                    gok.setIdVehiculo(idVehiculo);

                    //MODELO DEL GOKART
                    do {
                        modelo = JOptionPane.showInputDialog(null, "Ingresa el modelo: ", "Alta de un Gokart", 3);
                        stval = false;
                        if (modelo == null || modelo.trim().isEmpty() || !modelo.matches("^[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$")) {
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de modelo valido", "Warning", 2);
                        } else {
                            gok.setModelo(modelo);
                            stval = true;
                        }
                    } while (stval == false);

                    //MARCA DEL GOKART
                    do {
                        marca = JOptionPane.showInputDialog(null, "Ingresa la marca: ", "Alta de un Gokart", 3);
                        stval = false;
                        if (marca == null || marca.trim().isEmpty() || !marca.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de marca valido", "Warning", 2);
                        } else {
                            gok.setMarca(marca);
                            stval = true;
                        }
                    } while (stval == false);

                    //ALTA DEL AÑO PARA EL  GOKART
                    do {
                        anio = 0;
                        try {
                            anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el año: ", "Alta de un Gokart", 3));
                            if (anio < 2000 || anio > 2025) {
                                JOptionPane.showMessageDialog(null, "No corresponde a un año válido (2000-2025)", "Warning", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El año debe ser numerico ", "Error de entrada", 0);
                        }
                    } while (anio < 2000 || anio > 2025);
                    gok.setAnio(anio);

                    //ALTA DE COLOR DEL GOKART}
                    do {
                        color = JOptionPane.showInputDialog(null, "Ingresa el color: ", "Alta de un Gokart", 3);
                        stval = false;
                        if (color == null || color.trim().isEmpty() || !color.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de color valido", "Warning", 2);
                        } else {
                            gok.setColor(color);
                            stval = true;
                        }
                    } while (stval == false);

                    //ALTA DE PRECIO DEL GOKART
                    do {
                        precio = 0.0f;
                        try {
                            precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa el precio", "Alta de un Gokart", 3));
                            if (precio <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "El precio de ser mayor a 0", "WARNING", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser un numero", "ERROR", 0);
                        }
                    } while (precio <= 0.0f);
                    gok.setPrecio(precio);

                    //ALTA DE CILINDRADA
                    do {
                        cilindrada = 0;
                        try {
                            cilindrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cilindrada (CC): ", "Alta de un Gokart", 3));
                            if (cilindrada <= 0) {
                                JOptionPane.showMessageDialog(null, "La cilindrada debe ser positiva", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La cilindrada debe ser numerica ", "Error de entrada", 0);
                        }
                    } while (cilindrada <= 0);
                    gok.setCilindrada(cilindrada);

                    //NO. DE LLANTAS
                    do {
                        noLlantas = 0;
                        try {
                            noLlantas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa El no. de llantas", "Alta de un Gokart", 3));
                            if ( noLlantas <= 0) {
                                JOptionPane.showMessageDialog(null, "No. Llantas debe ser positiva", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "No. llantas debe ser numerica ", "Error de entrada", 0);
                        }
                    } while (noLlantas  <= 0);
                    gok.setNoLlantas(noLlantas);

                    //VEL MAXIMA
                    do {
                        velocidadMaxima = 0.0f;
                        try {
                            velocidadMaxima = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa la velocidad máxima (km/h): ", "Alta de un Gokart", 3));
                            if (velocidadMaxima <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "La velocidad debe ser mayor a 0", "WARNING", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La velocidad debe ser un numero", "ERROR", 0);
                        }
                    } while (velocidadMaxima <= 0.0f);
                    gok.setVelocidadMaxima(velocidadMaxima);

                    // Agregación de los nuevo datos al ArrayList de vehiculos
                    vehiculos.add(gok);
                    //Escritura del gokart en el archivo(De momento en comentario)
                      escritura(vehiculos, "Vehiculos.txt");

                    break;
                case 3:

                    Cliente client = new Cliente();
                    do {

                        idCliente = JOptionPane.showInputDialog(null, "Ingresa el id  del cliente: ", "Alta de un Cliente", 3);

                        stval = false;
                        if (idCliente == null || idCliente.trim().isEmpty()
                                || !idCliente.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]+$")) {
                            //La expresión permite nombres como: 
                            //GOFL2402, Roper, UAEH, Arathcpp
                            //La expresión no permite nombres como:
                            //-UAEH, GOFL2402-, Roper@, Arath--cpp
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de id valido",
                                    "Warning", 2);
                        } else {
                            client.setIdCliente(idCliente);
                            stval = true;
                        }

                    } while (stval == false);

                    do {
                        nombre = JOptionPane.showInputDialog(null, "Ingresa nombre del cliente: ", "Alta de un Cliente", 3);
                        stval = false;
                        if (nombre == null || nombre.trim().isEmpty()
                                || !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Jose Luis, Rainy, José, Alexis-Arath
                            //La expresión no permite nombres como:
                            //-Carlos, Rainy-, Arath//Alexis, Luis1234
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre valido",
                                    "Warning", 2);
                        } else {
                            client.setNombre(nombre);
                            stval = true;
                        }
                    } while (stval == false);

                    do {
                        direccion = JOptionPane.showInputDialog(null, "Ingresa dirección del cliente: ", "Alta de un Cliente", 3);
                        stval = false;
                        if (direccion == null || direccion.trim().isEmpty()
                                || !direccion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,#\\s-]+$")) {
                            //La expresión permite nombres como: 
                            //JAv. Juárez #23, Calle 5 de Mayo, Calle Hidalgo-poniente
                            //La expresión no permite nombres como:
                            //@Calle Falsa 123, --Calle Real
                            JOptionPane.showMessageDialog(null, "No corresponde a una dirección valida",
                                    "Warning", 2);
                        } else {
                            client.setDireccion(direccion);
                            stval = true;
                        }
                    } while (stval == false);

                    do {
                        identificacion = JOptionPane.showInputDialog(null, "Ingresa identificación del cliente: ", "Alta de un Cliente", 3);
                        stval = false;
                        if (identificacion == null || identificacion.trim().isEmpty()
                                || !identificacion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9]+$")) {
                            //La expresión permite nombres como: 
                            //RFCMX09, Cliente55, ID4589
                            //La expresión no permite nombres como:
                            //ABC 123, #ID4589, ID.45 
                            JOptionPane.showMessageDialog(null, "No corresponde a una identificación valida",
                                    "Warning", 2);
                        } else {
                            client.setIdentificacion(identificacion);
                            stval = true;
                        }
                    } while (stval == false);

                    do {
                        tipoCliente = JOptionPane.showInputDialog(null, "Ingresa tipo de cliente: ", "Alta de un Cliente", 3);
                        stval = false;
                        if (tipoCliente == null || tipoCliente.trim().isEmpty()
                                || !tipoCliente.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Cliente VIP, Regular, Corporativo Frecuente
                            //La expresión no permite nombres como:
                            //VIP123, Cliente_VIP, Preimum@
                            JOptionPane.showMessageDialog(null, "No corresponde a una tipo de cliente valido",
                                    "Warning", 2);
                        } else {
                            client.setTipo(tipoCliente);
                            stval = true;
                        }

                    } while (stval == false);

                    do {
                        telefono = JOptionPane.showInputDialog(null, "Ingresa telefono del cliente: ", "Alta de un Cliente", 3);
                        stval = false;
                        if (telefono == null || telefono.trim().isEmpty()
                                || !telefono.matches("^[0-9]{7,15}$")) {
                            //La expresión permite nombres como: 
                            //5512345678, 9998887777, 1234567
                            //La expresión no permite nombres como:
                            //+52 5512345678, 55123-45678, tel5512345678  
                            JOptionPane.showMessageDialog(null, "No corresponde a un telefono valido",
                                    "Warning", 2);
                        } else {
                            client.setTelefono(telefono);
                            stval = true;
                        }
                    } while (stval == false);

                    do {
                        dc = 0;
                        try {
                            dc = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese dia de nacimiento del cliente",
                                    "Alta de un cliente", 3));
                            if (dc <= 0 || dc > 31) {
                                JOptionPane.showMessageDialog(null, "El dia de nacimiento debe ser mayor a cero pero menor a 31",
                                        "Error de alta", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El dia de nacimiento debe ser numerico", "Error de alta", 2);
                        }
                    } while (dc <= 0 || dc > 31);

                    do {
                        mc = 0;
                        try {
                            mc = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese mes de nacimiento del cliente",
                                    "Alta de un cliente", 3));
                            if (mc <= 0 || mc > 12) {
                                JOptionPane.showMessageDialog(null, "El mes de nacimiento debe ser mayor a cero pero menor a 12",
                                        "Error de alta", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El mes de nacimiento debe ser numerico", "Error de alta", 2);
                        }
                    } while (mc <= 0 || mc > 12);

                    do {
                        ac = 0;
                        try {
                            ac = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese año de nacimiento del cliente",
                                    "Alta de un cliente", 3));
                            if (ac >= 2011 || ac <= 1925) {
                                JOptionPane.showMessageDialog(null, "El año de nacimiento debe ser menor al 2010 y mayor a 1920",
                                        "Error de alta", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El año de nacimiento debe ser numerico", "Error de alta", 2);
                        }
                    } while (ac >= 2011 || ac <= 1925);
                    client.setFechaNacimiento(dc, mc, ac);
                    clientes.add(client);
                    //Se escribe los clientes en el archivo Clientes, mientras comentado para no generar nada
                    escritura(clientes, "Clientes.txt");

                    break;
                case 4: // Alta de una Renta 
                    do {
                        idRenta = 0;
                        try {
                            do {
                                idRenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id-Renta: ",
                                        "Alta de una Renta", 3));
                                idEx = buscarIdRenta(idRenta, rentas);
                                if (idEx != -1) {
                                    JOptionPane.showMessageDialog(null, "Ese id de Renta ya ah sido registrado..", "Warning", 2);
                                }
                            } while (idEx != -1);

                            if (idRenta <= 0) {
                                JOptionPane.showMessageDialog(null, "El id-Renta debe ser positivo", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id-Renta debe ser numérico ", "Error de entrada", 0);
                        }
                    } while (idRenta <= 0);
                    Renta ren  = new Renta();
                    ren.setIdRenta(idRenta);
                    //ID VEHÍCULO
                    do {
                        idVehiculo = 0;
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id-Vehiculo: ",
                                         "Alta de una renta", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El id-Vehiculo debe ser positivo", "Error", 0);
                            }else{
                                idEx = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                                idEx2 = buscarIdVehiculo(idVehiculo, vehiculos, 2);
                                if (idEx == -1 && idEx2 == -1) {
                                    JOptionPane.showMessageDialog(null, "Ese id de Vehiculo No ah sido registrado..", "Warning", 2);
                                    idVehiculo = -1;
                                }
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id-Vehiculo debe ser numerico ", "Error de entrada", 0);
                        }
                    } while (idVehiculo <= 0);
                    ren.setIdVehiculo(idVehiculo);
                    //ID CLIENTE*********************
                    do {

                        idCliente = JOptionPane.showInputDialog(null, "Ingresa el id  del cliente: ", "Alta de una Renta", 3);

                        stval = false;
                        if (idCliente == null || idCliente.trim().isEmpty()
                                || !idCliente.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]+$")) {
                            //La expresión permite nombres como: 
                            //GOFL2402, Roper, UAEH, Arathcpp
                            //La expresión no permite nombres como:
                            //-UAEH, GOFL2402-, Roper@, Arath--cpp
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de id valido",
                                    "Warning", 2);
                        } else {
                            idEx = buscarIdCliente(idCliente, clientes);
                            if(idEx == -1){
                                JOptionPane.showMessageDialog(null, "Ese id de Cliente No ah sido registrado..", "Warning", 2);
                            }else{
                                stval = true;
                            }
                        }

                    } while (stval == false);
                    ren.setIdCliente(idCliente);
                    //FECHA*************************
                        do {
                            dc = 0;
                            try {
                                dc = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese día de la fecha-Renta",
                                        "Alta de una renta", 3));
                                if (dc <= 0 || dc > 31) {
                                    JOptionPane.showMessageDialog(null, "El día de la fecha-Renta debe ser mayor a cero pero menor a 31",
                                            "Error de alta", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "El día de fecha-Renta debe ser numérico", "Error de alta", 2);
                            }
                        } while (dc <= 0 || dc > 31);

                        do {
                            mc = 0;
                            try {
                                mc = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese mes de la fecha-Renta",
                                        "Alta de una Renta", 3));
                                if (mc <= 0 || mc > 12) {
                                    JOptionPane.showMessageDialog(null, "El mes de la fecha-Renta debe ser mayor a cero pero menor a 12",
                                            "Error de alta", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "El mes de la fecha-Renta debe ser numérico", "Error de alta", 2);
                            }
                        } while (mc <= 0 || mc > 12);

                        do {
                            ac = 0;
                            try {
                                ac = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese año de la fecha-Renta",
                                        "Alta de un cliente", 3));
                                if (ac < 2023 || ac > 2025) {
                                    JOptionPane.showMessageDialog(null, "El año de la fecha-Renta debe estar entre (2023-2025)",
                                            "Error de alta", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "El año de la fecha-Renta debe ser numerico", "Error de alta", 2);
                            }
                        } while (ac < 2023 || ac > 2025);
                        ren.setFechaRenta(dc, mc, ac);
                    //HORA INICIO ************************
                    do{
                        stval = false;
                        do{
                            num1 = 0;
                            try{
                                num1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la hora de Inicio: ", "Alta de una renta", 3));
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "La hora de Inicio debe ser numérica", "Error de alta", 2);
                            }
                        }while(num1 <= 0);
                        do{
                            num2 = 0;
                            try{
                                num2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa los minutos de la Hora de Inicio: ", "Alta de una renta", 3));
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "los minutos de la Hora de Inicio deben ser numéricos", "Error de alta", 2);
                            }
                        }while(num2 < 0);
                        horaInicio.setHora(num1, num2);
                        valH = horaInicio.horaCorrecta();
                        if(valH){
                            stval = true;
                        }
                    }while(stval == false);
                    ren.setHoraInicio(horaInicio);
                    //HORA FINAL ************************
                    do{
                        stval = false;
                        do{
                            num1 = 0;
                            try{
                                num1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la hora del Fin: ", "Alta de una renta", 3));
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "La hora del Fin debe ser numérica", "Error de alta", 2);
                            }
                        }while(num1 <= 0);
                        do{
                            num2 = 0;
                            try{
                                num2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa los minutos de la Hora del Fin: ", "Alta de una renta", 3));
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "los minutos de la Hora del Fin deben ser numéricos", "Error de alta", 2);
                            }
                        }while(num2 < 0);
                        horaFinal.setHora(num1, num2);
                        valH = horaFinal.horaCorrecta();
                        if(valH){
                            stval = true;
                        }
                    }while(stval == false);
                    ren.setHoraFinal(horaFinal);
                    rentas.add(ren);
                    escritura(rentas, "Rentas.txt");
                    break;
               
                case 5:
                    if(vehiculos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay vehiculos para listar...", "Error", 2);
                        break;
                    }
                    lista = String.format("%-10s %-20s %-10s %-10s %-15s %-15s %-8s\n",
                                        "Id", "Modelo", "Color", "Precio", "Tipo", "Material", "Ruedas");
                    lista += "-----------------------------------------------------------------------------------------------------------------\n";
                    for (Vehiculo v : vehiculos) {
                        if (v instanceof Patines) {
                            Patines p = (Patines) v;
                            lista += String.format("%-10d %-20s %-10s %-10.2f %-15s %-15s %-8d\n",
                                    p.getIdVehiculo(),
                                    p.getModelo(),
                                    p.getColor(),
                                    p.getPrecio(),
                                    p.getTipo(),
                                    p.getMaterialBota(),
                                    p.getNoRuedas());
                        }
                    }
                    //hola papus
                    JOptionPane.showMessageDialog(null, lista, "LISTA DE PATINES", 1);
                    
                    break;
                case 6:
                    if(vehiculos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay vehiculos para listar...", "Error", 2);
                        break;
                    }
                    lista = String.format("%-10s %-20s %-10s %-10s %-10s %-8s\n",
                                        "IdVehiculo", "Modelo", "Color", "Precio", "Cilindradas", "No.Llantas");
                    lista += "-----------------------------------------------------------------------------------------------------------------\n";
                    for (Vehiculo v : vehiculos) {
                        if (v instanceof Gokart) {
                            Gokart p = (Gokart) v;
                            lista += String.format("%-10d %-20s %-10s %-10.2f %-10s %-8d\n",
                                    p.getIdVehiculo(),
                                    p.getModelo(),
                                    p.getColor(),
                                    p.getPrecio(),
                                    p.getCilindrada(),
                                    p.getNoLlantas());
                        }
                    }
                    JOptionPane.showMessageDialog(null, lista, "LISTA DE GOKARTS", 1);
                    break;
                case 7: 
                    if(clientes.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay clientes para listar...", "Error", 2);
                        break;
                    }
                    lista = String.format("%-10s %-20s %-20s %-20s %-15s %-15s\n",
                                        "IdCliente", "Nombre", "Identificación", "Dirección", "Tipo", "Telefono");
                    lista += "-----------------------------------------------------------------------------------------------------------------\n";
                    for (Cliente v : clientes) {
                            lista += String.format("%-10s %-20s %-20s %-20s %-15s %-15s\n",
                                    v.getIdCliente(),
                                    v.getNombre(),
                                    v.getIdentificacion(),
                                    v.getDireccion(),
                                    v.getTipo(),
                                    v.getTelefono());
                        }
                    JOptionPane.showMessageDialog(null, lista, "LISTA DE CLIENTES", 1);
                    break;
                case 8:
                    if(rentas.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay rentas para listar...", "Error", 2);
                        break;
                    }
                    lista = String.format("%-10s %-20s %-10s %-10s %-15s %-15s %-8s\n",
                                        "IdRenta", "IdVehiculo", "IdCliente", "Fecha", "Renta", "Hora", "Inicio");
                    lista += "-----------------------------------------------------------------------------------------------------------------\n";
                    for (Renta v : rentas) {
                            lista += String.format("%-10d %-10s %-15s %-15s %-15s\n",
                                    v.getIdRenta(),
                                    v.getIdVehiculo(),
                                    v.getIdCliente(),
                                    v.getFechaRenta(),
                                    v.getHoraInicio());
                        }
                    JOptionPane.showMessageDialog(null, lista, "LISTA DE RENTAS", 1);
                    break;

                case 9:
                    // Consultar detalles de unos patines
                    if (vehiculos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay vehiculos(patines) existentes", "Sin datos", 2);
                        break;
                    }
                    idVehiculo = -1;
                    stval = false;
                    do {
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de los patines a consultar: ",
                                    "Detalles de patines", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID debe ser positivo", "Error de entrada", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID solo debe contener valores NUMÉRICOS POSITIVOS",
                                    "ERROR", 2);
                            break;
                        }
                    } while (!stval);
                    posPatines = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if (posPatines == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontraron patines con el ID ingresado",
                                "Error de búsqueda", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, vehiculos.get(posPatines).getDatos(), "Detalles de patines", 1);
                    }
                    break;

                case 10:
                    // Consultar dealles de un gokart
                    if (vehiculos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay vehiculos(Gokarts) existentes", "Sin datos", 2);
                        break;
                    }
                    idVehiculo = -1;
                    stval = false;
                    do {
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del Gokart a consultar: ",
                                    "Detalles de Gokart", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID debe tener valores positivos", "Error de entrada", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID solo puede contener valores NUMÉRICOS POSITIVOS",
                                    "ERROR", 2);
                            break;
                        }
                    } while (!stval);

                    posGokart = buscarIdVehiculo(idVehiculo, vehiculos, 2);
                    if (posGokart == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontraron Gokarts con el ID ingresado",
                                "Error de búsqueda", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, vehiculos.get(posGokart).getDatos(), "Detalles de Gokart", 2);
                    }
                    break;

                case 11:
                    //CONSULTAR DETALLES DE UN CLIENTE
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes registrados", "Sin datos", 2);
                        break;
                    }
                    idCliente = ""; //Se usa comilla para inicializar en vacio
                    stval = false;
                    do {
                        idCliente = JOptionPane.showInputDialog(null, " Ingrese el ID del cliente a consultar: ", "Detalles de un cliente", 3);
                        if (idCliente == null || idCliente.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La clave de cliente no puede ser vacia...", "Error de entrada", 2);
                        } else {
                            stval = true;
                        }
                    } while (!stval);

                    //Metodo de busqueda del cliente
                    posCliente = buscarIdCliente(idCliente, clientes);
                    if (posCliente == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro un cliente con ID ingresado", "Fallo de busqueda", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, clientes.get(posCliente).getDatos(), "Detalles del cliente", 1);
                    }
                    break;
                case 12:
                    //Consultar una renta por ID
                    if (rentas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay rentas registradas...", "Sin datos", 2);
                        break;
                    }
                    idRenta = 0;
                    stval = false;
                    do{
                        //Asignamos una variable auxiliar para el ingreso del ID y asi poder lograr cancelar el metodo si el usuario cancela
                        auxID = JOptionPane.showInputDialog(null,"Ingresel el ID  de la renta a consultar: ","Cetalle de una renta",3);
                        if(auxID == null){
                            idRenta = -1;
                            stval = true;
                            //Si es vacio la opción es decir, cancelar esto nos regresa al menu principal
                        }else{
                            //Lado contrario si el ID no es un vacio procede a asignarse la variable auxiliar en un Integer para proceder con la conversion de texto a dato num.
                            try{
                                idRenta = Integer.parseInt(auxID);
                                if(idRenta <= 0){
                                    JOptionPane.showMessageDialog(null, "El ID de la renta debe ser positivo","Warning",2);
                                }else{
                                    stval = true;
                                }
                            }catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "El ID a ingresar debe ser numérico","ERROR",2);
                            }
                        }
                    }while(!stval);
                    if(idRenta <= 0) break; //Aseguramos que se cierre o se haga el salto del case si no se ingreso un ID valido
                    //Se evita la busqueda con un ID menor a cero y asegurar que no se generen errores 
                    
                    posRenta = buscarIdRenta(idRenta, rentas);
                    if(posRenta == -1){
                        JOptionPane.showMessageDialog(null,"No se encontro una renta con el ID ingresado", "Busqueda fallida",2);
                    }else{
                        Renta rentaConsultada = rentas.get(posRenta);
                        
                        //Obtencion de detalles del vehiculo con la renta
                        IdVeRenta = rentaConsultada.getIdVehiculo();
                        posVe = buscarIdVehiculo(IdVeRenta, vehiculos,1);//Buscar patines rentados
                        if(posVe == -1){
                            posVe = buscarIdVehiculo(IdVeRenta, vehiculos, 2);//O buscar gokart rentado
                        }
                        
                        if(posVe != -1){
                            DatosVe =vehiculos.get(posVe).getDatos();
                        }else{
                            DatosVe = "Error: El vehiculo asociado con el ID ingresado no se ha encontrado";
                        }
                        
                        //Metodo para obtener detalles del cliente
                        idClienteRenta = rentaConsultada.getIdCliente();
                        posCli = buscarIdCliente(idClienteRenta, clientes);
                        
                        if(posCli != -1){
                            datosCliente = clientes.get(posCli).getDatos();
                        }else{
                            datosCliente = "Error: El ID ingresado de renta no tiene asociadoo un cliente";
                        }
                        
                        //Obtener datos de la renta
                        datosRenta = rentaConsultada.getDatos().replace("ID renta:", "\nId renta:");
                        
                        msjFinal = "---DETALLE DE UNA RENTA---\n"+
                                   DatosVe +"\n" +
                                   datosCliente +"\n" +
                                   "---DATOS DE LA RENTA---\n" +
                                   datosRenta;
                        
                        JOptionPane.showMessageDialog(null, msjFinal,"Detalles de renta",1);
                    }
                    break;
               
                // eliminar patines
                case 13:
                    //Buscar si el arreglo se encuentra vacio
                    if (vehiculos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay datos registrados", "Sin datos", 2);
                        break;
                    }

                    //stval sirve para marcar si exsite el id o no
                    stval = false;
                    idVehiculo = -1;

                    //ciclo para repetir hasta que el id sea valido
                    do {
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id de los patines",
                                    "Eliminación de unos patines", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El id debe de ser positivo", "Error de eliminación", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id debe ser numerico...", "Error de eliminación", 2);
                            break;

                        }
                    } while (!stval);

                    //si el id existe realizara...
                    cont = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if (cont == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro el id...", "Error", 2);
                    } else {

                        //se usa un for para ciclar entre los datos guardados de lsa renta
                        //re realiza por si el cliente no ingresa la renta despues de agregar su vehiculo
                        for (int i = 0; i < rentas.size(); i++) {
                            //se compara con el idvehiculo registrado en rentas con el idvehiculo para  que solo se borre
                            //el archivo del vehiculo elegido
                            if (rentas.get(i).getIdVehiculo() == idVehiculo) {
                                rentas.remove(i);
                                i--;
                            }
                        }
                        vehiculos.remove(cont);
                        escritura(vehiculos, "Vehiculos.txt");
                        JOptionPane.showMessageDialog(null, "Patines eliminados correctamente... ", "Eliminación exitosa", 3);
                    }
                    break;

                //eliminar gokarts
                case 14:
                    //verifica que el arreglo tenga datos
                    if (vehiculos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay datos registrados", "Sin datos", 2);
                        break;
                    }

                    //stval sirve para marcar si exsite el id o no
                    stval = false;
                    idVehiculo = -1;

                    //ciclo para repetir hasta que el id sea valido
                    do {
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del gokart",
                                    "Eliminación de gokart", 3));
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El id debe de ser positivo...", "Error...", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id debe ser numerico...", "Error...", 2);
                            break;
                        }
                    } while (!stval);

                    //si el id existe realizara...
                    cont = buscarIdVehiculo(idVehiculo, vehiculos, 2);
                    if (cont == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro el id...", "Error...", 2);
                        break;
                    } else {

                        //se usa un for para ciclar entre los datos guardados de lsa renta
                        //re realiza por si el cliente no ingresa la renta despues de agregar su vehiculo
                        for (int i = 0; i < rentas.size(); i++) {
                            //se compara con el idvehiculo registrado en rentas con el idvehiculo para  que solo se borre
                            //el archivo del vehiculo elegido
                            if (rentas.get(i).getIdVehiculo() == idVehiculo) {
                                rentas.remove(i);
                                i--;
                            }
                        }
                        vehiculos.remove(cont);
                        escritura(vehiculos, "Vehiculos.txt");
                        JOptionPane.showMessageDialog(null, "Gokart eliminado correctamente... ", "Eliminación exitosa", 3);
                    }
                    break;

                //eliminar clientes
                case 15:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay datos existentes", "Sin datos", 2);
                        break;
                    }
                    idCliente = "";
                    stval = false;

                    do {
                        idCliente = JOptionPane.showInputDialog(null, "Ingresa id del cliente", 
                                                                "Eliminación de un cliente", 3);
                        if (idCliente == null || idCliente.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El id cliente no puede estar vacio...", "Error...", 2);
                        } else {
                            stval = true;
                        }
                    } while (!stval);

                    posCliente = buscarIdCliente(idCliente, clientes);
                    if (posCliente == -1) {
                        JOptionPane.showMessageDialog(null, "El id no existe...", "Error...", 2);
                    } else {
                        for (int i = 0; i < rentas.size(); i++) {
                            if (rentas.get(i).getIdCliente().equals(idCliente)) {
                                rentas.remove(i);
                                i--;
                            }
                        }
                        clientes.remove(posCliente);
                        JOptionPane.showMessageDialog(null, "El Cliente ha sido borrado exitosamente....",
                                                      "Eliminación exitosa", 3);
                    }
                    escritura(clientes,"Clientes.txt");
                    break;

                //eliminar rentas
                case 16:
                    if (rentas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay datos existentes", "Sin datos", 2);
                        break;
                    }
                    idRenta = -1;
                    stval = false;
                    
                    do {
                        try {
                            idRenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id",
                                    "Eliminación de una renta", 3));
                            if (idRenta == 0) {
                                JOptionPane.showInputDialog(null, "El id debe ser mayor a cero...", "Error...", 2);
                            }else{
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id debe de ser numerico...", "Error...", 2);
                        }
                        break;
                    } while (!stval);

                    cont = buscarIdRenta(idRenta, rentas);
                    if (cont == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro el id...", "Error...", 2);
                    } else {
                        rentas.remove(cont);
                        escritura(rentas, "Rentas.txt");
                        JOptionPane.showMessageDialog(null, "La renta ha sido eliminada exitosamente...",
                                                      "Eliminación exitosa", 3);
                    }
                    break;
                    
                case 17:
                    
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "La opción no existe...", "Error", 2);
                    break;
                
            }
        } while (op != 17);

    }

    //FUNCION DE BUSQUEDA POR ID DE VEHICULOS
    public static int buscarIdVehiculo(int id, ArrayList<Vehiculo> V, int tipoV) {
        // tipoV es para asignarle un valor a cada clase hija de vehiculo
        // Patines = 1, Gokart = 2
        for (int i = 0; i < V.size(); i++) {
            Vehiculo v1 = V.get(i);
            if (v1.getIdVehiculo() == id) {
                if (tipoV == 1 && v1 instanceof Patines) {
                    return i;
                } else if (tipoV == 2 && v1 instanceof Gokart) {
                    return i;
                }
            }
        }
        return -1;
    }

    //FUNCION DE BUSQUEDA POR ID DE RENTAS
    public static int buscarIdRenta(int id, ArrayList<Renta> rentas) {
        int pos = -1;
        int cont = 0;
        for (Renta ren : rentas) {
            if (id == ren.getIdRenta()) {
                return cont;
            }
            cont++;
        }
        return pos;
    }

    //FUNCION DE BUSQUEDA POR CLAVE DE CLIENTES
    public static int buscarIdCliente(String clave, ArrayList<Cliente> clientes) {
        int pos = -1;
        int cont = 0;
        for (Cliente cli : clientes) {
            if (clave.trim().equalsIgnoreCase(cli.getIdCliente())) {
                return cont;
            }
            cont++;
        }
        return pos;
    }

    //FUNCION DE ESCRITURA 
    public static <T> void escritura(ArrayList<T> lista, String nomArchivo) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(nomArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(fout);
            salida.writeObject(lista);
            JOptionPane.showMessageDialog(null, "Objetos guardados en el archivo....");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo para escritura.\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de Entrada/Salida.\n" + e.getMessage());
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo" + e.getMessage());
            }
        }
    }

    //FUNCION DE LECTURA
    public static <T> ArrayList<T> lectura(String nomArchivo) {
        ArrayList<T> lista = new ArrayList<>();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(nomArchivo);
            ObjectInputStream entrada = new ObjectInputStream(fin);
            lista = (ArrayList<T>) entrada.readObject();
        } catch (EOFException e) {
            JOptionPane.showMessageDialog(null, "Se encontro el fin de archivo.\n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro la clase para lectura.\n" + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo para lectura.\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de Entrada/Salida.\n" + e.getMessage());
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo" + e.getMessage());
            }
        }
        return lista;
    }

}
