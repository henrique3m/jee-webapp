package br.pucminas.aulapratica.jee.trabalho_jee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.pucminas.aulapratica.jee.trabalho_jee.business.ClienteBusiness;
import br.pucminas.aulapratica.jee.trabalho_jee.exception.CpfJaExistenteException;
import br.pucminas.aulapratica.jee.trabalho_jee.resource.ClienteResource;

@Model
public class ClienteBean {

	@EJB
	private ClienteBusiness clienteBusiness;

	private ClienteResource clienteResource = new ClienteResource();
	private List<ClienteResource> clientes = new ArrayList<>();

	public List<ClienteResource> getClientes() {
		return clientes;
	}

	public ClienteResource getClienteResource() {
		return clienteResource;
	}

	public void setClienteResource(ClienteResource clienteResource) {
		this.clienteResource = clienteResource;
	}

	public String listarClientes() {
		clientes = clienteBusiness.listarClientes();
		return "clienteListagem";

	}

	public void salvarCliente() {
		try {
			clienteBusiness.salvarCliente(clienteResource);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente inserido com sucesso!", "");
			FacesContext.getCurrentInstance().addMessage("formCliente:messages", message);
		} catch (CpfJaExistenteException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("formCliente:messages", message);
		}
	}

}
