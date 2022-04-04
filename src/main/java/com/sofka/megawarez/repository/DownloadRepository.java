package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Download;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de la entidad Descarga
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
public interface DownloadRepository extends JpaRepository<Download, Integer> {

    /**
     * Selecciona las descargas de un usuario en específico
     *
     * @param user Objeto del usuario
     * @return Listado de descargas encontradas
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT dwn " +
            "FROM Download dwn " +
            "WHERE dwn.user = :user")
    public List<Download> findAllByUser(@Param(value = "user") User user);

    /**
     * Selecciona las descargas de un item en específico
     *
     * @param item Objeto del item
     * @return Listado de descargas encontradas
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT dwn " +
            "FROM Download dwn " +
            "WHERE dwn.item = :item")
    public List<Download> findAllByUser(@Param(value = "item") Item item);
}