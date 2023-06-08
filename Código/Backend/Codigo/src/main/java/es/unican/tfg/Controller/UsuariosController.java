package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.unican.tfg.Domain.*;
import es.unican.tfg.Repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private final UsuarioRepository usuarioRepository;

	public UsuariosController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping
    public List<Usuario> getArticulos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
    	Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return ResponseEntity.created(new URI("/usuarios/" + usuarioGuardado.getId())).body(usuarioGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    	Usuario currentUsuario = usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    	currentUsuario.setNombre(usuario.getNombre());
    	currentUsuario.setUsuario(usuario.getUsuario());
    	currentUsuario.setContraseña(usuario.getContraseña());
    	currentUsuario.setEsAdmin(usuario.isEsAdmin());
    	currentUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(currentUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
    	usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
