package com.usuario.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.usuario.service.dto.UsuarioDto;
import com.usuario.service.entidades.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	//ejemplo uno es una entidad y el otro es una entidadDTO pero el nombre es diferente
	
	//@Mapping(source = "telefono", target = "telefonoContacto")
	//@Mapping(source = "telefonoContacto", target = "telefono")
	
	UsuarioDto toDto(Usuario usuarioEntity);
	Usuario toEntity(UsuarioDto usuarioDto);
	
	List<UsuarioDto> toDtoList ( List<Usuario> usuario);
	

	
}
