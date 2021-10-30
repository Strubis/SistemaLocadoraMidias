package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.entidades.ClassificacaoInterna;

/**
 *
 * @author EmersonPC
 */
public class ClassificacaoInternaServices {
    /**
     * Para obter todas as classificações internas.
     * 
     * @return Lista das classificações internas
     */
    public List<ClassificacaoInterna> getTodos(){
        List<ClassificacaoInterna> ci = new ArrayList<>();
        
        try(ClassificacaoInternaDAO dao = new ClassificacaoInternaDAO()){
            ci = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return ci;
    }
}
