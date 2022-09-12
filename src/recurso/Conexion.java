package recurso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    private Connection conexion;
    
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/javabd";
    String ip = "127.0.0.1";
    String usr = "root";
    String pass = "123456789";

    public Conexion() {
        
        try {
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(url, usr, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error\nIntentelo mas tarde");
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
    }
    
    /*Metodo para obtener la conexion a la bd */
    public Connection getConexion(){
        return conexion;
    }    
    
    /*Metodo para cerrar la conexion de la bd */
    public Connection cerrarConexion() throws SQLException {
        conexion.close();
        conexion = null;
        return conexion;
    }
    
}
