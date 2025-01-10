package com.tendencias.jsio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "profesores")
@Data
public class Profesor {

	@Id
    private String profId;

    @NotNull(message = "El ID del departamento no puede ser nulo.")
    private String deptoId;

    @NotNull(message = "El nombre del profesor es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotNull(message = "La dirección es obligatoria.")
    @Size(min = 10, max = 100, message = "La dirección debe tener entre 10 y 100 caracteres.")
    private String direccion;

    @NotNull(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener exactamente 10 dígitos.")
    private String telefono;
    
    public Profesor(String profId, String deptoId, String nombre, String direccion, String telefono) {
        this.profId = profId;
        this.deptoId = deptoId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

	public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
