
package matriculas_cursos.tablas;

import java.time.LocalDateTime;


public class Matriculas {
    
    private Integer id;
    private Integer estudianteId;
    private Double costo;
    private Integer turnoId;
    private LocalDateTime fecheRegistro;

    public Matriculas() {
    }

    public Matriculas(Integer id) {
        this.id = id;
    }

    public Matriculas(Integer id, Integer estudianteId, Double costo, Integer turnoId, LocalDateTime fecheRegistro) {
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

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Integer turnoId) {
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
        return "Matriculas{" + "id=" + id + ", estudianteId=" + estudianteId + ", costo=" + costo + ", turnoId=" + turnoId + ", fecheRegistro=" + fecheRegistro + '}';
    }
    
    
}
