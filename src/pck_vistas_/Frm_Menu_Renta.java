//José Luis González Flor
//Alexis Aarath Lara Hernandez
//Carlos Alberto Godinez Hernandez
//Joshua Trejo Hernandez
package pck_vistas_;

import Pck_Examen.Hora;
import javax.swing.JOptionPane;
import pck_datos_.RentaDatos_DB;
import java.sql.Date;
import java.sql.Time;

public class Frm_Menu_Renta extends javax.swing.JFrame {

    private final RentaDatos_DB PDB;

    public Frm_Menu_Renta() {
        initComponents();
        this.setLocationRelativeTo(null);
        PDB = new RentaDatos_DB();
        this.limpiar();
        this.listar();
    }

    private void listar() {
        tbl_Rentas.setModel(PDB.getDatos());
    }

    private void limpiar() {
        ct_HoraFinal.setText("");
        ct_HoraInicio.setText("");
        ct_idCliente.setText("");
        ct_idRenta.setText("");
        ct_idVehiculo.setText("");
        jdc_FechaRenta.setDate(null);

    }

    private Hora validarHora(String horaStr, String campoNombre) {
        String[] partes = horaStr.split(":");
        if (partes.length != 2) {
            JOptionPane.showMessageDialog(null, "El formato de la " + campoNombre + " debe ser en formato HH:MM",
                    "Warning", 2);
            return null;
        }
        try {
            int hora = Integer.parseInt(partes[0].trim());
            int minuto = Integer.parseInt(partes[1].trim());
            Hora horaObj = new Hora(hora, minuto);

            if (horaObj.horaCorrecta()) {
                return horaObj;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "La hora debe contener valores numéricos para horas y minutos en formato HH:MM",
                    "Warning", 2);
            return null;
        }
    }

    private void agregar_actualizar(boolean agregar) {
        int idRenta = 0, idVehiculo = 0, res = 0;
        String idCliente = "";
        Hora horaInicio = null;
        Hora horaFinal = null;
        Date FechaRenta = null;
        boolean valido = true;

        try {
            idRenta = Integer.parseInt(ct_idRenta.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID de la renta debe ser numérico",
                    "Warning", 2);
            ct_idRenta.setText("");
            valido = false;
        }

        try {
            idVehiculo = Integer.parseInt(ct_idVehiculo.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del vehiculo debe ser numérico",
                    "Warning", 2);
            ct_idVehiculo.setText("");
            valido = false;
        }

        idCliente = ct_idCliente.getText().trim();
        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo ID Cliente no puede estar vacío.", "Warning", 2);
            valido = false;
        } else if (!idCliente.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9_-]+$")) {
            JOptionPane.showMessageDialog(null, "El ID cliente solo puede contener letras, numero, guiones y guines bajos",
                    "Warning", 2);
            ct_idCliente.setText("");
            valido = false;
        }

        if (!valido) {
            return;
        }

        horaInicio = validarHora(ct_HoraInicio.getText(), "Hora de Inicio");
        if (horaInicio == null) {
            ct_HoraInicio.setText("");
            valido = false;
        }

        horaFinal = validarHora(ct_HoraFinal.getText(), "Hora Final");
        if (horaFinal == null) {
            ct_HoraFinal.setText("");
            valido = false;
        }

        if (!valido) {
            return;
        }

        if (horaFinal.esMenorQue(horaInicio) || horaFinal.getHora().equals(horaInicio.getHora())) {
            JOptionPane.showMessageDialog(null, "La Hora Final (" + horaFinal.getHora() + ") no puede ser menor o igual a la Hora de Inicio (" + horaInicio.getHora() + ").",
                    "Error de Renta", 2);
            valido = false;
        }

        if (!valido) {
            return;
        }

        java.util.Date DateRenta = jdc_FechaRenta.getDate();
        if (DateRenta == null) {
            JOptionPane.showMessageDialog(null, "Porfavor, ingrese una fecha de renta", "Warning", 2);
            valido = false;
        }
        if (!valido) {
            return;
        }

        FechaRenta = new java.sql.Date(DateRenta.getTime());
        java.sql.Time sqlHoraInicio = java.sql.Time.valueOf(horaInicio.getHora() + ":00");
        java.sql.Time sqlHoraFinal = java.sql.Time.valueOf(horaFinal.getHora() + ":00");

        //Si el campo de fecha es un textField
