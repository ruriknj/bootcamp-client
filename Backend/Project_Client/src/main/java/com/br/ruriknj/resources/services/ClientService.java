package com.br.ruriknj.resources.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.ruriknj.dto.ClientDTO;
import com.br.ruriknj.entities.Client;
import com.br.ruriknj.repositories.ClientRepository;
import com.br.ruriknj.resources.services.exceptions.EntityNotFoundException;

//registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	// Usando // Usando @Transactional(readOnly = true) e
	// spring.jpa.open-in-view=false
	// garante que tudo relacionado com banco de dados e JPA ficará na camada de
	// serviço.
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll(); // Entidades não se relaciona com DTO

		List<ClientDTO> listDTO = new ArrayList<>();
		for (Client cat : list) {
			listDTO.add(new ClientDTO(cat));
		}
		return listDTO; // passou a list do Client para o ClientDTO
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id); // optional -> evita trabalhar com valor nulo
		Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
		return new ClientDTO(entity);
	}

}
