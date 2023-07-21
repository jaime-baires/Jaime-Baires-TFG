package es.unican.tfg.Controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import es.unican.tfg.Domain.*;
import es.unican.tfg.Repository.*;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	private final RolRepository rolRepository;
	
	public RolController(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	
	@GetMapping
	public List<Rol> getRoles() {
		return rolRepository.findAll();
	}

}
