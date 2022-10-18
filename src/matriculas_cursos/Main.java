
package matriculas_cursos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import matriculas_cursos.dao.EstudianteDao;
import matriculas_cursos.dao.TurnosDao;
import matriculas_cursos.tablas.Estudiante;
import matriculas_cursos.tablas.Turnos;

public class Main {

    
    public static void main(String[] args) {
        // TODO code application logic here
        EstudianteDao estudianteDao= new EstudianteDao();
        //Estudiante estudiante= new Estudiante(3234123, "Esteban", "Gonzalez",23, LocalDate.of(1999, Month.MARCH, 2));
       
        //Integer row=estudianteDao.save(estudiante);
        //if(row==1){
            //List<Estudiante> estudiantes=estudianteDao.getAll();
            
            //estudiantes.forEach(System.out::println);
        //}
        /*Estudiante es= new Estudiante(1);
         es=estudianteDao.getById(es);
        System.out.println("ID: "+es);
        es=estudianteDao.getByCedula(es);
        System.out.println("Cedula: "+es);
        
        es.setEdad(24);
        Integer row=estudianteDao.update(es);
        if(row==1){
            System.out.println(es);
        }*/
        Turnos turno= new Turnos();
        turno.setNombre("Matutino");
        TurnosDao dao=new TurnosDao();
        System.out.println(dao.save(turno));
        turno.setNombre("Vespertino");
        System.out.println(dao.save(turno));
        turno.setNombre("Nocturno");
        System.out.println(dao.save(turno));

        
        
        
    }
    
}
