package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.unican.tfg.Domain.LineaFactura;
import es.unican.tfg.Repository.LineaFacturaRepository;

@RestController
@RequestMapping("/facturas/{id}")
public class LineasFacturaController {
	
	private final LineaFacturaRepository lineaFacturaRepository;

	public LineasFacturaController(LineaFacturaRepository lineaFacturaRepository) {
		this.lineaFacturaRepository = lineaFacturaRepository;
	}

    @GetMapping("/{idLinea}")
    public LineaFactura getLineaFactura(@PathVariable("id") Long id, @PathVariable("idLinea") Long idLinea) {
        return lineaFacturaRepository.findById(idLinea).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    public ResponseEntity createLineaFactura(@PathVariable("id") Long id, @RequestBody LineaFactura lineafactura) throws URISyntaxException {
    	LineaFactura lineafacturaGuardada = lineaFacturaRepository.save(lineafactura);
        return ResponseEntity.created(new URI("/facturas/" + lineafacturaGuardada.getFactura().getId() + "/"
    	+ lineafacturaGuardada.getId())).body(lineafacturaGuardada);
    }

    @PutMapping("/{idLinea}")
    public ResponseEntity updateLineaFactura(@PathVariable("id") Long id, @PathVariable("idLinea") Long idLinea, @RequestBody LineaFactura lineafactura) {
    	LineaFactura currentLineaFactura = lineaFacturaRepository.findById(idLinea).orElseThrow(RuntimeException::new);
    	currentLineaFactura.setArticulo(lineafactura.getArticulo());
    	currentLineaFactura.setCantidad(lineafactura.getCantidad());
    	currentLineaFactura.setPrecio(lineafactura.getPrecio());
    	currentLineaFactura.setDescuento(lineafactura.getDescuento());
    	currentLineaFactura.setImporte(lineafactura.getImporte());
    	currentLineaFactura.setIva(lineafactura.getIva());
    	currentLineaFactura = lineaFacturaRepository.save(lineafactura);

        return ResponseEntity.ok(currentLineaFactura);
    }

    @DeleteMapping("/{idLinea}")
    public ResponseEntity deleteLineaFactura(@PathVariable("id") Long id, @PathVariable("idLinea") Long idLinea) {
    	lineaFacturaRepository.deleteById(idLinea);
        return ResponseEntity.ok().build();
    }

}
