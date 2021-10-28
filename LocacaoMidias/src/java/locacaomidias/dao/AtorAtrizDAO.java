package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class AtorAtrizDAO extends DAO<AtorAtriz> {

    public AtorAtrizDAO() throws SQLException {
    }

    @Override
    public void salvar(AtorAtriz obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO ator_atriz "
                + "( nome, sobrenome, dataEstreia )"
                + " VALUES( ?, ?, ? );",
                new String[]{"id"});

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate( 3, obj.getDataEstreia() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(AtorAtriz obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE ator_atriz "
                + "SET nome = ?, "
                + "sobrenome = ?, dataEstreia = ? "
                + "WHERE id = ?;");

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate( 3, obj.getDataEstreia() );
        stmt.setLong( 4, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(AtorAtriz obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM ator_atriz "
                + "WHERE id = ?;" );
        stmt.setLong( 1, obj.getId() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<AtorAtriz> listarTodos() throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "a.id idAtorAtriz, "
                        + "a.nome nomeAtorAtriz, "
                        + "a.sobrenome sobrenomeAtorAtriz, "
                        + "a.dataEstreia dataAtorAtriz "
                + "FROM "
                        + "ator_atriz a "
                + "ORDER BY a.nome, a.sobrenome;" );
        
        ResultSet rs = stmt.executeQuery();
        
        List<AtorAtriz> atores = new ArrayList<>();
        
        while( rs.next() ){
            AtorAtriz aa = new AtorAtriz();
            aa.setId( rs.getLong( "idAtorAtriz" ) );
            aa.setNome( rs.getString( "nomeAtorAtriz" ) );
            aa.setSobrenome( rs.getString( "sobrenomeAtorAtriz" ) );
            aa.setDataEstreia( rs.getDate( "dataAtorAtriz" ) );
            
            atores.add( aa );
        }
        
        stmt.close();
        rs.close();
        
        return atores;
    }

    @Override
    public AtorAtriz obterPorId(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "a.id idAtorAtriz, "
                        + "a.nome nomeAtorAtriz, "
                        + "a.sobrenome sobrenomeAtorAtriz, "
                        + "a.dataEstreia dataAtorAtriz "
                + "FROM "
                        + "ator_atriz a "
                + "WHERE id = ?;" );
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();
        
        AtorAtriz atorAtriz = new AtorAtriz();
        if( rs.next() ){
            atorAtriz.setId( rs.getLong( "idAtorAtriz" ) );
            atorAtriz.setNome( rs.getString( "nomeAtorAtriz" ) );
            atorAtriz.setSobrenome( rs.getString( "sobrenomeAtorAtriz" ) );
            atorAtriz.setDataEstreia( rs.getDate( "dataAtorAtriz" ) );
        }
        
        stmt.close();
        rs.close();
        
        return atorAtriz;
    }

}
