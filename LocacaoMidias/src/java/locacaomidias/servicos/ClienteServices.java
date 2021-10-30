package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.entidades.Cliente;

/**
 *
 * @author EmersonPC
 */
public class ClienteServices {
    /**
     * Para obter todos os clientes.
     * 
     * @return Lista de clientes
     */
    public List<Cliente> getTodos(){
        List<Cliente> clientes = new ArrayList<>();
        
        try(ClienteDAO dao = new ClienteDAO()){
            clientes = dao.listarTodos();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return clientes;
    }
}
