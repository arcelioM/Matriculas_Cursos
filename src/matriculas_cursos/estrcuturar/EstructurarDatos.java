
package matriculas_cursos.estrcuturar;

import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import matriculas_cursos.dao.CursoMatriculaDao;
import matriculas_cursos.dao.CursosDao;
import matriculas_cursos.dao.EstudianteDao;
import matriculas_cursos.dao.MatriculasDao;
import matriculas_cursos.dao.TurnosDao;
import matriculas_cursos.dao.connection.ConnectionMySql;
import matriculas_cursos.model.CursoMatricula;
import matriculas_cursos.model.Cursos;
import matriculas_cursos.model.Estudiante;
import matriculas_cursos.model.Matriculas;
import matriculas_cursos.model.Turnos;
import java.sql.SQLException;



public class EstructurarDatos {
    
    private final MatriculasDao matriculaDao = new MatriculasDao();
    private final TurnosDao turnosDao = new TurnosDao();
    private final EstudianteDao estudianteDao = new EstudianteDao();
    private final CursoMatriculaDao cursoMatriculaDao= new CursoMatriculaDao();
    private final CursosDao cursosDao = new CursosDao();
    
    public DefaultTableModel cargarDatoMatriculas(){
        DefaultTableModel tableModel= new DefaultTableModel();
        
        List<Matriculas> matriculas=matriculaDao.getAll();
        //tableModel.(cursoMatriculas);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre de estudiante");
        tableModel.addColumn("Cedula");
        tableModel.addColumn("Turno");
        tableModel.addColumn("Costo");

        for(Matriculas cm : matriculas){
            
            Estudiante estudiante = estudianteDao.getById(cm.getEstudianteId());
            cm.setEstudianteId(estudiante);
            
            Turnos turno= turnosDao.getById(cm.getTurnoId());
            cm.setTurnoId(turno);
            
        }

       for(Matriculas cm : matriculas){
            Vector row= new Vector();
            Integer id= cm.getId();
            row.add(id);
            
            String nombreEstudiante = cm.getEstudianteId().getNombre() +" " +cm.getEstudianteId().getApellido();
            row.add(nombreEstudiante);
            
            Integer cedula = cm.getEstudianteId().getCedula();
            row.add(cedula);
            
            String turno= cm.getTurnoId().getNombre();
            row.add(turno);
            
            Double costo=cm.getCosto();
            row.add(costo);
            tableModel.addRow(row);
        }
       
       return tableModel;
    }
    
    public DefaultTableModel cargarDatosCursoPorMatricula(Integer idMatricula){
        Matriculas matricula = new Matriculas(idMatricula);
        CursoMatricula cursoMatricula = new CursoMatricula(matricula);
        List<CursoMatricula> matriculaCursos=cursoMatriculaDao.getByMatriculaId(cursoMatricula);
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        
        for(CursoMatricula cm : matriculaCursos){
            Cursos curso = new Cursos(cm.getCursoId().getId());
            curso=cursosDao.getById(curso);
            cm.setCursoId(curso);
        }
        
        for (CursoMatricula cm : matriculaCursos){
            Vector<Object> row = new Vector<>();
            Integer idCurso = cm.getCursoId().getId();
            row.add(idCurso);
            
            String nombreCurso = cm.getCursoId().getNombre();
            row.add(nombreCurso);
            
            tableModel.addRow(row);
        }
        
        return tableModel;
    }
    
    public DefaultComboBoxModel cargarDatosCursos(){
        
        List<Cursos> listCursos= this.cursosDao.getAll();
        DefaultComboBoxModel dataCombo= new DefaultComboBoxModel();
        dataCombo.addAll(listCursos);
        return dataCombo;
    }
    
    public DefaultComboBoxModel cargarDatosTurnos(){
        
        List<Turnos> listTurnos= this.turnosDao.getAll();
        DefaultComboBoxModel dataCombo= new DefaultComboBoxModel();
        dataCombo.addAll(listTurnos);
        return dataCombo;
    }
    
    public boolean guardarRegistroMatriculas(Matriculas matriculas, List<Cursos> cursos){
        
        Estudiante estudiante = matriculas.getEstudianteId();
        
        Integer idGenEstudiante = this.estudianteDao.save(estudiante);
        
        //SI NO SE AGREGO CORRECTAMENTE EL ESTUDIANTE SE HARA UN ROOLLBACK
        if(idGenEstudiante==0){
            if(ConnectionMySql.rollBack()){
                ConnectionMySql.closeConexion();
            }   
            return false;
        }
        matriculas.getEstudianteId().setId(idGenEstudiante);
        
        Integer idMatricula = this.matriculaDao.save(matriculas);
        
        //SI NO SE AGREGO CORRECTAMENTE LA MATRICULA SE HARA UN ROOLLBACK
        if(idMatricula==0){
            if(ConnectionMySql.rollBack()){
                ConnectionMySql.closeConexion();
            }   
            return false;
        }
        matriculas.setId(idMatricula);
        
        Integer filaAFectadas = 0;
        for(Cursos cs : cursos){
            CursoMatricula cm = new CursoMatricula();
            cm.setCursoId(cs);
            cm.setMatriculaId(matriculas);
            Integer filaRegistro = this.cursoMatriculaDao.save(cm);
            
            //SE EN ALGUN CASO SUCEDE ALGUN ERROR EN INSERCION, ROMPERA EL CICLO Y RETORNARA FALSO
            if(filaRegistro==0){
                filaAFectadas = 0;
                break;
            }
             filaAFectadas +=filaRegistro; 
        }
        
        //SE HARA ROLLBACK SI NO SE AGREGA CORRECTAMENTES LOS CURSOS
        if(filaAFectadas>0){
            
            if(ConnectionMySql.commit()){
                ConnectionMySql.closeConexion();
                return true;
            }else if(ConnectionMySql.rollBack()){
                ConnectionMySql.closeConexion();
                return false;
            }
        }
        return false;
    }
}
 