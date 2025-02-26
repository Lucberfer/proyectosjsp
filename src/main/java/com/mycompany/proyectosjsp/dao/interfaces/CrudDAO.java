package com.mycompany.proyectosjsp.dao.interfaces;

import java.util.List;
import java.util.Collections;

/**
 * Generic CRUD interface for DAO classes.
 * This interface defines common operations for database access.
 *
 * @param <T> The entity type
 * @author Lucas
 */
public interface CrudDAO<T> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The entity's ID
     * @return The entity object or null if not found
     */
    T obtenerPorId(Long id);

    /**
     * Retrieves all entities of a specific type.
     *
     * @return A list of entities (returns empty list if none found)
     */
    default List<T> obtenerTodos() {
        return Collections.emptyList();
    }

    /**
     * Saves a new entity in the database.
     *
     * @param entidad The entity to be saved
     */
    void guardar(T entidad);

    /**
     * Updates an existing entity in the database.
     *
     * @param entidad The entity to be updated
     */
    void actualizar(T entidad);

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to be deleted
     */
    void eliminar(Long id);
}
