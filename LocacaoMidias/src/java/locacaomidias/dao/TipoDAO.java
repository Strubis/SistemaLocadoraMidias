package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class TipoDAO extends DAO<Tipo>{
    
    public TipoDAO() throws SQLException{
    }

    @Override
    public void salvar(Tipo obj) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "tipo(descricao) "
                        + "VALUES(?);",
                new String[]{ "id" } )) {
            stmt.setString( 1, obj.getDescricao() );
            
            stmt.executeUpdate();
            obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        }
    }

    @Override
    public void atualizar(Tipo obj) throws SQLException {
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE "
                        + "tipo "
              + "SET descricao = ? "
              + "WHERE id = ?;" ) ){
            stmt.setString( 1, obj.getDescricao() );
            stmt.setLong( 2, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Tipo obj) throws SQLException {
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM "
                        + "tipo "
              + "WHERE id = ?;" ) ){
            stmt.setLong( 1, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Tipo> listarTodos() throws SQLException {
        List<Tipo> tipos = new ArrayList<>();
        
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "t.id idTipo, "
                        + "t.descricao descTipo "
              + "FROM tipo t "
              + "ORDER BY t.descricao;" ) ){
            
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                Tipo t = new Tipo();
                t.setId( rs.getLong( "idTipo" ) );
                t.setDescricao( rs.getString( "descTipo" ) );
                
                tipos.add( t );
            }
            
            rs.close();
        }
        
        return tipos;
    }

    @Override
    public Tipo obterPorId(Long id) throws SQLException {
        Tipo tipo = null;
        
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "t.id idTipo, "
                        + "t.descricao descTipo "
              + "FROM tipo t "
              + "WHERE t.id = ?;" ) ){
            stmt.setLong( 1, id );
            
            ResultSet rs = stmt.executeQuery();
            
            if( rs.next() ){
                tipo = new Tipo();
                tipo.setId( rs.getLong( "idTipo" ) );
                tipo.setDescricao( rs.getString( "descTipo" ) );
            }
            
            rs.close();
        }
        
        return tipo;
    }
}
