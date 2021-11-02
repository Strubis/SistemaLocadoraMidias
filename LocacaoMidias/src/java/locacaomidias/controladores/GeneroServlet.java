package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.entidades.Genero;
import locacaomidias.utils.Utils;

/**
 *
 * @author Emerson
 */
@WebServlet(name = "GeneroServlet", urlPatterns = {"/processaGenero"})
public class GeneroServlet extends HttpServlet {

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

        try (GeneroDAO dao = new GeneroDAO()) {

            if (acao.equals("inserir")) {
                String descricao = request.getParameter("descricao");
                
                Genero g = new Genero();
                g.setDescricao( descricao );
                
                Utils.validar( g, "id" );
                dao.salvar(g);
                disp = request.getRequestDispatcher(
                        "/formularios/genero/listagem.jsp");
            } else if (acao.equals("alterar")) {
                Long id = Utils.getLong( request, "id" );
                String descricao = request.getParameter("descricao");
                
                Genero g = dao.obterPorId( id );
                g.setDescricao( descricao );
                
                Utils.validar( g, "id" );
                dao.atualizar( g );
                disp = request.getRequestDispatcher(
                        "/formularios/genero/listagem.jsp");
            } else if (acao.equals("excluir")) {
                Long id = Utils.getLong( request, "id" );

                Genero gen = dao.obterPorId( id );

                dao.excluir(gen);
                disp = request.getRequestDispatcher(
                        "/formularios/genero/listagem.jsp");
            } else {
                Long id = Utils.getLong( request, "id" );

                Genero gen = dao.obterPorId( id );
                request.setAttribute("genero", gen);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/genero/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/genero/excluir.jsp");
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
        return "GeneroServlet";
    }// </editor-fold>

}
