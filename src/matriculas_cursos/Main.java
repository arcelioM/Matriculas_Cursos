
package matriculas_cursos;

import java.util.Scanner;
import matriculas_cursos.tablas.Estudiante;
import matriculas_cursos.vistamenu.MatriculaVista;

public class Main {

    static Scanner sc= new Scanner(System.in);
    
    public static void main(String[] args) {

        
        
        int inicioRegistro=1;
        
        while(inicioRegistro==1){
            
            System.out.println("BIENVENIDO");
            System.out.println("------------------------------");
            System.out.println("Menu de opciones");
            System.out.println("1 - Registrar una matricula");
            System.out.println("2 - Ver matriculas");
            System.out.println("3 - Ver matriculas por cedula");
            System.out.println("4 - Agregar nuevo curso");
            
            System.out.print("Escriba la opcion: ");
            int elecion=sc.nextInt();
            sc.nextLine();
            Main.ejecutarEleccion(elecion);
            inicioRegistro=Main.decidirContinuacion(sc);
            
        }
        
    }
    
    public static void ejecutarEleccion(int eleccion){
        MatriculaVista mv=new MatriculaVista();
        System.out.println("");
        switch(eleccion){
            case 1 :
                    mv.registrarMatricula();
                    break;
            case 2 : 
                    mv.mostrarMatriculas();
                    break;
            case 3 : 
                    System.out.print("Escriba numero de cedula: ");
                    Integer cedula=sc.nextInt();
                    sc.nextLine();
                    Estudiante estudiante= new Estudiante();
                    estudiante.setCedula(cedula);
                    mv.mostrarMatriculaPorCedula(estudiante);
                    break;
                    
            case 4 : 
                    mv.agregarCurso();
                    break;
            
            default : System.out.println("Eleccion no valida");
                    
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
