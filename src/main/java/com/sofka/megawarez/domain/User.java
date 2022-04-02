package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

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
 * Entidad del usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", nullable = false)
    private Integer id;

    /**
     * Nombre del usuario
     */
    @Column(name = "usr_username", nullable = false, length = 80)
    private String username;

    /**
     * Contraseña del usuario
     */
    @Column(name = "usr_password", nullable = false, length = 32)
    private String password;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "usr_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada
     */
    @Column(name = "usr_updated_at")
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad del Usuario y Descargas
     * (una usuario puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Download.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user")
    @JsonManagedReference
    private Set<Download> downloads = new LinkedHashSet<>();

    /**
     * Punto de enlace entre la entidad del Usuario y Sesion
     * (un usuario puede tener muchas sesiones)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user")
    @JsonManagedReference
    private Set<Session> sessions = new LinkedHashSet<>();

}