package uni.sgia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.sgia.model.Inmueble;
import uni.sgia.repository.InmuebleRepository;

import java.util.List;
import java.util.Optional;

@Service //Anotacion que indica que esta clase es un servicio
public class InmuebleService{
    @Autowired //Indica que InmuebleRepository debe ser inyectado automaticamente
    InmuebleRepository inmuebleRepository;

    public List<Inmueble> getInmuebles(){ //Metodo que devuleve una lista de todos los inmuebles de la base de datos.
        return inmuebleRepository.findAll();
    }

    public Optional<Inmueble> getInmueble(Long id){ //Metodo que devuelve un inmubele por su id. Para ello utiliza el metodo findById(id) proporcionado por InmuebleRepository
        return inmuebleRepository.findById(id);
    }
    public void saveOrUpdate(Inmueble imb){ //Metodo que guarda o acutaliza un inmueble en la base datos. Para ello utliza el metodo save(imb) proporcionado por InmuebleRepository
        inmuebleRepository.save(imb);
    }
    public void delete(Long id){ //Metodo que elimina un inmubele pro su id. Para ello utiliza el metodo deleteById(id) proporcionado por InmuebleRepository
        inmuebleRepository.deleteById(id);
    }
}
