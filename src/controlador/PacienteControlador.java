package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.FrmPacienteVista;

public class PacienteControlador implements ActionListener {
    
    vista.FrmPacienteVista frmPacienteVista;
    modelo.GestionarPacienteModelo gestionarPacienteModelo;
    modelo.PacienteModelo pacienteModelo;

    public PacienteControlador(FrmPacienteVista frmPacienteVista) {
        this.frmPacienteVista = frmPacienteVista;
        gestionarPacienteModelo = new modelo.GestionarPacienteModelo();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmPacienteVista.RegistrarBtn)) {
            registrarPacienteControlador();
        }    
        if (e.getSource().equals(frmPacienteVista.ConsultarBtn)) {
            limpiarTabla();
            consultarPacienteControlador();
        }
        if (e.getSource().equals(frmPacienteVista.NuevoBtn)) {
            limpiarFormularioControlador();
        }
        if (e.getSource().equals(frmPacienteVista.EditarBtn)) {
            try {
                editarPacienteControlador();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, e.getModifiers());
            }
        }
        if (e.getSource().equals(frmPacienteVista.ModificarBtn)) {
            actualizarPacienteControlador();
            limpiarTabla();
            consultarPacienteControlador();
        }
        if (e.getSource().equals(frmPacienteVista.EliminarBtn)) {
            int res = JOptionPane.showConfirmDialog(null,"¿Esta Seguro de Eliminar Paciente?","Eliminar Paciente", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                eliminarPacienteControlador();
                limpiarTabla();
                consultarPacienteControlador();
            }
            else{
                JOptionPane.showMessageDialog(null, "Accion Cancelada");
            }
        }
    }
    
    ///METODO PARA REGISTRAR PACIENTES//

    public void registrarPacienteControlador(){
        
        int documento = Integer.parseInt(frmPacienteVista.IdentificacionTxt.getText());
        String nombre = frmPacienteVista.NombreTxt.getText();
        String apellido = frmPacienteVista.ApellidoTxt.getText();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimiento = fecha.format(frmPacienteVista.FechaNacimientoDte.getDate());
        String genero = "";
        if (frmPacienteVista.FemeninoOpt.isSelected()) {
            genero = "Femenino";
        }
        else if (frmPacienteVista.MasculioOpt.isSelected()){
            genero = "Masculino";
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe Seleccionar El Genero");
            return;
        }
        pacienteModelo = new modelo.PacienteModelo(nombre, apellido, documento,fechaNacimiento, genero);
        gestionarPacienteModelo.registrarPacienteModelo(pacienteModelo);
    }
    
    //METODO PARA CONSULTAR PACIENTES //
    
    public void consultarPacienteControlador(){
        Vector<modelo.PacienteModelo> pacientes = new Vector<>();
        String datosTabla[] = new String[6];
        int parametro = 0;
        String valor = "";
        String identificacion = frmPacienteVista.IdentificacionTxt.getText();
        String nombre = frmPacienteVista.NombreTxt.getText();
        String apellido = frmPacienteVista.ApellidoTxt.getText();
        
        if (identificacion.length() != 0) {
            parametro = 1;
            valor = identificacion;
        }
        else if (nombre.length() != 0) {
            parametro = 2;
            valor = nombre;
        }
        else if (apellido.length() != 0) {
            parametro = 3;
            valor = apellido;
        }
        
        pacientes = gestionarPacienteModelo.consultarPacienteModelo(parametro, valor);
        
        for(modelo.PacienteModelo pac: pacientes){
            datosTabla[0] = String.valueOf(pac.getIdPaciente());
            datosTabla[1] = pac.getNombresPaciente();
            datosTabla[2] = pac.getApellidosPaciente();
            datosTabla[3] = String.valueOf(pac.getDocumentoPaciente());
            datosTabla[4] = pac.getFechaNacimientoPaciente();
            datosTabla[5] = pac.getGeneroPaciente();
            frmPacienteVista.getTableModel().addRow(datosTabla);
            frmPacienteVista.getTableModel().fireTableDataChanged();
        }
    }
    
    public void limpiarTabla(){
        frmPacienteVista.getTableModel().setRowCount(0);
    }
    
    public void limpiarFormularioControlador(){
        frmPacienteVista.IdentificacionTxt.setText(null);
        frmPacienteVista.NombreTxt.setText(null);
        frmPacienteVista.ApellidoTxt.setText(null);
        frmPacienteVista.FechaNacimientoDte.setDate(null);
        frmPacienteVista.GeneroOpt.clearSelection();
    }
    
    public void editarPacienteControlador() throws ParseException{
        int fila = frmPacienteVista.ResultadosTbl.getSelectedRow();
        String nombre = "";
        String apellido = "";
        String documento = "";
        String fecha = "";
        String genero = "";
        Date fechaNacimiento;
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Paciente");
        }
        else{
            nombre = (String) frmPacienteVista.ResultadosTbl.getValueAt(fila, 1);
            apellido = (String) frmPacienteVista.ResultadosTbl.getValueAt(fila, 2);
            documento = (String) frmPacienteVista.ResultadosTbl.getValueAt(fila, 3);
            fecha = (String) frmPacienteVista.ResultadosTbl.getValueAt(fila, 4);
            genero = (String) frmPacienteVista.ResultadosTbl.getValueAt(fila, 5);
            fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            frmPacienteVista.IdentificacionTxt.setText(documento);
            frmPacienteVista.NombreTxt.setText(nombre);
            frmPacienteVista.ApellidoTxt.setText(apellido);
            frmPacienteVista.FechaNacimientoDte.setDate(fechaNacimiento);
            if (genero.equals("Femenino")) {
                frmPacienteVista.FemeninoOpt.setSelected(true);
            }
            else {
                frmPacienteVista.MasculioOpt.setSelected(true);
            }
        }
    }
    
    public void actualizarPacienteControlador(){
        int fila = frmPacienteVista.ResultadosTbl.getSelectedRow();
        int id = Integer.parseInt(frmPacienteVista.ResultadosTbl.getValueAt(fila, 0).toString());
        int documento = Integer.parseInt(frmPacienteVista.IdentificacionTxt.getText());
        String nombre = frmPacienteVista.NombreTxt.getText();
        String apellido = frmPacienteVista.ApellidoTxt.getText();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimiento = fecha.format(frmPacienteVista.FechaNacimientoDte.getDate());
        String genero = "";
        if (frmPacienteVista.FemeninoOpt.isSelected()) {
            genero = "Femenino";
        }
        else if (frmPacienteVista.MasculioOpt.isSelected()){
            genero = "Masculino";
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe Seleccionar El Genero");
            return;
        }
        pacienteModelo = new modelo.PacienteModelo(id,nombre,apellido,documento,fechaNacimiento, genero);
        gestionarPacienteModelo.actualizarPacienteModelo(pacienteModelo);
    }
    
    public void eliminarPacienteControlador(){
        int fila = frmPacienteVista.ResultadosTbl.getSelectedRow();
        int id = 0;
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Registro Para Eliminar");            
        }
        else{
            id = Integer.parseInt(frmPacienteVista.ResultadosTbl.getValueAt(fila, 0).toString());
            pacienteModelo = new modelo.PacienteModelo(id);
            gestionarPacienteModelo.eliminarPacienteModelo(pacienteModelo);
        }
    }
}













