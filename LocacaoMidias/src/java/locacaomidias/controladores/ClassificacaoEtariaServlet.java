package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 *
 * @author Emerson
 */
@WebServlet(name = "ClassificacaoEtariaServlet", urlPatterns = {"/processaClassificacaoEtaria"})
public class ClassificacaoEtariaServlet extends HttpServlet {

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
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;

        try (ClassificacaoEtariaDAO dao = new ClassificacaoEtariaDAO()) {

            if (acao.equals("inserir")) {
                String descricao = request.getParameter("descricao");
                
                ClassificacaoEtaria ce = new ClassificacaoEtaria();
                ce.setDescricao( descricao );
                
                Utils.validar( ce, "id" );
                dao.salvar( ce );
                disp = request.getRequestDispatcher( 
                        "/formularios/classificacaoEtaria/listagem.jsp" );
            } else if (acao.equals("alterar")) {
                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter("descricao");
                
                ClassificacaoEtaria ce = dao.obterPorId( id );
                ce.setDescricao( descricao );
                
                Utils.validar( ce, "id" );
                dao.atualizar( ce );
                disp = request.getRequestDispatcher( 
                        "/formularios/classificacaoEtaria/listagem.jsp" );
            } else if (acao.equals("excluir")) {
                Long id = Utils.getLong( request, "id" );

                ClassificacaoEtaria ce = dao.obterPorId( id );
                dao.excluir(ce);

                disp = request.getRequestDispatcher(
                        "/formularios/classificacaoEtaria/listagem.jsp");
            } else {
                Long id = Utils.getLong( request, "id" );

                ClassificacaoEtaria ce = dao.obterPorId( id );
                request.setAttribute( "classEtaria", ce );

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/classificacaoEtaria/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/classificacaoEtaria/excluir.jsp");
                }

            }
        } catch (SQLException exc) {
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
        return "ClassificacaoEtariaServlet";
    }// </editor-fold>

}
