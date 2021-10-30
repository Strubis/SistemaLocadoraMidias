package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author EmersonPC
 */
public class LocacaoDAO extends DAO<Locacao>{
    
    public LocacaoDAO() throws SQLException{
    }

    @Override
    public void salvar(Locacao obj) throws SQLException {
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "INSERT INTO "
                        + "locacao(dataInicio, dataFim, cancelada, cliente_id) "
              + "VALUES(?, ?, ?, ?);",
                new String[]{ "id" } )){
            
            stmt.setDate( 1, obj.getDataInicio() );
            stmt.setDate( 2, obj.getDataFim() );
            stmt.setBoolean( 3, obj.getCancelada() );
            stmt.setLong( 4, obj.getIdCliente().getId() );
            
            stmt.executeUpdate();
            obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "id" ) );
        }
    }

    @Override
    public void atualizar(Locacao obj) throws SQLException {
        // Vamos somente atualizar o campo de cancelamento
        
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "UPDATE "
                        + "locacao "
              + "SET dataInicio = ?, dataFim = ?, "
                  + "cancelada = ?, cliente_id = ? "
              + "WHERE id = ?;" )){
            
            stmt.setDate( 1, obj.getDataInicio() );
            stmt.setDate( 2, obj.getDataFim() );
            stmt.setBoolean( 3, obj.getCancelada() );
            stmt.setLong( 4, obj.getIdCliente().getId() );
            stmt.setLong( 5, obj.getId() );
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Locacao obj) throws SQLException {
        // Não serão excluídas
    }

    @Override
    public List<Locacao> listarTodos() throws SQLException {
        List<Locacao> locacoes = new ArrayList<>();
        
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "l.id idLocacao, "
                        + "l.dataInicio dataIn, "
                        + "l.dataFim dataFim, "
                        + "l.cancelada cancelada, "
                        + "c.id idCliente, " + 
                        "c.nome nomeCliente, " + 
                        "c.sobreNome sobrenomeCliente, " + 
                        "c.dataNascimento dataNascimentoCliente, " + 
                        "c.cpf cpfCliente, " + 
                        "c.email emailCliente, " + 
                        "c.logradouro logradouroCliente, " + 
                        "c.numero numeroCliente, " + 
                        "c.bairro bairroCliente, " + 
                        "c.cep cepCliente, " + 
                        "ci.id idCidade, " + 
                        "ci.nome nomeCidade, " + 
                        "e.id idEstado, " + 
                        "e.nome nomeEstado, " + 
                        "e.sigla siglaEstado "
              + "FROM locacao l, "
                   + "cliente c, "
                   + "cidade ci, "
                   + "estado e "
              + "WHERE "
                        + "l.cliente_id = c.id AND "
                        + "c.cidade_id = ci.id AND "
                        + "c.estado_id = e.id "
              + "ORDER BY l.cancelada, c.nome, ci.nome, e.nome;" )){
            
            ResultSet rs = stmt.executeQuery();
            
            while( rs.next() ){
                Locacao l = new Locacao();
                Cliente c = new Cliente();
                Cidade ci = new Cidade();
                Estado e = new Estado();
                
                l.setId( rs.getLong( "idLocacao" ) );
                l.setDataInicio( rs.getDate( "dataIn" ) );
                l.setDataFim( rs.getDate( "dataFim" ) );
                l.setCancelada( rs.getBoolean( "cancelada" ) );
                l.setIdCliente( c );
                
                c.setId( rs.getLong( "idCliente" ) );
                c.setNome( rs.getString( "nomeCliente" ) );
                c.setSobrenome( rs.getString( "sobrenomeCliente" ) );
                c.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
                c.setCpf( rs.getString( "cpfCliente" ) );
                c.setEmail( rs.getString( "emailCliente" ) );
                c.setLogradouro( rs.getString( "logradouroCliente" ) );
                c.setNumero( rs.getString( "numeroCliente" ) );
                c.setBairro( rs.getString( "bairroCliente" ) );
                c.setCep( rs.getString( "cepCliente" ) );
                c.setCidade( ci );
                
                ci.setId( rs.getLong( "idCidade" ) );
                ci.setNome( rs.getString( "nomeCidade" ) );
                ci.setEstado( e );

                e.setId( rs.getLong( "idEstado" ) );
                e.setNome( rs.getString( "nomeEstado" ) );
                e.setSigla( rs.getString( "siglaEstado" ) );
                
                locacoes.add( l );
            }
            
            rs.close();
        }
        
        return locacoes;
    }

    @Override
    public Locacao obterPorId(Long id) throws SQLException {
        Locacao locacao = null;
        
        try(PreparedStatement stmt = getConnection().prepareStatement( 
                "SELECT "
                        + "l.id idLocacao, "
                        + "l.dataInicio dataIn, "
                        + "l.dataFim dataFim, "
                        + "l.cancelada cancelada, "
                        + "c.id idCliente, " + 
                        "c.nome nomeCliente, " + 
                        "c.sobreNome sobrenomeCliente, " + 
                        "c.dataNascimento dataNascimentoCliente, " + 
                        "c.cpf cpfCliente, " + 
                        "c.email emailCliente, " + 
                        "c.logradouro logradouroCliente, " + 
                        "c.numero numeroCliente, " + 
                        "c.bairro bairroCliente, " + 
                        "c.cep cepCliente, " + 
                        "ci.id idCidade, " + 
                        "ci.nome nomeCidade, " + 
                        "e.id idEstado, " + 
                        "e.nome nomeEstado, " + 
                        "e.sigla siglaEstado "
              + "FROM locacao l, "
                   + "cliente c, "
                   + "cidade ci, "
                   + "estado e "
              + "WHERE "
                        + "l.id = ? AND "
                        + "l.cliente_id = c.id AND "
                        + "c.cidade_id = ci.id AND "
                        + "c.estado_id = e.id;" )){
            stmt.setLong( 1, id );
            
            ResultSet rs = stmt.executeQuery();
            
            if( rs.next() ){
                locacao = new Locacao();
                Cliente c = new Cliente();
                Cidade ci = new Cidade();
                Estado e = new Estado();
                
                locacao.setId( rs.getLong( "idLocacao" ) );
                locacao.setDataInicio( rs.getDate( "dataIn" ) );
                locacao.setDataFim( rs.getDate( "dataFim" ) );
                locacao.setCancelada( rs.getBoolean( "cancelada" ) );
                locacao.setIdCliente( c );
                
                c.setId( rs.getLong( "idCliente" ) );
                c.setNome( rs.getString( "nomeCliente" ) );
                c.setSobrenome( rs.getString( "sobrenomeCliente" ) );
                c.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
                c.setCpf( rs.getString( "cpfCliente" ) );
                c.setEmail( rs.getString( "emailCliente" ) );
                c.setLogradouro( rs.getString( "logradouroCliente" ) );
                c.setNumero( rs.getString( "numeroCliente" ) );
                c.setBairro( rs.getString( "bairroCliente" ) );
                c.setCep( rs.getString( "cepCliente" ) );
                c.setCidade( ci );
                
                ci.setId( rs.getLong( "idCidade" ) );
                ci.setNome( rs.getString( "nomeCidade" ) );
                ci.setEstado( e );

                e.setId( rs.getLong( "idEstado" ) );
                e.setNome( rs.getString( "nomeEstado" ) );
                e.setSigla( rs.getString( "siglaEstado" ) );
            }
            
            rs.close();
        }
        
        return locacao;
    }
    
}