//        try{
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            
//        }
        if (valido && agregar) {
            res = PDB.agregarRegistro(idRenta, idVehiculo, idCliente, FechaRenta, sqlHoraInicio, sqlHoraFinal);
        } else if (valido && !agregar) {
            res = PDB.actualizarRegistro(idRenta, idVehiculo, idCliente, FechaRenta, sqlHoraInicio, sqlHoraFinal);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor conteste todos campos o verifique que sean datos validos",
                    "Error de carga de datos", 0);
        }
        if (res > 0) {
            this.listar();
            this.limpiar();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ct_idCliente = new javax.swing.JTextField();
        lb_idCliente = new javax.swing.JLabel();
        lb_idRenta = new javax.swing.JLabel();
        ct_idRenta = new javax.swing.JTextField();
        lb_idVehiculo = new javax.swing.JLabel();
        ct_idVehiculo = new javax.swing.JTextField();
        lb_FechaRenta = new javax.swing.JLabel();
        lb_HoraFinal = new javax.swing.JLabel();
        ct_HoraInicio = new javax.swing.JTextField();
        lb_HoraInicio = new javax.swing.JLabel();
        ct_HoraFinal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jdc_FechaRenta = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        cmb_opcionesR = new javax.swing.JComboBox<>();
        ct_parametroR = new javax.swing.JTextField();
        btn_buscarR = new javax.swing.JButton();
        btn_resetR = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Actualizar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Rentas = new javax.swing.JTable();
        btn_Regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu de Rentas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 1, 24))); // NOI18N

        ct_idCliente.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_idCliente.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_idCliente.setText("Id Cliente:");

        lb_idRenta.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_idRenta.setText("Id Renta:");

        ct_idRenta.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_idVehiculo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_idVehiculo.setText("Id Vehiculo:");

        ct_idVehiculo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_FechaRenta.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_FechaRenta.setText("Fecha de Renta:");

        lb_HoraFinal.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_HoraFinal.setText("Hora Final: ");

        ct_HoraInicio.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        lb_HoraInicio.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lb_HoraInicio.setText("Hora de Inicio:");

        ct_HoraFinal.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        jLabel1.setText("agregar jdc con el name jdc_FechaRenta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_idRenta)
                        .addGap(18, 18, 18)
                        .addComponent(ct_idRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_idVehiculo)
                        .addGap(18, 18, 18)
                        .addComponent(ct_idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(lb_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ct_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(lb_HoraInicio)
                                .addGap(18, 18, 18)
                                .addComponent(ct_HoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(lb_HoraFinal)
                                .addGap(18, 18, 18)
                                .addComponent(ct_HoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(lb_FechaRenta)
                                .addGap(18, 18, 18)
                                .addComponent(jdc_FechaRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(305, 305, 305))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_idRenta)
                    .addComponent(ct_idRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_idVehiculo)
                    .addComponent(ct_idVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_idCliente)
                    .addComponent(ct_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_HoraInicio)
                    .addComponent(ct_HoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_HoraFinal)
                    .addComponent(ct_HoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_FechaRenta)
                    .addComponent(jdc_FechaRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        cmb_opcionesR.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        cmb_opcionesR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Venta", "Id Cliente", "Id Vehiculo" }));
        cmb_opcionesR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_opcionesRActionPerformed(evt);
            }
        });

        ct_parametroR.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        btn_buscarR.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_buscarR.setText("Buscar");
        btn_buscarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarRActionPerformed(evt);
            }
        });

        btn_resetR.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_resetR.setText("Reset");
        btn_resetR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_opcionesR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(ct_parametroR, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btn_buscarR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btn_resetR)
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_opcionesR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ct_parametroR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarR)
                    .addComponent(btn_resetR))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btn_Consultar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Consultar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Consultar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Consultar.setText("Consultar");
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });

        btn_Eliminar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Eliminar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
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

        btn_Agregar.setBackground(new java.awt.Color(51, 51, 51));
        btn_Agregar.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        btn_Agregar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        tbl_Rentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_Rentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_RentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Rentas);

        btn_Regresar.setBackground(new java.awt.Color(0, 0, 0));
        btn_Regresar.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        btn_Regresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Regresar.setText("Regresar");
        btn_Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Regresar))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_Agregar)
                        .addGap(56, 56, 56)
                        .addComponent(btn_Actualizar)
                        .addGap(39, 39, 39)
                        .addComponent(btn_Eliminar)
                        .addGap(49, 49, 49)
                        .addComponent(btn_Consultar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Regresar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_opcionesRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_opcionesRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_opcionesRActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        this.agregar_actualizar(false);
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        this.agregar_actualizar(true);
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void tbl_RentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_RentasMouseClicked
        int fila = tbl_Rentas.getSelectedRow();
        ct_idRenta.setText(tbl_Rentas.getValueAt(fila, 0).toString());
        ct_idVehiculo.setText(tbl_Rentas.getValueAt(fila, 1).toString());
        ct_idCliente.setText(tbl_Rentas.getValueAt(fila, 2).toString());
        java.sql.Time horaInicio = (java.sql.Time) tbl_Rentas.getValueAt(fila, 3); //aqui se genera hora inicio como tipo time en lugar de string 
        java.sql.Time horaFinal = (java.sql.Time) tbl_Rentas.getValueAt(fila, 4);   //lo mismo que en el anterior
        jdc_FechaRenta.setDate((java.util.Date) tbl_Rentas.getValueAt(fila, 5));
        
        java.text.SimpleDateFormat formatoMH = new java.text.SimpleDateFormat("HH:mm"); //aqui se hace un formateo de hora para solo sea hora y minuto en lugar de hora,  minuto y segundo
        ct_HoraInicio.setText(formatoMH.format(horaInicio));   //se asginan el valor de hora y minuto a las horas 
        ct_HoraFinal.setText(formatoMH.format(horaFinal));
    }//GEN-LAST:event_tbl_RentasMouseClicked

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        Frm_Consulta_Renta frmCR = new Frm_Consulta_Renta();
        frmCR.setVisible(true);
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        int res;
        int fila = tbl_Rentas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla", "Eliminar un resgitro", 1);
        }
        res = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este registro?\n No podrá recuperar esta informacion despues de esta acción",
                "Eliminar registro", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            res = PDB.eliminarRegistro((Integer) tbl_Rentas.getValueAt(fila, 0));
            if (res > 0) {
                this.limpiar();
                this.listar();
            }
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_buscarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarRActionPerformed
        tbl_Rentas.setModel(PDB.buscarRenta(cmb_opcionesR.getSelectedIndex(), ct_parametroR.getText()));

    }//GEN-LAST:event_btn_buscarRActionPerformed

    private void btn_resetRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetRActionPerformed
        this.listar();
        this.limpiar();
    }//GEN-LAST:event_btn_resetRActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.limpiar();
    }//GEN-LAST:event_formWindowOpened

    private void btn_RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegresarActionPerformed
        Frm_Menu_Principal frmP = new Frm_Menu_Principal();
        frmP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_RegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Menu_Renta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Renta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Renta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Menu_Renta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Menu_Renta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Regresar;
    private javax.swing.JButton btn_buscarR;
    private javax.swing.JButton btn_resetR;
    private javax.swing.JComboBox<String> cmb_opcionesR;
    private javax.swing.JTextField ct_HoraFinal;
    private javax.swing.JTextField ct_HoraInicio;
    private javax.swing.JTextField ct_idCliente;
    private javax.swing.JTextField ct_idRenta;
    private javax.swing.JTextField ct_idVehiculo;
    private javax.swing.JTextField ct_parametroR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdc_FechaRenta;
    private javax.swing.JLabel lb_FechaRenta;
    private javax.swing.JLabel lb_HoraFinal;
    private javax.swing.JLabel lb_HoraInicio;
    private javax.swing.JLabel lb_idCliente;
    private javax.swing.JLabel lb_idRenta;
    private javax.swing.JLabel lb_idVehiculo;
    private javax.swing.JTable tbl_Rentas;
    // End of variables declaration//GEN-END:variables
}
