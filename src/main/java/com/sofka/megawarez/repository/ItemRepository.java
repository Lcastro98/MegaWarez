package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de la entidad Item
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Busca los items que empiezan por X dato
     *
     * @param data Dato a buscar
     * @return Listado de items encontrados
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT itm " +
            "FROM Item itm " +
            "WHERE (itm.name LIKE :data%) " +
            "ORDER BY itm.name ASC")
    public List<Item> findByName(@Param("data") String data);

    /**
     * Selecciona los items de una subcategoría en específico
     *
     * @param subcategory Objeto de la subcategoría
     * @return Listado de items encontrados
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT itm " +
            "FROM Item itm " +
            "WHERE itm.subcategory = :subcategory")
    public List<Item> findAllBySubcategory(@Param(value = "subcategory") Subcategory subcategory);

    /**
     * Actualizar el nombre de un item
     *
     * @param id Identificador del item
     * @param name Nuevo nombre del item
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "UPDATE Item itm " +
            "SET itm.name = :name " +
            "WHERE itm.id = :id")
    public void updateName(@Param(value = "id") Integer id, @Param(value = "name") String name);
}