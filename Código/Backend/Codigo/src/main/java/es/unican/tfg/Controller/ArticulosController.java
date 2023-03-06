package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.unican.tfg.Domain.Articulo;
import es.unican.tfg.Repository.ArticuloRepository;

@RestController
@RequestMapping("/articulos")
public class ArticulosController {
	
	private final ArticuloRepository articuloRepository;

	public ArticulosController(ArticuloRepository articuloRepository) {
		this.articuloRepository = articuloRepository;
	}
	
	@GetMapping
    public List<Articulo> getArticulos() {
        return articuloRepository.findAll();
    }

    @GetMapping("/{id}")
    public Articulo getArticulo(@PathVariable Long id) {
        return articuloRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    public ResponseEntity createArticulo(@RequestBody Articulo articulo) throws URISyntaxException {
    	Articulo articuloGuardado = articuloRepository.save(articulo);
        return ResponseEntity.created(new URI("/articulos/" + articuloGuardado.getId())).body(articuloGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
    	Articulo currentArticulo = articuloRepository.findById(id).orElseThrow(RuntimeException::new);
    	currentArticulo.setDescripcion(articulo.getDescripcion());
    	currentArticulo.setFechaAlta(articulo.getFechaAlta());
    	currentArticulo.setPrecio(articulo.getPrecio());
    	currentArticulo.setStock(articulo.getStock());
    	currentArticulo.setStockSeguridad(articulo.getStockSeguridad());
    	currentArticulo.setDescuento(articulo.getDescuento());
    	currentArticulo.setIVA(articulo.getIVA());
    	currentArticulo = articuloRepository.save(articulo);

        return ResponseEntity.ok(currentArticulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticulo(@PathVariable Long id) {
    	articuloRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
