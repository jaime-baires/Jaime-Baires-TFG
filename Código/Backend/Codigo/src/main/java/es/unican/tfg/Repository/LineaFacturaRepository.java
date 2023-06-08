package es.unican.tfg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.tfg.Domain.LineaFactura;

public interface LineaFacturaRepository extends JpaRepository<LineaFactura, Long> {

}
