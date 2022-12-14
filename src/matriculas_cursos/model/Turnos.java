
package matriculas_cursos.model;

public class Turnos {
    
    private Integer id;
    private String nombre;

    public Turnos() {
    }

    public Turnos(Integer id) {
        this.id = id;
    }

    public Turnos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
        return nombre ;
    }
    
    
}
