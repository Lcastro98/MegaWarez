package com.sofka.megawarez.service.interfaces;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Iterface para el servicio de catalogo
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcasrto0398@gmail.com>
 * @since 1.0.0
 */
public interface ICatalogue {

    /**
     * Devuelve una lista de Item con todos los items del sistema
     *
     * @return
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    public List<Item> getList();

    /**
     * Devuelve una lista de Item con todos los items del sistema ordenados por el campo indicado
     * (nombre) ya sea ascendente o descentente
     *
     * @param field campo por el cual ordenar
     * @param order método para ordenar ACS o DESC
     * @return Lista de items
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    public List<Item> getList(String field, Sort.Direction order);

    /**
     * Crea una categoría en el sistema
     *
     * @param category Objeto de la categoria a crear
     * @return Objeto de la categoria creado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    public Category createCategory(Category category);

    /**
     * Crea una subcategoría en el sistema
     *
     * @param subcategory Objeto de la subcategoria a crear
     * @return Objeto de la subcategoria creado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    public Subcategory createSubcategory(Subcategory subcategory);

    /**
     * Crea un item en el sistema
     *
     * @param item Objeto del item a crear
     * @return Objeto del item creado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    public Item createItem(Item item);

    /**
     * Actualizar la tupla completa de una categoría
     *
     * @param id Identificador de la categoria a actualizar
     * @param category Objeto de la categoria a actualizar
     * @return Objeto de la categoria actualizado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    Category updateCategory(Integer id, Category category);

    /**
     * Actualizar la tupla completa de una subcategoría
     *
     * @param id Identificador de la subcategoría a actualizar
     * @param subcategory Objeto de la subcategoría a actualizar
     * @return Objeto de la subcategoria actualizada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    Subcategory updateSubcategory(Integer id, Subcategory subcategory);

    /**
     * Actualiza la tupla completa de un item
     *
     * @param id Identificador del item a actualizar
     * @param item Objeto del item a actualizar
     * @return Objeto del item actualizado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    Item updateItem(Integer id, Item item);

    /**
     * Borra una categoría del sistema basado en su identificador
     * @param id Identificador de la categoría a borrar
     * @return Objeto de la categoria borrada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    Category deleteCategory(Integer id);

    /**
     * Borra una subcategoría del sistema basado en su identificador
     *
     * @param id Identificador de la subcategoria a borrar
     * @return Objeto de la subcategoria borrada
     */
    Subcategory deleteSubcategory(Integer id);

    /**
     * Borra un item del sistema basado en su identificador
     *
     * @param id Identificador del item a borrar
     * @return Objeto del item borrado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    Item deleteItem(Integer id);

}