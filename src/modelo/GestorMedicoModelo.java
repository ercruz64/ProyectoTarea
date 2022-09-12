package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

public class GestorMedicoModelo {
    recurso.Conexion conexion;
    Connection conn;
    modelo.MedicoModelo medicoModelo;

    public GestorMedicoModelo() {
        conexion = new recurso.Conexion();
        conn = conexion.getConexion();
    }
    
    public void registrarMedicoModelo(modelo.MedicoModelo medico){
        String sql = "INSERT INTO medico (nombremedico,apellidomedico,documentomedico,especialidadmedico) "
                + "VALUES(?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, medico.getNombreMedico());
            ps.setString(2, medico.getApellidoMedico());
            ps.setInt(3, medico.getDocumentoMedico());
            ps.setString(4, medico.getEspecialidadMedico());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Medico Registrado");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    public Vector<modelo.MedicoModelo> consultarMedicoModelo(int parametro, String valor){
        Vector<modelo.MedicoModelo> medicos = new Vector<>();
        String sql = "SELECT * FROM medico ";
        switch(parametro){
            case 1:
                sql += " WHERE nombremedico like '%"+valor+"%' ORDER BY nombremedico";
                break;
            case 2:
                sql += " WHERE apellidomedico like '%"+valor+"%' ORDER BY apellidomedico";
                break;
            case 3:
                sql += " WHERE documentomedico like '%"+valor+"%' ORDER BY documentomedico";
                break;
            case 4:
                sql += " WHERE especialidadmedico like '%"+valor+"%' ORDER BY especialidadmedico";
                break;
            default:
                sql += "ORDER BY apellidomedico";
                break;
        }
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                medicos.add(new modelo.MedicoModelo(
                        rs.getInt("idmedico"), 
                        rs.getString("nombremedico"), 
                        rs.getString("apellidomedico"), 
                        rs.getInt("documentomedico"), 
                        rs.getString("especialidadmedico")
                ));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return medicos;
    }
    
    public void actualizarMedicoModelo(modelo.MedicoModelo medico){
        String sql = "UPDATE medico SET nombremedico=?,apellidomedico=?,documentomedico=?,especialidadmedico=? "
                + "WHERE idmedico=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, medico.getNombreMedico());
            ps.setString(2, medico.getApellidoMedico());
            ps.setInt(3, medico.getDocumentoMedico());
            ps.setString(4, medico.getEspecialidadMedico());
            ps.setInt(5, medico.getIdMedico());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Medico Actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    public void eliminarMedicoModelo(modelo.MedicoModelo medico){
        String sql = "DELETE FROM medico WHERE idmedico=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, medico.getIdMedico());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Medico Eliminado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
