package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class ClassificacaoInternaDAO extends DAO<ClassificacaoInterna>{

    public ClassificacaoInternaDAO() throws SQLException{
    }
    
    @Override
    public void salvar(ClassificacaoInterna obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                    + "classificacao_interna ( descricao, valorAluguel ) "
                + "VALUES( ?, ? );", 
                new String[]{ "id" } );
        stmt.setString( 1, obj.getDescricao() );
        stmt.setBigDecimal( 2, obj.getValorAluguel() );
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(ClassificacaoInterna obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE classificacao_interna "
              + "SET "
                   + "descricao = ?, valorAluguel = ? "
              + "WHERE id = ?;" );
        stmt.setString( 1, obj.getDescricao() );
        stmt.setBigDecimal( 2, obj.getValorAluguel() );
        stmt.setLong( 3, obj.getId() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(ClassificacaoInterna obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM "
                  + "classificacao_interna "
              + "WHERE id = ?;" );
        stmt.setLong( 1, obj.getId() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<ClassificacaoInterna> listarTodos() throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "ci.id idClassInterna, "
                        + "ci.descricao descClassInterna, "
                        + "ci.valorAluguel valorAluguel "
                + "FROM classificacao_interna ci "
                + "ORDER BY ci.descricao;" );
        
        ResultSet rs = stmt.executeQuery();
        
        List<ClassificacaoInterna> classInternas = new ArrayList<>();
        
        while( rs.next() ){
            ClassificacaoInterna ci = new ClassificacaoInterna();
            ci.setId( rs.getLong( "idClassInterna" ) );
            ci.setDescricao( rs.getString( "descClassInterna" ) );
            ci.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );
            
            classInternas.add( ci );
        }
        
        rs.close();
        stmt.close();
        
        return classInternas;
    }

    @Override
    public ClassificacaoInterna obterPorId(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "ci.id idClassInterna, "
                        + "ci.descricao descClassInterna, "
                        + "ci.valorAluguel valorAluguel "
                + "FROM classificacao_interna ci "
                + "WHERE ci.id = ?;" );
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();
        
        ClassificacaoInterna ci = null;
        if( rs.next() ){
            ci = new ClassificacaoInterna();
            ci.setId( rs.getLong( "idClassInterna" ) );
            ci.setDescricao( rs.getString( "descClassInterna" ) );
            ci.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );
        }
        
        stmt.close();
        rs.close();
        
        return ci;
    }
    
}
