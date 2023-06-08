package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
