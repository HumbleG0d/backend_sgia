package uni.sgia.controller;

import uni.sgia.service.InmuebleService;
import uni.sgia.model.Inmueble;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

@RestController //Anotacion que indica que esta clase es un controlador que maneja las solicitudes HTTP.
@RequestMapping(path = "api/v1/inmuebles") //Anotacion que especifica que raiz del URL para todas la solicitudes manejadas por este controlador.
@CrossOrigin(origins = "http://localhost:4200/") //Anotacion que permite solicitudes desde un origen diferente, en este caso http://localhost:4200/
public class InmuebleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass()); //Objeto de registro de Logger utilizado para registrar mensajes y eventos en la aplicacion.

    @Autowired //Indica que InmuebleRepository debe ser inyectado automaticamente
    InmuebleService inmuebleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //La anotacion RequestMapping especifica como debe manejarse las solicitudes HTTP entrantes en el metodo getInmuebles.

    public ResponseEntity<List<Inmueble>> getInmuebles() { //Metodo que maneja las solicitudes HTTP GET para obtener la lista de inmuebles.Usa inmuebleService.getInmuebles() para obtener la lista desde el sevicio. Devuelve una respuesta con la lista de inmuebles y un codigo de estado HTTP.
        logger.info("> getInmuebles[Inmueble]");
        List<Inmueble> list = null; //Incializamos la lista de inmuebles

        //Usamos try catch para el manejo de excepciones en caso de error al obtener la lista de inmuebles.
        try {
            //Obtenemos la lista de inmuebles del servicio
            list = inmuebleService.getInmuebles();
            if (list == null) //Si la lista es nula , se inicializa como una lista vacia
                list = new ArrayList<>();
        } catch (Exception e) {
            logger.error("Excepcion inesperada al obtener la lista de personas", e); //Registra un mensaje de error en el sistema de registro
            return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR); //Devuelve una respuesta con la lista nula y un codigo de estado HTTP 500(Error interno del servidor)
        }
        logger.info("> getPersonas[Persona]"); //Registramos el mensaje de informacion en el sistema de registro
        return new ResponseEntity<>(list, HttpStatus.OK); //Devuelce una respuesta con la lista de inmuebles y un codigo de estado HTTP 200.
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Inmueble> addInmueble(@RequestBody Inmueble inmueble) { //Metodo que maneja las solicitudes HTTP POST para gregar un uevo inmueble. Utiliza inmuebleService.saveOrUpdate(inmueble) para guardar el inmueble en la base de datos. Devuelve una respuesta con el inmueble agregado y un codigo de estado HTTP
        // Registra un mensaje de información en el sistema de registro.
    logger.info(" >agregar: " + inmueble.toString());

    // Usamos try catch para el manejo de excepciones en caso de error al agregar el inmueble.
    try {
        // Intenta guardar o actualizar el inmueble utilizando el servicio.
        inmuebleService.saveOrUpdate(inmueble);
    } catch (Exception e) {
        // Registra un mensaje de error en el sistema de registro.
        logger.error("Excepción inesperada al agregar inmueble", e);

        // Devuelve una respuesta con el inmueble nulo y un código de estado HTTP 500 (Error interno del servidor).
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Devuelve una respuesta con el inmueble agregado y un código de estado HTTP 200 (OK).
    return new ResponseEntity<>(inmueble, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Inmueble> searchInmueble(@RequestParam(name = "id") Long id) { //Metodo que maneja las solicitudes HTTP GET para buscar un inmueble por su ID. Utiliza inmuebleService.getInmueble(id) para obtener el inmueble. Devuelve una respuesta con el inmueble encontrado o un codigo de estado HTTP de "no encontrado"
        // Registra un mensaje de información en el sistema de registro.
    logger.info(">getInmueble id_inmueble: " + id);

    // Usamos try catch para el manejo de excepciones en caso de error al obtener el inmueble.
    try {
        // Intenta obtener el inmueble del servicio utilizando su ID.
        Optional<Inmueble> inmueble = inmuebleService.getInmueble(id);

        // Verifica si el inmueble está presente en la opción.
        if (inmueble.isPresent()) {
            // Devuelve una respuesta con el inmueble encontrado y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(inmueble.get(), HttpStatus.OK);
        } else {
            // Devuelve una respuesta con el inmueble nulo y un código de estado HTTP 404 (No encontrado).
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        // Registra un mensaje de error en el sistema de registro.
        logger.error("Excepción inesperada al obtener un inmueble", e);

        // Devuelve una respuesta con el inmueble nulo y un código de estado HTTP 500 (Error interno del servidor).
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> deleteInmueble(@PathVariable Long id) { //Metodo que maneja las solicitudes HTTP DELETE para eliminar un inmueble por su ID. Utiliza inmuebleService.delete(id) para eliminar el inmueble. Devuelve una respuesta con un mensaje indicando el resultado y un codigo de estado HTTP
        // Registra un mensaje de información en el sistema de registro.
    logger.info(">getInmueble id_inmueble: " + id);

    // Usamos try catch para el manejo de excepciones en caso de error al eliminar el inmueble.
    try {
        // Intenta eliminar el inmueble del servicio utilizando su ID.
        inmuebleService.delete(id);
    } catch (Exception e) {
        // Registra un mensaje de error en el sistema de registro.
        logger.error("Excepción inesperada al obtener un inmueble", e);

        // Devuelve una respuesta con un mensaje de error y un código de estado HTTP 500 (Error interno del servidor).
        return new ResponseEntity<>("Error al eliminar el inmueble", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Devuelve una respuesta con un mensaje indicando el éxito y un código de estado HTTP 200 (OK).
    return new ResponseEntity<>("Inmueble eliminado exitosamente", HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inmueble> updateInmueble(@PathVariable Long id, @RequestBody Inmueble inmueble) { //Metodo que maneja las solicitudes HTTP PUT para actualizar un inmueble por su ID. Utiliza inmuebleService.getInmueble(id) para obtener el inmueble existente , actualiza sus propiedades y lo guarda. Devuelve una respuesta con el inmueble actualizado o un codigo de estado HTTP de "no encontrado"
        // Registra un mensaje de información en el sistema de registro.
    logger.info(">updateInmueble id_inmueble: " + id);

    // Usamos try catch para el manejo de excepciones en caso de error al actualizar el inmueble.
    try {
        // Intenta obtener el inmueble existente del servicio utilizando su ID.
        Optional<Inmueble> existingInmueble = inmuebleService.getInmueble(id);

        // Verifica si el inmueble existente está presente en la opción.
        if (existingInmueble.isPresent()) {
            // Obtiene el inmueble existente.
            Inmueble updatedInmueble = existingInmueble.get();

            // Actualiza las propiedades del inmueble con los valores proporcionados en el cuerpo de la solicitud.
            updatedInmueble.setInmuebles(inmueble.getInmuebles());
            updatedInmueble.setDireccion(inmueble.getDireccion());
            // Puedes agregar líneas adicionales para actualizar otros campos según sea necesario.

            // Guarda el inmueble actualizado en la base de datos.
            inmuebleService.saveOrUpdate(updatedInmueble);

            // Devuelve una respuesta con el inmueble actualizado y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(updatedInmueble, HttpStatus.OK);
        } else {
            // Devuelve una respuesta con el inmueble nulo y un código de estado HTTP 404 (No encontrado)
            // si el inmueble existente no está presente.
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        // Registra un mensaje de error en el sistema de registro.
        logger.error("Excepción inesperada al actualizar un inmueble", e);

        // Devuelve una respuesta con el inmueble nulo y un código de estado HTTP 500 (Error interno del servidor)
        // en caso de error.
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
