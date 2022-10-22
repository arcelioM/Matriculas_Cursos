
package matriculas_cursos.estrcuturar;

import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import matriculas_cursos.dao.EstudianteDao;
import matriculas_cursos.dao.MatriculasDao;
import matriculas_cursos.dao.TurnosDao;
import matriculas_cursos.model.Estudiante;
import matriculas_cursos.model.Matriculas;
import matriculas_cursos.model.Turnos;


public class EstructurarDatos {
    
    private final MatriculasDao matriculaDao = new MatriculasDao();
    private final TurnosDao turnosDao = new TurnosDao();
    private final EstudianteDao estudianteDao = new EstudianteDao();
    
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
}
