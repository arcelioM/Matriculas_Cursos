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



public class EstructurarDatos {
    
    private final MatriculasDao matriculaDao = new MatriculasDao();
    private final TurnosDao turnosDao = new TurnosDao();
    private final EstudianteDao estudianteDao = new EstudianteDao();
    private final CursoMatriculaDao cursoMatriculaDao= new CursoMatriculaDao();
    private final CursosDao cursosDao = new CursosDao();
    
    public DefaultTableModel cargarDatoMatriculas(){
        
        //SE CREA EL OBJETO NECESARIO PARA AGREGAR LA INFORMACION A LA TABLA
        DefaultTableModel tableModel= new DefaultTableModel();
        
        //SE BUSCA LA INFORMACION DE LAS MATRICULAS
        List<Matriculas> matriculas=matriculaDao.getAll();
        
        //SE DEFINE LOS NOMBRES DE LAS COLUMNAS
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre de estudiante");
        tableModel.addColumn("Cedula");
        tableModel.addColumn("Turno");
        tableModel.addColumn("Costo");

        //SE RECORRERA TODAS LAS MATRICULAS DISPONIBLES
        for(Matriculas cm : matriculas){
            
            //SE LE AGREGARA LA INFORMACION COMPLETA DEL ESTUDIANTE QUE LE PERTENECE A LA MATRICULA
            Estudiante estudiante = estudianteDao.getById(cm.getEstudianteId());
            cm.setEstudianteId(estudiante);
            
            //SE LE AGREGARA LA INFORMACION DEL TURNO QUE LE PERTENECE A LA MATRICULA
            Turnos turno= turnosDao.getById(cm.getTurnoId());
            cm.setTurnoId(turno);
            
        }

        //YA CON TODA LA INFORMACION CREADA
       for(Matriculas cm : matriculas){
           
           //SE CREARA UN OBJETO VECTOR PARA AGREGAR CADA FILA DE DATOS A LA TABLA
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
            //SE AGREGA (VECTOR) LA FILA A LA TABLA 
            tableModel.addRow(row);
        }
       
       return tableModel; //SE ENVIA LA INFORMACION PARA SER AGREGADO A LA TABLA
    }
    
    public DefaultTableModel cargarDatosCursoPorMatricula(Integer idMatricula){
        //SE RECIBE UN PARAMETRO QUE SERA EL ID MATRICULA
        //SE CREA UN OBJETO MATRICULA Y SE LE ASIGNA EL ID DE MATRICULA RECIBIDO
        Matriculas matricula = new Matriculas(idMatricula);
        //SE ASIGNA ESTA OBJETO DE MATRICULA A CURSOAMTRICULA
        CursoMatricula cursoMatricula = new CursoMatricula(matricula);
        
        //SE OBTIENE LOS CURSOS RELACIONADOS A LA MATRICULA
        List<CursoMatricula> matriculaCursos=cursoMatriculaDao.getByMatriculaId(cursoMatricula);
        
        //SE DEFINE UN OBJETO PARA AGREGAR A TABLA DE CURSOS
        DefaultTableModel tableModel = new DefaultTableModel();
        
        //SE CREA NOMBRE DE COLUMNAS
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        
        //SE RECORRE LOS CURSOS RELACIONADOS A LA MATRICULAS
        for(CursoMatricula cm : matriculaCursos){
            
            //SE OBTIENE EL ID DE CADA CURSO, PARA ASI PODER CARGAR EL NOMBRE DE CURSO
            Cursos curso = new Cursos(cm.getCursoId().getId());
            curso=cursosDao.getById(curso);
            cm.setCursoId(curso);
        }
        
        //SE RECORRE LOS CURSOS YA CON LOS DATOS COMPLETOS
        for (CursoMatricula cm : matriculaCursos){
            
            //SE CREA UN VECTOR QUE SE ENCAGARA DE TENER LA INFORMACION DE CADA FILA
            Vector<Object> row = new Vector<>();
            Integer idCurso = cm.getCursoId().getId();
            row.add(idCurso);
            
            String nombreCurso = cm.getCursoId().getNombre();
            row.add(nombreCurso);
            
            //SE AGREGA NUEVA FILA
            tableModel.addRow(row);
        }
        
        return tableModel; //SE DEVUELVE EL VALOR
    }
    
