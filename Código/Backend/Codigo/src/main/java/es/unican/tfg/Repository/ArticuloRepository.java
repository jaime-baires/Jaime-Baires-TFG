package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
