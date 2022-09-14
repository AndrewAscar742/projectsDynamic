package models;

public class ValidarCpf {
	private static String devolverCPF(String parteCPF) {
		String cpf = parteCPF;
		cpf = cpf.replace(".", "").replace("-", "");
		return cpf;
	}
	
	public static int validarCPF(String parteCPF) throws Exception {
		//Variáveis
		int somaTotal = 0;
		boolean dif = false;
		String cpf = devolverCPF(parteCPF);
		
		//Validação
		if (cpf.length() > 11) 
			throw new Exception("Seu CPF contém mais de 11 digitos");
		
		
		if (cpf.length() < 11)
			throw new Exception("Seu CPF contém menos de 11 digitos");
		
		
		for (int i = 0; i < cpf.length() - 1; i++) {
			if (cpf.charAt(i) != cpf.charAt(i + 1)) {
				dif = true;
				break;
			}
		}
		
		if(dif == false)
			throw new Exception("CPF Inválido, todos os digitos são iguais");
		
		//Calculando CPF
		int cont = cpf.length() + 1; //contador
		for (int i = 0; i < cpf.length(); i++) {
			char c = cpf.charAt(i);
			String s = String.valueOf(c);
			int ii = Integer.parseInt(s);
			
			somaTotal += ii * cont;
			cont--;
		}
		
		int resto = somaTotal % 11;
		if (resto < 2)
			return 0;
		else
			return 11 - resto;
	}
}
