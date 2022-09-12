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
import vista.FrmCitasVista;

/**
 *
 * @author Edgar Cruz España
 */
public class CitasControlador implements ActionListener{
    
    modelo.GestionarPacienteModelo gestionarPacienteModelo;
    modelo.GestorMedicoModelo gestorMedicoModelo;
    modelo.GestionarConsultorioModelo gestionarConsultorioModelo;
    modelo.CitasModelo citasModelo;
    modelo.GestorCitasModelo gestorCitasModelo;
    vista.FrmCitasVista frmCitasVista;

    public CitasControlador(FrmCitasVista frmCitasVista) {
        this.frmCitasVista = frmCitasVista;
        gestorCitasModelo = new modelo.GestorCitasModelo();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmCitasVista.RegistrarBtn)) {
            registrarCitasControlador();
        }
        if (e.getSource().equals(frmCitasVista.BuscarBtn)) {
            consultarCitasControlador();
        }
        if (e.getSource().equals(frmCitasVista.EditarBtn)) {
            editarCitasControlador();
        }
        if (e.getSource().equals(frmCitasVista.ModificarBtn)) {
            actualizarCitasControlador();
        }
        if (e.getSource().equals(frmCitasVista.EliminarBtn)) {
            eliminarCitaControlador();
        }
    }
    
    
    public void registrarCitasControlador(){
        int idPaciente = 0;
        int idMedico = 0;
        int idConsultorio = 0;
        String estadoCita = "";
        
        idPaciente = frmCitasVista.PacienteCmb.getItemAt(frmCitasVista.PacienteCmb.getSelectedIndex()).getIdPaciente();
        idMedico = frmCitasVista.MedicoCmb.getItemAt(frmCitasVista.MedicoCmb.getSelectedIndex()).getIdMedico();
        idConsultorio = frmCitasVista.ConsultorioCmb.getItemAt(frmCitasVista.ConsultorioCmb.getSelectedIndex()).getIdConsultorio();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaCita = formato.format(frmCitasVista.FechaDte.getDate());
        String horaCita = frmCitasVista.HoraCmb.getSelectedItem() + ":" + frmCitasVista.MinutosCmb.getSelectedItem();
        if (frmCitasVista.AsignadaOpt.isSelected()) {
            estadoCita = "Asignada";
        }
        else if(frmCitasVista.CanceladaOpt.isSelected()){
            estadoCita = "Cancelada";
        }
        else if(frmCitasVista.AtendidaOpt.isSelected()){
            estadoCita = "Atendida";
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Estado de Cita");
            return;
        }
        String observaciones = frmCitasVista.ObservacionesTxt.getText();
        citasModelo = new modelo.CitasModelo(fechaCita,horaCita,idPaciente,idMedico,idConsultorio,estadoCita,observaciones);
        gestorCitasModelo.registrarCitaModelo(citasModelo);
    }
    
    
    public void consultarCitasControlador(){
        Vector<modelo.CitasVistaModelo> citas = new Vector<>();
        citas = gestorCitasModelo.consultarCitasModelo();
        String datoCita[] = new String[8];
        for (modelo.CitasVistaModelo datos: citas) {
            datoCita[0] = Integer.toString(datos.getCitid());
            datoCita[1] = datos.getCitfecha();
            datoCita[2] = datos.getCithora();
            datoCita[3] = datos.getNombrespaciente() + " " + datos.getApellidospaciente();
            datoCita[4] = datos.getNombremedico() + " " + datos.getApellidomedico();
            datoCita[5] = datos.getNombreconsultorio();
            datoCita[6] = datos.getCitestado();
            datoCita[7] = datos.getCitobservaciones();
            frmCitasVista.getTableModel().addRow(datoCita);
            frmCitasVista.getTableModel().fireTableDataChanged();
        }       
    }
    
    
    public void cargarPacientesControlador(){
        Vector<modelo.PacienteModelo> pacientes = new Vector<>();
        gestionarPacienteModelo = new modelo.GestionarPacienteModelo();
        pacientes = gestionarPacienteModelo.consultarPacienteModelo(0, "");
        for (int i = 0; i < pacientes.size(); i++) {
            frmCitasVista.PacienteCmb.addItem(new modelo.PacienteModelo(
                    pacientes.get(i).getIdPaciente(), 
                    pacientes.get(i).getNombresPaciente(), 
                    pacientes.get(i).getApellidosPaciente(), 
                    pacientes.get(i).getDocumentoPaciente(), 
                    pacientes.get(i).getFechaNacimientoPaciente(), 
                    pacientes.get(i).getGeneroPaciente()));
        }
    }
    
    public void cargarMedicosControlador(){
        Vector<modelo.MedicoModelo> medicos = new Vector<>();
        gestorMedicoModelo = new modelo.GestorMedicoModelo();
        medicos = gestorMedicoModelo.consultarMedicoModelo(0, "");
        for (int i = 0; i < medicos.size(); i++) {
            frmCitasVista.MedicoCmb.addItem(new modelo.MedicoModelo(
                    medicos.get(i).getIdMedico(), 
                    medicos.get(i).getNombreMedico(), 
                    medicos.get(i).getApellidoMedico(), 
                    medicos.get(i).getDocumentoMedico()));
        }
    }
    
    public void cargarConsultoriosControlador(){
        Vector<modelo.ConsultorioModelo> consultorio = new Vector<>();
        gestionarConsultorioModelo = new modelo.GestionarConsultorioModelo();
        consultorio = gestionarConsultorioModelo.consultarConsultorioModelo(0, "");
        for (int i = 0; i < consultorio.size(); i++) {
            frmCitasVista.ConsultorioCmb.addItem(new modelo.ConsultorioModelo(consultorio.get(i).getIdConsultorio(), 
                    consultorio.get(i).getNombreConsultorio(), 
                    consultorio.get(i).getEstadoConsultorio()));
        }
    }
    
    public void editarCitasControlador(){
        int fila = frmCitasVista.ResultadosTbl.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una cita");
        }
        else{            
            try {
                int id = Integer.parseInt(frmCitasVista.ResultadosTbl.getValueAt(fila, 0).toString());
                Vector<modelo.CitasModelo> citas = new Vector<>();
                citas = gestorCitasModelo.consultarCitasByIdControlador(id);
                int idPaciente = citas.get(0).getCitPaciente();
                int idMedico   = citas.get(0).getCitMedico();
                int idConsultorio = citas.get(0).getCitConsultorio();
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(citas.get(0).getCitFecha().toString());
                String hora = citas.get(0).getCitHora().substring(0, 2);
                String minutos = citas.get(0).getCitHora().substring(3,5);
                String estado = citas.get(0).getCitEstado();
                String observacion = citas.get(0).getCitObservaciones();
                
                for (int i = 0; i < frmCitasVista.PacienteCmb.getItemCount(); i++) {
                    if ( idPaciente == frmCitasVista.PacienteCmb.getItemAt(i).getIdPaciente()) {
                        frmCitasVista.PacienteCmb.setSelectedIndex(i);
                        break;
                    }
                }
                
                for (int i = 0; i < frmCitasVista.MedicoCmb.getItemCount(); i++) {
                    if (idMedico == frmCitasVista.MedicoCmb.getItemAt(i).getIdMedico()) {
                        frmCitasVista.MedicoCmb.setSelectedIndex(i);
                        break;
                    }
                }
                
                for (int i = 0; i < frmCitasVista.ConsultorioCmb.getItemCount(); i++) {
                    if (idConsultorio == frmCitasVista.ConsultorioCmb.getItemAt(i).getIdConsultorio()) {
                        frmCitasVista.ConsultorioCmb.setSelectedIndex(i);
                        break;
                    }
                }
                
                frmCitasVista.FechaDte.setDate(fecha);
                
                for (int i = 0; i < frmCitasVista.HoraCmb.getItemCount(); i++) {
                    if (hora.equals(frmCitasVista.HoraCmb.getItemAt(i))) {
                        frmCitasVista.HoraCmb.setSelectedIndex(i);
                        break;
                    }
                }
                for (int i = 0; i < frmCitasVista.MinutosCmb.getItemCount(); i++) {
                    if (minutos.equals(frmCitasVista.MinutosCmb.getItemAt(i))) {
                        frmCitasVista.MinutosCmb.setSelectedIndex(i);
                        break;
                    }
                }
                if (estado.equals("Asignada")) {
                    frmCitasVista.AsignadaOpt.setSelected(true);
                }
                else if(estado.equals("Cancelada")){
                    frmCitasVista.CanceladaOpt.setSelected(true);
                }
                else if (estado.equals("Atendida")){
                    frmCitasVista.AtendidaOpt.setSelected(true);
                }
                frmCitasVista.ObservacionesTxt.setText(observacion);
            } catch (ParseException ex) {
                Logger.getLogger(CitasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarCitasControlador(){
        int fila = frmCitasVista.ResultadosTbl.getSelectedRow();
        int id = 0;
        int idPaciente, idMedico,idConsultorio;
        String fecha, hora, observacion;
        String estado = "";
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Cita");
        }
        else{
            id = Integer.parseInt(frmCitasVista.ResultadosTbl.getValueAt(fila, 0).toString());
            idPaciente = frmCitasVista.PacienteCmb.getItemAt(frmCitasVista.PacienteCmb.getSelectedIndex()).getIdPaciente();
            idMedico = frmCitasVista.MedicoCmb.getItemAt(frmCitasVista.MedicoCmb.getSelectedIndex()).getIdMedico();
            idConsultorio = frmCitasVista.ConsultorioCmb.getItemAt(frmCitasVista.ConsultorioCmb.getSelectedIndex()).getIdConsultorio();
            hora = frmCitasVista.HoraCmb.getSelectedItem() + ":" + frmCitasVista.MinutosCmb.getSelectedItem();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fecha = formato.format(frmCitasVista.FechaDte.getDate());
            if (frmCitasVista.AsignadaOpt.isSelected()) {
                estado = "Asignada";
            }
            else if(frmCitasVista.CanceladaOpt.isSelected()){
                estado = "Cancelada";
            }
            else if(frmCitasVista.AtendidaOpt.isSelected()){
                estado = "Atendida";
            }
            else{
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un Estado de Cita");
            }
            observacion = frmCitasVista.ObservacionesTxt.getText();
            
            modelo.CitasModelo cita = new modelo.CitasModelo(id, fecha, hora, idPaciente, idMedico, idConsultorio, estado, observacion);
            gestorCitasModelo.actauizarCitaModelo(cita);
        }
    }
    
    public void eliminarCitaControlador(){
        int fila, id;
        fila = frmCitasVista.ResultadosTbl.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para eliminar");
        }
        else{
            id = Integer.parseInt(frmCitasVista.ResultadosTbl.getValueAt(fila, 0).toString());
            modelo.CitasModelo cita = new modelo.CitasModelo(id);
            gestorCitasModelo.eliminarCitaModelo(cita);
        }
    }
    
}
