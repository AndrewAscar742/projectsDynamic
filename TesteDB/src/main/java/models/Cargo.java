package models;

public enum Cargo {
	ANALISTA("Analista"), PROGRAMADOR("Programador"), GER_PROJETO("Gerente de Projeto");

	protected String nome = "";

	Cargo(String nome) {
		this.nome = nome;
	}

	public String retornarCargo(Cargo text) {
		
		if (text == Cargo.ANALISTA) 
			return "ANALISTA";
		else
			if (text == Cargo.PROGRAMADOR)
				return "PROGRAMADOR";
		else
				return "GER_PROJETO";
	}
}
