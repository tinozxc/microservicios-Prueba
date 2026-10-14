package com.moto.service.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.moto.service.dto.CreateMotoDto;
import com.moto.service.dto.MotoDto;
import com.moto.service.entidades.Moto;
import com.moto.service.mapper.MotoMapper;
import com.moto.service.repositorio.MotoRepository;

@Service
public class MotoService {

	private final MotoRepository motoRepository;
	private final MotoMapper motoMapper;
	
	public MotoService(MotoRepository motoRepository , MotoMapper motoMapper) { 
		this.motoRepository = motoRepository;
		this.motoMapper = motoMapper;
	}
	
	public List<MotoDto> listarMoto(){ 
		 return motoMapper.toDtoList(motoRepository.findAll());
	}
	
	public MotoDto obtenerMotoPorId(Long id) { 
		return motoRepository.findById(id)
				.map(motoMapper::toDto)
				.orElseThrow( () -> new ResponseStatusException
					(HttpStatus.NOT_FOUND , "el usuario con id: " + id + "no fue encontrado"));
	}
	
	public MotoDto crearUnaMoto(CreateMotoDto createMotoDto) { 
		Moto moto = motoMapper.toEntity(createMotoDto);
		Moto guardar = motoRepository.save(moto);
		
		return motoMapper.toDto(guardar);
	}
	
	public List<MotoDto> obtenerPorUsuarioId(Long id){ 

		return motoMapper.toDtoList(motoRepository.findByUsuarioId(id));
	}
	
	
}
