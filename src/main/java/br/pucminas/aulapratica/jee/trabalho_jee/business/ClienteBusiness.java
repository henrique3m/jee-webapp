package br.pucminas.aulapratica.jee.trabalho_jee.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.pucminas.aulapratica.jee.trabalho_jee.entity.ClienteEntity;
import br.pucminas.aulapratica.jee.trabalho_jee.exception.CpfJaExistenteException;
import br.pucminas.aulapratica.jee.trabalho_jee.repository.ClienteRepository;
import br.pucminas.aulapratica.jee.trabalho_jee.resource.ClienteResource;

@Stateless
public class ClienteBusiness {

	@Inject
	private ClienteRepository clienteRepository;
	
	public void salvarCliente(ClienteResource clienteResource) throws CpfJaExistenteException{
		ClienteEntity cliente = convertToEntity(clienteResource);
		
		List<ClienteEntity> listaClientesMesmoCpf = clienteRepository.buscarClientePorCpf(clienteResource.getCpf());
		if(listaClientesMesmoCpf != null && listaClientesMesmoCpf.size() > 0){
			throw new CpfJaExistenteException();
		}
		
		clienteRepository.salvar(cliente);
	}
	
	public List<ClienteResource> listarClientes(){
		List<ClienteResource> listaClientes = new ArrayList<>();
		List<ClienteEntity> todosClientes = clienteRepository.getTodosClientes();
		for (ClienteEntity clienteEntity : todosClientes) {
			ClienteResource resource = new ClienteResource();
			resource = convertToResource(clienteEntity);
			listaClientes.add(resource);
		}
		return listaClientes;
	}
	
	private ClienteEntity convertToEntity(ClienteResource resource){
		ClienteEntity entity = new ClienteEntity();
		entity.setCpf(resource.getCpf());
		entity.setDataNascimento(resource.getDataNascimento());
		entity.setEmail(resource.getEmail());
		entity.setNome(resource.getNome());
		entity.setId(resource.getId());
		return entity;
	}
	
	private ClienteResource convertToResource(ClienteEntity entity){
		ClienteResource resource = new ClienteResource();
		resource.setCpf(entity.getCpf());
		resource.setDataNascimento(entity.getDataNascimento());
		resource.setEmail(entity.getEmail());
		resource.setNome(entity.getNome());
		resource.setId(entity.getId());
		return resource;
	}
}
