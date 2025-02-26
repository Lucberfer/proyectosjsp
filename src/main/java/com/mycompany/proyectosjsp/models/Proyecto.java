package com.mycompany.proyectosjsp.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Entity class representing a project.
 * This class is mapped to the "proyectos" table in the database.
 *
 * @author Lucas
 */
@Entity
@Table(name = "proyectos")
public class Proyecto {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Project name
    @Column(name = "nombre_proyecto", nullable = false, length = 100)
    private String nombreProyecto;

    // Project description
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // Start and end dates
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    // Project status (in progress/completed...)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // One-to-many relationship: a project can have multiple tasks
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>(); // Initialize list to prevent null errors

    // Default constructor
    public Proyecto() {}

    // Constructor with parameters
    public Proyecto(String nombreProyecto, String descripcion, Date fechaInicio, Date fechaFin, String estado) {
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.tareas = new ArrayList<>(); // Initialize list in constructor
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    // Override toString() with null-safe checks
    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombreProyecto='" + nombreProyecto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + (fechaInicio != null ? fechaInicio.toString() : "null") +
                ", fechaFin=" + (fechaFin != null ? fechaFin.toString() : "null") +
                ", estado='" + estado + '\'' +
                ", tareas=" + (tareas != null ? tareas.size() : "No tasks") +
                '}';
    }
}
