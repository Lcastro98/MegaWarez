package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de la entidad Usuario
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Actualiza el nombre de un usuario
     *
     * @param id Identificador del usuario
     * @param username Nuevo nombre del usuario
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "UPDATE User usr " +
            "SET usr.username = :username, usr.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE usr.id = :id")
    public void updateUsername(@Param(value = "id") Integer id, @Param(value = "username") String username);

    /**
     * Actualiza la contraseña de un usuario
     *
     * @param id Identificador del usuario
     * @param password Nueva contraseña del usuario
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "UPDATE User usr " +
            "SET usr.password = :password, usr.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE usr.id = :id")
    public void updatePassword(@Param(value = "id") Integer id, @Param(value = "password") String password);

}