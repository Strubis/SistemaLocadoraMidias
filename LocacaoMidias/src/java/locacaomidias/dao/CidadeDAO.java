package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class CidadeDAO extends DAO<Cidade> {
    
    public CidadeDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Cidade obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "cidade( nome, estado_id ) " + 
                "VALUES( ?, ? );",
                new String[]{ "id" } );

        stmt.setString( 1, obj.getNome() );
        stmt.setLong( 2, obj.getEstado().getId() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Cidade obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE cidade " + 
                "SET" + 
                "    nome = ?," + 
                "    estado_id = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getNome() );
        stmt.setLong( 2, obj.getEstado().getId() );
        stmt.setLong( 3, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Cidade obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM cidade " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Cidade> listarTodos() throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    c.id idCidade, " + 
                "    c.nome nomeCidade, " + 
                "    e.id idEstado, " + 
                "    e.nome nomeEstado, " + 
                "    e.sigla siglaEstado " + 
                "FROM" + 
                "    cidade c, " + 
                "    estado e " + 
                "WHERE" + 
                "    c.estado_id = e.id " +
                "ORDER BY c.nome, e.nome, e.sigla;" );

        ResultSet rs = stmt.executeQuery();
        
        List<Cidade> cidades = new ArrayList<>();
        
        while( rs.next() ){
            Cidade cidade = new Cidade();
            Estado estado = new Estado();

            cidade.setId( rs.getLong( "idCidade" ) );
            cidade.setNome( rs.getString( "nomeCidade" ) );
            cidade.setEstado( estado );

            estado.setId( rs.getLong( "idEstado" ) );
            estado.setNome( rs.getString( "nomeEstado" ) );
            estado.setSigla( rs.getString( "siglaEstado" ) );
        }
        
        stmt.close();
        rs.close();
        
        return cidades;
    }

    @Override
    public Cidade obterPorId(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    c.id idCidade, " + 
                "    c.nome nomeCidade, " + 
                "    e.id idEstado, " + 
                "    e.nome nomeEstado, " + 
                "    e.sigla siglaEstado " + 
                "FROM" + 
                "    cidade c, " + 
                "    estado e " + 
                "WHERE" + 
                "    c.id = ? AND " +
                "    c.estado_id = e.id; " );
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();
        
        Cidade cidade = null;
        if( rs.next() ){
            cidade = new Cidade();
            Estado estado = new Estado();

            cidade.setId( rs.getLong( "idCidade" ) );
            cidade.setNome( rs.getString( "nomeCidade" ) );
            cidade.setEstado( estado );

            estado.setId( rs.getLong( "idEstado" ) );
            estado.setNome( rs.getString( "nomeEstado" ) );
            estado.setSigla( rs.getString( "siglaEstado" ) );
        }
        
        stmt.close();
        rs.close();
        
        return cidade;
    }
    
}
