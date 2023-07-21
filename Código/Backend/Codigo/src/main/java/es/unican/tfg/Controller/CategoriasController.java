package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.unican.tfg.Domain.Categoria;
import es.unican.tfg.Repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	
	private final CategoriaRepository categoriaRepository;

	public CategoriasController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@GetMapping
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable String nombre) {
    	Categoria categoria;
    	try {
    		categoria = categoriaRepository.findById(nombre).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(404).build();
    	}
        return ResponseEntity.ok(categoria);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
    	Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.created(new URI("/categorias/" + nuevaCategoria.getNombre())).body(nuevaCategoria);
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable String nombre, @RequestBody Categoria categoria) {
    	Categoria current = categoriaRepository.findById(nombre).get();
    	if (current == null) {
    		return ResponseEntity.status(400).build();
    	}
    	current.setNombre(categoria.getNombre());

        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity deleteCategoria(@PathVariable String nombre) {
    	try {
    		Categoria current = categoriaRepository.findById(nombre).get();
    	} catch (Exception e) {
    		return ResponseEntity.status(400).build();
    	}
    	
    	categoriaRepository.deleteById(nombre);
        return ResponseEntity.ok().build();
    }

}
