package Pck_Examen;

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
        int idVehiculo, anio, cilindrada, noLLantas, noRuedas, idRenta, op, idEx = -1, cont = -1, posPatines, posGokart, posCliente, posRenta;
        int dc = 0, mc = 0, ac = 2011;
        String modelo, marca, color, tipoPatin, materialBota, idCliente, nombre,
                direccion, identificacion, tipoCliente, telefono;
        float precio, velocidadMaxima;
        Fecha fechaNacimiento, fechaRenta;
        Hora horaInicio, horaFinal;
        boolean stval;
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

                    } while (noRuedas < 0 || noRuedas > 8);
                    pat.setNoRuedas(noRuedas);
                    //Se agrega al ArrayList de Vehículos
                    vehiculos.add(pat);
                    //Se escribe los patines en el archivo vehículos, mientras comentado para no generar nada
                    //escritura(vehiculos, "Vehiculos.txt");
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
//                    escritura(vehiculos, "Vehiculos.txt");

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
                    //escritura(clientes, "Clientes.txt");

                    break;
                case 4: // Alta de una Renta 
                    Renta renta = new Renta();
                    int dh = 0,
                     mh = 0,
                     ah = 0,
                     hh = 0,
                     minH = 0;
                    idVehiculo = 0;
                    idCliente = "";

                    // 1. ID de la Renta
                    do {
                        idRenta = 0;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Ingresa el ID de la Renta:", "Alta de una Renta", 3);
                            if (input == null) {
                                idRenta = -1;
                                break;
                            } // Manejo de Cancelar
                            idRenta = Integer.parseInt(input);
                            if (idRenta <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID de la Renta debe ser positivo.", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID de la Renta debe ser numérico.", "Error de entrada", 0);
                        }
                    } while (idRenta <= 0);
                    if (idRenta == -1) {
                        break; // Sale si se canceló
                    }
                    // Verificar si el ID de Renta ya existe
                    idEx = buscarIdRenta(idRenta, rentas);
                    if (idEx != -1) {
                        JOptionPane.showMessageDialog(null, "Ese ID de Renta ya ha sido registrado.", "Advertencia", 2);
                        break;
                    }
                    renta.setIdRenta(idRenta);

                    // 2. ID del Cliente (Verificación de Existencia)
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes registrados.", "Error", 2);
                        break;
                    }

                    idCliente = JOptionPane.showInputDialog(null, "Ingresa el ID del Cliente:", "Alta de una Renta", 3);
                    if (idCliente == null) {
                        break;
                    }

                    posCliente = buscarIdCliente(idCliente, clientes);

                    if (posCliente == -1) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado. Renta cancelada.", "Error", 2);
                        break;
                    }
                    renta.setIdCliente(idCliente);

                    // 3. ID del Vehículo (Verificación de Existencia)
                    if (vehiculos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay vehículos (Patines o Gokarts) registrados para rentar.", "Error", 2);
                        break;
                    }

                    do {
                        idVehiculo = 0;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Ingresa el ID del Vehículo:", "Alta de una Renta", 3);
                            if (input == null) {
                                idVehiculo = -1;
                                break;
                            } // Manejo de Cancelar
                            idVehiculo = Integer.parseInt(input);
                            if (idVehiculo <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID del Vehículo debe ser positivo.", "Error", 0);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID del Vehículo debe ser numérico.", "Error de entrada", 0);
                        }
                    } while (idVehiculo <= 0);

                    if (idVehiculo == -1) {
                        break; // Sale si se canceló
                    }
                    int posVehiculo = buscarIdVehiculo(idVehiculo, vehiculos, 1);

                    if (posVehiculo == -1) {
                        JOptionPane.showMessageDialog(null, "Vehículo no encontrado. Renta cancelada.", "Error", 2);
                        break;
                    }
                    renta.setIdVehiculo(idVehiculo);

                    // 4. Fecha de Renta (Día, Mes, Año)
                    JOptionPane.showMessageDialog(null, "Ingresa la Fecha de Renta (Día/Mes/Año):", "Fecha de Renta", 3);

                    // Días
                    do {
                        dh = 0;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Día (1-31):", "Fecha de Renta", 3);
                            if (input == null) {
                                dh = -1;
                                break;
                            }
                            dh = Integer.parseInt(input);
                            if (dh <= 0 || dh > 31) {
                                JOptionPane.showMessageDialog(null, "Día no válido (1-31).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El día debe ser numérico.", "Error de entrada", 2);
                        }
                    } while (dh <= 0 || dh > 31);
                    if (dh == -1) {
                        break;
                    }

                    // Mes
                    do {
                        mh = 0;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Mes (1-12):", "Fecha de Renta", 3);
                            if (input == null) {
                                mh = -1;
                                break;
                            }
                            mh = Integer.parseInt(input);
                            if (mh <= 0 || mh > 12) {
                                JOptionPane.showMessageDialog(null, "Mes no válido (1-12).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El mes debe ser numérico.", "Error de entrada", 2);
                        }
                    } while (mh <= 0 || mh > 12);
                    if (mh == -1) {
                        break;
                    }

                    // Año
                    do {
                        ah = 0;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Año (2020-2025):", "Fecha de Renta", 3);
                            if (input == null) {
                                ah = -1;
                                break;
                            }
                            ah = Integer.parseInt(input);
                            if (ah < 2020 || ah > 2025) {
                                JOptionPane.showMessageDialog(null, "Año no válido (2020-2025).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El año debe ser numérico.", "Error de entrada", 2);
                        }
                    } while (ah < 2020 || ah > 2025);
                    if (ah == -1) {
                        break;
                    }

                    // Se asume constructor Fecha(dia, mes, anio)
                    fechaRenta = new Fecha(dh, mh, ah);
                    renta.setFechaRenta(fechaRenta);

                    // 5. Hora de Inicio
                    JOptionPane.showMessageDialog(null, "Ingresa la Hora de Inicio:", "Hora de Inicio", 3);

                    // Hora Inicio
                    do {
                        hh = -1;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Hora (0-23):", "Hora de Inicio", 3);
                            if (input == null) {
                                hh = -1;
                                break;
                            }
                            hh = Integer.parseInt(input);
                            if (hh < 0 || hh > 23) {
                                JOptionPane.showMessageDialog(null, "Hora no válida (0-23).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La hora debe ser numérica.", "Error de entrada", 2);
                        }
                    } while (hh < 0 || hh > 23);
                    if (hh == -1) {
                        break;
                    }

                    // Minuto Inicio
                    do {
                        minH = -1;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Minuto (0-59):", "Hora de Inicio", 3);
                            if (input == null) {
                                minH = -1;
                                break;
                            }
                            minH = Integer.parseInt(input);
                            if (minH < 0 || minH > 59) {
                                JOptionPane.showMessageDialog(null, "Minuto no válido (0-59).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El minuto debe ser numérico.", "Error de entrada", 2);
                        }
                    } while (minH < 0 || minH > 59);
                    if (minH == -1) {
                        break;
                    }

                    // Se asume constructor Hora(hora, minuto)
                    horaInicio = new Hora(hh, minH);
                    renta.setHoraInicio(horaInicio);

                    // 6. Hora de Finalización
                    JOptionPane.showMessageDialog(null, "Ingresa la Hora de Finalización:", "Hora de Finalización", 3);

                    // Hora Final
                    do {
                        hh = -1;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Hora (0-23):", "Hora de Finalización", 3);
                            if (input == null) {
                                hh = -1;
                                break;
                            }
                            hh = Integer.parseInt(input);
                            if (hh < 0 || hh > 23) {
                                JOptionPane.showMessageDialog(null, "Hora no válida (0-23).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La hora debe ser numérica.", "Error de entrada", 2);
                        }
                    } while (hh < 0 || hh > 23);
                    if (hh == -1) {
                        break;
                    }

                    // Minuto Final
                    do {
                        minH = -1;
                        try {
                            String input = JOptionPane.showInputDialog(null, "Minuto (0-59):", "Hora de Finalización", 3);
                            if (input == null) {
                                minH = -1;
                                break;
                            }
                            minH = Integer.parseInt(input);
                            if (minH < 0 || minH > 59) {
                                JOptionPane.showMessageDialog(null, "Minuto no válido (0-59).", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El minuto debe ser numérico.", "Error de entrada", 2);
                        }
                    } while (minH < 0 || minH > 59);
                    if (minH == -1) {
                        break;
                    }

                    // Se asume constructor Hora(hora, minuto)
                    horaFinal = new Hora(hh, minH);
                    renta.setHoraFinal(horaFinal);

                    // 7. Agregar Renta a la lista
                    rentas.add(renta);
                    // Asumiendo que Renta tiene un método para obtener detalles
                    JOptionPane.showMessageDialog(null, "Renta registrada con éxito.\n" + renta.getDatos(), "Alta Exitosa", 1);

                    break;
                // inicio de la parte de joshua
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                // fin de la parte de joshua

                // inicio de la parte de arath
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
                    do {
                        try {
                            idRenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de renta al consultar: ", "Consultar unas renta", 3));
                            if (idRenta <= 0) {
                                JOptionPane.showMessageDialog(null, "El ID para la renta a consultar debe ser positiva", "Error de entrada", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El ID para la renta a consultar debe contener valores numericos", "ERROR", 2);
                        }
                    } while (!stval);

                    posRenta = buscarIdRenta(idRenta, rentas);
                    if (posRenta == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro una renta con el ID ingresado", "Fallo de busqueda", 2);
                    }
                    break;
                // fin de la parte de arath

                // inicio de la parte de luis
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
                        JOptionPane.showMessageDialog(null, "La renta ha sido eliminada exitosamente...",
                                                      "Eliminación exitosa", 3);
                    }
                    break;
                    
                case 17:
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "La opción no existe...", "Error", 2);
                    break;
                //fin de la parte de luis
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
    public static <T> ArrayList<T> lectura(ArrayList<T> lista, String nomArchivo) {
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
