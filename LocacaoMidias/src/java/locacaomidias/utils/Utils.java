package locacaomidias.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author EmersonPC
 */
public abstract class Utils {
    
    /*
     * Faz a leitura da chave primária após inserção no banco.
     * Assume que  o PreparedStatement foi configurado apropriadamente.
     */
    public static Long getChavePrimariaAposInsercao( 
            PreparedStatement stmt, String nomeColunaChave ) 
            throws SQLException{
        
        Long pk = null;
        
        try( ResultSet rsPK = stmt.getGeneratedKeys() ){
            if( rsPK.next() ){
                pk = rsPK.getLong( nomeColunaChave );
            }
        }
        
        return pk;
    }
    
}
