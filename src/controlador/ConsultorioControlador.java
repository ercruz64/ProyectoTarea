package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.ConsultorioModelo;
import modelo.GestionarConsultorioModelo;
import vista.FrmConsultorioVista;

public class ConsultorioControlador implements ActionListener{
    
    vista.FrmConsultorioVista frmConsultorioVista;
    modelo.ConsultorioModelo consultorioModelo;
    modelo.GestionarConsultorioModelo gestionarConsultorioModelo;

    public ConsultorioControlador(FrmConsultorioVista frmConsultorioVista) {
        this.frmConsultorioVista = frmConsultorioVista;
        gestionarConsultorioModelo = new modelo.GestionarConsultorioModelo();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConsultorioVista.RegistrarBtn)) {
            registrarConsultorioControlador();
            limpiarFormularioControlador();
            consultarConsultorioControlador();
        }
        if (e.getSource().equals(frmConsultorioVista.BuscarBtn)) {
            consultarConsultorioControlador();
        }
        if (e.getSource().equals(frmConsultorioVista.EditarBtn)) {
            limpiarFormularioControlador();
            editarConsultorioControlador();
        }
        if (e.getSource().equals(frmConsultorioVista.ModificarBtn)) {
            actualizarConsultorioControlador();
            limpiarTablaControlador();
            consultarConsultorioControlador();
        }
        if (e.getSource().equals(frmConsultorioVista.EliminarBtn)) {
            int i = JOptionPane.showConfirmDialog(null, "Esta Seguro de Eliminar El Consutorio?","ELiminar Consultorio", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                eliminarConsultorioConstrolador();
                limpiarTablaControlador();
                consultarConsultorioControlador();
            }
        }
        if (e.getSource().equals(frmConsultorioVista.NuevoBtn)) {
            limpiarFormularioControlador();
        }
    }
    
    ////METODOS///
    
    private void registrarConsultorioControlador(){
        String estado;
        String nombre = frmConsultorioVista.ConsultorioTxt.getText();
        
        if (frmConsultorioVista.ActivoOpt.isSelected()) {
            estado = "Activo";
        }
        else{
            estado = "Inactivo";
        }
        
        consultorioModelo = new modelo.ConsultorioModelo(nombre, estado);
        gestionarConsultorioModelo.registrarConsultorioModelo(consultorioModelo);        
    }
    
    
    public void consultarConsultorioControlador(){
        Vector<modelo.ConsultorioModelo> consultorios = new Vector<>();
        String datos[] = new String[3];
        int parametro = 0;
        String valor = "";
        
        if (frmConsultorioVista.ConsultorioTxt.getText().length() > 0) {
            parametro = 1;
            valor = frmConsultorioVista.ConsultorioTxt.getText();
        }
        else if (frmConsultorioVista.ActivoOpt.isSelected()) {
            parametro = 2;
            valor = "Activo";
        }
        else{
            parametro = 3;
            valor = "Inactivo";
        }
        
        consultorios = gestionarConsultorioModelo.consultarConsultorioModelo(parametro, valor);
        for (modelo.ConsultorioModelo datConsultorio: consultorios) {
            datos[0] = Integer.toString(datConsultorio.getIdConsultorio());
            datos[1] = datConsultorio.getNombreConsultorio();
            datos[2] = datConsultorio.getEstadoConsultorio();
            frmConsultorioVista.getTableModel().addRow(datos);
            frmConsultorioVista.getTableModel().fireTableDataChanged();
        }
    }
    
    public void editarConsultorioControlador(){
        int fila = frmConsultorioVista.ResultadosTbl.getSelectedRow();
        String consultorio = "";
        String estado = "";
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Consultorio");
        }
        else{
            consultorio = (String) frmConsultorioVista.ResultadosTbl.getValueAt(fila, 1);
            estado = (String) frmConsultorioVista.ResultadosTbl.getValueAt(fila, 2);
            frmConsultorioVista.ConsultorioTxt.setText(consultorio);
            JOptionPane.showMessageDialog(null, estado);
            if (estado.equals("Activo")) {
                frmConsultorioVista.ActivoOpt.setSelected(true);
            }
            else{
                frmConsultorioVista.InactivoOpt.setSelected(true);
            }
        }
    }
    
    
   public void actualizarConsultorioControlador(){
       int fila = 0;
       int id = 0;
       String consultorio = "";
       String estado =  "";
       
       fila = frmConsultorioVista.ResultadosTbl.getSelectedRow();
       id = Integer.parseInt(frmConsultorioVista.ResultadosTbl.getValueAt(fila, 0).toString());
       
       consultorio = frmConsultorioVista.ConsultorioTxt.getText();
       if (frmConsultorioVista.ActivoOpt.isSelected()) {
           estado = "Activo";
       }
       else if (frmConsultorioVista.InactivoOpt.isSelected()) {
           estado = "Inactivo";
       }
       else{
           JOptionPane.showMessageDialog(null, "Debe Seleccionar un Estado");
           return;
       }

       consultorioModelo = new modelo.ConsultorioModelo(id, consultorio, estado);
       gestionarConsultorioModelo.actualizarConsultorioModelo(consultorioModelo);
   }
   
   public void eliminarConsultorioConstrolador(){
       int fila = frmConsultorioVista.ResultadosTbl.getSelectedRow();
       int id = 0;
       if (fila == -1) {
           JOptionPane.showMessageDialog(null, "Debe Seleccionar un Consultorio Para Eliminarlo");
       }
       else{
           id = Integer.parseInt(frmConsultorioVista.ResultadosTbl.getValueAt(fila, 0).toString());
           consultorioModelo = new modelo.ConsultorioModelo(id);
           gestionarConsultorioModelo.eliminarConsultorioModelo(consultorioModelo);
       }
   }
   
   public void limpiarFormularioControlador(){
       frmConsultorioVista.ConsultorioTxt.setText(null);
       frmConsultorioVista.ActivoOpt.setSelected(false);
       frmConsultorioVista.InactivoOpt.setSelected(false);           
   }
   
   public void limpiarTablaControlador(){
       frmConsultorioVista.getTableModel().setRowCount(0);
   }
}