    public DefaultComboBoxModel cargarDatosCursos(){
        
        //SE OBTIENE LOS CURSOS QUE ESTAN REGISTRADO EN LA BD
        List<Cursos> listCursos= this.cursosDao.getAll();
        
        //SE CREA UN CONTENEDOR PARA LOS DATOS DEL COMBOBOX PARA LOS CURSOS
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
        
        //SE RECIBE LOS DATOS DE LA NUEVA MATRICULA Y LOS CURSOS A LOS QUE FUE MATRICULADO
        //SE CREA UN OBJETO ESTUDIANTE CON LOS DATOS INGRESADOS POR EL USUARIO
        Estudiante estudiante = matriculas.getEstudianteId();
        
        //SE GUARDA EN LA BD, Y SE OBTIENE EL ID GENERADO
        Integer idGenEstudiante = this.estudianteDao.save(estudiante);
        
        //SI EL ESTUDIANTE NO SE AGREGO CORRECTAMENTE EL ESTUDIANTE SE HARA UN ROOLLBACK
        if(idGenEstudiante==0){
            if(ConnectionMySql.rollBack()){
                ConnectionMySql.closeConexion();
            }   
            return false;
        }
        
        //SE AGREGA EL ID GENERADO AL OBJETO DE MATRICULA
        matriculas.getEstudianteId().setId(idGenEstudiante);
        
        //SE GUARDA LA MATRICULA, Y SE ALMACENA EL ID GENERADO
        Integer idMatricula = this.matriculaDao.save(matriculas);
        
        //SI NO SE AGREGO CORRECTAMENTE LA MATRICULA SE HARA UN ROOLLBACK
        if(idMatricula==0){
            if(ConnectionMySql.rollBack()){
                ConnectionMySql.closeConexion();
            }   
            return false;
        }
        
        //SE AGREGA EL ID DE LA MATRICULA AL OBJETO
        matriculas.setId(idMatricula);
        
        Integer filaAFectadas = 0;
        //SE RECORRE LOS CURSOS PARA MATRICULAR, Y SE GUARDAN EN BD
        for(Cursos cs : cursos){
            
            //SE CREA EL OBJETO DE CURSO MATRICULA PARA SER GUARDADO
            CursoMatricula cm = new CursoMatricula();
            cm.setCursoId(cs);
            cm.setMatriculaId(matriculas);
            
            //SE GUARDA LA INFORMACION EN LA BD
            Integer filaRegistro = this.cursoMatriculaDao.save(cm);
            
            //SE EN ALGUN CASO SUCEDE ALGUN ERROR EN INSERCION, ROMPERA EL CICLO Y RETORNARA FALSO
            if(filaRegistro==0){
                filaAFectadas = 0;
                break;
            }
             filaAFectadas +=filaRegistro; 
        }
        
        //SE VERIFICA SI SE AGREGO CORRECTAMENTES LOS CURSOS
        if(filaAFectadas>0){
            
            //SI  HACE COMMIT CORRECTAMENTE , SE CERRARA LA CONEXION
            //CON ESTE COMMIT SE GUARDARA DEFINITIVAMENTE LOS DATOS DE ESUDIANTE, MATRICULA Y CURSOS
            if(ConnectionMySql.commit()){
                ConnectionMySql.closeConexion();
                return true;
            }else if(ConnectionMySql.rollBack()){ //SINO INTENTARA HACER UN ROLLBACK Y SE CERRARA LA CONEXION
                //SI HACE ROLLBACK, NADA DE LO QUE HAYA INGRESADO SE GUARDARA
                ConnectionMySql.closeConexion();
                return false;
            }
        }
        
        return false;
    }
}
 