/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.TDA.Lista.Exepciones.EmptyList;
import Controlador.TDA.Lista.DinamicList;
import Controlador.utiles.Utiles;
import Modelo.informacionBoleto;
import Modelo.Dao.PasajeroDao;
import Modelo.Pasajero;
import Vista.Modelo.ModeloTablaVenta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander
 */
public class VistaPrincipal extends javax.swing.JFrame {

    ModeloTablaVenta modeloTabla = new ModeloTablaVenta();
    PasajeroDao pasajeroControlListas = new PasajeroDao();

    /**
     * Creates new form VistaPrincipal
     *
     * @throws Controlador.TDA.Lista.Exepciones.EmptyList
     */
    public VistaPrincipal() throws EmptyList {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarTabla();
    }

    private void CargarTabla() {
        modeloTabla.setPasajerosTabla(pasajeroControlListas.getListaPasajeros());
        tblVentas.setModel(modeloTabla);
        tblVentas.updateUI();
        cbxGenero.setSelectedIndex(-1);
        cbxOrigen.setSelectedIndex(-1);
        cbxDestino.setSelectedIndex(-1);
    }

    private Boolean Validar() {
        return (!txtNumeroCedula.getText().trim().isEmpty() && !txtNombre.getText().trim().isEmpty() && !txtApellido.getText().trim().isEmpty() && !txtDireccion.getText().trim().isEmpty() && !txtCantidadBoletos.getText().trim().isEmpty() && !txtFechaBoleto.getText().trim().isEmpty());
    }

    private void Limpiar() throws EmptyList {
        txtApellido.setText("");
        txtNombre.setText("");
        txtNumeroCedula.setText("");
        cbxGenero.setSelectedIndex(-1);
        cbxOrigen.setSelectedIndex(-1);
        cbxDestino.setSelectedIndex(-1);
        txtDireccion.setText("");
        txtFechaBoleto.setText("");
        txtCantidadBoletos.setText("");
        pasajeroControlListas.setPasajero(null);
        CargarTabla();
    }

