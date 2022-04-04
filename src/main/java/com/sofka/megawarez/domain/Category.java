package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad de la Categoria
 *
 * @version 1.0.0 2022-03-31
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Integer id;

    /**
     * Nombre de la categoria
     */
    @Column(name = "cat_name", nullable = false, length = 80)
    private String name;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "cat_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Punto de enlace entre la entidad del Categoria y Subcategoria
     * (una categoria puede tener muchas subcategorias)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Subcategory.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "category")
    @JsonIgnore
    private Set<Subcategory> subcategories = new LinkedHashSet<>();
}