package com.tendencias.jsio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "departamentos")
@Data
public class Depto {

	@Id
    private String deptoId;

    @NotNull(message = "El nombre del departamento es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotNull(message = "El director del departamento es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre del director debe tener entre 3 y 50 caracteres.")
    private String director;

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres.")
    private String descripcion;

    public Depto(String deptoId, String nombre, String director, String descripcion) {
        this.deptoId = deptoId;
        this.nombre = nombre;
        this.director = director;
        this.descripcion = descripcion;
    }
   

	public String getDeptoId() {
        return deptoId;
    }

    public void setDeptoId(String deptoId) {
        this.deptoId = deptoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
