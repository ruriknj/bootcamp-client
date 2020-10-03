package com.br.ruriknj.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ruriknj.dto.ClientDTO;
import com.br.ruriknj.resources.services.ClientService;

//IMPLEMENTA O CONTROLADOR REST -> Resources
@RestController // transforma a classe no resoucr REST
@RequestMapping(value = "/clients") //
public class ClienteResource {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {

	/*	List<Client> list = new ArrayList<>();
		Instant birthDate = Instant.parse("1994-07-20T10:30:00Z");

		list.add(new Client(1L, "Maria Silva", "12345678901", 6500.0, birthDate, 3));
	*/
		
		List<ClientDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);

	}


}
