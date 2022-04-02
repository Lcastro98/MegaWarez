package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad del item
 *
 * @version 1.0.0 2022-03-31
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace entre la entidad de la subcategoria e item
     * (una subcategoria puede tener muchos items)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "itm_subcategory_id", nullable = false)
    @JsonBackReference
    private Subcategory subcategory;

    /**
     * Nombre del item
     */
    @Column(name = "itm_name", nullable = false, length = 80)
    private String name;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "itm_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Punto de enlace entre la entidad de la item y descarga
     * (un item puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Download.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "item")
    @JsonManagedReference
    private Set<Download> downloads = new LinkedHashSet<>();

}