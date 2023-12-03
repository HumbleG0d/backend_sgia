package uni.sgia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.sgia.model.Inmueble;
@Repository //Indica que  esta interfaz es un componente de repositorio.
public interface InmuebleRepository  extends JpaRepository<Inmueble , Long> { //Interfaz que proporciona metodos para realizar operaciones CRUD.Esta interfaz extiende de JPpaRepsitory y especifica dos parametros : Inmubele -> que es el tipo de entidad con la que trabaja el repositorio  y Long -> es el tipo del campo de clave primaria de la entidad.
}
