package com.mycompany.proyectosjsp.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class representing a task.
 * This class is mapped to the "tareas" table in the database.
 *
 * @author Lucas
 */
@Entity
@Table(name = "tareas")
public class Tarea {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Proyecto (each task belongs to a project)
    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    // Task description
    @Column(name = "descripcion_tarea", nullable = false, length = 255)
    private String descripcionTarea;

    // Person responsible for the task
    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    // Start and end dates
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    // Task status (pending/in progress/completed)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Default constructor
    public Tarea() {
    }

    // Constructor with parameters
    public Tarea(Proyecto proyecto, String descripcionTarea, String responsable, Date fechaInicio, Date fechaFin, String estado) {
        this.proyecto = proyecto;
        this.descripcionTarea = descripcionTarea;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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

    // Override toString()
    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", proyecto=" + (proyecto != null ? proyecto.getNombreProyecto() : "No Project") +
                ", descripcionTarea='" + descripcionTarea + '\'' +
                ", responsable='" + responsable + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                '}';
    }
}
