package com.br.ruriknj.resources;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ruriknj.entities.Client;

@RestController // transforma a classe no resoucr REST
@RequestMapping(value = "/clients") //
public class ClienteResource {
	
	Instant birthDate= Instant.parse("1994-07-20T10:30:00Z");
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {

		List<Client> list = new ArrayList<>();

		list.add(new Client(1L, "Maria Silva", "12345678901", 6500.0, birthDate, 3));
		
		//list.add(new Client(2L, name, cpf, income, birthDate));

		return ResponseEntity.ok().body(list);


	}

}