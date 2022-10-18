
package matriculas_cursos;

import java.util.Scanner;
import matriculas_cursos.dao.TurnosDao;
import matriculas_cursos.tablas.Turnos;
import matriculas_cursos.vistamenu.MatriculaVista;

public class Main {

    
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        
        int inicioRegistro=1;
        
        while(inicioRegistro==1){
            
            System.out.println("BIENVENIDO");
            System.out.println("------------------------------");
            System.out.println("Menu de opciones");
            System.out.println("1 - Registrar una matricula");
            System.out.println("2 - Ver matriculas");
            System.out.println("3 - Actualizar Datos de matricula");
            System.out.println("4 - Eliminar matricula");
            
            System.out.print("Escriba la opcion: ");
            int elecion=sc.nextInt();
            sc.nextLine();
            Main.ejecutarEleccion(elecion);
            inicioRegistro=Main.decidirContinuacion(sc);
            
        }
        
               /*TurnosDao dao=new TurnosDao();
        Turnos turno=new Turnos(1);
        System.out.println(dao.getById(turno));
        */
    }
    
    public static void ejecutarEleccion(int eleccion){
        MatriculaVista mv=new MatriculaVista();
        System.out.println("");
        switch(eleccion){
            case 1 -> mv.registrarMatricula();
            case 2 -> mv.mostrarMatriculas();
            default -> System.out.println("Eleccion no valida");
                    
        }
    }
    
     public static int decidirContinuacion(Scanner sc){
        System.out.println("¿Desea registrar otra matricula?");
        System.out.println("1 - Si");
        System.out.println("0 - No");
        
        int desicion=sc.nextInt();
        
        if(desicion<0 || desicion>1){
            System.out.println("Eleccion no valida");
           return Main.decidirContinuacion(sc);
        }
        
        return desicion;
     }
     
    
}
