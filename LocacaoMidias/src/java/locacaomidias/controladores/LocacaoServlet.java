package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
@WebServlet(name = "LocacaoServlet", urlPatterns = {"/processaLocacao"})
public class LocacaoServlet extends HttpServlet {

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
        
        try(    LocacaoDAO daoLocacao = new LocacaoDAO();
                ClienteDAO daoCliente = new ClienteDAO();
                ExemplarDAO daoExemplar = new ExemplarDAO();
                ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO() ){
            
            if( acao.equals( "inserir" ) ){
                Long idCliente = Utils.getLong( request, "idCliente" );
                String itensLocacao = request.getParameter( "itensLocacao" );
                Date dataFim = Utils.getDate( request.getParameter( "dataFim" ) );
                
                // Pegando todas mídias
                JsonReader jr = Json.createReader( new StringReader( itensLocacao ) );
                // Converte para array
                JsonArray ja = jr.readArray();
                
                Cliente c = daoCliente.obterPorId( idCliente );
                
                Locacao loc = new Locacao();
                loc.setDataInicio( Date.valueOf( LocalDate.now() ) );
                loc.setDataFim( dataFim );
                loc.setCancelada( false );
                loc.setIdCliente( c );
                
                Utils.validar( loc, "id" );
                daoLocacao.salvar( loc );
                
                // Iterando sobre as mídias
                for (JsonValue jsonValue : ja) {  
                    // O objeto do nosso array
                    JsonObject jo = jsonValue.asJsonObject();
                    
                    // Pegando os dados
                    Long idExemplar = Utils.getLong( 
                            jo.getString( "idExemplar" ) );
                    BigDecimal valorAluguel = Utils.getBigDecimal( 
                            jo.getString( "valorAluguel" ) );
                    
                    // Pegando o exemplar
                    Exemplar e = daoExemplar.obterPorId( idExemplar );
                    e.setDisponivel( false );
                    
                    ItemLocacao il = new ItemLocacao();
                    il.setIdExemplar( e );
                    il.setIdLocacao( loc );
                    il.setValor( valorAluguel );
                    
                    // como dica do professor:
                    // não validaremos o produto, pois
                    // permitiremos estoque negativo na venda
                    daoExemplar.atualizar( e );
                    daoItemLocacao.salvar( il );
                }
                
                disp = request.getRequestDispatcher( 
                        "/formularios/locacao/listagem.jsp" );
                
            }else if( acao.equals( "cancelar" ) ){
                Long id = Utils.getLong( request, "id" );
                
                // Cancelando a locação
                Locacao l = daoLocacao.obterPorId( id );
                l.setCancelada( true );
                daoLocacao.atualizar( l );
                
                // Percorrendo todas as locações cadastradas com o id, para
                // colocar como disponível novamente
                for (ItemLocacao il : daoItemLocacao.obterPorIdLocacao( id ) ) {
                    Exemplar e = il.getIdExemplar();
                    e.setDisponivel( true );
                    daoExemplar.atualizar( e );
                }
                
                response.setContentType( "application/json;charset=UTF-8" );
                
                JsonObject jo = Json.createObjectBuilder()
                        .add( "status", "ok" )
                        .build();
                
                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jo );
                }
            }
            
        }catch(SQLException exc){
            Utils.prepararDespachoErro( request, exc.getMessage() );
        }finally{
            if( disp != null ){
                disp.forward( request, response );
            }
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
