
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Persistencia {
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
   
    
    public String servidor,bb,usuario,contrasena,ejecutar;
    
    public Connection conectarse() throws  SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            servidor="localhost";
            bb="pf_nardelli_luciano";
            usuario="root";contrasena="root";
            String ruta="jdbc:mysql://"+servidor+":3306/"+bb+"?useSSL=false";
            cn=DriverManager.getConnection(ruta,usuario,contrasena);
            System.out.println("Conectado");
        } catch (ClassNotFoundException ex ) {
            System.out.println("No conectado");
            ex.printStackTrace();
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE,null,ex);
        }        
        return cn;
    }
    public ResultSet consultaSQL(String consulta){
        
        try {
            ps=conectarse().prepareStatement(consulta);
            rs=ps.executeQuery();           
        } catch (SQLException ex) {
             Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE,null,ex);
        }
        return rs;
    }
    public void desconexion(){
        try{
           if(cn!=null){
               cn.close();
           } 
           if(rs!=null){
               rs.close();
           }
           if(ps!=null){
               ps.close();
           }
        }catch(Exception e){
            System.err.println("Error de desconexion");
            e.printStackTrace();
        }
    }
}
