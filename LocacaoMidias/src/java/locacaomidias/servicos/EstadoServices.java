package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Estado;

/**
 *
 * @author EmersonPC
 */
public class EstadoServices {
    /**
     * Para obter todos os estados.
     * 
     * @return Lista de estados
     */
    public List<Estado> getTodos(){
        List<Estado> estados = new ArrayList<>();
        
        try(EstadoDAO dao = new EstadoDAO()){
            estados = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return estados;
    }
}
