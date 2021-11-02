package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 *
 * @author Emerson
 */
@WebServlet(name = "AtorServlet", urlPatterns = {"/processaAtorAtriz"})
public class AtorServlet extends HttpServlet {

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
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;
        
        try(AtorAtrizDAO dao = new AtorAtrizDAO()){
            
            if( acao.equals( "inserir" ) ){
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );
                
                AtorAtriz aa = new AtorAtriz();
                aa.setNome( nome );
                aa.setSobrenome( sobrenome );
                aa.setDataEstreia( Utils.getDate( dataEstreia ) );
                
                Utils.validar( aa, "id" );
                dao.salvar( aa );
                disp = request.getRequestDispatcher( 
                        "/formularios/ator/listagem.jsp" );
            }else if( acao.equals( "alterar" ) ){
                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );
                
                AtorAtriz aa = dao.obterPorId( id );
                aa.setNome( nome );
                aa.setSobrenome( sobrenome );
                aa.setDataEstreia( Utils.getDate( dataEstreia ) );
                
                Utils.validar( aa, "id" );
                dao.atualizar( aa );
                disp = request.getRequestDispatcher( 
                        "/formularios/ator/listagem.jsp" );
            }else if( acao.equals( "excluir" ) ){
                Long id = Utils.getLong( request, "id" );
                AtorAtriz aa = dao.obterPorId( id );
                
                dao.excluir( aa );
                disp = request.getRequestDispatcher( 
                        "/formularios/ator/listagem.jsp" );
            }else{
                Long id = Utils.getLong( request, "id" );
                AtorAtriz aa = dao.obterPorId( id );
                request.setAttribute( "ator", aa );
                
                if( acao.equals( "prepararAlteracao" ) ){
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/alterar.jsp" );
                }else if( acao.equals( "prepararExclusao" ) ){
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/excluir.jsp" );
                }
            }
            
        }catch(SQLException exc){
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }
        
        
        if( disp != null ){
            disp.forward( request, response );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
