package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.Rol;

public interface RolRepository extends JpaRepository<Rol, String> {

}
