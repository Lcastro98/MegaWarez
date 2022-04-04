package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de la entidad subcategoría
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    /**
     * Busca las categorías que empiezan por X dato
     *
     * @param data Dato a buscar
     * @return Listado de subcategorías encontradas
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT scat " +
            "FROM Subcategory scat " +
            "WHERE (scat.name LIKE :data%) " +
            "ORDER BY scat.name ASC")
    public List<Subcategory> findByName(@Param("data") String data);

    /**
     * Selecciona las subcategorías de una categoría en específico
     *
     * @param category Objeto de la categoría
     * @return Listado de subcategorías encontradas
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT scat " +
            "FROM Subcategory scat " +
            "WHERE scat.category = :category")
    public List<Subcategory> findAllByCategory(@Param(value = "category") Category category);

    /**
     * Actualizar el nombre de una subcategoría
     *
     * @param id Identificador del subcategoría
     * @param name Nuevo nombre de la subcategoría
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "UPDATE Subcategory scat " +
            "SET scat.name = :name " +
            "WHERE scat.id =:id")
    public void updateName(@Param(value = "id") Integer id, @Param(value = "name") String name);
}