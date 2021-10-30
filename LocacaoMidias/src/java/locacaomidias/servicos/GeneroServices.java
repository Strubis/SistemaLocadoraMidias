package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.entidades.Genero;

/**
 *
 * @author EmersonPC
 */
public class GeneroServices {
    /**
     * Para obter todos os gêneros.
     * 
     * @return Lista de gêneros
     */
    public List<Genero> getTodos(){
        List<Genero> generos = new ArrayList<>();
        
        try(GeneroDAO dao = new GeneroDAO()){
            generos = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return generos;
    }
}
