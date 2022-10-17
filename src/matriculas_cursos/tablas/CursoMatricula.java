
package matriculas_cursos.tablas;


public class CursoMatricula {
   
    private Integer cursoId;
    private Integer matriculaId;

    public CursoMatricula(Integer cursoId, Integer matriculaId) {
        this.cursoId = cursoId;
        this.matriculaId = matriculaId;
    }

    public CursoMatricula() {
    }

    public CursoMatricula(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    @Override
    public String toString() {
        return "CursoMatricula{" + "cursoId=" + cursoId + ", matriculaId=" + matriculaId + '}';
    }
    
    
}
