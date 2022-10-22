
package matriculas_cursos.vistamenu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import matriculas_cursos.dao.CursoMatriculaDao;
import matriculas_cursos.dao.CursosDao;
import matriculas_cursos.dao.EstudianteDao;
import matriculas_cursos.dao.MatriculasDao;
import matriculas_cursos.dao.TurnosDao;
import matriculas_cursos.model.CursoMatricula;
import matriculas_cursos.model.Cursos;
import matriculas_cursos.model.Estudiante;
import matriculas_cursos.model.Matriculas;
import matriculas_cursos.model.Turnos;

/**
 *
 * @author pc
 */
public class MatriculaVista {
    
    private Scanner sc=new Scanner(System.in);
    private EstudianteDao estudianteDao=new EstudianteDao();
    private TurnosDao turnosDao=new TurnosDao();
    private MatriculasDao matriculasDao=new MatriculasDao();
    private CursosDao cursosDao=new CursosDao();
    private CursoMatriculaDao cursoMatriculaDao=new CursoMatriculaDao();
    
    /**
     * SE ENCARGARA DE REGISTRAR LOS DATOS DEL ESTUDIANTE Y CREAR UNA MATRICULA PARA ESTE ESTUDIANTE
     */
    public void registrarMatricula(){
        
        System.out.println("Informacion de estudiante");
        System.out.println("--------------------------------------------------");
        System.out.print("Cedula (sin guiones): ");
        Integer cedula=sc.nextInt();
        sc.nextLine();
        
        System.out.println("--------------------------------------------------");
        System.out.print("Nombre: ");
        String nombre=sc.nextLine();
        
        System.out.println("--------------------------------------------------");
        System.out.print("Apellido: ");
        String apellido=sc.nextLine();
        
        System.out.println("--------------------------------------------------");
        System.out.print("Edad: ");
        int edad=sc.nextInt();
        sc.nextLine();
        
        System.out.println("--------------------------------------------------");
        System.out.println("Fecha de nacimiento");
        
        System.out.print("Año: ");
        Integer año=sc.nextInt();
        sc.nextLine();
        
        System.out.print("MES: ");
        Integer mes=sc.nextInt();
        sc.nextLine();
        
        System.out.print("Dia: ");
        Integer dia=sc.nextInt();
        sc.nextLine();
        LocalDate fechaNacimiento=LocalDate.of(año, mes, dia);
        
        Estudiante estudiante=new Estudiante(cedula, nombre, apellido, edad, fechaNacimiento);
        
        //REGISTRAR ESTUDIANTE EN BD
        Integer filaAfectadas=estudianteDao.save(estudiante);
        if(filaAfectadas==1){
            //PARA TENER LOS DATOS COMPLETOS DEL ESTUDIANTE REGISTRADO EN LA BD
            estudiante=estudianteDao.getByCedula(estudiante);
        }
        
        System.out.println("--------------------------------------------------");
        Integer estudianteId=estudiante.getId();
        
        System.out.println("Costo de matricula: ");
        Double costo=sc.nextDouble();
        sc.nextLine();
        
        System.out.println("--------------------------------------------------");
        System.out.println("Turnos disponibles: ");
        
        //OBTENDREMOS LOS TURNOS DISPONIBLES PARA MATRICULAR
        List<Turnos> turnos=turnosDao.getAll();
        System.out.println("Elija un turno: ");
        //MOSTRANDO EL LISTADO DE TURNOS DISPONIBLE AL USUARIO
        for(Turnos turno : turnos){
            System.out.println(turno.getId() +" - "+turno.getNombre());
        }
        
        Integer turnoId=sc.nextInt();
        sc.nextLine();
        
        Matriculas matricula=new Matriculas(estudianteId, costo, turnoId);
        
        Integer idGenerado=matriculasDao.save(matricula);
        
        if(idGenerado>0){
            matricula.setId(idGenerado); //ACTUALIZAMOS EL OBJETO MATRICULA CON EL ID QUE TIENE EN LA BD
            System.out.println("Matricula registrada....");
            this.registrarCursos(matricula);
            System.out.println("Proceso de registro de matricula y curso terminado....");
        }else{
            System.out.println("Error en creacion de matricula......");
        }
        
        
    }
    
