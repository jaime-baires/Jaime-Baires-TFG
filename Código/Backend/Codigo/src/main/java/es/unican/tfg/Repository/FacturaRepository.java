package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
