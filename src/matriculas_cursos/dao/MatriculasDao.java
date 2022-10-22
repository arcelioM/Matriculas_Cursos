
package matriculas_cursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import matriculas_cursos.dao.connection.ConnectionMySql;
import matriculas_cursos.model.Matriculas;


public class MatriculasDao {
    
    /**
     * METODO QUE RETORNARA TODOS LOS Matriculas REGISTRADA
     * @return Matriculas
     */
    public List<Matriculas> getAll(){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM matriculas";
            List<Matriculas> listMatriculas=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                Matriculas matriculas= new Matriculas();
                
                matriculas.setId(rs.getInt(1));
                matriculas.getEstudianteId().setId(rs.getInt(2));
                matriculas.setCosto(rs.getDouble(3));
                matriculas.getTurnoId().setId(rs.getInt(4));

                //FORMATEO DE FECHA Y HORA
                String formato = "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
           
                LocalDateTime fechaCreacion=LocalDateTime.parse(rs.getString(5), formateador);
                matriculas.setFecheRegistro(fechaCreacion);
                
                listMatriculas.add(matriculas);
            }
            return listMatriculas;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.getAll()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
       
    }
    
    /**
     * Buscara a un turno por su id
     * @param matriculas
     * @return matriculas
     */
    public Matriculas getById(Matriculas matriculas){
        
        
        if(matriculas==null || matriculas.getId()==null || matriculas.getId()<=0){
            return matriculas;
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM matriculas WHERE id=?";
        Integer id=matriculas.getId();
        
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            rs=ps.executeQuery();
            
            if(rs.next()){
                 
                matriculas.getEstudianteId().setId(rs.getInt(2));
                matriculas.setCosto(rs.getDouble(3));
                matriculas.getTurnoId().setId(rs.getInt(4));

                //FORMATEO DE FECHA Y HORA
                String formato = "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
           
                LocalDateTime fechaCreacion=LocalDateTime.parse(rs.getString(5), formateador);
                matriculas.setFecheRegistro(fechaCreacion);
                
            }
            
            return matriculas;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.getById()");
            e.printStackTrace(System.out);
            return matriculas;
        }

    }
    
    public List<Matriculas> getByEstudianteId(Matriculas matriculas){
        
        
        if(matriculas==null || matriculas.getEstudianteId()==null || matriculas.getEstudianteId().getId()==null  || matriculas.getEstudianteId().getId()<=0){
            return Collections.emptyList();
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM matriculas WHERE estudiante_id=?";
        Integer idEstudiante=matriculas.getEstudianteId().getId();
        
        try{
            List<Matriculas> mastriculasDisponibles=new ArrayList<>();
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, idEstudiante);
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                Matriculas matriculaDisponible= new Matriculas();
                matriculaDisponible.setId(rs.getInt(1));
                matriculaDisponible.getEstudianteId().setId(rs.getInt(2));
                matriculaDisponible.setCosto(rs.getDouble(3));
                matriculaDisponible.getTurnoId().setId(rs.getInt(4));

                //FORMATEO DE FECHA Y HORA
                String formato = "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
           
                LocalDateTime fechaCreacion=LocalDateTime.parse(rs.getString(5), formateador);
                matriculaDisponible.setFecheRegistro(fechaCreacion);
                mastriculasDisponibles.add(matriculaDisponible);
                
            }
            
            return mastriculasDisponibles;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.getById()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
            
        }

    }
    
    
    /**
     * 1 si la operacion se ejcuta con exito
     * 0 si hubo algun error
     * @param matriculas 
     * @return Integer
     */
    public Integer save(Matriculas matriculas){
        
        /**
         * ESTO ES PARA EVITAR QUE SE HAGA ALGUNA OPERACION QUE PROVOQUE UN ERROR
         */
        if(matriculas==null || matriculas.getId()!=null){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="INSERT INTO matriculas (estudiante_id,costo,turno_id) VALUES(?,?,?)";
        
        try{
            ps=conexionBD.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, matriculas.getEstudianteId().getId());
            ps.setDouble(2, matriculas.getCosto());
            ps.setInt(3, matriculas.getTurnoId().getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            if(rowAffected==1){
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1); //DEVOLVERA EL ID CREADO AL GENERAL EL REGISTRO
                }
            }
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.save()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer update(Matriculas matriculas){
        
        if(matriculas==null || matriculas.getId()==null || matriculas.getId()<=0){
            return 0;
        }
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="UPDATE matriculas SET estudiante_id=?, costo=?,turno_id=? WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);

           ps.setInt(1, matriculas.getEstudianteId().getId());
           ps.setDouble(2, matriculas.getCosto());
           ps.setInt(3, matriculas.getTurnoId().getId());
           ps.setInt(4, matriculas.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.update()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer remove(Matriculas matriculas){
        
        if(matriculas==null || matriculas.getId()==null || matriculas.getId()<=0){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="DELETE FROM matriculas WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);
           
            ps.setInt(1, matriculas.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.MatriculasDao.remove()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
}
