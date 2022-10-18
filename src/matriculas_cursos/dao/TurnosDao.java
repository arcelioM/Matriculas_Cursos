
package matriculas_cursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import matriculas_cursos.dao.connection.ConnectionMySql;
import matriculas_cursos.tablas.Turnos;


public class TurnosDao {
    
    /**
     * METODO QUE RETORNARA TODOS LOS Turnos REGISTRADO
     * @return 
     */
    public List<Turnos> getAll(){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM turnos";
            List<Turnos> listTurnos=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                Turnos turno= new Turnos();
                
                turno.setId(rs.getInt(1));
                turno.setNombre(rs.getString(2));

                
                listTurnos.add(turno);
            }
            return listTurnos;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.TurnosDao.getAll()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
       
    }
    
    /**
     * Buscara a un turno por su id
     * @param turnos
     * @return estudiante
     */
    public Turnos getById(Turnos turnos){
        
        
        if(turnos==null || turnos.getId()==null || turnos.getId()<=0){
            return turnos;
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT nombre FROM turnos WHERE id=?";
        Integer id=turnos.getId();
        
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            rs=ps.executeQuery();
            
            if(rs.next()){
                 
                turnos.setNombre(rs.getString(1));
                
            }
            
            return turnos;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.TurnosDao.getById()");
            e.printStackTrace(System.out);
            return turnos;
        }

    }
    
   
    
    /**
     * 1 si la operacion se ejcuta con exito
     * 0 si hubo algun error
     * @param turnos
     * @return Integer
     */
    public Integer save(Turnos turnos){
        
        /**
         * ESTO ES PARA EVITAR QUE SE HAGA ALGUNA OPERACION QUE PROVOQUE UN ERROR
         */
        if(turnos==null || turnos.getId()!=null){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="INSERT INTO turnos (nombre) VALUES(?)";
        
        try{
            ps=conexionBD.prepareStatement(query);

            ps.setString(1, turnos.getNombre());
           
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.TurnosDao.save()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer update(Turnos turnos){
        
        if(turnos==null || turnos.getId()==null || turnos.getId()<=0){
            return 0;
        }
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="UPDATE turnos SET nombre=? WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);

            ps.setString(1, turnos.getNombre());
            ps.setInt(2, turnos.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.TurnosDao.update()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer remove(Turnos turnos){
        
        if(turnos==null || turnos.getId()==null || turnos.getId()<=0){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="DELETE FROM turnos WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);
           
            ps.setInt(1, turnos.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.TurnosDao.remove()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
}
