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

    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    public ResponseEntity createCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
    	Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.created(new URI("/categorias/" + nuevaCategoria.getId())).body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
    	Categoria current = categoriaRepository.findById(id).orElseThrow(RuntimeException::new);
    	current.setNombre(categoria.getNombre());

        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable Long id) {
    	categoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
