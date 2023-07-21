package es.unican.tfg.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
		Usuario usuario;
		try  {
			usuario = usuarioRepository.findById(id).get();
		} catch (Exception e) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(usuario);	
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
		String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		return ResponseEntity.created(new URI("/usuarios/" + usuarioGuardado.getId())).body(usuarioGuardado);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) throws URISyntaxException {
		List<Usuario> lista = usuarioRepository.findAll();
		Usuario usuarioRecuperado = null;
		for (Usuario u: lista) {
			if (u.getUsuario().equals(usuario.getUsuario())) {
				System.out.println(u.getPassword());
				BCryptPasswordEncoder temp = new BCryptPasswordEncoder();
				if (temp.matches(usuario.getPassword(), u.getPassword())) {
					usuarioRecuperado = u;
				}
			}
		}
		if (usuarioRecuperado != null) {
			return ResponseEntity.ok(usuarioRecuperado);
		} else {
			return ResponseEntity.status(401).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario currentUsuario;
		try {
			currentUsuario = usuarioRepository.findById(id).get();
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
		currentUsuario.setNombre(usuario.getNombre());
		currentUsuario.setUsuario(usuario.getUsuario());
		String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
		currentUsuario.setPassword(encodedPassword);
		//System.out.println("encoded===> "+encodedPassword);
		//System.out.println("prueba====> "+usuario.getPassword());
		//currentUsuario.setContraseña(usuario.getContraseña());
		currentUsuario.setRol(usuario.getRol());
		currentUsuario = usuarioRepository.save(currentUsuario);

		return ResponseEntity.ok(currentUsuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUsuario(@PathVariable Long id) {
		try {
			Usuario currentUsuario = usuarioRepository.findById(id).get();
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
