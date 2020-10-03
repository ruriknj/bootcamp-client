package com.br.ruriknj.resources;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.ruriknj.entities.Client;
import com.br.ruriknj.resources.services.ClientService;

//IMPLEMENTA O CONTROLADOR REST -> Resources
@RestController // transforma a classe no resoucr REST
@RequestMapping(value = "/clients") //
public class ClienteResource {
	
	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {

	/*	List<Client> list = new ArrayList<>();
		Instant birthDate = Instant.parse("1994-07-20T10:30:00Z");

		list.add(new Client(1L, "Maria Silva", "12345678901", 6500.0, birthDate, 3));
	*/
		
		List<Client> list 	= service.findAll();
		return ResponseEntity.ok().body(list);

	}

}
