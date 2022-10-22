
package matriculas_cursos.model;



public class Cursos {
    
    private Integer id;
    private String nombre;

    public Cursos() {
    }

    public Cursos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cursos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cursos{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
}
