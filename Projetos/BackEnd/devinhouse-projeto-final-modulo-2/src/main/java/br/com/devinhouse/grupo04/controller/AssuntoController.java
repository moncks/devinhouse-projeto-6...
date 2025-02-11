package br.com.devinhouse.grupo04.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.grupo04.dto.AssuntoDTOInput;
import br.com.devinhouse.grupo04.dto.AssuntoDTOOutput;
import br.com.devinhouse.grupo04.entity.Assunto;
import br.com.devinhouse.grupo04.mapper.AssuntoMapper;
import br.com.devinhouse.grupo04.service.AssuntoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "v1" + "/assuntos")
@Api(value="API Rest Assuntos")
public class AssuntoController {
	
	@Autowired
	private AssuntoMapper assuntoMapper;
	
	@Autowired
	private AssuntoService service;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value="Retorna todos os assuntos salvos")
	public List<AssuntoDTOOutput> findAll() {
		List<Assunto> assuntos = service.findAll();
		
		return assuntoMapper.toDto(assuntos);
	}
	
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value="Retorna o assunto do id informado")
	public AssuntoDTOOutput find(@PathVariable Long id) {
		return assuntoMapper.toDto(service.find(id));
	}
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value="Cria um novo assunto")
	public AssuntoDTOOutput create(@Valid @RequestBody AssuntoDTOInput assuntoDTO) {
		Assunto assunto = service.create(assuntoMapper.toAssunto(assuntoDTO));
		
		return assuntoMapper.toDto(assunto);
	}
	
	@PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value="Atualiza o assunto desejado")
	public void update(@PathVariable Long id, @RequestBody AssuntoDTOInput assuntoDTO) {
		service.update(id, assuntoMapper.toAssunto(assuntoDTO));
	}
	
	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value="Deleta o assunto desejado")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
 