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
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "TipoServlet", urlPatterns = {"/processaTipos"})
public class TipoServlet extends HttpServlet {

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
        
        try (TipoDAO dao = new TipoDAO()) {
            if( acao.equals( "inserir" ) ){
                String descricao = request.getParameter( "descricao" );
                
                Tipo t = new Tipo();
                t.setDescricao( descricao );
                
                Utils.validar( t, "id" );
                dao.salvar( t );
                disp = request.getRequestDispatcher( 
                        "/formularios/tipo/listagem.jsp" );
            }else if( acao.equals( "alterar" ) ){
                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter( "descricao" );
                
                Tipo t = dao.obterPorId( id );
                t.setDescricao( descricao );
                
                Utils.validar( t, "id" );
                dao.atualizar( t );
                disp = request.getRequestDispatcher( 
                        "/formularios/tipo/listagem.jsp" );
            }else if( acao.equals( "excluir" ) ){
                Long id = Utils.getLong( request, "id" );
                
                Tipo t = dao.obterPorId( id );
                
                dao.excluir( t );
                disp = request.getRequestDispatcher( 
                        "/formularios/tipo/listagem.jsp" );
            }else{
                Long id = Utils.getLong( request, "id" );
                
                Tipo t = dao.obterPorId( id );
                request.setAttribute( "tipo", t );
                
                if( acao.equals( "prepararAlteracao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/tipo/alterar.jsp" );
                }else if( acao.equals( "prepararExclusao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/tipo/excluir.jsp" );
                }
            }
        }catch(SQLException exc){
            Utils.prepararDespachoErro( request, exc.getMessage() );
        }
        
        if (disp != null) {
            disp.forward(request, response);
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
