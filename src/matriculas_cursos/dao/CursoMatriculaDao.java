
package matriculas_cursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import matriculas_cursos.dao.connection.ConnectionMySql;
import matriculas_cursos.tablas.CursoMatricula;


public class CursoMatriculaDao {
    
    
    public List<CursoMatricula> getAll(){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM curso_matricula";
        
        List<CursoMatricula> listCursosMatriculas=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                CursoMatricula cursoMatricula= new CursoMatricula();
                
                cursoMatricula.setCursoId(rs.getInt(1));
                cursoMatricula.setMatriculaId(rs.getInt(2));

                listCursosMatriculas.add(cursoMatricula);
            }
            return listCursosMatriculas;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.}CursoMatriculaDao.getAll()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
        
        
    }
    
    public List<CursoMatricula> getByMatriculaId(CursoMatricula cursoMatricula){
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="SELECT * FROM curso_matricula WHERE matricula_id=?";
        
        List<CursoMatricula> cursosMatriculados=new ArrayList<>();
        try{
            ps=conexionBD.prepareStatement(query);
            ps.setInt(1,cursoMatricula.getMatriculaId() );
            
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                CursoMatricula cursosDisponibles= new CursoMatricula();
                cursosDisponibles.setCursoId(rs.getInt(1));
                cursosDisponibles.setMatriculaId(rs.getInt(2));
                cursosMatriculados.add(cursosDisponibles);
            }
            return cursosMatriculados;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursoMatriculaDao.getByMatriculaId()");
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
        
        
    }
    
    public Integer save(CursoMatricula cursosMatricula){
        
        /**
         * ESTO ES PARA EVITAR QUE SE HAGA ALGUNA OPERACION QUE PROVOQUE UN ERROR
         */
        if(cursosMatricula==null || cursosMatricula.getMatriculaId()==null || cursosMatricula.getCursoId()==null){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="INSERT INTO curso_matricula (curso_id,matricula_id) VALUES(?,?)";
        
        try{
            ps=conexionBD.prepareStatement(query);

            ps.setInt(1, cursosMatricula.getCursoId());
            ps.setInt(2, cursosMatricula.getMatriculaId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursoMatriculaDao.save()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
    
    
    public Integer remove(CursoMatricula cursoMatricula){
        
        if(cursoMatricula==null || cursoMatricula.getCursoId()==null || cursoMatricula.getMatriculaId()==null){
            return 0;
        }
        
        PreparedStatement ps=null;
        Connection conexionBD=ConnectionMySql.getConexion();
        String query="DELETE FROM curso_matricula WHERE curso_id=? AND matricula_id=? ";
        
        try{
            ps=conexionBD.prepareStatement(query);
           
            ps.setInt(1, cursoMatricula.getCursoId());
            ps.setInt(2, cursoMatricula.getMatriculaId());
            
            Integer rowAffected=ps.executeUpdate();
            
            return rowAffected;
        }catch(SQLException e){
            System.out.println("matriculas_cursos.dao.CursoMatriculaDao.remove()");
            e.printStackTrace(System.out);
            return 0;
        }
    }
}
