package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import vista.FrmMedicoVista;

public class MedicoControlador implements ActionListener{
    
    vista.FrmMedicoVista frmMedicoVista;
    modelo.GestorMedicoModelo gestorMedicoModelo;
    modelo.MedicoModelo medicoModelo;

    public MedicoControlador(FrmMedicoVista frmMedicoVista) {
        this.frmMedicoVista = frmMedicoVista;
        gestorMedicoModelo = new modelo.GestorMedicoModelo();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmMedicoVista.RegistrarBtn)) {
            registrarMedicoControlador();
            limpiarTablaControlador();
            consultarMedicoControlador();
        }
        if (e.getSource().equals(frmMedicoVista.ConsultarBtn)) {
            limpiarTablaControlador();
            consultarMedicoControlador();
        }
        if (e.getSource().equals(frmMedicoVista.ModificarBtn)) {
            this.modificarMedicoControlador();
            this.limpiarTablaControlador();
            consultarMedicoControlador();
        }
        if (e.getSource().equals(frmMedicoVista.EliminarBtn)) {
            eliminarMedicoControlador();
            limpiarTablaControlador();
            consultarMedicoControlador();
        }
        if (e.getSource().equals(frmMedicoVista.EditarBtn)) {
            editarMedicoControlador();
        }
        if (e.getSource().equals(frmMedicoVista.NuevoBtn)) {
            this.limpiarFormularioControlador();
        }
    }
    
    
    public void registrarMedicoControlador(){
        String nombre, apellido, especialidad;
        int documento;
        
        nombre = frmMedicoVista.NombreTxt.getText();
        apellido = frmMedicoVista.ApellidoTxt.getText();
        especialidad = frmMedicoVista.EspecializacionTxt.getText();
        documento = Integer.parseInt(frmMedicoVista.DocumentoTxt.getText());
        
        medicoModelo = new modelo.MedicoModelo(nombre, apellido, documento, especialidad);
        gestorMedicoModelo.registrarMedicoModelo(medicoModelo);
    }
    
    public void consultarMedicoControlador(){
        int parametro = 0;
        String valor = "";
        Vector<modelo.MedicoModelo> medicos;
        String datoMed[] = new String[5];
        if (frmMedicoVista.NombreTxt.getText().length() > 0) {
            parametro = 1;
            valor = frmMedicoVista.NombreTxt.getText();
        }
        else if( frmMedicoVista.ApellidoTxt.getText().length() > 0){
            parametro = 2;
            valor = frmMedicoVista.ApellidoTxt.getText();
        }
        else if (frmMedicoVista.DocumentoTxt.getText().length() > 0) {
            parametro = 3;
            valor = frmMedicoVista.DocumentoTxt.getText();
        }
        else if (frmMedicoVista.EspecializacionTxt.getText().length() > 0){
            parametro = 4;
            valor = frmMedicoVista.EspecializacionTxt.getText();
        }
        medicos = gestorMedicoModelo.consultarMedicoModelo(parametro, valor);
        for(modelo.MedicoModelo med: medicos){
            datoMed[0] = Integer.toString(med.getIdMedico());
            datoMed[1] = med.getNombreMedico();
            datoMed[2] = med.getApellidoMedico();
            datoMed[3] = Integer.toString(med.getDocumentoMedico());
            datoMed[4] = med.getEspecialidadMedico();
            frmMedicoVista.getTableModel().addRow(datoMed);
            frmMedicoVista.getTableModel().fireTableDataChanged();
        }
    }
    
    public void modificarMedicoControlador(){
        String nombre, apellido, especialidad;
        int documento, fila, id;
        fila = frmMedicoVista.ResultadosTbl.getSelectedRow();
        id = Integer.parseInt(frmMedicoVista.ResultadosTbl.getValueAt(fila, 0).toString());
        nombre = frmMedicoVista.NombreTxt.getText();
        apellido = frmMedicoVista.ApellidoTxt.getText();
        especialidad = frmMedicoVista.EspecializacionTxt.getText();
        documento = Integer.parseInt(frmMedicoVista.DocumentoTxt.getText());
        
        medicoModelo = new modelo.MedicoModelo(id, nombre, apellido, documento, especialidad);
        gestorMedicoModelo.actualizarMedicoModelo(medicoModelo);
    }
    
    
    
    
    public void editarMedicoControlador(){
        String nombre, apellido,especialidad;
        int documento = 0;
        int fila = frmMedicoVista.ResultadosTbl.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Registro Para Editarlo");
        }
        else{
            nombre = (String) frmMedicoVista.ResultadosTbl.getValueAt(fila, 1);
            apellido = (String) frmMedicoVista.ResultadosTbl.getValueAt(fila, 2);
            especialidad = (String) frmMedicoVista.ResultadosTbl.getValueAt(fila, 4);
            documento = Integer.parseInt(frmMedicoVista.ResultadosTbl.getValueAt(fila, 3).toString());
            frmMedicoVista.NombreTxt.setText(nombre);
            frmMedicoVista.ApellidoTxt.setText(apellido);
            frmMedicoVista.DocumentoTxt.setText(Integer.toString(documento));
            frmMedicoVista.EspecializacionTxt.setText(especialidad);
        }
    }
    
    public void eliminarMedicoControlador(){
        int fila = frmMedicoVista.ResultadosTbl.getSelectedRow();
        int id = 0;
        int resp = 0;
        if (fila == -1) {
             JOptionPane.showMessageDialog(null, "Debe Seleccionar un Registro Para Eliminar");
        }
        else{
            resp = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar Este Registro?","Eliminar Medico",JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                id = Integer.parseInt(frmMedicoVista.ResultadosTbl.getValueAt(fila, 0).toString());
                medicoModelo = new modelo.MedicoModelo(id);
                gestorMedicoModelo.eliminarMedicoModelo(medicoModelo);
            }
        }
    }
    
    public void limpiarTablaControlador(){
        frmMedicoVista.getTableModel().setRowCount(0);
    }
    
    public void limpiarFormularioControlador(){
        frmMedicoVista.NombreTxt.setText(null);
        frmMedicoVista.ApellidoTxt.setText(null);
        frmMedicoVista.DocumentoTxt.setText(null);
        frmMedicoVista.EspecializacionTxt.setText(null);
    }
}
