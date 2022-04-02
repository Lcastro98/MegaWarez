package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de la entidad Categoría
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since  1.0.0
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    /**
     * Busca las categorías que empiezan por X dato
     *
     * @param data Dato a buscar
     * @return Listado de categorías encontradas
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT cat " +
            "FROM Category cat " +
            "WHERE (cat.name LIKE :data%) " +
            "ORDER BY cat.name ASC")
    public List<Category> findByName(@Param("data") String data);

    /**
     * Actualiza el nombre de una categoría
     *
     * @param id Identificador de la categoría
     * @param name Nuevo nombre de la categoría
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "UPDATE Category cat " +
            "SET cat.name = :name " +
            "WHERE cat.id = :id")
    public void updateName(@Param(value = "id") Integer id, @Param(value = "name") String name);
}