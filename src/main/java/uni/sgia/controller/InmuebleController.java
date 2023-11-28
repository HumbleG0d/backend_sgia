package uni.sgia.controller;

import uni.sgia.service.InmuebleService;
import uni.sgia.model.Inmueble;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/inmuebles")
public class InmuebleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InmuebleService inmuebleService;

    @RequestMapping(value= "/list" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Inmueble>> getInmuebles(){
        logger.info("> getInmuebles[Inmueble]");
        List<Inmueble> list = null;

        try{
            list = inmuebleService.getInmuebles();
            if(list == null) list = new ArrayList<>();
        }
        catch(Exception e){
            logger.error("Excepcion inesperada al obtener la lista de personas",e);
            return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> getPersonas[Persona]");        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Inmueble> add(@RequestBody Inmueble inmueble){
        logger.info(" >agregar: " + inmueble.toString());

        try{
            inmuebleService.saveOrUpdate(inmueble);
        }
        catch(Exception e){
            logger.error("Excepcion inesperada al agregar inmuble",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(inmueble , HttpStatus.OK);
    }
}
