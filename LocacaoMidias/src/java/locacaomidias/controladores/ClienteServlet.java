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
import locacaomidias.dao.ClienteDAO;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/processaClientes"})
public class ClienteServlet extends HttpServlet {

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
        
        try ( ClienteDAO daoCliente = new ClienteDAO();
              CidadeDAO daoCidade = new CidadeDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataNascimento = request.getParameter( "dataNascimento" );
                String cpf = request.getParameter( "cpf" );
                String email = request.getParameter( "email" );
                String logradouro = request.getParameter( "logradouro" );
                String numero = request.getParameter( "numero" );
                String bairro = request.getParameter( "bairro" );
                String cep = request.getParameter( "cep" );
                Long idCidade = Utils.getLong( request, "idCidade" );

                Cidade ci = daoCidade.obterPorId( idCidade );

                Cliente c = new Cliente();
                c.setNome( nome );
                c.setSobrenome( sobrenome );
                c.setDataNascimento( Utils.getDate( dataNascimento ) );
                c.setCpf( cpf );
                c.setEmail( email );
                c.setLogradouro( logradouro );
                c.setNumero( numero );
                c.setBairro( bairro );
                c.setCep( cep );
                c.setCidade( ci );

                Utils.validar( c, "id" );
                daoCliente.salvar( c );
                disp = request.getRequestDispatcher(
                        "/formularios/clientes/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataNascimento = request.getParameter( "dataNascimento" );
                String cpf = request.getParameter( "cpf" );
                String email = request.getParameter( "email" );
                String logradouro = request.getParameter( "logradouro" );
                String numero = request.getParameter( "numero" );
                String bairro = request.getParameter( "bairro" );
                String cep = request.getParameter( "cep" );
                Long idCidade = Utils.getLong( request, "idCidade" );

                Cidade ci = daoCidade.obterPorId( idCidade );

                Cliente c = daoCliente.obterPorId( id );
                c.setNome( nome );
                c.setSobrenome( sobrenome );
                c.setDataNascimento( Utils.getDate( dataNascimento ) );
                c.setCpf( cpf );
                c.setEmail( email );
                c.setLogradouro( logradouro );
                c.setNumero( numero );
                c.setBairro( bairro );
                c.setCep( cep );
                c.setCidade( ci );

                Utils.validar( c );
                daoCliente.atualizar( c );
                disp = request.getRequestDispatcher(
                        "/formularios/clientes/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Cliente c = daoCliente.obterPorId( id );

                daoCliente.excluir( c );
                disp = request.getRequestDispatcher(
                        "/formularios/clientes/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Cliente c = daoCliente.obterPorId( id );
                request.setAttribute( "cliente", c );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/clientes/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/clientes/excluir.jsp" );
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
        return "Short description";
    }// </editor-fold>

}
