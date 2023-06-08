package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.unican.tfg.Domain.Factura;
import es.unican.tfg.Repository.FacturaRepository;

@RestController
@RequestMapping("/facturas")
public class FacturasController {

	private final FacturaRepository facturaRepository;

	public FacturasController(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}
	
	@GetMapping
    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Factura getFactura(@PathVariable Long id) {
        return facturaRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    public ResponseEntity createFactura(@RequestBody Factura factura) throws URISyntaxException {
    	Factura facturaGuardada = facturaRepository.save(factura);
        return ResponseEntity.created(new URI("/facturas/" + facturaGuardada.getId())).body(facturaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
    	Factura currentFactura = facturaRepository.findById(id).orElseThrow(RuntimeException::new);
    	currentFactura.setFechaFactura(factura.getFechaFactura());
    	currentFactura.setTotal(factura.getTotal());
    	currentFactura = facturaRepository.save(factura);

        return ResponseEntity.ok(currentFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFactura(@PathVariable Long id) {
    	facturaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
