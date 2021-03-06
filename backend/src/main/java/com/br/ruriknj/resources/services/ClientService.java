package com.br.ruriknj.resources.services;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.ruriknj.dto.ClientDTO;
import com.br.ruriknj.entities.Client;
import com.br.ruriknj.repositories.ClientRepository;
import com.br.ruriknj.resources.services.exceptions.DatabaseException;
import com.br.ruriknj.resources.services.exceptions.ResourceNotFoundException;

//registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	// Usando // Usando @Transactional(readOnly = true) e
	// spring.jpa.open-in-view=false
	// garante que tudo relacionado com banco de dados e JPA ficará na camada de
	// serviço.
	
	@Transactional(readOnly = true) // usa-se para o metodo de somente leitura ->( readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(X -> new ClientDTO(X)); // passou a list do Client para o ClientDTO
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id); // optional -> evita trabalhar com valor nulo
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO salvar(ClientDTO dto) {
		
	//	final Instant UpdatebirthDate =  Instant.now();
	
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO atualizar(Long id, ClientDTO dto) {
		// Atualizar sem precisar ir no banco de dados -> getOne(id)
		try {
			Client entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			return new ClientDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}

		
	}

	public void retirar(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de inegridade do banco de dados");
		}
	}

}
