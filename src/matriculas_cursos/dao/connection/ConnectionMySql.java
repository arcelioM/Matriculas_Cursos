
package matriculas_cursos.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
    
    private static final String USERNAME="root";
    private static final String PASS="holaCOMO";
    private static final String  HOST="localhost";
    private static final String DATABASE="matriculas";
    private static final String PORT="3306";
    private static  Connection conn;

    public static Connection getConexion(){
        
        try{
            if(conn==null){
               ConnectionMySql.conn=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE, USERNAME, PASS);
            }
            conn.setAutoCommit(false);
            return ConnectionMySql.conn;
        }catch(SQLException e){
            e.printStackTrace(System.out);
            return null;
        } 
    }
    
    public static void closeConexion(){
        try{
            ConnectionMySql.conn.close();
        }catch(SQLException e){
            e.printStackTrace(System.out);
            
        } 
    }
    
}
