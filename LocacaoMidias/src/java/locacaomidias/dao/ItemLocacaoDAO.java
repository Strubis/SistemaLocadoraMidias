package locacaomidias.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.entidades.ItemLocacao;

/**
 *
 * @author EmersonPC
 */
public class ItemLocacaoDAO extends DAO<ItemLocacao>{
    
    public ItemLocacaoDAO() throws SQLException{
    }

    @Override
    public void salvar(ItemLocacao obj) throws SQLException {
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "item_locacao(locacao_id, exemplar_codigo_interno, valor) "
              + "VALUES(?, ?, ?);" )){
            
            stmt.setLong( 1, obj.getIdExemplar().getCodigoInterno() );
            stmt.setLong( 2, obj.getIdLocacao().getId() );
            stmt.setBigDecimal( 3, obj.getValor() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(ItemLocacao obj) throws SQLException {
        /**
         * Não vamos atualizar um item de locação.
         */
    }

    @Override
    public void excluir(ItemLocacao obj) throws SQLException {
        /**
         * Não vamos excluir um item de locação.
         */
    }

    @Override
    public List<ItemLocacao> listarTodos() throws SQLException {
        /**
         * Não vamos listar todos os itens, pois essa entidade só
         * irá servir para representar a ligação.
         */
        
        return null;
    }

    @Override
    public ItemLocacao obterPorId(Long id) throws SQLException {
        /**
         * Para obter por Id iremos precisar fazer um tratamento
         * específico.
         */
        
        return null;
    }
}
