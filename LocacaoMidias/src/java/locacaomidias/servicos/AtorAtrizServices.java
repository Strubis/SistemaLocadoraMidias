package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.AtorAtrizDAO;
import locacaomidias.entidades.AtorAtriz;

/**
 *
 * @author EmersonPC
 */
public class AtorAtrizServices {
    /**
     * Para obter todos os atores.
     * 
     * @return Lista de atores
     */
    public List<AtorAtriz> getTodos(){
        List<AtorAtriz> atores = new ArrayList<>();
        
        try(AtorAtrizDAO dao = new AtorAtrizDAO()){
            atores = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return atores;
    }
}
