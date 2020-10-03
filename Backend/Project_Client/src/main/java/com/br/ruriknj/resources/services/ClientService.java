package com.br.ruriknj.resources.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.ruriknj.entities.Client;
import com.br.ruriknj.repositories.ClientRepository;

//registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)  
	// Usando  // Usando @Transactional(readOnly = true) e spring.jpa.open-in-view=false
	// garante que tudo relacionado com banco de dados e JPA ficará na camada de serviço.
	
	public List<Client> findAll() {

		return repository.findAll();
	}

}
