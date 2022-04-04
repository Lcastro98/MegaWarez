package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
import com.sofka.megawarez.repository.CategoryRepository;
import com.sofka.megawarez.repository.ItemRepository;
import com.sofka.megawarez.repository.SubcategoryRepository;
import com.sofka.megawarez.service.interfaces.ICatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Clase tipo  Servicio para el manejo del catalogo
 *
 * @version 1.0.0 2022-04-03
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Service
public class CatalogueService implements ICatalogue {

    /**
     * Repositorio de Categoría
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Repositorio de Subcategoría
     */
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    /**
     * Repositorio de Item
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * Devuelve una lista de Item con todos los items del sistema
     *
     * @return
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Item> getList() {
        return itemRepository.findAll();
    }

    /**
     * Devuelve una lista de Item con todos los items ordenados por el campo indicado ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método para ordenar ACS o DESC
     * @return Lista de items
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Item> getList(String field, Sort.Direction order) {
        return itemRepository.findAll(Sort.by(order, field));
    }

    /**
     * Crea una categoría en el sistema
     *
     * @param category Objeto de la categoria a crear
     * @return Objeto de la categoría creada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category createCategory(Category category) {
        category.setCreatedAt(Instant.now());
        return categoryRepository.save(category);
    }

    /**
     * Crea una subcategoría en el sistema
     *
     * @param subcategory Objeto de la subcategoria a crear
     * @return Objeto de la subcategoría creada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory createSubcategory(Subcategory subcategory) {
        subcategory.setCreatedAt(Instant.now());
        return subcategoryRepository.save(subcategory);
    }

    /**
     * Crea un item en el sistema
     *
     * @param item Objeto de la item a crear
     * @return Objeto de la item creada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item createItem(Item item) {
        item.setCreatedAt(Instant.now());
        return itemRepository.save(item);
    }

    /**
     * Actualiza una categoría del sistema
     *
     * @param id Identificador de la categoria a actualizar
     * @param category Objeto de la categoria a actualizar
     * @return Objeto de la categoría actualizada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category updateCategory(Integer id, Category category) {
        var cat = categoryRepository.findById(id);
        if(cat.isPresent()) {
            Category _category = cat.get();
            _category.setName(category.getName());
            category = _category;
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    /**
     * Actualiza una subcategoría del sistema
     *
     * @param id Identificador de la subcategoria a actualizar
     * @param subcategory Objeto de la subcategoria a actualizar
     * @return Objeto de la subcategoría actualizada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory updateSubcategory(Integer id, Subcategory subcategory) {
        var scat = subcategoryRepository.findById(id);
        if(scat.isPresent()) {
            Subcategory _subcategory = scat.get();
            _subcategory.setName(subcategory.getName());
            subcategory = _subcategory;
            return subcategoryRepository.save(subcategory);
        } else {
            return null;
        }
    }

    /**
     * Actualiza un item del sistema
     *
     * @param id Identificador del item a actualizar
     * @param item Objeto del item a actualizar
     * @return Objeto del item actualizado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item updateItem(Integer id, Item item) {
        var itm = itemRepository.findById(id);
        if(itm.isPresent()) {
            Item _item = itm.get();
            _item.setName(item.getName());
            item = _item;
            return itemRepository.save(item);
        } else {
            return null;
        }
    }

    /**
     * Borra una categoría del sistema
     *
     * @param id Identificador de la categoría a borrar
     * @return Objeto de la categoría borrada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category deleteCategory(Integer id) {
        var category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return category.get();
        } else {
            return null;
        }
    }

    /**
     * Borra una subcategoría del sistema
     *
     * @param id Identificador de la subcategoría a borrar
     * @return Objeto de la subcategoría borrada
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory deleteSubcategory(Integer id) {
        var subcategory = subcategoryRepository.findById(id);
        if (subcategory.isPresent()) {
            subcategoryRepository.delete(subcategory.get());
            return subcategory.get();
        } else {
            return null;
        }
    }

    /**
     * Borra un item del sistema
     *
     * @param id Identificador del item a borrar
     * @return Objeto del item borrado
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item deleteItem(Integer id) {
        var item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return item.get();
        } else {
            return null;
        }
    }
}
