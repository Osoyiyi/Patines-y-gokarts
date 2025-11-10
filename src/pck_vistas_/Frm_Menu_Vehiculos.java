
package pck_vistas_;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pck_datos_.RentasPG_DB;

public class Frm_Menu_Vehiculos extends javax.swing.JFrame {
    private final RentasPG_DB RPGDB;
    public Frm_Menu_Vehiculos() {
        initComponents();
        RPGDB = new RentasPG_DB();
        this.setLocationRelativeTo(null);
        this.limpiar();
        this.listar(-1);
    }
    private void listar(int temp){
        if(temp == -1){
            
        }else if(temp == 0){
            tb_datosV.setModel(RPGDB.getDatosVG());
        }else if(temp == 1){
            tb_datosV.setModel(RPGDB.getDatosVP());
        }
    }
    private void limpiar(){
        ct_idVehiculo.setText("");
        ct_Modelo.setText("");
        ct_Precio.setText("");
        ct_Marca.setText("");
        ct_Color.setText("");
        ct_Cilindrada.setText("");
        ct_velocidadMaxima.setText("");
        ct_Tipo.setText("");
        ct_materialBota.setText("");
        jyc_Anio.setYear(0);
        cmb_tipoVehiculo.setSelectedIndex(-1);
        cmb_noLLantas.setSelectedIndex(-1);
        cmb_noRuedas.setSelectedIndex(-1);
        tb_datosV.clearSelection();
        ct_idVehiculo.setEditable(false);
        ct_Modelo.setEditable(false);
        ct_Precio.setEditable(false);
        ct_Marca.setEditable(false);
        ct_Color.setEditable(false);
        ct_Cilindrada.setEditable(false);
        ct_velocidadMaxima.setEditable(false);
        ct_Tipo.setEditable(false);
        ct_materialBota.setEditable(false);
        jyc_Anio.setEnabled(false);
        cmb_noLLantas.setEnabled(false);
        cmb_noRuedas.setEnabled(false);
        ct_parametroV.setText("");
        ct_parametroV.setEditable(false);
    }
    private void agregar_actualizar(boolean agregar){
        int idVehiculo = 0, anio = 0, cilindrada = 0, noLLantas = 0, noRuedas = 0, res = 0, temp = -1;
        String modelo = null, marca = null, color = null, tipo = null, materialBota = null;
        boolean valido = true;
        float precio = 0.0f, velocidadMaxima = 0.0f;
        
        try{
            idVehiculo = Integer.parseInt(ct_idVehiculo.getText());
            if(idVehiculo <= 0){
                JOptionPane.showMessageDialog(null, "El dato Id Vehículo debe ser Positivo verifique", "Warning", 2);
                ct_idVehiculo.setText("");
                valido = false;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El dato Id Vehículo debe ser Entero verifique", "Warning", 2);
            ct_idVehiculo.setText("");
            valido  = false;
        }
        modelo = ct_Modelo.getText();
        if (modelo == null || modelo.isBlank()
                                || !modelo.matches("^[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$")) {
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de Modelo válido.",
                            "Warning", 2);
                            //La expresión permite nombres como: 
                            //CX-5, A4, GT-R, Corolla Cross
                            //La expresión no permite nombres como:
                            //-GT, Focus--RS, Civic@2024
                            ct_Modelo.setText("");
                            valido = false;
        }
        try{
            precio = Float.parseFloat(ct_Precio.getText());
            if(precio <= 0){
                JOptionPane.showMessageDialog(null, "El Precio debe ser positivo verifique", "Warning", 2);
                ct_Precio.setText("");
                valido = false;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El dato Precio debe ser decimal verifique", "Warning", 2);
            ct_Precio.setText("");
            valido  = false;
        }
        marca = ct_Marca.getText();
        if (marca == null || marca.isBlank()
                                || !marca.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de Marca válido.",
                            "Warning", 2);
                            //La expresión permite nombres como: 
                            //Ford, Mercedes-Benz, Land Rover
                            //La expresión no permite nombres como:
                            //-Ford, BMW-, Audi@@
                            ct_Marca.setText("");
                            valido = false;
        }
        anio = jyc_Anio.getYear();
        if(anio == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Año...", "Warning", 2);
            valido = false;
        }else if(anio < 2000 || anio > 2025){
            JOptionPane.showMessageDialog(null, "No corresponde a un Año válido (2000-2025)", "Warning", 2);
            valido = false;
            jyc_Anio.setYear(0);
        }
        color = ct_Color.getText();
        if (color == null || color.isBlank()
                                || !color.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Rojo, Rojo-Mate, Azul Marino, Gris-Plata
                            //La expresión no permite nombres como:
                            //-Rojo, -Rojo, Verde--Lima, " "Rojo-Mate
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de Color válido.",
                            "Warning", 2);
                            ct_Color.setText("");
                            valido = false;
        }
        if(cmb_tipoVehiculo.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de Vehículo...", "Warning", 2);
            valido = false;
        }else{
            if(cmb_tipoVehiculo.getSelectedIndex() == 0){
                try{
                    cilindrada = Integer.parseInt(ct_Cilindrada.getText());
                    if(cilindrada <= 0){
                        JOptionPane.showMessageDialog(null, "La Cilindrada debe ser positiva verifique", "Warning", 2);
                        ct_Cilindrada.setText("");
                        valido = false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "El dato Cilindrada debe ser numérico verifique", "Warning", 2);
                    ct_Cilindrada.setText("");
                    valido  = false;
                }
                if(cmb_noLLantas.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un No de LLantas...", "Warning", 2);
                    valido = false;
                }else{
                    noLLantas = Integer.parseInt(cmb_noLLantas.getSelectedItem().toString());
                }
                try{
                    velocidadMaxima = Float.parseFloat(ct_velocidadMaxima.getText());
                    if(velocidadMaxima <= 0){
                        JOptionPane.showMessageDialog(null, "La Velocidad Máxima debe ser positiva verifique", "Warning", 2);
                        ct_velocidadMaxima.setText("");
                        valido = false;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "El dato Velocidad Máxima debe ser decimal verifique", "Warning", 2);
                    ct_velocidadMaxima.setText("");
                    valido  = false;
                }
            }else if(cmb_tipoVehiculo.getSelectedIndex() == 1){
                tipo = ct_Tipo.getText();
                if (tipo == null || tipo.isBlank()
                                || !tipo.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Inline, Roller, Roller-Inline, Patín de hielo
                            //La expresión no permite nombres como:
                            //-Inline, Roller-, Patín123, " "Inline
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de tipo de patín válido.",
                            "Warning", 2);
                            ct_Tipo.setText("");
                            valido = false;
                }
                materialBota = ct_materialBota.getText();
                if (materialBota == null || materialBota.isBlank()
                                || !materialBota.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[ -/][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
                            //La expresión permite nombres como: 
                            //Cuero, Cuero Sintético, Nylon/Poliéster, Cuero-Plástico
                            //La expresión no permite nombres como:
                            //-Cuero, Cuero-, Nylon//Poliéster, Cuero123
                            JOptionPane.showMessageDialog(null, "No corresponde a un nombre de material de bota válido",
                            "Warning", 2);
                            ct_materialBota.setText("");
                            valido = false;
                }
                if(cmb_noRuedas.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un No de Ruedas...", "Warning", 2);
                    valido = false;
                }else{
                    noRuedas = Integer.parseInt(cmb_noRuedas.getSelectedItem().toString());
                }
            }    
        }
        if(valido && agregar && cmb_tipoVehiculo.getSelectedIndex() == 0){
            if(RPGDB.existeV(idVehiculo)){
                JOptionPane.showMessageDialog(null,"Ese idVehiculo ya ha sido registrado, Verifique...", 
                "Warning", 2);
                ct_idVehiculo.setText("");
                ct_idVehiculo.setEditable(true);
                valido = false;
            }
            if(valido){
                temp = 0;
                res = RPGDB.agregarRegistroGoKart(idVehiculo, modelo, marca, anio, color, precio, cilindrada
                        , noLLantas, velocidadMaxima);
            }
        }else if(valido && !agregar && cmb_tipoVehiculo.getSelectedIndex() == 0){
            temp = 0;
            res = RPGDB.actualizarRegistroGoKart(idVehiculo, modelo, marca, anio, color, precio, cilindrada
                        , noLLantas, velocidadMaxima);
        }else if(valido && agregar && cmb_tipoVehiculo.getSelectedIndex() == 1){
            if(RPGDB.existeV(idVehiculo)){
                JOptionPane.showMessageDialog(null,"Ese idVehiculo ya ha sido registrado, Verifique...", 
                "Warning", 2);
                ct_idVehiculo.setText("");
                ct_idVehiculo.setEditable(true);
                valido = false;
            }
            if(valido){
                temp = 1;
                res = RPGDB.agregarRegistroPatines(idVehiculo, modelo, marca, anio, color, precio,
                        tipo, materialBota, noRuedas);
            }
        }else if(valido && !agregar && cmb_tipoVehiculo.getSelectedIndex() == 1){
            temp = 1;
            res = RPGDB.actualizarRegistroPatin(idVehiculo, modelo, marca, anio, color, precio,
                    tipo, materialBota, noRuedas);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se puede seguir con el proceso, Todos los datos deben ser válidos", "ERROR", 0);
        }
        if(res > 0){
            this.limpiar();
            this.listar(temp);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_idVehiculo = new javax.swing.JLabel();
        ct_idVehiculo = new javax.swing.JTextField();
        lb_Modelo = new javax.swing.JLabel();
        ct_Modelo = new javax.swing.JTextField();
        lb_Marca = new javax.swing.JLabel();
        ct_Marca = new javax.swing.JTextField();
        lb_Anio = new javax.swing.JLabel();
        lb_Color = new javax.swing.JLabel();
        lb_Precio = new javax.swing.JLabel();
        ct_Precio = new javax.swing.JTextField();
        ct_Color = new javax.swing.JTextField();
        lb_tipoVehiculo = new javax.swing.JLabel();
        cmb_tipoVehiculo = new javax.swing.JComboBox<>();
        lb_Cilindrada = new javax.swing.JLabel();
        ct_Cilindrada = new javax.swing.JTextField();
        lb_noLLantas = new javax.swing.JLabel();
        lb_velocidadMaxima = new javax.swing.JLabel();
        ct_velocidadMaxima = new javax.swing.JTextField();
        cmb_noLLantas = new javax.swing.JComboBox<>();
        lb_Tipo = new javax.swing.JLabel();
        ct_Tipo = new javax.swing.JTextField();
        lb_materialBota = new javax.swing.JLabel();
        ct_materialBota = new javax.swing.JTextField();
        ct_noRuedas = new javax.swing.JLabel();
        cmb_noRuedas = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        cmb_opcionesV = new javax.swing.JComboBox<>();
        ct_parametroV = new javax.swing.JTextField();
        btn_buscarV = new javax.swing.JButton();
        btn_resetV = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        btn_Actualizar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_datosV = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menú de Vehículos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 24), new java.awt.Color(0, 0, 204))); // NOI18N

        lb_idVehiculo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_idVehiculo.setText("Id Vehículo:");

        ct_idVehiculo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_Modelo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Modelo.setText("Modelo:");

        ct_Modelo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_Marca.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Marca.setText("Marca:");

        ct_Marca.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_Anio.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Anio.setText("Año:");

        lb_Color.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Color.setText("Color:");

        lb_Precio.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Precio.setText("Precio:");

        ct_Precio.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        ct_Color.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_tipoVehiculo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_tipoVehiculo.setText("Tipo Vehiculo:");

        cmb_tipoVehiculo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        cmb_tipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gokart", "Patines" }));
        cmb_tipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_tipoVehiculoActionPerformed(evt);
            }
        });

        lb_Cilindrada.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Cilindrada.setText("Cilindrada:");

        ct_Cilindrada.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_noLLantas.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_noLLantas.setText("No LLantas:");

        lb_velocidadMaxima.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_velocidadMaxima.setText("Velocidad Máxima:");

        ct_velocidadMaxima.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        cmb_noLLantas.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        cmb_noLLantas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4", "6", "8" }));

        lb_Tipo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_Tipo.setText("Tipo:");

        ct_Tipo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_materialBota.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_materialBota.setText("Material Bota:");

        ct_materialBota.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        ct_noRuedas.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        ct_noRuedas.setText("No Ruedas:");

        cmb_noRuedas.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        cmb_noRuedas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8" }));
        cmb_noRuedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_noRuedasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(lb_tipoVehiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_idVehiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ct_idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_Modelo)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lb_Cilindrada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ct_Cilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lb_Marca)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ct_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lb_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lb_Color, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ct_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(lb_Precio)))
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(ct_Color, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                            .addComponent(ct_Precio)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(214, 214, 214)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ct_noRuedas)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmb_noRuedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lb_materialBota)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ct_materialBota, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 12, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lb_Tipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ct_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_velocidadMaxima)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ct_velocidadMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_noLLantas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_noLLantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_idVehiculo)
                    .addComponent(ct_idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Modelo)
                    .addComponent(ct_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Precio)
                    .addComponent(ct_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Marca)
                    .addComponent(ct_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Anio)
                    .addComponent(lb_Color)
                    .addComponent(ct_Color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tipoVehiculo)
                    .addComponent(cmb_tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Cilindrada)
                    .addComponent(ct_Cilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Tipo)
                    .addComponent(ct_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_noLLantas)
                    .addComponent(cmb_noLLantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_materialBota)
                    .addComponent(ct_materialBota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ct_velocidadMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_velocidadMaxima)
                    .addComponent(ct_noRuedas)
                    .addComponent(cmb_noRuedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        cmb_opcionesV.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        cmb_opcionesV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Vehículo", "Marca", " " }));
        cmb_opcionesV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_opcionesVActionPerformed(evt);
            }
        });

        ct_parametroV.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        btn_buscarV.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_buscarV.setText("Buscar");
        btn_buscarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarVActionPerformed(evt);
            }
        });

        btn_resetV.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_resetV.setText("Reset");
        btn_resetV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_opcionesV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(ct_parametroV, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btn_buscarV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_resetV)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_opcionesV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ct_parametroV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarV)
                    .addComponent(btn_resetV))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btn_Agregar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Agregar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Agregar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Actualizar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Actualizar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Actualizar.setText("Actualizar");
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });

        btn_Eliminar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Eliminar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("Eliminar");

        btn_Consultar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Consultar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Consultar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Consultar.setText("Consultar");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tb_datosV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_datosV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_datosVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_datosV);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Consultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_Agregar)
                        .addGap(48, 48, 48)
                        .addComponent(btn_Actualizar)
                        .addGap(59, 59, 59)
                        .addComponent(btn_Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Consultar)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_opcionesVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_opcionesVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_opcionesVActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.limpiar();
    }//GEN-LAST:event_formWindowOpened

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        this.agregar_actualizar(true);
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        this.agregar_actualizar(false);
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tb_datosVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_datosVMouseClicked
        ct_idVehiculo.setEditable(false);
        int fila = tb_datosV.getSelectedRow();
        ct_idVehiculo.setText(tb_datosV.getValueAt(fila, 0).toString());
        ct_Modelo.setText(tb_datosV.getValueAt(fila, 1).toString());
        ct_Marca.setText(tb_datosV.getValueAt(fila, 2).toString());
        try {
            int anio = Integer.parseInt(tb_datosV.getValueAt(fila, 3).toString());
            jyc_Anio.setYear(anio);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el año.", "Error de datos", 2);
            jyc_Anio.setYear(0); // opcional: restablecer
        }
        ct_Color.setText(tb_datosV.getValueAt(fila, 4).toString());
        ct_Precio.setText(tb_datosV.getValueAt(fila, 5).toString());
        if(cmb_tipoVehiculo.getSelectedIndex() == 0){
            ct_Cilindrada.setText(tb_datosV.getValueAt(fila, 6).toString());
            cmb_noLLantas.setSelectedItem(tb_datosV.getValueAt(fila, 7).toString());
            ct_velocidadMaxima.setText(tb_datosV.getValueAt(fila, 8).toString());
        }else if(cmb_tipoVehiculo.getSelectedIndex() == 1){
            ct_Tipo.setText(tb_datosV.getValueAt(fila, 6).toString());
            ct_materialBota.setText(tb_datosV.getValueAt(fila, 7).toString());
            cmb_noRuedas.setSelectedItem(tb_datosV.getValueAt(fila, 8).toString());
        }
    }//GEN-LAST:event_tb_datosVMouseClicked

    private void btn_buscarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarVActionPerformed
        int tipo = -1;
        if(cmb_tipoVehiculo.getSelectedIndex() == 0){
            tipo = 0;
        }else if(cmb_tipoVehiculo.getSelectedIndex() == 1){
            tipo = 1;
        }
        DefaultTableModel modelo = RPGDB.buscaV(cmb_opcionesV.getSelectedIndex(), 
                ct_parametroV.getText()
                ,tipo);
        if (modelo == null) {
            ct_parametroV.setText("");
        } else {
            tb_datosV.setModel(modelo);
        }
    }//GEN-LAST:event_btn_buscarVActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        RPGDB.cerrar();
    }//GEN-LAST:event_formWindowClosed

    private void btn_resetVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetVActionPerformed
        this.limpiar();
    }//GEN-LAST:event_btn_resetVActionPerformed

    private void cmb_noRuedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_noRuedasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_noRuedasActionPerformed

    private void cmb_tipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_tipoVehiculoActionPerformed
        int fila = tb_datosV.getSelectedRow();
        if(cmb_tipoVehiculo.getSelectedIndex() == -1){
            ct_Cilindrada.setText("");
            cmb_noLLantas.setSelectedIndex(-1);
            ct_velocidadMaxima.setText("");
            ct_Tipo.setText("");
            ct_materialBota.setText("");
            cmb_noRuedas.setSelectedIndex(-1);
            ct_idVehiculo.setEditable(true);
            ct_Modelo.setEditable(true);
            ct_Precio.setEditable(true);
            ct_Marca.setEditable(true);
            ct_Color.setEditable(true);
            ct_Tipo.setEditable(false);
            jyc_Anio.setEnabled(true);
            this.listar(-1);
        }else if(cmb_tipoVehiculo.getSelectedIndex() == 0){
            if(fila != -1){
                ct_idVehiculo.setText("");
                ct_Modelo.setText("");
                ct_Precio.setText("");
                ct_Marca.setText("");
                ct_Color.setText("");
                jyc_Anio.setYear(0);
            }
            ct_Tipo.setText("");
            ct_materialBota.setText("");
            cmb_noRuedas.setSelectedIndex(-1);
            ct_idVehiculo.setEditable(true);
            ct_Modelo.setEditable(true);
            ct_Precio.setEditable(true);
            ct_Marca.setEditable(true);
            ct_Color.setEditable(true);
            ct_Tipo.setEditable(false);
            jyc_Anio.setEnabled(true);
            ct_materialBota.setEditable(false);
            cmb_noRuedas.setEnabled(false);
            ct_Cilindrada.setEditable(true);
            cmb_noLLantas.setEnabled(true);
            ct_velocidadMaxima.setEditable(true);
            ct_parametroV.setEditable(true);
            this.listar(0);
        }else if(cmb_tipoVehiculo.getSelectedIndex() == 1){
            if(fila != -1){
                ct_idVehiculo.setText("");
                ct_Modelo.setText("");
                ct_Precio.setText("");
                ct_Marca.setText("");
                ct_Color.setText("");
                jyc_Anio.setYear(0);
            }
            ct_Cilindrada.setText("");
            cmb_noLLantas.setSelectedIndex(-1);
            ct_velocidadMaxima.setText("");
            ct_idVehiculo.setEditable(true);
            ct_Modelo.setEditable(true);
            ct_Precio.setEditable(true);
            ct_Marca.setEditable(true);
            ct_Color.setEditable(true);
            ct_Tipo.setEditable(false);
            jyc_Anio.setEnabled(true);
            ct_Cilindrada.setEditable(false);
            cmb_noLLantas.setEnabled(false);
            ct_velocidadMaxima.setEditable(false);
            ct_Tipo.setEditable(true);
            ct_materialBota.setEditable(true);
            cmb_noRuedas.setEnabled(true);
            ct_parametroV.setEditable(true);
            this.listar(1);
        }
    }//GEN-LAST:event_cmb_tipoVehiculoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Vehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Vehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Vehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Vehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Menu_Vehiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_buscarV;
    private javax.swing.JButton btn_resetV;
    private javax.swing.JComboBox<String> cmb_noLLantas;
    private javax.swing.JComboBox<String> cmb_noRuedas;
    private javax.swing.JComboBox<String> cmb_opcionesV;
    private javax.swing.JComboBox<String> cmb_tipoVehiculo;
    private javax.swing.JTextField ct_Cilindrada;
    private javax.swing.JTextField ct_Color;
    private javax.swing.JTextField ct_Marca;
    private javax.swing.JTextField ct_Modelo;
    private javax.swing.JTextField ct_Precio;
    private javax.swing.JTextField ct_Tipo;
    private javax.swing.JTextField ct_idVehiculo;
    private javax.swing.JTextField ct_materialBota;
    private javax.swing.JLabel ct_noRuedas;
    private javax.swing.JTextField ct_parametroV;
    private javax.swing.JTextField ct_velocidadMaxima;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Anio;
    private javax.swing.JLabel lb_Cilindrada;
    private javax.swing.JLabel lb_Color;
    private javax.swing.JLabel lb_Marca;
    private javax.swing.JLabel lb_Modelo;
    private javax.swing.JLabel lb_Precio;
    private javax.swing.JLabel lb_Tipo;
    private javax.swing.JLabel lb_idVehiculo;
    private javax.swing.JLabel lb_materialBota;
    private javax.swing.JLabel lb_noLLantas;
    private javax.swing.JLabel lb_tipoVehiculo;
    private javax.swing.JLabel lb_velocidadMaxima;
    private javax.swing.JTable tb_datosV;
    // End of variables declaration//GEN-END:variables
}
