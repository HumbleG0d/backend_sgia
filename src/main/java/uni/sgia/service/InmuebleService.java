package uni.sgia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.sgia.model.Inmueble;
import uni.sgia.respository.InmuebleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InmuebleService{
    @Autowired
    InmuebleRepository inmuebleRepository;

    public List<Inmueble> getInmueble(){
        return inmuebleRepository.findAll();
    }

    public Optional<Inmueble> getInmueble(String id){
        return inmuebleRepository.findById(id);
    }
    public void saveOrUpdate(Inmueble imb){
        inmuebleRepository.save(imb);
    }
    public void delete(String id){
        inmuebleRepository.deleteById(id);
    }
}
