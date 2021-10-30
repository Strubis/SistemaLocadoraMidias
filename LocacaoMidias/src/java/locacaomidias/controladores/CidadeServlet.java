package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "CidadeServlet", urlPatterns = {"/processaCidades"})
public class CidadeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( CidadeDAO daoCidade = new CidadeDAO();
              EstadoDAO daoEstado = new EstadoDAO() ){

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                Long idEstado = Utils.getLong( request, "idEstado" );

                Estado e = daoEstado.obterPorId( idEstado );

                Cidade c = new Cidade();
                c.setNome( nome );
                c.setEstado( e );

                Utils.validar( c, "id" );
                daoCidade.salvar( c );
                disp = request.getRequestDispatcher(
                        "/formularios/cidades/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                Long idEstado = Utils.getLong( request, "idEstado" );

                Estado e = daoEstado.obterPorId( idEstado );

                Cidade c = daoCidade.obterPorId( id );
                c.setNome( nome );
                c.setEstado( e );

                Utils.validar( c );
                daoCidade.atualizar( c );
                disp = request.getRequestDispatcher(
                        "/formularios/cidades/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Cidade c = daoCidade.obterPorId( id );

                daoCidade.excluir( c );
                disp = request.getRequestDispatcher(
                        "/formularios/cidades/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Cidade c = daoCidade.obterPorId( id );
                request.setAttribute( "cidade", c );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/cidades/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/cidades/excluir.jsp" );
                }
                
            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
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
        return "CidadesServlet";
    }// </editor-fold>

}
