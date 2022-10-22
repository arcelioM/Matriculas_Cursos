
package matriculas_cursos.model;


public class CursoMatricula {
   
    private Cursos cursoId;
    private Matriculas matriculaId;
    

    public CursoMatricula(Cursos cursoId, Matriculas matriculaId) {
        this.cursoId = cursoId;
        this.matriculaId = matriculaId;
    }

    public CursoMatricula() {
    }

    public CursoMatricula(Matriculas matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Cursos getCursoId() {
        return cursoId;
    }

    public void setCursoId(Cursos cursoId) {
        this.cursoId = cursoId;
    }

    public Matriculas getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Matriculas matriculaId) {
        this.matriculaId = matriculaId;
    }

    @Override
    public String toString() {
        return "CursoMatricula{" + "cursoId=" + cursoId.getId() + ", matriculaId=" + matriculaId.getId() + '}';
    }
    
    
}
