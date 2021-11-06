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
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "MidiaServlet", urlPatterns = {"/processaMidia"})
public class MidiaServlet extends HttpServlet {

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
        
        try (MidiaDAO daoMidia = new MidiaDAO(); 
                AtorAtrizDAO daoAtor = new AtorAtrizDAO();
                GeneroDAO daoGen = new GeneroDAO();
                ClassificacaoEtariaDAO daoCe = new ClassificacaoEtariaDAO();
                ClassificacaoInternaDAO daoCi = new ClassificacaoInternaDAO();
                TipoDAO daoTipo = new TipoDAO();) {
            
            if( acao.equals( "inserir" ) ){
                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter( "anoLancamento" );
                String codigoBarras = request.getParameter( "codBarras" );
                Long durMin = Utils.getLong( request, "durMin" );
                Long atorAtrizP = Utils.getLong( request, "idAap" );
                Long atorAtrizC = Utils.getLong( request, "idAac" );
                Long gen = Utils.getLong( request, "idGen" );
                Long ce = Utils.getLong( request, "idCe" );
                Long tipo = Utils.getLong( request, "idTipo" );
                Long ci = Utils.getLong( request, "idCi" );
                
                AtorAtriz aap = daoAtor.obterPorId( atorAtrizP );
                AtorAtriz aac = daoAtor.obterPorId( atorAtrizC );
                Genero g = daoGen.obterPorId( gen );
                ClassificacaoEtaria classEtaria = daoCe.obterPorId( ce );
                Tipo t = daoTipo.obterPorId( tipo );
                ClassificacaoInterna classInterna = daoCi.obterPorId( ci );
                
                Midia m = new Midia();
                m.setTitulo( titulo );
                m.setAnoLancamento( anoLancamento );
                m.setCodigoBarras( codigoBarras );
                m.setDuracaoMin( durMin );
                m.setIdAtorAtrizPrincipal( aap );
                m.setIdAtorAtrizCoadjuvante( aac );
                m.setIdGenero( g );
                m.setIdClassEtaria( classEtaria );
                m.setIdTipo( t );
                m.setIdClassInterna( classInterna );
                
                Utils.validar( m, "id" );
                daoMidia.salvar( m );
                disp = request.getRequestDispatcher( 
                        "/formularios/midia/listagem.jsp" );
            }else if( acao.equals( "alterar" ) ){
                Long id = Utils.getLong( request, "id" );
                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter( "anoLancamento" );
                String codigoBarras = request.getParameter( "codBarras" );
                Long durMin = Utils.getLong( request, "durMin" );
                Long atorAtrizP = Utils.getLong( request, "idAap" );
                Long atorAtrizC = Utils.getLong( request, "idAac" );
                Long gen = Utils.getLong( request, "idGen" );
                Long ce = Utils.getLong( request, "idCe" );
                Long tipo = Utils.getLong( request, "idTipo" );
                Long ci = Utils.getLong( request, "idCi" );
                
                AtorAtriz aap = daoAtor.obterPorId( atorAtrizP );
                AtorAtriz aac = daoAtor.obterPorId( atorAtrizC );
                Genero g = daoGen.obterPorId( gen );
                ClassificacaoEtaria classEtaria = daoCe.obterPorId( ce );
                Tipo t = daoTipo.obterPorId( tipo );
                ClassificacaoInterna classInterna = daoCi.obterPorId( ci );
                
                Midia m = daoMidia.obterPorId( id );
                m.setTitulo( titulo );
                m.setAnoLancamento( anoLancamento );
                m.setCodigoBarras( codigoBarras );
                m.setDuracaoMin( durMin );
                m.setIdAtorAtrizPrincipal( aap );
                m.setIdAtorAtrizCoadjuvante( aac );
                m.setIdGenero( g );
                m.setIdClassEtaria( classEtaria );
                m.setIdTipo( t );
                m.setIdClassInterna( classInterna );
                
                Utils.validar( m, "id" );
                daoMidia.atualizar( m );
                disp = request.getRequestDispatcher( 
                        "/formularios/midia/listagem.jsp" );
            }else if( acao.equals( "excluir" ) ){
                Long id = Utils.getLong( request, "id" );
                
                Midia m = daoMidia.obterPorId( id );
                daoMidia.excluir(m);
                
                disp = request.getRequestDispatcher( 
                        "/formularios/midia/listagem.jsp" );
            }else{
                Long id = Utils.getLong( request, "id" );
                
                Midia m = daoMidia.obterPorId( id );
                request.setAttribute( "midia", m );
                
                if( acao.equals( "prepararAlteracao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/midia/alterar.jsp" );
                }else if( acao.equals( "prepararExclusao" ) ){
                    disp = request.getRequestDispatcher( 
                        "/formularios/midia/excluir.jsp" );
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
