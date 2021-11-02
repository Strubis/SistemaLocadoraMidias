package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "ExemplarServlet", urlPatterns = {"/processaExemplar"})
public class ExemplarServlet extends HttpServlet {

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
        
        try(ExemplarDAO dao = new ExemplarDAO(); 
                MidiaDAO daoMidia = new MidiaDAO()){
            if( acao.equals( "inserir" ) ){
                Long idMidia = Utils.getLong( request, "idMidia" );
                
                Midia midia = daoMidia.obterPorId( idMidia );
                
                Exemplar e = new Exemplar();
                e.setDisponivel( true );
                e.setMidia( midia );
                
                Utils.validar( e, "codigoInterno" );
                dao.salvar( e );
                disp = request.getRequestDispatcher( 
                        "/formularios/exemplar/listagem.jsp" );
            }else if( acao.equals( "alterar" ) ){
                Long idMidia = Utils.getLong( request, "idMidia" );
                Long id = Utils.getLong( request, "id" );
                
                Midia midia = daoMidia.obterPorId( idMidia );
                Exemplar e = dao.obterPorId( id );
                e.setDisponivel( true );
                e.setMidia( midia );
                
                Utils.validar( e, "codigoInterno" );
                dao.atualizar( e );
                disp = request.getRequestDispatcher( 
                        "/formularios/exemplar/listagem.jsp" );
            }else if( acao.equals( "excluir" ) ){
                Long id = Utils.getLong( request, "id" );
                
                Exemplar e = dao.obterPorId( id );
                
                dao.excluir( e );
                disp = request.getRequestDispatcher( 
                        "/formularios/exemplar/listagem.jsp" );
            }else{
                Long id = Utils.getLong( request, "id" );
                Exemplar e = dao.obterPorId( id );
                
                request.setAttribute( "exemplar", e );
                
                if( acao.equals( "prepararAlteracao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/exemplar/alterar.jsp" );
                }else if( acao.equals( "prepararExclusao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/exemplar/excluir.jsp" );
                }
            }
        }catch(SQLException exc){
            Utils.prepararDespachoErro( request, exc.getMessage() );
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
