package com.usuario.service.controlador;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.usuario.service.dto.UsuarioDto;
import com.usuario.service.entidades.Usuario;
import com.usuario.service.mapper.UsuarioMapper;
import com.usuario.service.modelos.CarroRequest;
import com.usuario.service.modelos.CarroResponse;
import com.usuario.service.modelos.MotoRequest;
import com.usuario.service.modelos.MotoResponse;
import com.usuario.service.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	 private final UsuarioService usuarioService;
	 private final UsuarioMapper usuarioMapper;
	
	public UsuarioController(UsuarioService usuarioService , UsuarioMapper usuarioMapper) { 
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
	}
	
	 @GetMapping()
	 public ResponseEntity<List<UsuarioDto>> listarUsuarios(){ 
		 List<UsuarioDto> listUsuarioDto = usuarioService.getAll();
		 if(listUsuarioDto.isEmpty()) { 
			 return ResponseEntity.noContent().build();
		 }
		 return ResponseEntity.ok(listUsuarioDto);
		 
		 
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable Long id) {
		 
		 return ResponseEntity.ok(usuarioService.getUsuarioById(id));
		  
	 }
	 
	 @PostMapping()
	 public ResponseEntity<UsuarioDto> crearUsuario (@RequestBody UsuarioDto usuario){ 

		 UsuarioDto usuarioDto = usuarioService.crearUsuarioDto(usuario);
		 
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(usuarioDto.getId())
				 .toUri();
		 
		 return ResponseEntity.created(location).body(usuarioDto);
	 }
	 
	
		/*
		 * @GetMapping("/carros/{usuarioId}") public ResponseEntity<List <CarroRequest>
		 * > getCarros(@PathVariable("usuarioId") Long id){ UsuarioDto usuario =
		 * usuarioService.getUsuarioById(id);
		 * 
		 * if(usuario == null) { return ResponseEntity.notFound().build(); }
		 * 
		 * List<CarroRequest> carros = usuarioService.getCarros(id); return
		 * ResponseEntity.ok(carros); }
		 * 
		 * @GetMapping("/motos/{usuarioId}") public ResponseEntity<List <MotoRequest> >
		 * getMotos(@PathVariable("usuarioId") Long id){ UsuarioDto usuario =
		 * usuarioService.getUsuarioById(id);
		 * 
		 * if(usuario == null) { return ResponseEntity.notFound().build(); }
		 * 
		 * return ResponseEntity.ok(usuarioService.getMotos(id)); }
		 */
	 
		@PostMapping("/carro/{usuarioId}")
		 public ResponseEntity<CarroResponse> guardarCarro(@PathVariable("usuarioId") Long usuarioId
				 , @RequestBody CarroRequest request ){ 
		
			CarroResponse carroCreado = usuarioService.saveCarro(usuarioId, request);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")				
					.buildAndExpand(carroCreado.getId())
					.toUri();
			
			return ResponseEntity.created(location).body(carroCreado);
		}
		
		@PostMapping("/moto/{usuarioId}")
		public ResponseEntity<MotoResponse> guardarMoto(@PathVariable("usuarioId") Long usuarioId ,
				@RequestBody MotoRequest request){ 
			
			MotoResponse moto =  usuarioService.saveMoto(usuarioId , request);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(moto.getId())
					.toUri();
			
			return ResponseEntity.created(location).body(moto);
			
		}
		
		@GetMapping("/todos/{usuarioId}")
		public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(
				@PathVariable("usuarioId") Long idUsuario){ 
			
			return ResponseEntity.ok(usuarioService.getUsuarioAndVehiculo(idUsuario));
			
		}
		
		
	
}


