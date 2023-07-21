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
    public ResponseEntity<Articulo> getArticulo(@PathVariable Long id) {
    	Articulo articulo;
    	try {
    		articulo = articuloRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(404).build();
    	}
        return ResponseEntity.ok(articulo);
    }
    
    @PostMapping
    public ResponseEntity<Articulo> createArticulo(@RequestBody Articulo articulo) throws URISyntaxException {
    	Articulo articuloGuardado = articuloRepository.save(articulo);
        return ResponseEntity.created(new URI("/articulos/" + articuloGuardado.getId())).body(articuloGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> updateArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
    	Articulo currentArticulo;
    	try {
    		currentArticulo = articuloRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(404).build();
    	}
    	currentArticulo.setDescripcion(articulo.getDescripcion());
    	currentArticulo.setFechaAlta(articulo.getFechaAlta());
    	currentArticulo.setPrecio(articulo.getPrecio());
    	currentArticulo.setStock(articulo.getStock());
    	currentArticulo.setStockSeguridad(articulo.getStockSeguridad());
    	currentArticulo.setDescuento(articulo.getDescuento());
    	currentArticulo.setUnidad(articulo.getUnidad());
    	currentArticulo.setIva(articulo.getIva());
    	currentArticulo = articuloRepository.save(articulo);

        return ResponseEntity.ok(currentArticulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticulo(@PathVariable Long id) {
    	try {
    		Articulo articulo = articuloRepository.findById(id).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(400).build();
    	}
    	articuloRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
