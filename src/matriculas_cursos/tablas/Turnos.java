
package matriculas_cursos.tablas;

public class Turnos {
    
    private Integer id;
    private String name;

    public Turnos() {
    }

    public Turnos(Integer id) {
        this.id = id;
    }

    public Turnos(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Turnos{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
