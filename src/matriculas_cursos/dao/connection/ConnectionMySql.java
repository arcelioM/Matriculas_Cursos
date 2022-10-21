
package matriculas_cursos.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
    
    private static ConnectionMySql CONEXION;
    static Connection conn;

    private ConnectionMySql() {
        this.crearConexionBd();
    }

    private synchronized  static void crearInstancia(){
        if(ConnectionMySql.CONEXION==null){
            ConnectionMySql.CONEXION= new ConnectionMySql();
        }
    }
    
    public static ConnectionMySql getInstance(){
        if(ConnectionMySql.CONEXION==null){
            ConnectionMySql.crearInstancia();
        }
        return ConnectionMySql.CONEXION;
    }
    
    public void crearConexionBd(){
        
        String usarname="root";
        String  host="localhost";
        String pass="holaCOMO";
        String database="matriculas";
        String port="3306";
        
         try{
            ConnectionMySql.conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, usarname, pass);
            ConnectionMySql.conn.setAutoCommit(false);
        }catch(SQLException e){
             System.out.println("Error en creacionde BD");
        } 
        
    }
    
    public static void cerrarConexion(){
        try{
            ConnectionMySql.conn.close();
        }catch(SQLException e){
            System.out.println("Error al cerrar conexion");
        }
    }
    
    
    public static void delInstance(){
        ConnectionMySql.CONEXION=null;
        ConnectionMySql.cerrarConexion();
    }
    
    
}
