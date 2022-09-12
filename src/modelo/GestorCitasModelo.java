package modelo;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar Cruz España
 */
public class GestorCitasModelo {
    
    recurso.Conexion conexion;
    Connection conn;

    public GestorCitasModelo() {
        conexion = new recurso.Conexion();
        conn = conexion.getConexion();
    }
    
    
    public void registrarCitaModelo(modelo.CitasModelo cita){
        String sql = "INSERT INTO cita (citFecha, citHora, citPaciente,citMedico, citConsultorio,citEstado,citObservaciones) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cita.getCitFecha());
            ps.setString(2, cita.getCitHora());
            ps.setInt(3, cita.getCitPaciente());
            ps.setInt(4, cita.getCitMedico());
            ps.setInt(5, cita.getCitConsultorio());
            ps.setString(6, cita.getCitEstado());
            ps.setString(7, cita.getCitObservaciones());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cita Registrada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public Vector consultarCitasModelo(){
        Vector<modelo.CitasVistaModelo> citas = new Vector<>();
        String sql = "SELECT * FROM view_citas";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                citas.add(new modelo.CitasVistaModelo(
                        rs.getInt("citid"), 
                        rs.getString("citfecha"), 
                        rs.getString("cithora"), 
                        rs.getInt("citpaciente"), 
                        rs.getInt("citmedico"), 
                        rs.getInt("citconsultorio"), 
                        rs.getString("citestado"), 
                        rs.getString("citobservaciones"), 
                        rs.getString("nombrespaciente"), 
                        rs.getString("apellidospaciente"), 
                        rs.getInt("documentopaciente"), 
                        rs.getString("fechanacimientopaciente"), 
                        rs.getString("nombremedico"), 
                        rs.getString("apellidomedico"), 
                        rs.getInt("documentomedico"), 
                        rs.getString("especialidadmedico"), 
                        rs.getString("nombreconsultorio"), 
                        rs.getString("estadoconsultorio")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return citas;
    }
    
    public Vector consultarCitasByIdControlador(int id){
        Vector<modelo.CitasModelo> citas = new Vector<>();
        String sql = "SELECT * FROM cita WHERE citid = " + id;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                citas.add(new modelo.CitasModelo(rs.getInt("citid"), 
                        rs.getString("citfecha"), 
                        rs.getString("cithora"), 
                        rs.getInt("citpaciente"), 
                        rs.getInt("citmedico"), 
                        rs.getInt("citconsultorio"), 
                        rs.getString("citestado"),
                        rs.getString("citobservaciones")
                ));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return citas;
    }
    
    public void actauizarCitaModelo(modelo.CitasModelo cita){
        String sql = "UPDATE cita SET citfecha=?,"
                + "cithora=?, citmedico=?,citconsultorio=?,"
                + "citestado=?,citobservaciones=? WHERE citid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cita.getCitFecha());
            ps.setString(2, cita.getCitHora());
            ps.setInt(3, cita.getCitMedico());
            ps.setInt(4, cita.getCitConsultorio());
            ps.setString(5, cita.getCitEstado());
            ps.setString(6, cita.getCitObservaciones());
            ps.setInt(7, cita.getCitId());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cita Actualizada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void eliminarCitaModelo(modelo.CitasModelo cita){
        String sql = "DELETE FROM cita WHERE citid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cita.getCitId());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cita Eliminada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