    /**
     * SE ENCARGARA DE REGISTRAR LOS CURSOS A LOS QUE SE MATRICULA UN ESTUDIANTE EN ESPECIFICO
     * @param matricula 
     */
    private void registrarCursos(Matriculas matricula){
        System.out.println("Registrar cursos a matricular");
        System.out.println("0 - Salir");
        List<Integer> cursosSeleccionados=new ArrayList<>();
        
        List<Cursos> cursos=cursosDao.getAll();
        for(Cursos curso: cursos){
            System.out.println(curso.getId()+" - "+curso.getNombre());
        }
        
        
        Integer seleccion=1;
        System.out.println("Elija los cursos a matricular, dar enter para confirmar cada curso");
        while(seleccion>0){
            System.out.print("Escriba numero del curso (0 para salir): ");
            seleccion=sc.nextInt();
            sc.nextLine();
            if(seleccion>0){
                cursosSeleccionados.add(seleccion);
            }
        }
        
        //REGISTRAR CADA CURSO MATRICULADO EN LA BD
        Integer matriculaId=matricula.getId();
        for(Integer idCurso : cursosSeleccionados){
            CursoMatricula cursoMatricula= new CursoMatricula(idCurso, matriculaId);
            int registrosCreados=cursoMatriculaDao.save(cursoMatricula);
            if(registrosCreados<=0){
                System.out.println("Error en registro de cursos matriculados");
                break; //CUANDO SE EJECUTA EL BREAK, ROMPERA EL CICLO POR COMPLETO
            }
        }
        
        System.out.println("Registro de matriculas realizados");
    }
    
    
    public void mostrarMatriculas(){
        
       
        System.out.println("Listado de matriculas registradas");
        System.out.println("--------------------------------------------------------------------");
        
        //OBTENDREMOS LAS MATRICULAS REGISTRADA EN LA BD
        List<Matriculas> matriculas=matriculasDao.getAll();
        
        //MOSTRAREMOS LA INFORMACION DE LA MATRICULA
        for(Matriculas matricula : matriculas){
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("Informacion de matricula");
            System.out.println("---------------------------------------------------------");
            
            System.out.print("ID: "+matricula.getId() +" | Costo de matricula: "+matricula.getCosto() + " | Turno: "+this.mostrarTurno(matricula.getTurnoId()));
            System.out.println(" | Fecha de creacion: "+matricula.getFecheRegistro());
            
            //MOSTRAREMOS LA INFORMACION DEL ESTUDIANTE MATRICULADO
             System.out.println("");
            System.out.println("Informacion del estudiante");
            System.out.println("---------------------------------------------------------");
            Estudiante estudiante = new Estudiante(matricula.getEstudianteId());
            estudiante=estudianteDao.getById(estudiante);
            System.out.print("Cedula: "+estudiante.getCedula() +" | Nombre: "+estudiante.getNombre()+" | Apellido: "+estudiante.getApellido());
            System.out.println(" | Edad: "+estudiante.getEdad()+" | Fecha de nacimiento: "+estudiante.getFechaNacimiento());
            
            //AHORA MOSTRAREMOS TODOS LOS CURSOS QUE SE HA MATRICULADO EL ESTUDIANTE
            System.out.println("");
            System.out.println("Cursos mastriculados");
            System.out.println("---------------------------------------------------------");
            this.mosTrarCursosMatriculados(matricula.getId());
            
            
        }

    }
    
    private String mostrarTurno(Integer turnoId){
        
        //BUSCAREMOS EL NOMBRE DEL TURNO POR EL ID REGISTRADO EN LA MATRICULA
        //PARA MOSTRARLO POR CONSOLA
        Turnos turno=new Turnos(turnoId);
        turno=turnosDao.getById(turno);
        
        return turno.getNombre();
    }
    
    private void mosTrarCursosMatriculados(Integer matriculaId){
        
        CursoMatricula cursoMatricula=new CursoMatricula(matriculaId);
        List<CursoMatricula> cursoMatriculas=cursoMatriculaDao.getByMatriculaId(cursoMatricula);
        Integer cont=0;
        for( CursoMatricula cm : cursoMatriculas){
            cont++;
            Cursos curso= new Cursos(cm.getCursoId());
            curso=cursosDao.getById(curso);
            
            System.out.println(cont+" - "+curso.getNombre());
            System.out.println("----------------------------------------------------");
        }
    }
    
    
    public void mostrarMatriculaPorCedula(Estudiante estudianteArg){
        
        //BUSCAR LA INFORMACION DE COMPLETA DEL ESTUDIANTE
        estudianteArg=estudianteDao.getByCedula(estudianteArg);
        
        //BUSCAR INFORMACION DE LAS MATRICULAS ASOCIADA A EL ESTUDIANTE
        Matriculas matriculaArg=new Matriculas();
        matriculaArg.setEstudianteId(estudianteArg.getId());
        List<Matriculas> matriculas=matriculasDao.getByEstudianteId(matriculaArg);
        
        for(Matriculas matricula: matriculas){
            
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("Informacion de matricula");
            System.out.println("---------------------------------------------------------");
            
            System.out.print("ID: "+matricula.getId() +" | Costo de matricula: "+matricula.getCosto() + " | Turno: "+this.mostrarTurno(matricula.getTurnoId()));
            System.out.println(" | Fecha de creacion: "+matricula.getFecheRegistro());
            
            //MOSTRAREMOS LA INFORMACION DEL ESTUDIANTE MATRICULADO
             System.out.println("");
            System.out.println("Informacion del estudiante");
            System.out.println("---------------------------------------------------------");
            Estudiante estudiante = new Estudiante(matricula.getEstudianteId());
            estudiante=estudianteDao.getById(estudiante);
            System.out.print("Cedula: "+estudiante.getCedula() +" | Nombre: "+estudiante.getNombre()+" | Apellido: "+estudiante.getApellido());
            System.out.println(" | Edad: "+estudiante.getEdad()+" | Fecha de nacimiento: "+estudiante.getFechaNacimiento());
            
            //AHORA MOSTRAREMOS TODOS LOS CURSOS QUE SE HA MATRICULADO EL ESTUDIANTE
            System.out.println("");
            System.out.println("Cursos mastriculados");
            System.out.println("---------------------------------------------------------");
            this.mosTrarCursosMatriculados(matricula.getId());
            
        }
    }
    
    
    public void agregarCurso(){
        System.out.println("------------------------------------------------------------------");
        System.out.println("");
        System.out.print("Nombre del curso: ");
        String nombre=sc.nextLine();
        Cursos curso= new Cursos();
        curso.setNombre(nombre);
        
        Integer registroInsertado=cursosDao.save(curso);
        if(registroInsertado==1){
            System.out.println("Curso agregado");
            System.out.println("");
        }
    }
}
