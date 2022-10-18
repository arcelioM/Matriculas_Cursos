
package matriculas_cursos.dao;

import java.util.List;
import matriculas_cursos.tablas.Estudiante;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import matriculas_cursos.dao.connection.ConnectionMySql;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class EstudianteDao {
    
    /**
     * METODO QUE RETORNARA TODOS LOS ESTUDIANTES REGISTRADO
     * @return 
     */
    public List<Estudiante> getAll(){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM estudiantes";
        List<Estudiante> listEstudiantes=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                Estudiante estudiante=new Estudiante();
                
                estudiante.setId(rs.getInt(1));
                estudiante.setCedula(rs.getInt(2));
                estudiante.setNombre(rs.getString(3));
                estudiante.setApellido(rs.getString(4));
                estudiante.setEdad(rs.getInt(5));
                estudiante.setFechaNacimiento(LocalDate.parse(rs.getString(6)));
                estudiante.setFechaCreacion(LocalDateTime.parse(rs.getString(7)));
                
                listEstudiantes.add(estudiante);
            }
            return listEstudiantes;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.EstudianteDao.getAll()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
       
    }
    
    /**
     * Buscara a un estudiante por su id
     * @param estudiante
     * @return estudiante
     */
    public Estudiante getById(Estudiante estudiante){
        
        
        if(estudiante==null || estudiante.getId()==null || estudiante.getId()<=0){
            return estudiante;
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM estudiantes WHERE id=?";
        Integer id=estudiante.getId();
        
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, id);
            
            rs=ps.executeQuery();
            
            if(rs.first()){
         
                estudiante.setCedula(rs.getInt(2));
                estudiante.setNombre(rs.getString(3));
                estudiante.setApellido(rs.getString(4));
                estudiante.setEdad(rs.getInt(5));
                estudiante.setFechaNacimiento(LocalDate.parse(rs.getString(6)));
                estudiante.setFechaCreacion(LocalDateTime.parse(rs.getString(7)));
            }
            
            return estudiante;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.EstudianteDao.getById()");
            e.printStackTrace(System.out);
            return estudiante;
        }

    }
    
    /**
     * Buscara a un estudiante por su cedula
     * @param estudiante
     * @return estudiante
     */
    public Estudiante getByCedula(Estudiante estudiante){
        
        
        if(estudiante==null || estudiante.getCedula()==null || estudiante.getCedula()<=0){
            return estudiante;
        }
        
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM estudiantes WHERE cedula=?";
        Integer cedula=estudiante.getCedula();
        
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1, cedula);
            
            rs=ps.executeQuery();
            
            if(rs.first()){
         
                estudiante.setId(rs.getInt(1));
                estudiante.setNombre(rs.getString(3));
                estudiante.setApellido(rs.getString(4));
                estudiante.setEdad(rs.getInt(5));
                estudiante.setFechaNacimiento(LocalDate.parse(rs.getString(6)));
                estudiante.setFechaCreacion(LocalDateTime.parse(rs.getString(7)));
            }
            
            return estudiante;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.EstudianteDao.getByCedula()");
            e.printStackTrace(System.out);
            return estudiante;
        }

    }
    
    public Integer save(Estudiante estudiante){
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="INSERT INTO estudiantes (cedula,nombre,apellido,edad,fechaNacimiento)(?,?,?,?,?)";
        
        try{
            ps=conexionBD.prepareStatement(query);
            
            ps.setInt(1, estudiante.getCedula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setInt(4, estudiante.getEdad());
            ps.setString(4, estudiante.getFechaNacimiento().toString());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.EstudianteDao.save()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    public Integer update(Estudiante estudiante){
        
        if(estudiante==null || estudiante.getId()==null || estudiante.getId()<=0){
            return 0;
        }
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="UPDATE estudiantes SET cedula=?, nombre=?, apellido=?, edad=?, fechaNacimiento=? WHERE id=?";
        
        try{
            ps=conexionBD.prepareStatement(query);
            
            ps.setInt(1, estudiante.getCedula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setInt(4, estudiante.getEdad());
            ps.setString(5, estudiante.getFechaNacimiento().toString());
            ps.setInt(6, estudiante.getId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.EstudianteDao.update()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
}
