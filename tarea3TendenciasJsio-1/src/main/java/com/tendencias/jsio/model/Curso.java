package com.tendencias.jsio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "cursos")
@Data
public class Curso {

	 @Id
	    private String cursoId;

	    @NotNull(message = "El ID del profesor no puede ser nulo.")
	    private String profId;

	    @NotNull(message = "El nombre del curso es obligatorio.")
	    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
	    private String nombre;

	    @NotNull(message = "El nivel es obligatorio.")
	    @Pattern(regexp = "^(Básico|Intermedio|Avanzado)$", message = "El nivel debe ser Básico, Intermedio o Avanzado.")
	    private String nivel;

	    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres.")
	    private String descripcion;
    
	    public Curso(String cursoId, String profId, String nombre, String nivel, String descripcion) {
	        this.cursoId = cursoId;
	        this.profId = profId;
	        this.nombre = nombre;
	        this.nivel = nivel;
	        this.descripcion = descripcion;
	    }


	public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
