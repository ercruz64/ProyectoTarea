package modelo;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import recurso.Conexion;

public class GestionarPacienteModelo {
    
    private Connection conn;
    private recurso.Conexion conexion;

    public GestionarPacienteModelo() {
        conexion = new Conexion();
        conn = conexion.getConexion();
    }
    
    public void registrarPacienteModelo(modelo.PacienteModelo paciente){
        String sql = "INSERT INTO paciente (nombrespaciente, apellidospaciente,"
                + " documentopaciente,fechanacimientopaciente, generopaciente)" 
                + " VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNombresPaciente());
            ps.setString(2, paciente.getApellidosPaciente());
            ps.setInt(3, paciente.getDocumentoPaciente());
            ps.setString(4, paciente.getFechaNacimientoPaciente());
            ps.setString(5, paciente.getGeneroPaciente());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Paciente Registrado");
            ps.close();
            //conexion.cerrarConexion();
//conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }       
        
    }
    
    public Vector<PacienteModelo> consultarPacienteModelo(int parametro, String valor){
        Vector<PacienteModelo> pacientes = new Vector<>();
        String sql = "SELECT * FROM paciente ";
        switch(parametro){
            case 1:
                sql += "WHERE documentopaciente like '%"+valor+"%' ORDER BY documentopaciente";
                break;
                
            case 2:
                sql += "WHERE nombrespaciente LIKE '%"+valor+"%' ORDER BY nombrespaciente";
                break;
                
            case 3:
                sql += "WHERE apellidospaciente LIKE '%"+valor+"%' ORDER BY apellidospaciente";
                break;
            default:
                sql = "SELECT * FROM paciente ORDER By apellidospaciente, nombrespaciente";
        }
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                pacientes.add(new modelo.PacienteModelo(rs.getInt("idpaciente"),
                        rs.getString("nombrespaciente"), 
                        rs.getString("apellidospaciente"), 
                        rs.getInt("documentopaciente"), 
                        rs.getString("fechanacimientopaciente"), 
                        rs.getString("generopaciente")));
            }
            st.close();
            rs.close();
            //conexion.cerrarConexion();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pacientes;
    }
    
    public void actualizarPacienteModelo(modelo.PacienteModelo paciente){
        String sql = "UPDATE paciente SET nombrespaciente=?, "
                + "apellidospaciente=?, documentopaciente=?, "
                + "fechanacimientopaciente=?,generopaciente=? "
                + "WHERE idpaciente=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, paciente.getNombresPaciente());
            ps.setString(2, paciente.getApellidosPaciente());
            ps.setInt(3, paciente.getDocumentoPaciente());
            ps.setString(4, paciente.getFechaNacimientoPaciente());
            ps.setString(5, paciente.getGeneroPaciente());
            ps.setInt(6, paciente.getIdPaciente());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente Actualizado");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void eliminarPacienteModelo(modelo.PacienteModelo paciente){
        String sql = "DELETE FROM paciente WHERE idpaciente = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, paciente.getIdPaciente());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Paciente Eliminado Correctamente");
        } catch (Exception e) {
        }
    }
}

















