package locacaomidias.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Midia;

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
            
            stmt.setLong( 1, obj.getIdLocacao().getId() );
            stmt.setLong( 2, obj.getIdExemplar().getCodigoInterno() );
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
    
    /**
     * Obtenção de todas as mídias por um identificador de locação.
     * Esse método será utilizado para o ajuste das mídias
     * que forem canceladas. Apenas os valores necessários serão obtidos.
     */
    public List<ItemLocacao> obterPorIdLocacao( Long idLocacao ) throws SQLException {

        List<ItemLocacao> itensLocacao = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                    "e.disponivel exemplarDisponivel, " + 
                    "e.codigo_interno codigoInterno, " + 
                    "m.id idMidia " +
                "FROM " +
                    "midia m, " +
                    "item_locacao il, " +
                    "exemplar e " + 
                "WHERE " + 
                    "il.locacao_id = ? AND " + 
                    "e.midia_id = m.id AND " +
                    "il.exemplar_codigo_interno = e.codigo_interno;" );
        
        stmt.setLong( 1, idLocacao );
        
        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {
            ItemLocacao il = new ItemLocacao();
            Exemplar e = new Exemplar();
            Midia m = new Midia();
           
            il.setIdExemplar( e );
            
            e.setMidia( m );
            e.setCodigoInterno( rs.getLong( "codigoInterno" ) );
            e.setDisponivel( rs.getBoolean( "exemplarDisponivel" ) );
            
            m.setId( rs.getLong( "idMidia" ) );
            
            itensLocacao.add( il );
            
        }

        rs.close();
        stmt.close();
        
        return itensLocacao;

    }
}
