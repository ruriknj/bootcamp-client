package com.br.ruriknj.resources.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ruriknj.entities.Client;
import com.br.ruriknj.repositories.ClientRepository;

//registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {

		return repository.findAll();
	}

}
