package br.com.domain.acaipersonalizados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.acaipersonalizados.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clienteRepository;
	
	
}
