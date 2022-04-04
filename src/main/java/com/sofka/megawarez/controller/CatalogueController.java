package com.sofka.megawarez.controller;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Download;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
import com.sofka.megawarez.service.CatalogueService;
import com.sofka.megawarez.utility.LoginData;
import com.sofka.megawarez.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para el catalogo
 *
 * @version 1.0.0 2022-04-02
 * @author Lorena Castro <Lcastro0398@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
public class CatalogueController {

    /**
     * Servicio para el manejo del  catalogo
     */
    @Autowired
    private CatalogueService catalogueService;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema y versión, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index del sistema, responde con el listado de categorias y sus subcategorias
     *
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = catalogueService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve todos los items ordenados por nombre de forma ascendente o descendente
     *
     * @param category
     * @param subcategory
     * @param orderBy Nombre del campo por donde se desea ordenar la información (name o createdAt)
     * @param order Tipo de orden que debe tener la información (ASC o DESC)
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/{category}/{subcategory}/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> itemsOrderBy(
            @PathVariable(value="category") String category,
            @PathVariable(value="subcategory") String subcategory,
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = catalogueService.getList(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve los items ordenados por nombre de forma ascendente o descendente
     *
     * @param orderBy Nombre del campo por donde se desea ordenar la información (name o createdAt)
     * @param order Tipo de orden que debe tener la información (ASC o DESC)
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/index/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = catalogueService.getList(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(path = "/api/v1/descarga/{userId}/{itemId}")
    public ResponseEntity<Response> createdownload(@RequestBody Download download) {
        response.restart();
        {
            try {
                log.info("Descarga a crear: {}", download);
                response.data = catalogueService.createDownload(download);
                httpStatus = HttpStatus.CREATED;
            } catch (DataAccessException exception) {
                getErrorMessageForResponse(exception);
            } catch (Exception exception) {
                getErrorMessageInternal(exception);
            }
            return new ResponseEntity(response, httpStatus);
        }
    }

    @GetMapping(path = "/api/v1/descarga/{itemId}")
    public ResponseEntity<Response> userByItem(
            @PathVariable(value="itemId") Item itemId
    ) {
        response.restart();
        try {
            response.data = catalogueService.getList(itemId);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva categoría
     *
     * @param category Objeto Category a crear
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/category")
    public ResponseEntity<Response> createCategory(@RequestBody Category category) {
        response.restart();
        try {
            log.info("Categoría a crear: {}", category);
            response.data = catalogueService.createCategory(category);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva subcategoría
     *
     * @param subcategory Objeto Subcategory a crear
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/subcategory")
    public ResponseEntity<Response> createSubcategory(@RequestBody Subcategory subcategory) {
        response.restart();
        try {
            log.info("Subcategoría a crear: {}", subcategory);
            response.data = catalogueService.createSubcategory(subcategory);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea un nuevo item
     *
     * @param item Objeto Item a crear
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/item")
    public ResponseEntity<Response> createItem(@RequestBody Item item) {
        response.restart();
        try {
            log.info("Item a crear: {}", item);
            response.data = catalogueService.createItem(item);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de una categoría
     *
     * @param category Objeto Category a actualizar
     * @param id Identificador de la categoría
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/api/v1/category/{id}")
    public ResponseEntity<Response> updateCategory(
            @RequestBody Category category,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            log.info("Categoría a modificar: {}", category);
            response.data = catalogueService.updateCategory(id, category);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de una subcategoría
     *
     * @param subcategory Objeto Subcategory a actualizar
     * @param id Identificador de la subcategoría
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/api/v1/subcategory/{id}")
    public ResponseEntity<Response> updateSubcategory(
            @RequestBody Subcategory subcategory,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            log.info("Subcategoría a modificar: {}", subcategory);
            response.data = catalogueService.updateSubcategory(id, subcategory);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un item
     *
     * @param item Objeto Item a actualizar
     * @param id Identificador del item
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/api/v1/item/{id}")
    public ResponseEntity<Response> updateCategory(
            @RequestBody Item item,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            log.info("Item a modificar: {}", item);
            response.data = catalogueService.updateItem(id, item);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra una categoría del sistema
     *
     * @param id Identificador de la categoría
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/category/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = catalogueService.deleteCategory(id);
            if (response.data == null) {
                response.message = "La categoría no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La categoría fue removida exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra una subcategoría del sistema
     *
     * @param id Identificador de la subcategoría
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/subcategory/{id}")
    public ResponseEntity<Response> deleteSubcategory(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = catalogueService.deleteSubcategory(id);
            if (response.data == null) {
                response.message = "La subcategoría no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La csubategoría fue removida exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra un item del sistema
     *
     * @param id Identificador del item
     * @return Objeto Response en formato json
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/item/{id}")
    public ResponseEntity<Response> deleteItem(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = catalogueService.deleteItem(id);
            if (response.data == null) {
                response.message = "El item no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El item fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Inicio de sesión del usuario
     *
     * @param loginData Objeto para validar el inicio de sesión
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/login")
    public ResponseEntity<Response> login(@RequestBody LoginData loginData) {
        response.restart();
        try {
            response.message = "Todo OK";
            response.data = loginData.getToken();
            httpStatus = HttpStatus.OK;
            // realizo consulta para saber si el usuario y contraseña coinciden
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Valida el token
     *
     * @param authorization
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/token")
    public ResponseEntity<Response> getToken(@RequestHeader("Authorization") String authorization) {
        response.restart();
        try {
            response.message = "Todo OK - TOKEN";
            response.data = authorization.replace("Bearer ", "");
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para la redirección al controllador /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Lorena Castro <Lcastro0398@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
