
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cargo;
import models.Funcionario;

@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instâncias e Preparamento de Respostas
		List<Funcionario> fun = new ArrayList<Funcionario>();
		PrintWriter writer = response.getWriter();
		
		//Pegando os parâmetros post
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String cargo = request.getParameter("select");
		
		//Pegando a sessão
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("keyLista");
		
		//Validando se a sessão existe e adicionando
		if (obj == null) {
			fun.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
			session.setAttribute("keyLista", fun);
			
		} else {
			@SuppressWarnings("unchecked")
			List<Funcionario> fun1 = (List<Funcionario>) obj;
			fun = fun1;
			fun.add(new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo)));
			
			//Atribuindo na mesma memória (List) armazenada na sessão a nova lista
			session.setAttribute("keyLista", fun);
		}
		
		//Construindo uma página
		fun.forEach(func -> {
			writer.println(func.toString() + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncDeleteServlet?cpf=" 
		+ func.getCpf() + "';> Excluir Funcionário </button>" + "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/FuncAlterarServlet?cpf=" 
		+ func.getCpf() + "';> Alterar Funcionário </button>"
		+ "<button onclick=location.href='http://localhost:6919/CadastroFuncionario/index.html" + "';>Voltar para página inicial </button>");
		});

		writer.println("ID da sessão: " + session.getId() + "\n");
		writer.println("Hora de Criação: " + new Date(session.getCreationTime()));

	}

}
