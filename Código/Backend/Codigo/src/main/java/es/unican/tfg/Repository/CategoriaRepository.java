package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
