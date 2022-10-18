
package matriculas_cursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import matriculas_cursos.dao.connection.ConnectionMySql;
import matriculas_cursos.tablas.Cursos;


public class CursosDao {
    
     /**
     * METODO QUE RETORNARA TODOS LOS CURSOS REGISTRADO
     * @return 
     */
    public List<Cursos> getAll(){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM turnos";
            List<Cursos> listCursos=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                Cursos curso= new Cursos();
                
                curso.setId(rs.getInt(1));
                curso.setNombre(rs.getString(3));

                
                listCursos.add(curso);
            }
            return listCursos;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursosDao.getAll()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
       
    }
    
    /**
     * Buscara a un curso por su id
     * @param cursos
     * @return curso
     */
    public Cursos getById(Cursos cursos){
        
        
        if(cursos==null || cursos.getId()==null || cursos.getId()<=0){
            return cursos;
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT nombre FROM cursos WHERE id=?";
        Integer id=cursos.getId();
        
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            rs=ps.executeQuery();
            
            if(rs.next()){
                 
                cursos.setNombre(rs.getString(1));
                
            }
            
            return cursos;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursosDao.getById()");
            e.printStackTrace(System.out);
            return cursos;
        }

    }
    
   
    
    /**
     * 1 si la operacion se ejcuta con exito
     * 0 si hubo algun error
     * @param cursos
     * @return Integer
     */
    public Integer save(Cursos cursos){
        
        /**
         * ESTO ES PARA EVITAR QUE SE HAGA ALGUNA OPERACION QUE PROVOQUE UN ERROR
         */
        if(cursos==null || cursos.getId()!=null){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="INSERT INTO cursos (nombre) VALUES(?)";
        
        try{
            ps=conexionBD.prepareStatement(query);

            ps.setString(1, cursos.getNombre());
           
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursosDao.save()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer update(Cursos cursos){
        
        if(cursos==null || cursos.getId()==null || cursos.getId()<=0){
            return 0;
        }
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="UPDATE cursos SET nombre=? WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);

            ps.setString(1, cursos.getNombre());
            ps.setInt(2, cursos.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursosDao.update()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer remove(Cursos cursos){
        
        if(cursos==null || cursos.getId()==null || cursos.getId()<=0){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="DELETE FROM cursos WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);
           
            ps.setInt(1, cursos.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursosDao.remove()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
}
