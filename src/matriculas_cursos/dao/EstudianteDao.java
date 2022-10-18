
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
}
