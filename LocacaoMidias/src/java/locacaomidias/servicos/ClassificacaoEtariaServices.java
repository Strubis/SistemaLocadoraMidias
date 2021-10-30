package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.entidades.ClassificacaoEtaria;

/**
 *
 * @author EmersonPC
 */
public class ClassificacaoEtariaServices {
    /**
     * Para obter todas as classificações etárias.
     * 
     * @return Lista das classificações etárias
     */
    public List<ClassificacaoEtaria> getTodos(){
        List<ClassificacaoEtaria> ce = new ArrayList<>();
        
        try(ClassificacaoEtariaDAO dao = new ClassificacaoEtariaDAO()){
            ce = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return ce;
    }
}
