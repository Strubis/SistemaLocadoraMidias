package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.AtorAtriz;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class ExemplarDAO extends DAO<Exemplar>{

    public ExemplarDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Exemplar obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "exemplar(disponivel, midia_id) "
              + "VALUES(?, ?);",
                new String[]{ "codigo_interno" } );
        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );
        
        stmt.executeUpdate();
        obj.setCodigoInterno( Utils.getChavePrimariaAposInsercao( stmt, "codigo_interno" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Exemplar obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE exemplar "
              + "SET disponivel = ?, midia_id = ? "
              + "WHERE codigo_interno = ?;" );
        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );
        stmt.setLong( 3, obj.getCodigoInterno() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Exemplar obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "DELETE FROM exemplar " +
                "WHERE codigo_interno = ?" );
        stmt.setLong( 1, obj.getCodigoInterno() );
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "e.codigo_interno codInterno, "
                        + "e.disponivel disponivel, "
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
                        + "exemplar e, "
                        + "midia m, "
                        + "ator_atriz aap, "
                        + "ator_atriz aac, "
                        + "genero g, "
                        + "classificacao_etaria ce, "
                        + "tipo t, "
                        + "classificacao_interna ci "
              + "WHERE "
                        + "e.midia_id = m.id AND "
                        + "m.ator_atriz_principal = aap.id AND "
                        + "m.ator_atriz_coadjuvante = aac.id AND "
                        + "m.genero_id = g.id AND "
                        + "m.classificacao_etaria_id = ce.id AND "
                        + "m.classificacao_interna_id = ci.id AND "
                        + "m.tipo_id = t.id "
              + "ORDER BY e.disponivel, m.titulo, m.anoLancamento;" );
        
        ResultSet rs = stmt.executeQuery();
        
        List<Exemplar> exemplares = new ArrayList<>();
        
        while( rs.next() ){
            Exemplar e = new Exemplar();
            Midia m = new Midia();
            AtorAtriz aap = new AtorAtriz();
            AtorAtriz aac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            Tipo t = new Tipo();
            
            e.setCodigoInterno( rs.getLong( "codInterno" ) );
            e.setDisponivel( rs.getBoolean( "disponivel" ) );
            e.setMidia( m );
            
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
            
            exemplares.add( e );
        }
        
        rs.close();
        stmt.close();
        
        return exemplares;
    }

    @Override
    public Exemplar obterPorId(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "e.codigo_interno codInterno, "
                        + "e.disponivel disponivel, "
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
                        + "exemplar e, "
                        + "midia m, "
                        + "ator_atriz aap, "
                        + "ator_atriz aac, "
                        + "genero g, "
                        + "classificacao_etaria ce, "
                        + "tipo t, "
                        + "classificacao_interna ci "
              + "WHERE "
                        + "e.midia_id = m.id AND "
                        + "m.ator_atriz_principal = aap.id AND "
                        + "m.ator_atriz_coadjuvante = aac.id AND "
                        + "m.genero_id = g.id AND "
                        + "m.classificacao_etaria_id = ce.id AND "
                        + "m.classificacao_interna_id = ci.id AND "
                        + "m.tipo_id = t.id AND "
                        + "e.id = ?;" );
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();
        
        Exemplar exemplar = null;
        if( rs.next() ){
            exemplar = new Exemplar();
            Midia m = new Midia();
            AtorAtriz aap = new AtorAtriz();
            AtorAtriz aac = new AtorAtriz();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            Tipo t = new Tipo();
            
            exemplar.setCodigoInterno( rs.getLong( "codInterno" ) );
            exemplar.setDisponivel( rs.getBoolean( "disponivel" ) );
            exemplar.setMidia( m );
            
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
        }
        
        rs.close();
        stmt.close();
        
        return exemplar;
    }
    
}
