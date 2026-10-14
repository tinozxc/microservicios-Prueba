package com.usuario.service.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.usuario.service.dto.UsuarioDto;
import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.mapper.UsuarioMapper;
import com.usuario.service.modelos.CarroRequest;
import com.usuario.service.modelos.CarroResponse;
import com.usuario.service.modelos.MotoRequest;
import com.usuario.service.modelos.MotoResponse;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	

	 private final UsuarioRepository usuarioRepository;
	 private final UsuarioMapper usuarioMapper;
	 private final RestTemplate restTemplate;
	 private final CarroFeignClient carroFeign;
	 private final MotoFeignClient motoFeign;
	 
	 
		/*
		 * //restTemplate (ANTIGUO) public List<CarroRequest> getCarros(Long usuarioId){
		 * List<CarroRequest> carros =
		 * restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId,
		 * List.class ); return carros;
		 * 
		 * }
		 * 
		 * public List<MotoRequest> getMotos (Long usuarioId){ List<MotoRequest> motos =
		 * restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId,
		 * List.class); return motos;
		 * 
		 * }
		 */
	 
	 //termina aqui el RestTemplate ----------------------------------
	 
	 
	 //--------------------------------------------------------------
	 
	 //  OpenFeignt(se usa mucho aun) , tambien el webClient(MODERNO y USADO)
	 
	
	 public CarroResponse saveCarro(Long usuarioId , CarroRequest carro) { 
		 
		 if(!usuarioRepository.existsById(usuarioId)) { 
			 throw new ResponseStatusException
			 (HttpStatus.NOT_FOUND , "el usuario no fue encontrado");
		 }
		 carro.setUsuarioId(usuarioId);
		 return carroFeign.save(carro);
		 
	 }
	 
	 public MotoResponse saveMoto(Long usuarioId , MotoRequest  moto) { 
		 
		 if(!usuarioRepository.existsById(usuarioId)) { 
			 throw new ResponseStatusException
			 (HttpStatus.NOT_FOUND , "el usuario con su id: " + usuarioId + "no fue encontrado");
		 }
		 
		 moto.setUsuarioId(usuarioId);
		 return motoFeign.saveMoto(moto);
		 
		
		 
	 }
	 
	 
	 
	 
	 public Map<String, Object> getUsuarioAndVehiculo(Long usuarioId){
		 
		  Map<String, Object> resultado = new HashMap<>();
		  UsuarioDto usuario = usuarioRepository.findById(usuarioId)
				  .map(usuarioMapper::toDto)
				  .orElseThrow( () ->
				  new NoSuchElementException("el usuario con id: " + usuarioId + "no fue encontrado") );
		
		  
		  resultado.put("Usuario", usuario);
		  
		  List<CarroResponse> carros = carroFeign.obtenerCarroPorUsuarioId(usuarioId);	
		  resultado.put("carros", carros);
		  if (carros.isEmpty()) {
		        resultado.put("mensajeCarros", "La lista de carros está vacía");
		    }

		  
		  List<MotoResponse> motos = motoFeign.obtenerMotoPorUsuarioId(usuarioId);
		  resultado.put("motos", motos);
		  if (motos.isEmpty()) {
		        resultado.put("mensajeMotos", "La lista de motos está vacía");
		    }
		  
		  return resultado;
	 }
	 
	 
	 //Termina aqui el OpenFeignt ------------------------------
	 
	
	 public UsuarioService( UsuarioRepository usuarioRepository ,
			 UsuarioMapper usuarioMapper , RestTemplate restTemplate , CarroFeignClient
			 carroFeign , MotoFeignClient motoFeing) { 
		  this.usuarioRepository = usuarioRepository;
		  this.usuarioMapper = usuarioMapper;
		  this.restTemplate = restTemplate;
		  this.carroFeign = carroFeign;
		  this.motoFeign = motoFeing;
	 }
	
	public List<UsuarioDto> getAll(){ 
		
		return usuarioMapper.toDtoList(usuarioRepository.findAll());

	}
	
	
	
	public UsuarioDto getUsuarioById(Long id) { 
		
		 return usuarioRepository.findById(id)
				 .map(usuarioMapper::toDto)
				 .orElseThrow( () -> new ResponseStatusException
						 (HttpStatus.NOT_FOUND , "Usuario con id " + id + "no encontrado") );
		
	}
	
	public UsuarioDto crearUsuarioDto(UsuarioDto usuarioDto) { 
		Usuario usuario = usuarioMapper.toEntity(usuarioDto);
		Usuario guardado = usuarioRepository.save(usuario);
		
		return usuarioMapper.toDto(guardado);
		
		
	}
	
	
}
