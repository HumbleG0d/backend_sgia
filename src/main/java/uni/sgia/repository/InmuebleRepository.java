package uni.sgia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.sgia.model.Inmueble;
@Repository
public interface InmuebleRepository  extends JpaRepository<Inmueble , Long> {
}
