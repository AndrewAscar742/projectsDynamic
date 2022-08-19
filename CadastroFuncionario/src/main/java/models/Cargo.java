package models;

public enum Cargo {
	ANALISTA("Analista"), PROGRAMADOR("Programador"), GER_PROJETO("Gerente de Projeto");
	
	protected String nome = "";
	Cargo(String nome) {
		this.nome = nome;
	}
}
