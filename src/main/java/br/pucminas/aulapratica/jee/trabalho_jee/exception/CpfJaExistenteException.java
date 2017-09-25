package br.pucminas.aulapratica.jee.trabalho_jee.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class CpfJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = -1816600114543260835L;

	@Override
	public String getMessage() {
		return "CPF informado já está presente na base de dados.";
	}

	
}
