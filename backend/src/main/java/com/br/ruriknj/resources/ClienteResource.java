package com.br.ruriknj.resources;

import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.ruriknj.dto.ClientDTO;
import com.br.ruriknj.resources.services.ClientService;

//IMPLEMENTA O CONTROLADOR REST -> Resources
@RestController // transforma a classe no resoucr REST
@RequestMapping(value = "/clients") //
public class ClienteResource {

	@Autowired
	private ClientService service;
		/*
		 * List<Client> list = new ArrayList<>(); Instant birthDate =
		 * Instant.parse("1994-07-20T10:30:00Z");
		 * 
		 * list.add(new Client(1L, "Maria Silva", "12345678901", 6500.0, birthDate, 3));
		 */

		@GetMapping
		public ResponseEntity<Page<ClientDTO>> findAll(
				
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
				
		
				) {
					
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			
			Page<ClientDTO> list = service.findAllPaged(pageRequest);

			return ResponseEntity.ok().body(list);

		}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);

	}

	@PostMapping
	public ResponseEntity<ClientDTO> salvar(@RequestBody ClientDTO dto) {
		dto = service.salvar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> atualizar(@PathVariable Long id, @RequestBody ClientDTO dto) {
		dto = service.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> retirar(@PathVariable Long id) {
		service.retirar(id);
		return ResponseEntity.noContent().build();
	}

}
