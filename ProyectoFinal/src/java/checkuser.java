

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/checkuser"})
public class checkuser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
      
           Persistencia base=new Persistencia();
           ResultSet rs=base.consultaSQL("select * from usuarios where usuario='"+request.getParameter("inputEmail")+"' and clave='"+request.getParameter("inputPassword")+"'");
           if(rs==null){
               out.println("No hay usuarios que coincidan con la base de datos");
           }else{
               while(rs.next()){
                   out.println("<h1>Bienvenido</h1>");
                    out.println("Usuario:"+rs.getString("usuario")+" NomYApe:");           
                    out.println(rs.getString("nombreyapellido")+"<br>");
               }
               if(rs.first()==false){
                    out.println("No hay usuarios que coincidan con la busqueda");
                    out.println(rs.getString("<h1>Proyecto: "+request.getContextPath()+"</h1>"));
                    out.println(rs.getString("<h1>Usuario ingresado: "+request.getParameter("inputEmail")+"</h1>"));
               }
               
               rs=base.consultaSQL("select * from usuarios where usuario!='"+request.getParameter("inputEmail")+"'");
               
               out.println("<br><br><h4>Lista del resto de los usuarios:</h4>");
               while(rs.next()){
                   out.println("Usuario:"+rs.getString("usuario")+" NomYApe:");           
                   out.println(rs.getString("nombreyapellido")+"<br>");
               }
               
               base.desconexion();
               System.out.println("Se desconecto de la base");
           }
           
        }
        catch(SQLException ex){
             Logger.getLogger(checkuser.class.getName()).log(Level.SEVERE,null,ex);
        }
     
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
