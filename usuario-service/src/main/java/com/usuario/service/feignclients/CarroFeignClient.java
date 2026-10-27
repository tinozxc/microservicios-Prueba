package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.usuario.service.modelos.CarroRequest;
import com.usuario.service.modelos.CarroResponse;

@FeignClient(name = "carro-service"  , path = "/carro")
public interface CarroFeignClient {

	@PostMapping()
	public CarroResponse save(@RequestBody CarroRequest carro);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<CarroResponse> obtenerCarroPorUsuarioId(@PathVariable("usuarioId") Long usuarioId);
	
	
}
