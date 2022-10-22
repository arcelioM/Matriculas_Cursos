
package matriculas_cursos.model;

import java.time.LocalDateTime;


public class Matriculas {
    
    private Integer id;
    //private Integer estudianteId;
    private Double costo;
    //private Integer turnoId;
    private LocalDateTime fecheRegistro;
    private Estudiante estudianteId;
    private Turnos turnoId;

    public Matriculas() {
    }

    public Matriculas(Integer id) {
        this.id = id;
    }

    public Matriculas(Estudiante estudianteId, Double costo, Turnos turnoId) {
        this.estudianteId = estudianteId;
        this.costo = costo;
        this.turnoId = turnoId;
    }

    
    public Matriculas(Integer id, Estudiante estudianteId, Double costo, Turnos turnoId, LocalDateTime fecheRegistro) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.costo = costo;
        this.turnoId = turnoId;
        this.fecheRegistro = fecheRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Turnos getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Turnos turnoId) {
        this.turnoId = turnoId;
    }

    public LocalDateTime getFecheRegistro() {
        return fecheRegistro;
    }

    public void setFecheRegistro(LocalDateTime fecheRegistro) {
        this.fecheRegistro = fecheRegistro;
    }

    @Override
    public String toString() {
        return "Matriculas{" + "id=" + id + ", estudianteId=" + estudianteId.getId() + ", costo=" + costo + ", turnoId=" + turnoId.getId() + ", fecheRegistro=" + fecheRegistro + '}';
    }
    
    
}
