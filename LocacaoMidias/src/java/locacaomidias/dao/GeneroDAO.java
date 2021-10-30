package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Genero;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class GeneroDAO extends DAO<Genero>{
    
    public GeneroDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Genero obj) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "genero(descricao) "
                        + "VALUES(?);",
                new String[]{ "id" } )) {
            stmt.setString( 1, obj.getDescricao() );
            
            stmt.executeUpdate();
            obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        }
    }

    @Override
    public void atualizar(Genero obj) throws SQLException {
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE "
                        + "genero "
              + "SET descricao = ? "
              + "WHERE id = ?;" ) ){
            stmt.setString( 1, obj.getDescricao() );
            stmt.setLong( 2, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Genero obj) throws SQLException {
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM "
                        + "genero "
              + "WHERE id = ?;" ) ){
            stmt.setLong( 1, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Genero> listarTodos() throws SQLException {
        List<Genero> generos = new ArrayList<>();
        
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "g.id idGen, "
                        + "g.descricao descGen "
              + "FROM genero g "
              + "ORDER BY g.descricao;" ) ){
            
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                Genero g = new Genero();
                g.setId( rs.getLong( "idGen" ) );
                g.setDescricao( rs.getString( "descGen" ) );
                
                generos.add( g );
            }
            
            rs.close();
        }
        
        return generos;
    }

    @Override
    public Genero obterPorId(Long id) throws SQLException {
        Genero genero = null;
        
        try( PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "g.id idGen, "
                        + "g.descricao descGen "
              + "FROM genero g "
              + "WHERE g.id = ?;" ) ){
            stmt.setLong( 1, id );
            
            ResultSet rs = stmt.executeQuery();
            
            if( rs.next() ){
                genero = new Genero();
                genero.setId( rs.getLong( "idGen" ) );
                genero.setDescricao( rs.getString( "descGen" ) );
            }
            
            rs.close();
        }
        
        return genero;
    }
    
}
