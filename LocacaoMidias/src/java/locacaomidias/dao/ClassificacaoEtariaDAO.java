package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class ClassificacaoEtariaDAO extends DAO<ClassificacaoEtaria> {
    
    public ClassificacaoEtariaDAO() throws SQLException{
    }
    
    @Override
    public void salvar(ClassificacaoEtaria obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                    + "classificacao_etaria ( descricao ) "
                + "VALUES( ? );", 
                new String[]{ "id" } );
        stmt.setString( 1, obj.getDescricao() );
        
        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(ClassificacaoEtaria obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE "
                    + "classificacao_etaria "
                + "SET descricao = ? "
                + "WHERE id = ?;" );
        stmt.setString( 1, obj.getDescricao() );
        stmt.setLong( 2, obj.getId() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(ClassificacaoEtaria obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM classificacao_etaria "
                + "WHERE id = ?;" );
        stmt.setLong( 1, obj.getId() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<ClassificacaoEtaria> listarTodos() throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "ce.id idClassEtaria, "
                        + "ce.descricao descClassEtaria "
                + "FROM classificacao_etaria ce "
                + "ORDER BY ce.descricao;" );
        
        ResultSet rs = stmt.executeQuery();
        
        List<ClassificacaoEtaria> classEtarias = new ArrayList<>();
        
        while( rs.next() ){
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            ce.setId( rs.getLong( "idClassEtaria" ) );
            ce.setDescricao( rs.getString( "descClassEtaria" ) );
            
            classEtarias.add( ce );
        }
        
        rs.close();
        stmt.close();
        
        return classEtarias;
    }

    @Override
    public ClassificacaoEtaria obterPorId(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "ce.id idClassEtaria, "
                        + "ce.descricao descClassEtaria "
                + "FROM classificacao_etaria ce "
                + "WHERE ce.id = ?;" );
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();
        
        ClassificacaoEtaria ce = null;
        if( rs.next() ){
            ce = new ClassificacaoEtaria();
            ce.setId( rs.getLong( "idClassEtaria" ) );
            ce.setDescricao( rs.getString( "descClassEtaria" ) );
        }
        
        rs.close();
        stmt.close();
        
        return ce;
    }
    
}
