package com.sofka.megawarez.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Entidad de la descarga
 *
 * @version 1.0.0 2022-03-31
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "download")
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dwn_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace entre la entidad del Usuario y Descargas
     * (una usuario puede tener muchas descargas)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dwn_user_id", nullable = false)
    private User user;

    /**
     * Punto de enlace entre la entidad de la item y descarga
     * (un item puede tener muchas descargas)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dwn_item_id", nullable = false)
    private Item item;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "dwn_created_at", nullable = false)
    private Instant dwnCreatedAt;

}