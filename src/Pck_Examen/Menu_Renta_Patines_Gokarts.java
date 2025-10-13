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
        int idVehiculo, anio, cilindrada, noLLantas, noRuedas, idRenta, op, idEx = -1, cont, posPatines, posGokart;
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
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id-Vehiculo: ",
                                    "Alta de un par de patines", 3));
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

                    break;
                case 3:
                    break;
                case 4:
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
                    }
                    idVehiculo = -1;
                    stval = false;
                    do {
                        try {
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID de los patines a consultar: ",
                                    "Detalles de patines", 3));
                            if(idVehiculo <= 0){
                                JOptionPane.showMessageDialog(null, "El ID debe ser positivo", "Error de entrada", 2);
                            }else{
                                stval = true;
                            }
                        }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "El ID solo debe contener valores NUMÉRICOS POSITIVOS", 
                                    "ERROR", 2);
                        }
                    }while(!stval);
                    posPatines = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if(posPatines == -1){
                        JOptionPane.showMessageDialog(null,"No se encontraron patines con el ID ingresado", 
                                "Error de búsqueda", 2);
                    }else{
                        JOptionPane.showMessageDialog(null, vehiculos.get(posPatines).getDatos(), "Detalles de patines", 1);
                    }
                    break;
                    
                case 10:
                    // Consultar dealles de un gokart
                    if(vehiculos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay vehiculos(Gokarts) existentes", "Sin datos", 2);
                    }
                    idVehiculo = -1;
                    stval = false;
                    do{
                        try{
                            idVehiculo = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del Gokart a consultar: ",
                                    "Detalles de Gokart", 3));
                            if(idVehiculo <= 0){
                                JOptionPane.showMessageDialog(null, "El ID debe tener valores positivos", "Error de entrada", 2);
                            }else{
                                stval = true;
                            }
                        }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "El ID solo puede contener valores NUMÉRICOS POSITIVOS",
                                    "ERROR", 2);
                        }
                    }while(!stval);
                    
                    posGokart = buscarIdVehiculo(idVehiculo, vehiculos, 2);
                    if(posGokart == -1){
                        JOptionPane.showMessageDialog(null,"No se encontraron Gokarts con el ID ingresado", 
                                "Error de búsqueda", 2);
                    }else{
                        JOptionPane.showMessageDialog(null,vehiculos.get(posGokart).getDatos(), "Detalles de Gokart", 2);
                    }
                    break;
                    
                case 11:
                    break;
                case 12:
                    break;
                // fin de la parte de arath

                // inicio de la parte de luis
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

                        }
                    } while (!stval);
                    //si el id existe realizara...
                    cont = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if (cont == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro el id...", "Error", 2);
                    } else {
                        vehiculos.remove(cont);
                        JOptionPane.showMessageDialog(null, "Patines eliminados correctamente... ", "Eliminación exitosa", 3);
                    }
                    break;
                case 14:
                    //verifica que el arregla tenga datos
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
                                JOptionPane.showMessageDialog(null, "El id debe de ser positivo", "Error de eliminación", 2);
                            } else {
                                stval = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id debe ser numerico...", "Error de eliminación", 2);
                        }
                    } while (!stval);
                    //si el id existe realizara...
                    cont = buscarIdVehiculo(idVehiculo, vehiculos, 1);
                    if (cont == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontro el id...", "Error", 2);
                        break;
                    } else {
                        vehiculos.remove(cont);
                        JOptionPane.showMessageDialog(null, "Gokart eliminado correctamente... ", "Eliminación exitosa", 3);
                    }
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                default:
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
