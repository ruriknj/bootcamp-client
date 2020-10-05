package com.br.ruriknj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.ruriknj.entities.Client;

//CAMADA DE ACESSO A DADOS

public interface ClientRepository extends JpaRepository<Client, Long> {
	

}
