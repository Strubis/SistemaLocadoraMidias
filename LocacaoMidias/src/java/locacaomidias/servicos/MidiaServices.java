package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Midia;

/**
 *
 * @author EmersonPC
 */
public class MidiaServices {
    /**
     * Para obter todas as mídias.
     * 
     * @return Lista de mídias
     */
    public List<Midia> getTodos(){
        List<Midia> midias = new ArrayList<>();
        
        try(MidiaDAO dao = new MidiaDAO()){
            midias = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return midias;
    }
}
