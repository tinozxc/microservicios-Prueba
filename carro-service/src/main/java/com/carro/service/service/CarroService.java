package com.carro.service.service;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.carro.service.dto.CarroDto;
import com.carro.service.dto.CreateCarroDto;
import com.carro.service.entidades.Carro;
import com.carro.service.mapper.CarroMapper;
import com.carro.service.repository.CarroRepository;


@Service
public class CarroService {

	
	private final CarroRepository carroRepository;
	private final CarroMapper carroMapper;
	
	public CarroService(CarroRepository carroRepository , CarroMapper carroMapper) { 
		this.carroRepository = carroRepository;
		this.carroMapper =  carroMapper;
	}
	
	 public List<CarroDto> getAll(){ 
		
		  return  carroMapper.toDtoList(carroRepository.findAll());

	}
	
	
	
	public CarroDto getCarroById(Long id) { 
		
		 return carroRepository.findById(id)
				 .map(carroMapper::ToDto)
				 .orElseThrow( () ->
				 new ResponseStatusException(HttpStatus.NOT_FOUND ,
						 "carro con el id: " + id +  "no fue encontrado") );
	}
	
	public CarroDto crearCarroDto(CreateCarroDto createCarroDto) { 
	
		Carro carroEntity = carroMapper.ToEntity(createCarroDto);
		Carro guardar = carroRepository.save(carroEntity);
		return carroMapper.ToDto(guardar);
		
	}
	
	public List<CarroDto> listarPorUsuario(Long id){ 
		return carroMapper.toDtoList(carroRepository.findByUsuarioId(id));
	}
	
}