    private void Guardar() throws EmptyList, ParseException {

        if (cbxGenero.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNumeroCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxOrigen.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxDestino.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtCantidadBoletos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaBoleto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String NumeroCedula = txtNumeroCedula.getText();
            String Nombre = txtNombre.getText();
            String Apellido = txtApellido.getText();
            String Genero = cbxGenero.getSelectedItem().toString();
            String direccion = txtDireccion.getText();
            Integer IdPersona = pasajeroControlListas.getListaPasajeros().getLegth() + 1;
            String Origen = cbxOrigen.getSelectedItem().toString();
            String Destino = cbxDestino.getSelectedItem().toString();
            Integer CantidadBoleto = Integer.valueOf(txtCantidadBoletos.getText());
            String FechaSalida = txtFechaBoleto.getText();
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMMM-yyyy");
            String fechaComoString = formatoFecha.format(fechaActual);
            float TotalPagar = CantidadBoleto * 45f;

            informacionBoleto BoletoPasajero = new informacionBoleto(IdPersona, Origen, Destino, CantidadBoleto, FechaSalida, fechaComoString, TotalPagar);

            pasajeroControlListas.getPasajero().setIdPersona(IdPersona);
            pasajeroControlListas.getPasajero().setNumeroCedula(NumeroCedula);
            pasajeroControlListas.getPasajero().setNombrePersona(Nombre);
            pasajeroControlListas.getPasajero().setApelldoPersona(Apellido);
            pasajeroControlListas.getPasajero().setGeneroPersona(Genero);
            pasajeroControlListas.getPasajero().setDireccionPersona(direccion);
            pasajeroControlListas.getPasajero().setBoletoPasajero(BoletoPasajero);

            if (pasajeroControlListas.Persist()) {
                JOptionPane.showMessageDialog(null, "Boleto comprado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                CargarTabla();

                pasajeroControlListas.setPasajero(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo comprar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        Limpiar();
    }

    private void CargarVista() {
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } else {
            try {
                pasajeroControlListas.setPasajero(modeloTabla.getPasajerosTabla().getInfo(fila));

                txtNombre.setText(pasajeroControlListas.getPasajero().getNombrePersona());
                txtApellido.setText(pasajeroControlListas.getPasajero().getApelldoPersona());
                txtNumeroCedula.setText(pasajeroControlListas.getPasajero().getNumeroCedula());
                txtDireccion.setText(pasajeroControlListas.getPasajero().getDireccionPersona());
                cbxDestino.setSelectedItem(pasajeroControlListas.getPasajero().getBoletoPasajero().getPaisDestino());
                cbxOrigen.setSelectedItem(pasajeroControlListas.getPasajero().getBoletoPasajero().getPaisOrigen());
                cbxGenero.setSelectedItem(pasajeroControlListas.getPasajero().getGeneroPersona());
                txtFechaBoleto.setText(pasajeroControlListas.getPasajero().getBoletoPasajero().getFechaViaje());
                txtCantidadBoletos.setText(pasajeroControlListas.getPasajero().getBoletoPasajero().getBoletosCantidad().toString());
            } catch (EmptyList | IndexOutOfBoundsException e) {
            }
        }
    }
    
    public  Integer Orden1(){
        String OrdenO = cbxOrden.getSelectedItem().toString();
        
        if ("Ascendente".equals(OrdenO)) {
            return 1;
        }
        if("Descendente".equals(OrdenO)){
            return 0;
        }
        return null;
    }
    
    public  Integer Orden2(){
        String OrdenO = cbxOrden.getSelectedItem().toString();
        
        if ("Ascendente".equals(OrdenO)) {
            return 0;
        }
        if("Descendente".equals(OrdenO)){
            return 1;
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnComprarBoleto = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtNumeroCedula = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxDestino = new javax.swing.JComboBox<>();
        txtCantidadBoletos = new javax.swing.JTextField();
        txtFechaBoleto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnCalcularVentas = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSeleccinar = new javax.swing.JButton();
        cbxOrigen = new javax.swing.JComboBox<>();
        cbxGenero = new javax.swing.JComboBox<>();
        cbxCriterio = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxOrden = new javax.swing.JComboBox<>();
        cbxMetoto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOLETOS");

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setForeground(new java.awt.Color(0, 102, 255));

        jLabel2.setBackground(new java.awt.Color(102, 102, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VIAJES SUR AMERICA");

        btnComprarBoleto.setBackground(new java.awt.Color(153, 255, 255));
        btnComprarBoleto.setForeground(new java.awt.Color(0, 0, 0));
        btnComprarBoleto.setText("COMPRAR");
        btnComprarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarBoletoActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(102, 102, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Datos comprador");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel13.setText("Genero");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel14.setText("Numero cedula");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel15.setText("Nombre");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel16.setText("Apellido");

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel17.setText("Direccion");

        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNumeroCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Informacion boleto");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Pais origen");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setText("Pias destino");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setText("Cantidad");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setText("Fecha de salida");

        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela" }));

        txtCantidadBoletos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFechaBoleto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Registro boletos vendidos");

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        btnCalcularVentas.setBackground(new java.awt.Color(153, 255, 255));
        btnCalcularVentas.setForeground(new java.awt.Color(0, 0, 0));
        btnCalcularVentas.setText("Ventas totales");
        btnCalcularVentas.setToolTipText("");
        btnCalcularVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularVentasActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(153, 255, 255));
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSeleccinar.setBackground(new java.awt.Color(153, 255, 255));
        btnSeleccinar.setForeground(new java.awt.Color(0, 0, 0));
        btnSeleccinar.setText("Seleccionar");
        btnSeleccinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccinarActionPerformed(evt);
            }
        });

        cbxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador", "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela" }));

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "No definido" }));

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Numero de cedula", "Genero" }));
        cbxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioActionPerformed(evt);
            }
        });

        btnOrdenar.setBackground(new java.awt.Color(153, 255, 255));
        btnOrdenar.setForeground(new java.awt.Color(0, 0, 0));
        btnOrdenar.setText("Ordenar\n");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        jLabel6.setText("Critterio de Ordenacion");

        cbxOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        cbxOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenActionPerformed(evt);
            }
        });

        cbxMetoto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shell", "Quick" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtNumeroCedula, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalcularVentas)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccinar)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnComprarBoleto)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxDestino, 0, 395, Short.MAX_VALUE)
                                    .addComponent(cbxOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCantidadBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxMetoto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNumeroCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidadBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnComprarBoleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnCalcularVentas)
                    .addComponent(btnModificar)
                    .addComponent(btnSeleccinar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenar)
                    .addComponent(cbxMetoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarBoletoActionPerformed
        if (cbxGenero.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNumeroCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxOrigen.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxDestino.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtCantidadBoletos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaBoleto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String Fechasalidaboleto = txtFechaBoleto.getText();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                Date FechaSalidaBoletoV = formatoDeFecha.parse(Fechasalidaboleto);
                Date fechaActual = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String fechaComoString = formatoFecha.format(fechaActual);
                if (FechaSalidaBoletoV.before(fechaActual)) {
                    JOptionPane.showMessageDialog(null, "La fecha seleccionada ya ha pasado", "FECHA PASADO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Integer IdPersona = pasajeroControlListas.getListaPasajeros().getLegth() + 1;
                    String NumeroCedula = txtNumeroCedula.getText();
                    String Nombre = txtNombre.getText();
                    String Apellido = txtApellido.getText();
                    String Genero = cbxGenero.getSelectedItem().toString();
                    String direccion = txtDireccion.getText();

                    String Origen = cbxOrigen.getSelectedItem().toString();
                    String Destino = cbxDestino.getSelectedItem().toString();
                    Integer CantidadBoleto = Integer.valueOf(txtCantidadBoletos.getText());
                    String FechaSalida = fechaComoString;
                    String FechaCompra = fechaComoString;
                    float TotalPagar = CantidadBoleto * 45f;

                    informacionBoleto BoletoPasajero = new informacionBoleto(IdPersona, Origen, Destino, CantidadBoleto, FechaSalida, FechaCompra, TotalPagar);
                    Pasajero PasajeroGuardar = new Pasajero(IdPersona, NumeroCedula, Nombre, Apellido, Genero, direccion, BoletoPasajero);
                    pasajeroControlListas.getListaPasajeros().Agregar(PasajeroGuardar);
                    Guardar();
                }
            } catch (ParseException | EmptyList ex) {
                Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnComprarBoletoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } else {
            try {
                Date fechaActual = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String fechaComoString = formatoFecha.format(fechaActual);
                Integer IdPersona = pasajeroControlListas.getPasajero().getIdPersona();
                String Origen = cbxOrigen.getSelectedItem().toString();
                String Destino = cbxDestino.getSelectedItem().toString();
                Integer CantidadBoleto = Integer.parseInt(txtCantidadBoletos.getText());
                String FechaSalida = fechaComoString;
                String FechaCompra = fechaComoString;
                float TotalPagar = CantidadBoleto * 45f;

                String NumeroCedula = txtNumeroCedula.getText();
                String Nombre = txtNombre.getText();
                String Apellido = txtApellido.getText();
                String Genero = cbxGenero.getSelectedItem().toString();
                String direccion = txtDireccion.getText();

                informacionBoleto BoletoPasajero = new informacionBoleto(IdPersona, Origen, Destino, CantidadBoleto, FechaCompra, FechaSalida, TotalPagar);
                Pasajero PasajeroM = new Pasajero(IdPersona, NumeroCedula, Nombre, Apellido, Genero, direccion, BoletoPasajero);

                pasajeroControlListas.Merge(PasajeroM, fila);
                CargarTabla();
                Limpiar();
            } catch (EmptyList | NumberFormatException e) {

            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSeleccinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccinarActionPerformed
        // TODO add your handling code here:
        CargarVista();
    }//GEN-LAST:event_btnSeleccinarActionPerformed

    private void btnCalcularVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularVentasActionPerformed
        // TODO add your handling code here:
        double sumaPrecioFinal = modeloTabla.calcularTotalVentas(11);
        JOptionPane.showMessageDialog(null, "Total de voletos vendidos suma: $ " + sumaPrecioFinal);

    }//GEN-LAST:event_btnCalcularVentasActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
//        UsuarioControl use = new UsuarioControl();
         try {
            String Campo = cbxCriterio.getSelectedItem().toString();
            String Metodo = cbxMetoto.getSelectedItem().toString();

            switch (Metodo) {
                case "Shell":
                    long tiempoInicio = System.currentTimeMillis();
                    
                    Utiles.ShellSort(pasajeroControlListas.all(), Orden2(), Campo);
                    
                    System.out.println(Utiles.ShellSort(pasajeroControlListas.all(), Orden2(), Campo));
                    
                    long tiempoFin = System.currentTimeMillis();
                    long tiempoEjecucion = tiempoFin - tiempoInicio;
                    
                    Utiles.ShellSort(modeloTabla.getPasajerosTabla(), Orden2(), Campo);
                    modeloTabla.fireTableDataChanged();
                    
                    JOptionPane.showMessageDialog(null, "Tiempo de metodo shell: " + tiempoEjecucion + " ms");
                    break;
                    
                case "Quick":
                    long tiempoInicioQ = System.currentTimeMillis();
                    
                    Utiles.MetodoQuickSort(pasajeroControlListas.all(), Orden1(), Campo);
                                        
                    long tiempoFinQ = System.currentTimeMillis();
                    long tiempoEjecucionQ = tiempoFinQ - tiempoInicioQ;
                    
                    Utiles.MetodoQuickSort(modeloTabla.getPasajerosTabla(), Orden1(), Campo);
                    modeloTabla.fireTableDataChanged();
                    
                    JOptionPane.showMessageDialog(null, "Tiempo de metodo Quick: " + tiempoEjecucionQ + " ms");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No válido");
            }
        }
        catch (Exception e) {

        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void cbxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterioActionPerformed

    private void cbxOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new VistaPrincipal().setVisible(true);
                } catch (EmptyList ex) {
                    Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularVentas;
    private javax.swing.JButton btnComprarBoleto;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSeleccinar;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> cbxMetoto;
    private javax.swing.JComboBox<String> cbxOrden;
    private javax.swing.JComboBox<String> cbxOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCantidadBoletos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaBoleto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroCedula;
    // End of variables declaration//GEN-END:variables
}
