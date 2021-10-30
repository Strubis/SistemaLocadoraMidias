package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;

/**
 *
 * @author EmersonPC
 */
public class ExemplarServices {
    /**
     * Para obter todos os exemplares.
     * 
     * @return Lista de exemplares
     */
    public List<Exemplar> getTodos(){
        List<Exemplar> exemplares = new ArrayList<>();
        
        try(ExemplarDAO dao = new ExemplarDAO()){
            exemplares = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return exemplares;
    }
}
