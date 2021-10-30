package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Tipo;

/**
 *
 * @author EmersonPC
 */
public class TipoServices {
    /**
     * Para obter todos os tipos.
     * 
     * @return Lista de tipos
     */
    public List<Tipo> getTodos(){
        List<Tipo> tipos = new ArrayList<>();
        
        try(TipoDAO dao = new TipoDAO()){
            tipos = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return tipos;
    }
}
