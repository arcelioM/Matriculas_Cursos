
package matriculas_cursos;

import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        
        int inicioRegistro=1;
        
        while(inicioRegistro==1){
            sc.nextLine();
            System.out.println("BIENVENIDO");
            System.out.println("------------------------------");
            System.out.println("Menu de opciones");
            System.out.println("1 - Registrar una matricula");
            System.out.println("2 - Ver matriculas");
            System.out.println("3 - Actualizar Datos de matricula");
            System.out.println("4 - Eliminar matricula");
            
            int elecion=sc.nextInt();
            Main.ejecutarEleccion(elecion);
            inicioRegistro=Main.decidirContinuacion(sc);
            
        }
        
       
        
    }
    
    public static void ejecutarEleccion(int eleccion){
        System.out.println("Inicio de ejecucion");
        switch(eleccion){
            
            case 1: 
                    
        }
    }
    
     public static int decidirContinuacion(Scanner sc){
        System.out.println("¿Desea agregar registrar otra matricula?");
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
