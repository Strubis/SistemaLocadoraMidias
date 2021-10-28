package locacaomidias.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author EmersonPC
 */
public class ConnectionFactory {
    
    /**
     * O método retorna uma conexão com o banco de dados locacao_midias
     * 
     * @return Conexão com o banco de dados
     * @throws SQLException Erro ao conectar com o banco de dados
     */
    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection( 
                "jdbc:mysql://localhost/locacao_midias", 
                "root", 
                "" 
        );
    }
}
