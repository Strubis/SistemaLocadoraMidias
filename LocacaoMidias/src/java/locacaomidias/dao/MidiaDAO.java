package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class MidiaDAO extends DAO<Midia>{
    
    public MidiaDAO() throws SQLException{
    }

    @Override
    public void salvar(Midia obj) throws SQLException {
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "midia("
                        + "titulo, "
                        + "anoLancamento, "
                        + "codigoBarras, "
                        + "duracaoEmMinutos, "
                        + "ator_atriz_principal, "
                        + "ator_atriz_coadjuvante, "
                        + "genero_id, "
                        + "classificacao_etaria_id, "
                        + "tipo_id, "
                        + "classificacao_interna_id) "
              + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                new String[]{ "id" } )){
            
            stmt.setString( 1, obj.getTitulo() );
            stmt.setString( 2, obj.getAnoLancamento() );
            stmt.setString( 3, obj.getCodigoBarras() );
            stmt.setLong( 4, obj.getDuracaoMin() );
            stmt.setLong( 5, obj.getIdAtorAtrizPrincipal().getId() );
            stmt.setLong( 6, obj.getIdAtorAtrizCoadjuvante().getId() );
            stmt.setLong( 7, obj.getIdGenero().getId() );
            stmt.setLong( 8, obj.getIdClassEtaria().getId() );
            stmt.setLong( 9, obj.getIdTipo().getId() );
            stmt.setLong( 10, obj.getIdClassInterna().getId() );
            
            stmt.executeUpdate();
            obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        }
    }

    @Override
    public void atualizar(Midia obj) throws SQLException {
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE midia "
              + "SET "
                        + "titulo = ?, "
                        + "anoLancamento = ?, "
                        + "codigoBarras = ?, "
                        + "duracaoEmMinutos = ?, "
                        + "ator_atriz_principal = ?, "
                        + "ator_atriz_coadjuvante = ?, "
                        + "genero_id = ?, "
                        + "classificacao_etaria_id = ?, "
                        + "tipo_id = ?, "
                        + "classificacao_interna_id = ? "
              + "WHERE id = ?;" )){
            
            stmt.setString( 1, obj.getTitulo() );
            stmt.setString( 2, obj.getAnoLancamento() );
            stmt.setString( 3, obj.getCodigoBarras() );
            stmt.setLong( 4, obj.getDuracaoMin() );
            stmt.setLong( 5, obj.getIdAtorAtrizPrincipal().getId() );
            stmt.setLong( 6, obj.getIdAtorAtrizCoadjuvante().getId() );
            stmt.setLong( 7, obj.getIdGenero().getId() );
            stmt.setLong( 8, obj.getIdClassEtaria().getId() );
            stmt.setLong( 9, obj.getIdTipo().getId() );
            stmt.setLong( 10, obj.getIdClassInterna().getId() );
            stmt.setLong( 11, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Midia obj) throws SQLException {
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM "
                        + "midia "
              + "WHERE id = ?;" )){
            
            stmt.setLong( 1, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {
        List<Midia> midias = new ArrayList<>();
        
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "m.id idMidia, "
                        + "m.codigoBarras codBarras, "
                        + "m.titulo titulo, "
                        + "m.anoLancamento anoLancamento, "
                        + "m.duracaoEmMinutos durMin, "
                        + "aap.id idAap, "
                        + "aap.nome nomePrin, "
                        + "aap.sobrenome sobrenomePrin, "
                        + "aap.dataEstreia dataEstreiaAap, "
                        + "aac.id idAac, "
                        + "aac.nome nomeCoad, "
                        + "aac.sobrenome sobrenomeCoad, "
                        + "aac.dataEstreia dataEstreiaAac, "
                        + "g.id idGen, "
                        + "g.descricao descGen, "
                        + "ce.id idClassEtaria, "
                        + "ce.descricao descClassEtaria, "
                        + "t.id idTipo, "
                        + "t.descricao descTipo, "
                        + "ci.id idClassInterna, "
                        + "ci.descricao descClassInterna, "
                        + "ci.valorAluguel valorAluguel "
              + "FROM "
                        + "midia m, "
                        + "ator_atriz aap, "
                        + "ator_atriz aac, "
                        + "genero g, "
                        + "classificacao_etaria ce, "
                        + "tipo t, "
                        + "classificacao_interna ci "
              + "WHERE "
                        + "m.ator_atriz_principal = aap.id AND "
                        + "m.ator_atriz_coadjuvante = aac.id AND "
                        + "m.genero_id = g.id AND "
                        + "m.classificacao_etaria_id = ce.id AND "
                        + "m.classificacao_interna_id = ci.id AND "
                        + "m.tipo_id = t.id "
              + "ORDER BY m.titulo, aap.nome, aac.nome;" )){
            
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                Midia m = new Midia();
                AtorAtriz aap = new AtorAtriz();
                AtorAtriz aac = new AtorAtriz();
                Genero g = new Genero();
                ClassificacaoEtaria ce = new ClassificacaoEtaria();
                ClassificacaoInterna ci = new ClassificacaoInterna();
                Tipo t = new Tipo();
                
                m.setId( rs.getLong( "idMidia" ) );
                m.setAnoLancamento( rs.getString( "anoLancamento" ) );
                m.setDuracaoMin( rs.getLong( "durMin" ) );
                m.setTitulo( rs.getString( "titulo" ) );
                m.setCodigoBarras( rs.getString( "codBarras" ) );
                m.setIdAtorAtrizPrincipal( aap );
                m.setIdAtorAtrizCoadjuvante( aac );
                m.setIdClassEtaria( ce );
                m.setIdClassInterna( ci );
                m.setIdGenero( g );
                m.setIdTipo( t );

                aap.setId( rs.getLong( "idAap" ) );
                aap.setNome( rs.getString( "nomePrin" ) );
                aap.setSobrenome( rs.getString( "sobrenomePrin" ) );
                aap.setDataEstreia( rs.getDate( "dataEstreiaAap" ) );

                aac.setId( rs.getLong( "idAac" ) );
                aac.setNome( rs.getString( "nomeCoad" ) );
                aac.setSobrenome( rs.getString( "sobrenomeCoad" ) );
                aac.setDataEstreia( rs.getDate( "dataEstreiaAac" ) );

                g.setId( rs.getLong( "idGen" ) );
                g.setDescricao( rs.getString( "descGen" ) );

                ce.setId( rs.getLong( "idClassEtaria" ) );
                ce.setDescricao( rs.getString( "descClassEtaria" ) );

                ci.setId( rs.getLong( "idClassInterna" ) );
                ci.setDescricao( rs.getString( "descClassInterna" ) );
                ci.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );

                t.setId( rs.getLong( "idTipo" ) );
                t.setDescricao( rs.getString( "descTipo" ) );
                
                midias.add( m );
            }
            
            rs.close();
        }
        
        return midias;
    }

    @Override
    public Midia obterPorId(Long id) throws SQLException {
        Midia midia = null;
        
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "m.id idMidia, "
                        + "m.codigoBarras codBarras, "
                        + "m.titulo titulo, "
                        + "m.anoLancamento anoLancamento, "
                        + "m.duracaoEmMinutos durMin, "
                        + "aap.id idAap, "
                        + "aap.nome nomePrin, "
                        + "aap.sobrenome sobrenomePrin, "
                        + "aap.dataEstreia dataEstreiaAap, "
                        + "aac.id idAac, "
                        + "aac.nome nomeCoad, "
                        + "aac.sobrenome sobrenomeCoad, "
                        + "aac.dataEstreia dataEstreiaAac, "
                        + "g.id idGen, "
                        + "g.descricao descGen, "
                        + "ce.id idClassEtaria, "
                        + "ce.descricao descClassEtaria, "
                        + "t.id idTipo, "
                        + "t.descricao descTipo, "
                        + "ci.id idClassInterna, "
                        + "ci.descricao descClassInterna, "
                        + "ci.valorAluguel valorAluguel "
              + "FROM "
                        + "midia m, "
                        + "ator_atriz aap, "
                        + "ator_atriz aac, "
                        + "genero g, "
                        + "classificacao_etaria ce, "
                        + "tipo t, "
                        + "classificacao_interna ci "
              + "WHERE "
                        + "m.id = ? AND "
                        + "m.ator_atriz_principal = aap.id AND "
                        + "m.ator_atriz_coadjuvante = aac.id AND "
                        + "m.genero_id = g.id AND "
                        + "m.classificacao_etaria_id = ce.id AND "
                        + "m.classificacao_interna_id = ci.id AND "
                        + "m.tipo_id = t.id;" )){
            
            stmt.setLong( 1, id );
            
            ResultSet rs = stmt.executeQuery();
            
            if( rs.next() ){
                midia = new Midia();
                AtorAtriz aap = new AtorAtriz();
                AtorAtriz aac = new AtorAtriz();
                Genero g = new Genero();
                ClassificacaoEtaria ce = new ClassificacaoEtaria();
                ClassificacaoInterna ci = new ClassificacaoInterna();
                Tipo t = new Tipo();
                
                midia.setId( rs.getLong( "idMidia" ) );
                midia.setAnoLancamento( rs.getString( "anoLancamento" ) );
                midia.setDuracaoMin( rs.getLong( "durMin" ) );
                midia.setTitulo( rs.getString( "titulo" ) );
                midia.setCodigoBarras( rs.getString( "codBarras" ) );
                midia.setIdAtorAtrizPrincipal( aap );
                midia.setIdAtorAtrizCoadjuvante( aac );
                midia.setIdClassEtaria( ce );
                midia.setIdClassInterna( ci );
                midia.setIdGenero( g );
                midia.setIdTipo( t );

                aap.setId( rs.getLong( "idAap" ) );
                aap.setNome( rs.getString( "nomePrin" ) );
                aap.setSobrenome( rs.getString( "sobrenomePrin" ) );
                aap.setDataEstreia( rs.getDate( "dataEstreiaAap" ) );

                aac.setId( rs.getLong( "idAac" ) );
                aac.setNome( rs.getString( "nomeCoad" ) );
                aac.setSobrenome( rs.getString( "sobrenomeCoad" ) );
                aac.setDataEstreia( rs.getDate( "dataEstreiaAac" ) );

                g.setId( rs.getLong( "idGen" ) );
                g.setDescricao( rs.getString( "descGen" ) );

                ce.setId( rs.getLong( "idClassEtaria" ) );
                ce.setDescricao( rs.getString( "descClassEtaria" ) );

                ci.setId( rs.getLong( "idClassInterna" ) );
                ci.setDescricao( rs.getString( "descClassInterna" ) );
                ci.setValorAluguel( rs.getBigDecimal( "valorAluguel" ) );

                t.setId( rs.getLong( "idTipo" ) );
                t.setDescricao( rs.getString( "descTipo" ) );
            }
            
            rs.close();
        }
        
        return midia;
    }
}
