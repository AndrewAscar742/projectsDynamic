package models;

import validar.ValidarCpf;

public class Funcionario {
	private String nome;
	private String cpf;
	private Cargo cargo;

	public Funcionario(String nome, String cpf, Cargo cargo) {
		setNome(nome);
		setCpf(cpf);
		setCargo(cargo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		try {
			if(ValidarCpf.validarCPF(cpf) == 0)
				this.cpf = cpf;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCargoString() {
		return cargo.nome;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ Nome: " + getNome() + " | CPF: " + getCpf() + " | Profissão: " + getCargoString() + "] \n";
	}
	
	@Override
	public boolean equals(Object obj) {
		Funcionario fun = (Funcionario) obj;
		
		if (this.cpf.equals(fun.getCpf())) 
			return true;
		else 
			return false;
		
	}
}
