package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.entidades.Cidade;

/**
 *
 * @author EmersonPC
 */
public class CidadeServices {
    /**
     * Para obter todas as cidades.
     * 
     * @return Lista de cidades
     */
    public List<Cidade> getTodos(){
        List<Cidade> cidades = new ArrayList<>();
        
        try(CidadeDAO dao = new CidadeDAO()){
            cidades = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return cidades;
    }
}
