package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import recurso.Conexion;

public class GestionarConsultorioModelo {
    
    private Connection conn;
    private Conexion conexion;

    public GestionarConsultorioModelo() {
        conexion = new Conexion();
        conn = conexion.getConexion();
    }
    
    
    public void registrarConsultorioModelo(ConsultorioModelo consultorio){
        String sql = "INSERT INTO consultorio (nombreconsultorio, estadoconsultorio) "
                + "VALUES(?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, consultorio.getNombreConsultorio());
            ps.setString(2, consultorio.getEstadoConsultorio());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Consultorio Registrado");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public Vector consultarConsultorioModelo(int parametro, String valor){
        Vector<modelo.ConsultorioModelo> consultorios = new Vector<>();
        String sql = "SELECT * FROM consultorio ";
        switch(parametro){
            case 1:
                sql += "WHERE nombreconsultorio like '%"+valor+"%' ORDER BY nombreconsultorio";
                break;
                
            case 2:
                sql += "WHERE estadoconsultorio = '"+valor+"' ORDER BY nombreconsultorio";
                break;
                
            case 3: 
                sql += "WHERE estadoconsultorio = '"+valor+"' ORDER BY nombreconsultorio";
                break;
                
            default:
                sql += "ORDER BY nombreconsultorio";
        }
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                consultorios.add(new modelo.ConsultorioModelo(
                        rs.getInt("idconsultorio"), 
                        rs.getString("nombreconsultorio"), 
                        rs.getString("estadoconsultorio")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return consultorios;
    }
    
    public void actualizarConsultorioModelo(modelo.ConsultorioModelo consultorio){
        String sql = "UPDATE consultorio SET nombreconsultorio=?, estadoconsultorio=? "
                + "WHERE idconsultorio=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, consultorio.getNombreConsultorio());
            ps.setString(2, consultorio.getEstadoConsultorio());
            ps.setInt(3, consultorio.getIdConsultorio());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Consultorio Actualizado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void eliminarConsultorioModelo(modelo.ConsultorioModelo consultorio){
        String sql = "DELETE FROM consultorio WHERE idconsultorio = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, consultorio.getIdConsultorio());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consultiro Eliminado Correctamente");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
