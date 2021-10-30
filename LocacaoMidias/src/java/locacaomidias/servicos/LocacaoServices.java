package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Locacao;

/**
 *
 * @author EmersonPC
 */
public class LocacaoServices {
    /**
     * Para obter todas as locações.
     * 
     * @return Lista de locações
     */
    public List<Locacao> getTodos(){
        List<Locacao> locacoes = new ArrayList<>();
        
        try(LocacaoDAO dao = new LocacaoDAO()){
            locacoes = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return locacoes;
    }
}
