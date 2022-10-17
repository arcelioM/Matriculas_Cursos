
package matriculas_cursos.tablas;



public class Cursos {
    
    private Integer id;
    private String name;

    public Cursos() {
    }

    public Cursos(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cursos{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
